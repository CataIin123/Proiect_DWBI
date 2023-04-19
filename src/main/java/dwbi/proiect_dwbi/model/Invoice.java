package dwbi.proiect_dwbi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVOICE")
    private int invoiceId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_RIDE")
    private Ride ride;

    @Column(name = "DATE_INVOICE")
    private String invoiceDate;

    @Column(name = "TOTAL_AMOUNT")
    private float totalAmount;

    private String status;

    public Invoice() {
    }

    public Invoice(int invoiceId, Ride ride, String invoiceDate, float totalAmount, String status) {
        this.invoiceId = invoiceId;
        this.ride = ride;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getRideId() {
        return ride.getRideId();
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String date_invoice) {
        this.invoiceDate = date_invoice;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float total_amount) {
        this.totalAmount = total_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", ride=" + ride +
                ", invoiceDate=" + invoiceDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
