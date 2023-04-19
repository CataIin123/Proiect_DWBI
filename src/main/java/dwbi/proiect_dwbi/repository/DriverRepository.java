package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver findByCnp(String cnp);

    Driver findByDriverId(int driverId);
}
