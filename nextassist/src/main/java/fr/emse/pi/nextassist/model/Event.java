package fr.emse.pi.nextassist.model;

import fr.emse.pi.nextassist.EventRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime end_date;
    public boolean movable;
    public String location;

    public Event(String name,LocalDateTime start_date,LocalDateTime end_date,String location){
        this.name = name;
        this.start_date= start_date;
        this.location = location;
        this.end_date = end_date;
    }

}


