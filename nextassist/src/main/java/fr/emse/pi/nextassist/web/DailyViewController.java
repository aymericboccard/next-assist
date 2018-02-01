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
        LocalDate Today = LocalDate.now();

        List<Event> events = eventRepository.findAll();
        List<EventDateDTO> eventDateDTOS;

        List<Task> tasks = taskRepository.findAll();
        List<TaskDeadlineDTO>  taskDeadlineDTOS;
        if(events!=null) {
            eventDateDTOS = events.stream()
                    .map(event -> new EventDateDTO(event))
                    .sorted(Comparator.comparing(EventDateDTO::getStart_date))
                    .collect(Collectors.toList());
        } else {
            eventDateDTOS = Collections.emptyList();
        }
        model.addAttribute("events", eventDateDTOS);

        if(tasks!=null) {
            taskDeadlineDTOS = tasks.stream()
                    .map(task -> new TaskDeadlineDTO(task))
                    .sorted(Comparator.comparing(TaskDeadlineDTO::getDeadline))
                    .collect(Collectors.toList());
        } else {
            taskDeadlineDTOS = Collections.emptyList();
        }
        model.addAttribute("tasks", taskDeadlineDTOS);

        return"todayEventsTasks";
    }
    @PostMapping("/daily")
    public String dayEventsTasks(@ModelAttribute LocalDate date,Model model){
        List<Event> events = eventRepository.findAll();
        List<EventDateDTO> eventDateDTOS;

        List<Task> tasks = taskRepository.findAll();
        List<TaskDeadlineDTO>  taskDeadlineDTOS;
        if(events!=null) {
            eventDateDTOS = events.stream()
                    .map(event -> new EventDateDTO(event))
                    .sorted(Comparator.comparing(EventDateDTO::getStart_date))
                    .collect(Collectors.toList());
        } else {
            eventDateDTOS = Collections.emptyList();
        }
        model.addAttribute("events", eventDateDTOS);

        if(tasks!=null) {
            taskDeadlineDTOS = tasks.stream()
                    .map(task -> new TaskDeadlineDTO(task))
                    .sorted(Comparator.comparing(TaskDeadlineDTO::getDeadline))
                    .collect(Collectors.toList());
        } else {
            taskDeadlineDTOS = Collections.emptyList();
        }
        model.addAttribute("tasks", taskDeadlineDTOS);

        return"dayEventsTasks";
    }

    @GetMapping("/daily")
    public String dayForm(Model model){
        LocalDate date= LocalDate.now();
        model.addAttribute("date",date);
        return"dayForm";
    }
}
