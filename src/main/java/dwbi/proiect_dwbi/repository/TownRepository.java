package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findByTownName(String townName);

    Town findByTownId(int townId);
}
