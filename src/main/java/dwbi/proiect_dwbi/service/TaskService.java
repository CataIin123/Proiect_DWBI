package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.*;
import dwbi.proiect_dwbi.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final DriverService driverService;
    private final CarService carService;

    public TaskService(TaskRepository taskRepository, DriverService driverService, CarService carService) {
        this.taskRepository = taskRepository;
        this.driverService = driverService;
        this.carService = carService;
    }

    public Page<Task> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return taskRepository.findAll(pageable);
    }

    public Page<Task> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
        return taskRepository.findAll(pageable);
    }

    public void save(Task task, String driverCnp, String carNumber) {
        Driver storedDriver = driverService.findByDriverCnp(driverCnp);
        Car storedCar = carService.findByCarNumber(carNumber);
        if(storedDriver != null || storedCar != null){
            task.setDriver(storedDriver);
            task.setCar(storedCar);
            taskRepository.save(task);
        }
        else{
            throw new ResourceNotFoundException("Resource not found");
        }
    }
    @Transactional
    public Task update(Task task, int taskId, String driverCnp, String carNumber) {
        Task storedTask = taskRepository.findByTaskId(taskId);
        if(storedTask == null){
            throw new ResourceNotFoundException("Task " + taskId + " not found");
        }
        Driver storedDriver = driverService.findByDriverCnp(driverCnp);
        Car storedCar = carService.findByCarNumber(carNumber);
        if(storedDriver != null || storedCar != null){
            storedTask.setDriver(storedDriver);
            storedTask.setCar(storedCar);
            storedTask.setStartDate(task.getStartDate());
            storedTask.setFinishDate(task.getFinishDate());
            return taskRepository.save(storedTask);
        }
        else{
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional
    public void delete(int id) {
        taskRepository.deleteById(id);
    }
}
