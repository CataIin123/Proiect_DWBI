package dwbi.proiect_dwbi.repository;


import dwbi.proiect_dwbi.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findByInvoiceId(int invoiceId);
}
