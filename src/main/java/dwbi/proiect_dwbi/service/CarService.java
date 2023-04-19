package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Car;
import dwbi.proiect_dwbi.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findByCarId(int car) {
        return carRepository.findByCarId(car);
    }

    public Car findByCarNumber(String carNumber) {
        return carRepository.findByCarNumber(carNumber);
    }

    public Page<Car> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return carRepository.findAll(pageable);
    }

    public Page<Car> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return carRepository.findAll(pageable);
    }

    public void save(Car car) {
        Car storedCar = carRepository.findByCarNumber(car.getCarNumber());
        if(storedCar != null){
            throw new ResourceAlreadyExistsException("Car " + car.getCarNumber() + " already exists");
        }
        carRepository.save(car);
    }

    @Transactional
    public Car update(Car car, int carId) {
        Car storedCar = carRepository.findByCarId(carId);
        if(storedCar == null){
            throw new ResourceNotFoundException("Car " + carId + " not found");
        }
        Car storedCarForNumber = carRepository.findByCarNumber(car.getCarNumber());
        if(storedCarForNumber != null && storedCarForNumber.getCarId() != carId){
            throw new ResourceAlreadyExistsException("Car " + car.getCarNumber() + " already exists");
        }
        return carRepository.save(car);
    }


    @Transactional
    public void delete(int id) {
        carRepository.deleteById(id);
    }
}
