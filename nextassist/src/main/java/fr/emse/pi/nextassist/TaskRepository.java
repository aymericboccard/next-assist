package fr.emse.pi.nextassist;


import fr.emse.pi.nextassist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByName(@Param("name") String name);
    List<Task> findByDeadline(@Param("deadline") String deadline);
    Task findById(@Param("id") Long id);
}
