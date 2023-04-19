package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Route;
import dwbi.proiect_dwbi.model.Town;
import dwbi.proiect_dwbi.repository.RouteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final TownService townService;


    public RouteService(RouteRepository routeRepository, TownService townService) {
        this.routeRepository = routeRepository;
        this.townService = townService;
    }

    public Route findByRouteId(int route) {
        return routeRepository.findByRouteId(route);
    }

    public Page<Route> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return routeRepository.findAll(pageable);
    }
    public Page<Route> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
        return routeRepository.findAll(pageable);
    }

    public void save(Route route, int departure, int arrival) {
        Town storedDeparture = townService.findByTownId(departure);
        Town storedArrival = townService.findByTownId(arrival);
        if(storedDeparture != null || storedArrival != null){
            route.setDeparture(storedDeparture);
            route.setArrival(storedArrival);
            routeRepository.save(route);
        }
        else{
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional
    public Route update(Route route, int routeId, int departure, int arrival) {
        Route storedRoute = routeRepository.findByRouteId(routeId);
        if(storedRoute == null){
            throw new ResourceAlreadyExistsException("Route not found");
        }
        Town storedDeparture = townService.findByTownId(departure);
        Town storedArrival = townService.findByTownId(arrival);
        if(storedDeparture == null || storedArrival == null){
            throw new ResourceAlreadyExistsException("Resource not found");
        }
        Route storedRouteForLocation = routeRepository.findByDepartureAndArrival(storedDeparture, storedArrival);
        if(storedRouteForLocation != null && storedRouteForLocation.getRouteId() != routeId){
            throw new ResourceAlreadyExistsException("Route " + routeId + " already exists");
        }

        storedRoute.setDeparture(storedDeparture);
        storedRoute.setArrival(storedArrival);
        storedRoute.setDistance(route.getDistance());
        return routeRepository.save(storedRoute);
    }

    @Transactional
    public void delete(int id) {
        routeRepository.deleteById(id);
    }
}
