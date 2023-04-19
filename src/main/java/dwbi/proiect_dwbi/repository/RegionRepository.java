package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findByRegionId(int regionId);

    Region findByRegionName(String regionName);

}
