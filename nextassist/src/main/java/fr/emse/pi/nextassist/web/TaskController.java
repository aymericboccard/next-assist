package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.TaskRepository;
import fr.emse.pi.nextassist.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/task")
public class TaskController {
    private static Logger LOG  = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all")
    public String allTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDeadlineDTO> taskDeadlineDTOS;

        if(tasks!=null) {
            taskDeadlineDTOS = tasks.stream()
                    .map(task -> new TaskDeadlineDTO(task))
                    .sorted(Comparator.comparing(TaskDeadlineDTO::getDeadline))
                    .collect(Collectors.toList());
        } else {
            taskDeadlineDTOS = Collections.emptyList();
        }
        model.addAttribute("tasks", taskDeadlineDTOS);

        LOG.info("Model for allTasks : {}", model);
        return "allTasks";
    }

    @PostMapping("/add")
    public String addTask (@ModelAttribute Task task) {

        LOG.info("Task submitted: {}", task);
        taskRepository.save(task);

        return "newTask";
    }

    @GetMapping("/add")
    public String taskForm (Model model) {
        Task task = new Task();
        LOG.info("New task created: {}", task);
        model.addAttribute("task", task);
        LOG.info("New task submitted: {}", task);
        return "addTask";
    }

}
