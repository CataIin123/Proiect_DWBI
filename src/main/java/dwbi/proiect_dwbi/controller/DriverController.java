package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Driver;
import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DriverController {

    private final DriverService driverService;


    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/drivers/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Driver> page = driverService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Driver> drivers = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("drivers", drivers);
        return "main_pages/driver-page";
    }

    @GetMapping("/drivers/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Driver> page = driverService.findAllWithSort(field, sortDir, currentPage);
        List<Driver> drivers = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("drivers", drivers);
        return "main_pages/driver-page";
    }

    @GetMapping("/drivers/create")
    public String create(Driver driver) {
        return "intermediary_pages/driver-create";
    }

    @PostMapping("/drivers/save")
    public String save(Driver driver){
        driverService.save(driver);
        return "redirect:/drivers/1";
    }

    @RequestMapping("/drivers/update/{driverId}")
    public String updateDriver(Driver driver, @PathVariable int driverId, Model model) {
        model.addAttribute(driverId);
        return "intermediary_pages/driver-update";
    }
    @RequestMapping("/drivers/saveUpdated/{driverId}")
    public String saveUpdatedDriver(Driver driver, @PathVariable int driverId) {
        driverService.update(driver, driverId);
        return "redirect:/drivers/1";
    }

    @RequestMapping("/drivers/delete/{id}")
    public String delete(@PathVariable int id) {
        driverService.delete(id);
        return "redirect:/drivers";
    }
}
