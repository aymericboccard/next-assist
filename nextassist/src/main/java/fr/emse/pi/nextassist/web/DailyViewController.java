package fr.emse.pi.nextassist.web;


import fr.emse.pi.nextassist.EventRepository;
import fr.emse.pi.nextassist.TaskRepository;
import fr.emse.pi.nextassist.model.Event;
import fr.emse.pi.nextassist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web")
public class DailyViewController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/today")
    public String todayEventsTasks(Model model){
        LocalDate today = LocalDate.now();

        List<Event> events = eventRepository.findAll();
        List<EventDateDTO> eventDateDTOS;

        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO>  taskDTOS;

        if(events!=null) {
            eventDateDTOS = events.stream()
                    .filter(event ->today.equals(event.start_date.toLocalDate()))
                    .map(event -> new EventDateDTO(event))
                    .sorted(Comparator.comparing(EventDateDTO::getStart_date))
                    .collect(Collectors.toList());
        } else {
            eventDateDTOS = Collections.emptyList();
        }
        model.addAttribute("events", eventDateDTOS);

        if(tasks!=null) {
            taskDTOS = tasks.stream()
                    .filter(task ->today.equals(task.deadline.toLocalDate()))
                    .map(task -> new TaskDTO(task))
                    .sorted(Comparator.comparing(TaskDTO::getDeadline))
                    .collect(Collectors.toList());
        } else {
            taskDTOS = Collections.emptyList();
        }
        model.addAttribute("tasks", taskDTOS);

        return"todayEventsTasks";
    }



}
