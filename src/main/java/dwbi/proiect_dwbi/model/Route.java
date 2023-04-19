package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROUTE")
    private int routeId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departure", referencedColumnName = "ID_TOWN")
    private Town departure;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "arrival", referencedColumnName = "ID_TOWN")
    private Town arrival;

    @Column(name = "KM")
    private int distance;

    public Route() {
    }

    public Route(int routeId, Town departure, Town arrival, int distance) {
        this.routeId = routeId;
        this.departure = departure;
        this.arrival = arrival;
        this.distance = distance;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Town getDeparture() {
        return departure;
    }

    public void setDeparture(Town departure) {
        this.departure = departure;
    }

    public Town getArrival() {
        return arrival;
    }

    public void setArrival(Town arrival) {
        this.arrival = arrival;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id_route=" + routeId +
                ", distance=" + distance +
                '}';
    }
}
