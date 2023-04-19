package dwbi.proiect_dwbi.controller;

import dwbi.proiect_dwbi.model.Task;
import dwbi.proiect_dwbi.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getAll(Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/tasks/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Task> page = taskService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Task> tasks = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("tasks", tasks);
        return "main_pages/task-page";
    }

    @GetMapping("/tasks/{currentPage}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable int currentPage,
                                  @PathVariable String field,
                                  @RequestParam String sortDir) {
        Page<Task> page = taskService.findAllWithSort(field, sortDir, currentPage);
        List<Task> tasks = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("tasks", tasks);
        return "main_pages/task-page";
    }

    @GetMapping("/tasks/create")
    public String create(Task task) {
        return "intermediary_pages/task-create";
    }

    @PostMapping("/tasks/save")
    public String save(Task task, String driverCnp, String carNumber) {
        taskService.save(task, driverCnp, carNumber);
        return "redirect:/tasks/1";
    }

    @RequestMapping("/tasks/update/{taskId}")
    public String updateTask(Task task, @PathVariable int taskId, Model model) {
        model.addAttribute(taskId);
        return "intermediary_pages/task-update";
    }

    @RequestMapping("/tasks/saveUpdated/{taskId}")
    public String saveUpdatedTask(Task task, @PathVariable int taskId, String driverCnp, String carNumber) {
        taskService.update(task, taskId, driverCnp, carNumber);
        return "redirect:/tasks";
    }


    @RequestMapping("/tasks/delete/{id}")
    public String delete(@PathVariable int id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}
