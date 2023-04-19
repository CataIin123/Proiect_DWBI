package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    Ride findByRideId(int rideId);
}
