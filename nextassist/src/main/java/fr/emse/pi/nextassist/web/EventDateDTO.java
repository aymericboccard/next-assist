package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventDateDTO {

    public String name;
    public String start_date;
    public String location;

    public String id;
    public String end_date;


    public EventDateDTO(Event event) {
        name = event.getName();
        start_date = event.getStart_date().toString().replace('T', ' ');
        end_date = event.getEnd_date().toString().replace('T', ' ');
        id = event.getId().toString();
        location = event.getLocation();
    }
}
