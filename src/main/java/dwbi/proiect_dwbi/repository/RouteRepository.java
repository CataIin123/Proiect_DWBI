package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Route;
import dwbi.proiect_dwbi.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    Route findByDepartureAndArrival(Town departure, Town arrival);

    Route findByRouteId(int routeId);
}
