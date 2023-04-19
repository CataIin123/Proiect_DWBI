package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Ride;
import dwbi.proiect_dwbi.model.Route;
import dwbi.proiect_dwbi.service.RideService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/rides")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/rides/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Ride> page = rideService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Ride> rides = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("rides", rides);
        return "main_pages/ride-page";
    }

    @GetMapping("/rides/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Ride> page = rideService.findAllWithSort(field, sortDir, currentPage);
        List<Ride> rides = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("rides", rides);
        return "main_pages/ride-page";
    }

    @GetMapping("/rides/create")
    public String create(Ride ride) {
        return "intermediary_pages/ride-create";
    }

    @PostMapping("/rides/save")
    public String save(Ride ride, int client, int car, int route) {
        rideService.save(ride, client, car, route);
        return "redirect:/rides/1";
    }

    @RequestMapping("/rides/update/{rideId}")
    public String updateRide(Ride ride, @PathVariable int rideId, Model model) {
        model.addAttribute(rideId);
        return "intermediary_pages/ride-update";
    }

    @RequestMapping("/rides/saveUpdated/{rideId}")
    public String saveUpdatedRide(Ride ride, @PathVariable int rideId, int client, int car, int route) {
        rideService.update(ride, rideId, client, car, route);
        return "redirect:/rides";
    }


    @RequestMapping("/rides/delete/{id}")
    public String delete(@PathVariable int id) {
        rideService.delete(id);
        return "redirect:/rides";
    }
}
