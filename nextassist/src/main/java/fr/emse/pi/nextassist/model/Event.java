package fr.emse.pi.nextassist.model;

import fr.emse.pi.nextassist.EventRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id @GeneratedValue
    public Long id;

    public String name;
    public LocalDateTime start_date;
    public LocalDateTime end_date;
    public boolean movable;
    public String location;

    public Event(String name,LocalDateTime start_date){
        this.name = name;
        this.start_date= start_date;
        this.location = "Saint_Etienne";
    }
    public void addEvent (EventRepository eventRepository, String name, LocalDateTime start_date) {
        Event event = new Event(name, start_date);
        eventRepository.save(event);
    }
}


