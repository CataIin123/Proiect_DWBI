package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByDistrictName(String districtName);

    District findByDistrictId(int districtId);
}
