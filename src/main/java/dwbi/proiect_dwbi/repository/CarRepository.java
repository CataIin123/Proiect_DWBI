package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByCarNumber(String carNumber);

    Car findByCarId(int carId);
}
