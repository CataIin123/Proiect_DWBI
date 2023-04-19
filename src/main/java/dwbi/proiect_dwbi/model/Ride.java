package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "RIDES")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RIDE")
    private int rideId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CAR")
    private Car car;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ROUTE")
    private Route route;

    @Column(name = "DATE_RIDE")
    private String rideDate;

    private String status;
    private float tarif;


    public Ride() {
    }

    public Ride(int rideId, Client client, Car car, Route route, String rideDate, String status, float tarif) {
        this.rideId = rideId;
        this.client = client;
        this.car = car;
        this.route = route;
        this.rideDate = rideDate;
        this.status = status;
        this.tarif = tarif;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", client=" + client +
                ", car=" + car +
                ", route=" + route +
                ", rideDate=" + rideDate +
                ", status='" + status + '\'' +
                ", tarif=" + tarif +
                '}';
    }
}
