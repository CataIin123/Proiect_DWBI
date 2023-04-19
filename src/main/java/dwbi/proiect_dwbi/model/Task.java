package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "DRIVER_CAR")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DRIVER_CAR")
    private int taskId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_DRIVER")
    private Driver driver;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CAR")
    private Car car;

    @Column(name = "DATE_START")
    private String startDate;

    @Column(name = "DATE_FINISH")
    private String finishDate;

    public Task() {
    }

    public Task(int taskId, Driver driver, Car car, String startDate, String finishDate) {
        this.taskId = taskId;
        this.driver = driver;
        this.car = car;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "DriverCar{" +
                "taskId=" + taskId +
                ", driver=" + driver +
                ", car=" + car +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
