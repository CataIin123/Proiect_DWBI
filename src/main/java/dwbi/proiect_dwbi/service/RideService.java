package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceAlreadyExistsException;
import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.*;
import dwbi.proiect_dwbi.repository.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final ClientService clientService;
    private final CarService carService;
    private final RouteService routeService;

    public RideService(RideRepository rideRepository, ClientService clientService, CarService carService, RouteService routeService) {
        this.rideRepository = rideRepository;
        this.clientService = clientService;
        this.carService = carService;
        this.routeService = routeService;
    }

    public Ride findById(int rideId) {
        return rideRepository.findByRideId(rideId);
    }

    public Page<Ride> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return rideRepository.findAll(pageable);
    }

    public Page<Ride> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return rideRepository.findAll(pageable);
    }

    public void save(Ride ride, int client, int car, int route) {
        Client storedClient = clientService.findByClientId(client);
        Car storedCar = carService.findByCarId(car);
        Route storedRoute = routeService.findByRouteId(route);
        if (storedClient != null && storedCar != null && storedRoute != null) {
            ride.setClient(storedClient);
            ride.setCar(storedCar);
            ride.setRoute(storedRoute);
            rideRepository.save(ride);
        } else {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

        @Transactional
        public Ride update (Ride ride,int rideId, int client, int car, int route){
            Ride storedRide = rideRepository.findByRideId(rideId);
            if (storedRide == null) {
                throw new ResourceAlreadyExistsException("Ride not found");
            }
            Client storedClient = clientService.findByClientId(client);
            Car storedCar = carService.findByCarId(car);
            Route storedRoute = routeService.findByRouteId(route);
            if (storedClient == null || storedCar == null || storedRoute == null) {
                throw new ResourceAlreadyExistsException("Resource not found");
            }
            if (storedRide != null && storedRide.getRideId() != rideId) {
                throw new ResourceAlreadyExistsException("Ride " + rideId + " already exists");
            }

            storedRide.setClient(storedClient);
            storedRide.setCar(storedCar);
            storedRide.setRoute(storedRoute);
            storedRide.setStatus(ride.getStatus());
            storedRide.setRideDate(ride.getRideDate());
            storedRide.setTarif(ride.getTarif());
            return rideRepository.save(storedRide);
        }

        @Transactional
        public void delete ( int id){
            rideRepository.deleteById(id);
        }
    }
