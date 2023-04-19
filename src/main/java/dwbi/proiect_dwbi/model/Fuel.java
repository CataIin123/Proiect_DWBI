package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "FUEL")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUELING")
    private int fuelId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CAR")
    private Car car;

    @Column(name = "DATE_FUELING")
    private String resupplyDate;

    private int quantity;

    @Column(name = "UNIT_PRICE")
    private float unitPrice;

    public Fuel() {
    }

    public Fuel(int fuelId, Car car, String resupplyDate, int quantity, float unitPrice) {
        this.fuelId = fuelId;
        this.car = car;
        this.resupplyDate = resupplyDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int rideId) {
        this.fuelId = rideId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getResupplyDate() {
        return resupplyDate;
    }

    public void setResupplyDate(String date_fueling) {
        this.resupplyDate = date_fueling;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "rideId=" + fuelId +
                ", car=" + car +
                ", date_fueling=" + resupplyDate +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
