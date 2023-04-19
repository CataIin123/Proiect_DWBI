package dwbi.proiect_dwbi.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Driver;
import java.util.List;

@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAR")
    private int carId;

    @Column(name = "CAR_NUMBER")
    private String carNumber;

    @Column(name = "TRANSPORT_CAPACITY")
    private int transportCapacity;

    @Column(name = "CAR_TYPE")
    private String carType;

    public Car() {
    }

    public Car(int carId, String carNumber, int transportCapacity, String carType) {
        this.carId = carId;
        this.carNumber = carNumber;
        this.transportCapacity = transportCapacity;
        this.carType = carType;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getTransportCapacity() {
        return transportCapacity;
    }

    public void setTransportCapacity(int transportCapacity) {
        this.transportCapacity = transportCapacity;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carNumber='" + carNumber + '\'' +
                ", transportCapacity=" + transportCapacity +
                ", carType='" + carType + '\'' +
                '}';
    }
}
