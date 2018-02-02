package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.EventRepository;
import fr.emse.pi.nextassist.model.Event;
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
@RequestMapping("/web/event")
public class EventController {
    private static Logger LOG  = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/all")
    public String allEvents(Model model) {
        List<Event> events = eventRepository.findAll();
        List<EventDateDTO> eventDateDTOS;

        LOG.info("listofevets : {}", events);

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
        List<Event> events = eventRepository.findAll();

        if ((event.name=="")||(event.start_date==null)||(event.start_date.isAfter(event.end_date))||(event.start_date.isBefore(LocalDateTime.now()))){
            return"failEvent";
        }
        if(events!=null) {
            for (Event event1 : events){
                if (event1.end_date!=null){
                    if (((event.start_date.isAfter(event1.start_date)) &&(event.start_date.isBefore(event1.end_date)))||
                            ((event.end_date.isAfter(event1.start_date)) &&(event.end_date.isBefore(event1.end_date)))||
                            ((event.start_date.isBefore(event1.start_date))&&(event.end_date.isAfter(event1.end_date)))){
                        return"plannedEvent";
                    }
                }
                else {
                    if(event.start_date==event1.start_date){
                        return"plannedEvent";
                    }

                }
            }

        }
        eventRepository.save(event);
        return "newEvent";
    }

    @GetMapping("/add")
    public String eventForm(Model model){
        Event event= new Event();
        model.addAttribute("event",event);
        return"addEvent";
    }

    @GetMapping("/{id}")
    public String eventDescrption (@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id);
        EventDateDTO eventDateDTO = new EventDateDTO(event);
        model.addAttribute("event", eventDateDTO);
        return "eventDetails";
    }

}
