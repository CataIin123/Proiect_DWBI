package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Invoice;
import dwbi.proiect_dwbi.service.InvoiceService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InvoiceController {

    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/invoices/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Invoice> page = invoiceService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Invoice> invoices = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("invoices", invoices);
        return "main_pages/invoice-page";
    }

    @GetMapping("/invoices/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Invoice> page = invoiceService.findAllWithSort(field, sortDir, currentPage);
        List<Invoice> invoices = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("invoices", invoices);
        return "main_pages/invoice-page";
    }

    @GetMapping("/invoices/create")
    public String create(Invoice invoice) {
        return "intermediary_pages/invoice-create";
    }

    @PostMapping("/invoices/save")
    public String save(Invoice invoice, int rideId){
        invoiceService.save(invoice, rideId);
        return "redirect:/invoices/1";
    }

    @RequestMapping("/invoices/update/{invoiceId}")
    public String updateInvoice(Invoice invoice, @PathVariable int invoiceId, Model model) {
        model.addAttribute(invoiceId);
        return "intermediary_pages/invoice-update";
    }
    @RequestMapping("/invoices/saveUpdated/{invoiceId}")
    public String saveUpdatedInvoice(Invoice invoice, @PathVariable int invoiceId, int rideId) {
        invoiceService.update(invoice, invoiceId, rideId);
        return "redirect:/invoices";
    }


    @RequestMapping("/invoices/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        try {
            invoiceService.delete(id);
            return "redirect:/invoices";
        } catch (DataAccessException e) {
            session.setAttribute("errorMessage", "The invoice cannot be deleted because it's linked to a ride.");
            return "redirect:/invoices";
        }
    }
}
