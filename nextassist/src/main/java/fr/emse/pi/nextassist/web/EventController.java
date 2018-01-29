package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.EventRepository;
import fr.emse.pi.nextassist.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/event")
public class EventController {
    private static Logger LOG  = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/all")
    public String allEvents(Model model) {
        List<Event> events = eventRepository.findAll();
        List<EventDateDTO> eventDateDTOS;

        if(events!=null) {
            eventDateDTOS = events.stream()
                    .map(event -> new EventDateDTO(event))
                    .sorted(Comparator.comparing(EventDateDTO::getStart_date))
                    .collect(Collectors.toList());
        } else {
            eventDateDTOS = Collections.emptyList();
        }
        model.addAttribute("events", eventDateDTOS);

        LOG.info("Model for allEvents : {}", model);
        return "allEvents";
    }

    @PostMapping("/add")
    public String addEvents(@ModelAttribute Event event){

        return"newTask";
    }

    @GetMapping("/add")
    public String eventForm(Model model){

        return"addEvent";
    }


}
