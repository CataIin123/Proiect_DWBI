package dwbi.proiect_dwbi.controller;


import dwbi.proiect_dwbi.model.District;
import dwbi.proiect_dwbi.model.Invoice;
import dwbi.proiect_dwbi.service.DistrictService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/districts/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<District> page = districtService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<District> districts = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("districts", districts);
        return "main_pages/district-page";
    }

    @GetMapping("/districts/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<District> page = districtService.findAllWithSort(field, sortDir, currentPage);
        List<District> districts = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("districts", districts);
        return "main_pages/district-page";
    }

    @GetMapping("/districts/create")
    public String create(District district) {
        return "intermediary_pages/district-create";
    }

    @PostMapping("/districts/save")
    public String save(District district, int regionId ){
        districtService.save(district, regionId);
        return "redirect:/districts/1";
    }

    @RequestMapping("/districts/update/{districtId}")
    public String updateDistrict(District district, @PathVariable int districtId, Model model) {
        model.addAttribute(districtId);
        return "intermediary_pages/district-update";
    }
    @RequestMapping("/districts/saveUpdated/{districtId}")
    public String saveUpdatedDistrict(District district, @PathVariable int districtId, int regionId) {
        districtService.update(district, districtId, regionId);
        return "redirect:/districts";
    }


    @RequestMapping("/districts/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        try {
            districtService.delete(id);
            return "redirect:/districts";
        } catch (DataAccessException e) {
            session.setAttribute("errorMessage", "The district cannot be deleted because it's linked to a town.");
            return "redirect:/districts";
        }
    }
}
