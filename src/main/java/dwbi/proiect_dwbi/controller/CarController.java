package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Car;
import dwbi.proiect_dwbi.service.CarService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/cars/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Car> page = carService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Car> cars = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("cars", cars);
        return "main_pages/car-page";
    }

    @GetMapping("/cars/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Car> page = carService.findAllWithSort(field, sortDir, currentPage);
        List<Car> cars = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("cars", cars);
        return "main_pages/car-page";
    }

    @GetMapping("/cars/create")
    public String create(Car car) {
        return "intermediary_pages/car-create";
    }

    @PostMapping("/cars/save")
    public String save(Car car){
        carService.save(car);
        return "redirect:/cars/1";
    }

    @RequestMapping("/cars/update/{carId}")
    public String updateCar(Car car, @PathVariable int carId, Model model) {
        model.addAttribute(carId);
        return "intermediary_pages/car-update";
    }
    @RequestMapping("/cars/saveUpdated/{carId}")
    public String saveUpdatedCar(Car car, @PathVariable int carId) {
        carService.update(car, carId);
        return "redirect:/cars";
    }

    @RequestMapping("/cars/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        try {
            carService.delete(id);
            return "redirect:/cars";
        } catch (DataAccessException e) {
            session.setAttribute("errorMessage", "The car cannot be deleted because it's linked to a driver.");
            return "redirect:/cars";
        }
    }
}
