package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Driver;
import dwbi.proiect_dwbi.repository.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver findByDriverCnp(String driverCnp) {
        return driverRepository.findByCnp(driverCnp);
    }

    public Page<Driver> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return driverRepository.findAll(pageable);
    }

    public Page<Driver> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return driverRepository.findAll(pageable);
    }

    public void save(Driver driver) {
        Driver storedDriver = driverRepository.findByCnp(driver.getCnp());
        if (storedDriver != null) {
            throw new ResourceAlreadyExistsException("Driver " + driver.getFirstName() + " " + driver.getLastName() + " already exists");
        }
        driverRepository.save(driver);
    }

    @Transactional
    public Driver update(Driver driver, int driverId) {
        Driver storedDriver = driverRepository.findByDriverId(driverId);
        if (storedDriver == null) {
            throw new ResourceNotFoundException("Driver " + driverId + " not found");
        }
        Driver storedDriverForCnp = driverRepository.findByCnp(driver.getCnp());
        if (storedDriverForCnp != null && storedDriverForCnp.getDriverId() != driverId) {
            throw new ResourceAlreadyExistsException("Driver " + driver.getFirstName() + " " + driver.getLastName() + " already exists");
        }
        return driverRepository.save(driver);
    }


    @Transactional
    public void delete(int id) {
        driverRepository.deleteById(id);
    }
}
