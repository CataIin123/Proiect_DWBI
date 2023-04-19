package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Car;
import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Fuel;
import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.repository.FuelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FuelService {

    private final FuelRepository fuelRepository;
    private final CarService carService;

    public FuelService(FuelRepository fuelRepository, CarService carService) {
        this.fuelRepository = fuelRepository;
        this.carService = carService;
    }

    public Page<Fuel> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return fuelRepository.findAll(pageable);
    }

    public Page<Fuel> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
        return fuelRepository.findAll(pageable);
    }
    public void save(Fuel fuel, String carNumber) {
        Car storedCar = carService.findByCarNumber(carNumber);
        if(storedCar != null){
            fuel.setCar(storedCar);
            fuel.setResupplyDate(fuel.getResupplyDate());
            fuel.setQuantity(fuel.getQuantity());
            fuel.setUnitPrice(fuel.getUnitPrice());
            fuelRepository.save(fuel);
        }
        else{
            throw new ResourceNotFoundException("Car " + carNumber + " not found");
        }
    }

    @Transactional
    public Fuel update(Fuel fuel, int fuelId, String carNumber) {
        Car storedCar = carService.findByCarNumber(carNumber);
        if(storedCar != null){
            fuel.setCar(storedCar);
            fuel.setResupplyDate(fuel.getResupplyDate());
            fuel.setQuantity(fuel.getQuantity());
            fuel.setUnitPrice(fuel.getUnitPrice());
            return fuelRepository.save(fuel);
        }
        else{
            throw new ResourceNotFoundException("Car " + carNumber + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        fuelRepository.deleteById(id);
    }
}
