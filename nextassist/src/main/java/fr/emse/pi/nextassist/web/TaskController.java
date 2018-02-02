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
        List<TaskDTO> taskDTOS;

        if(tasks!=null) {
            taskDTOS = tasks.stream()
                    .map(task -> new TaskDTO(task))
                    .sorted(Comparator.comparing(TaskDTO::getDeadline))
                    .collect(Collectors.toList());
        } else {
            taskDTOS = Collections.emptyList();
        }
        model.addAttribute("tasks", taskDTOS);

        LOG.info("Model for allTasks : {}", model);
        return "allTasks";
    }

    @PostMapping("/add")
    public String addTask (@ModelAttribute Task task) {
        LOG.info("task submitted : {}", task);
        LOG.info("date plus : {}", task.start_date.plus(task.duration));

        if(task.getDeadline() != null && task.getName() != "" && task.getStart_date() != null ){
            if(task.deadline.isAfter(task.start_date.plus(task.duration))) {
                taskRepository.save(task);
                return "correctTask";
            } else {
                return "wrongTask";
            }
        } else {
            return "wrongTask";
        }


    }

    @GetMapping("/add")
    public String taskForm (Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addTask";
    }

    @GetMapping("/{id}")
    public String taskDescrption (@PathVariable("id") Long id, Model model) {
        Task task = taskRepository.findById(id);
        TaskDTO taskDTO = new TaskDTO(task);
        model.addAttribute("task", taskDTO);
        return "taskDetails";
    }

}
