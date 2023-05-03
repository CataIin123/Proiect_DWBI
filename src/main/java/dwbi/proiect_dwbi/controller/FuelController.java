package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Fuel;
import dwbi.proiect_dwbi.service.FuelService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @GetMapping("/fuels")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/fuels/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Fuel> page = fuelService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Fuel> fuels = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("fuels", fuels);
        return "main_pages/fuel-page";
    }

    @GetMapping("/fuels/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Fuel> page = fuelService.findAllWithSort(field, sortDir, currentPage);
        List<Fuel> fuels = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("fuels", fuels);
        return "main_pages/fuel-page";
    }

    @GetMapping("/fuels/create")
    public String create(Fuel fuel) {
        return "intermediary_pages/fuel-create";
    }

    @PostMapping("/fuels/save")
    public String save(Fuel fuel, String carNumber) {
        fuelService.save(fuel, carNumber);
        return "redirect:/fuels/1";
    }

    @RequestMapping("/fuels/update/{fuelId}")
    public String updateFuel(Fuel fuel, @PathVariable int fuelId, Model model) {
        model.addAttribute(fuelId);
        return "intermediary_pages/fuel-update";
    }

    @RequestMapping("/fuels/saveUpdated/{fuelId}")
    public String saveUpdatedFuel(Fuel fuel, @PathVariable int fuelId, String carNumber) {
        fuelService.update(fuel, fuelId, carNumber);
        return "redirect:/fuels";
    }

    @RequestMapping("/fuels/delete/{id}")
    public String delete(@PathVariable int id) {
        fuelService.delete(id);
        return "redirect:/fuels";
    }
}
