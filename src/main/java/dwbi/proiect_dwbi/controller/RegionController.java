package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Region;
import dwbi.proiect_dwbi.service.RegionService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RegionController {

    private final RegionService regionService;


    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/regions")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/regions/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Region> page = regionService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Region> regions = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("regions", regions);
        return "main_pages/region-page";
    }

    @GetMapping("/regions/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Region> page = regionService.findAllWithSort(field, sortDir, currentPage);
        List<Region> regions = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("regions", regions);
        return "main_pages/region-page";
    }

    @GetMapping("/regions/create")
    public String create(Region region) {
        return "intermediary_pages/region-create";
    }

    @PostMapping("/regions/save")
    public String save(Region region){
        regionService.save(region);
        return "redirect:/regions/1";
    }

    @RequestMapping("/regions/update/{regionId}")
    public String updateRegion(Region region, @PathVariable int regionId, Model model) {
        model.addAttribute(regionId);
        return "intermediary_pages/region-update";
    }
    @RequestMapping("/regions/saveUpdated/{regionId}")
    public String saveUpdatedRegion(Region region, @PathVariable int regionId) {
        regionService.update(region, regionId);
        return "redirect:/regions";
    }

    @RequestMapping("/regions/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        try {
            regionService.delete(id);
            return "redirect:/regions";
        } catch (DataAccessException e) {
            session.setAttribute("errorMessage", "The region cannot be deleted because it's linked to a district.");
            return "redirect:/regions";
        }
    }
}
