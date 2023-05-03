package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Fuel;
import dwbi.proiect_dwbi.model.Route;
import dwbi.proiect_dwbi.service.RouteService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RouteController {

    private final RouteService routeService;


    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/routes/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Route> page = routeService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Route> routes = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("routes", routes);
        return "main_pages/route-page";
    }

    @GetMapping("/routes/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Route> page = routeService.findAllWithSort(field, sortDir, currentPage);
        List<Route> routes = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("routes", routes);
        return "main_pages/route-page";
    }

    @GetMapping("/routes/create")
    public String create(Route route) {
        return "intermediary_pages/route-create";
    }

    @PostMapping("/routes/save")
    public String save(Route route, int departure, int arrival) {
        routeService.save(route, departure, arrival);
        return "redirect:/routes/1";
    }

    @RequestMapping("/routes/update/{routeId}")
    public String updateRoute(Route route, @PathVariable int routeId, Model model) {
        model.addAttribute(routeId);
        return "intermediary_pages/route-update";
    }

    @RequestMapping("/routes/saveUpdated/{routeId}")
    public String saveUpdatedRoute(Route route, @PathVariable int routeId, int departure, int arrival) {
        routeService.update(route, routeId, departure, arrival);
        return "redirect:/routes";
    }


    @RequestMapping("/routes/delete/{routeId}")
    public String delete(@PathVariable int routeId, HttpSession session) {
        try {
            routeService.delete(routeId);
            return "redirect:/routes";
        } catch (DataAccessException e) {
            session.setAttribute("errorMessage", "The route cannot be deleted because it's linked to a ride.");
            return "redirect:/routes";
        }
    }
}
