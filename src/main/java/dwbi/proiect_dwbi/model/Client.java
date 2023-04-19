package dwbi.proiect_dwbi.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENT")
    private int clientId;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    private String cui;

    @Column(name = "INVOICE_ADDRESS")
    private String invoiceAddress;

    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    private String phone;

    public Client() {
    }

    public Client(int clientId, String clientName, String cui, String invoiceAddress, String deliveryAddress, String contactPerson, String phone) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.cui = cui;
        this.invoiceAddress = invoiceAddress;
        this.deliveryAddress = deliveryAddress;
        this.contactPerson = contactPerson;
        this.phone = phone;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", cui='" + cui + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
