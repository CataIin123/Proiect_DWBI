package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Client;
import dwbi.proiect_dwbi.service.ClientService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getAll(Model model, HttpSession session) {
        return getOnePage(model, 1);
    }

    @GetMapping("/clients/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Client> page = clientService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Client> clients = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("clients", clients);
        return "main_pages/client-page";
    }

    @GetMapping("/clients/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Client> page = clientService.findAllWithSort(field, sortDir, currentPage);
        List<Client> clients = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("clients", clients);
        return "main_pages/client-page";
    }

    @GetMapping("/clients/create")
    public String create(Client client) {
        return "intermediary_pages/client-create";
    }

    @PostMapping("/clients/save")
    public String save(Client client) {
        clientService.save(client);
        return "redirect:/clients/1";
    }

    @RequestMapping("/clients/update/{clientId}")
    public String updateClient(Client clients, @PathVariable int clientId, Model model) {
        model.addAttribute(clientId);
        return "intermediary_pages/client-update";
    }

    @RequestMapping("/clients/saveUpdated/{clientId}")
    public String saveUpdatedClient(Client client, @PathVariable int clientId) {
        clientService.update(client, clientId);
        return "redirect:/clients";
    }

    @RequestMapping("/clients/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        String pattern = "(.*)(SYSTEM.FK_)(\\w+)(_).+";
        Pattern compiler = Pattern.compile(pattern);
        try {
            clientService.delete(id);
            return "redirect:/clients";
        } catch (DataAccessException e) {
            Matcher matcher = compiler.matcher(Objects.requireNonNull(e.getMessage()));
            if (matcher.find()) {
                session.setAttribute("errorMessage", "The client cannot be deleted because he booked a "
                        + matcher.group(3).toLowerCase());
                session.setAttribute("errorMessageDisplay", true);
            }
            return "redirect:/clients";
        }
    }
}
