package dwbi.proiect_dwbi.model;

import javax.persistence.*;

@Entity
@Table(name = "DRIVERS")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DRIVER")
    private int driverId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DAY")
    private String birthDay;

    private String cnp;
    private String phone;
    private String address;

    @Column(name = "DRIVER_LICENCE_ISSUE_DATE")
    private String licenceIssueDate;

    @Column(name = "DRIVER_LICENCE_TIMEOUT_DATE")
    private String licenceTimeoutDate;

    public Driver() {
    }

    public Driver(int driverId, String firstName, String lastName, String birthDay, String cnp, String phone,
                  String address, String licenceIssueDate, String licenceTimeoutDate) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.cnp = cnp;
        this.phone = phone;
        this.address = address;
        this.licenceIssueDate = licenceIssueDate;
        this.licenceTimeoutDate = licenceTimeoutDate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenceIssueDate() {
        return licenceIssueDate;
    }

    public void setLicenceIssueDate(String licenceIssueDate) {
        this.licenceIssueDate = licenceIssueDate;
    }

    public String getLicenceTimeoutDate() {
        return licenceTimeoutDate;
    }

    public void setLicenceTimeoutDate(String licenceTimeoutDate) {
        this.licenceTimeoutDate = licenceTimeoutDate;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", cnp=" + cnp +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", licenceIssueDate=" + licenceIssueDate +
                ", licenceTimeoutDate=" + licenceTimeoutDate +
                '}';
    }
}
