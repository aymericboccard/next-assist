package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventDateDTO {

    public String name;
    public String start_date;
    public String location;

    public EventDateDTO(Event event) {
        name = event.getName();
        start_date = event.getStart_date().toString();
        location = event.getLocation();

    }
}
