package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Town;
import dwbi.proiect_dwbi.service.TownService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TownController {

    private final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping("/towns")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/towns/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Town> page = townService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Town> towns = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("towns", towns);
        return "main_pages/town-page";
    }

    @GetMapping("/towns/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Town> page = townService.findAllWithSort(field, sortDir, currentPage);
        List<Town> towns = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("towns", towns);
        return "main_pages/town-page";
    }

    @GetMapping("/towns/create")
    public String create(Town town) {
        return "intermediary_pages/town-create";
    }

    @PostMapping("/towns/save")
    public String save(Town town, int districtId ){
        townService.save(town, districtId);
        return "redirect:/towns/1";
    }

    @RequestMapping("/towns/update/{townId}")
    public String updateTown(Town town, @PathVariable int townId, Model model) {
        model.addAttribute(townId);
        return "intermediary_pages/town-update";
    }
    @RequestMapping("/towns/saveUpdated/{townId}")
    public String saveUpdatedTownt(Town town, @PathVariable int townId, int districtId) {
        townService.update(town, townId, districtId);
        return "redirect:/towns";
    }


    @RequestMapping("/towns/delete/{id}")
    public String delete(@PathVariable int id) {
        townService.delete(id);
        return "redirect:/towns";
    }
}
