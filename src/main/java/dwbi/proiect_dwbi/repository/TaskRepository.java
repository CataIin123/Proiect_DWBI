package dwbi.proiect_dwbi.repository;

import dwbi.proiect_dwbi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByTaskId(int taskId);
}
