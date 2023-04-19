package dwbi.proiect_dwbi.service;

import dwbi.proiect_dwbi.exception.ResourceNotFoundException;
import dwbi.proiect_dwbi.model.Invoice;
import dwbi.proiect_dwbi.model.Ride;
import dwbi.proiect_dwbi.repository.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final RideService rideService;

    public InvoiceService(InvoiceRepository invoiceRepository, RideService rideService) {
        this.invoiceRepository = invoiceRepository;
        this.rideService = rideService;
    }

    public Page<Invoice> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return invoiceRepository.findAll(pageable);
    }

    public Page<Invoice> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(field).ascending(): Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 5, sort);
        return invoiceRepository.findAll(pageable);
    }

    public void save(Invoice invoice, int rideId) {
        Ride storedRide = rideService.findById(rideId);
         if(storedRide != null){
             invoice.setRide(storedRide);
             invoiceRepository.save(invoice);
         }
         else{
            throw new ResourceNotFoundException("Ride " + rideId + " not found");
        }
    }
    @Transactional
    public Invoice update(Invoice invoice, int invoiceId, int rideId) {
        Invoice storedInvoice = invoiceRepository.findByInvoiceId(invoiceId);
        if(storedInvoice == null){
            throw new ResourceNotFoundException("Invoice " + invoiceId + " not found");
        }
        Ride storedRide = rideService.findById(rideId);
        if(storedRide != null){
            storedInvoice.setRide(storedRide);
            storedInvoice.setInvoiceDate(invoice.getInvoiceDate());
            storedInvoice.setTotalAmount(invoice.getTotalAmount());
            storedInvoice.setStatus(invoice.getStatus());
            return invoiceRepository.save(storedInvoice);
        }
        else{
            throw new ResourceNotFoundException("Ride " + rideId + " not found");
        }
    }

    @Transactional
    public void delete(int id) {
        invoiceRepository.deleteById(id);
    }
}
