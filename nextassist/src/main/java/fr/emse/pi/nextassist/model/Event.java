package fr.emse.pi.nextassist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Event {
    @Id@GeneratedValue
    public Long id;

    public String name;
    public Date start_date;
    public Date end_date;
    public boolean movable;
    public String location;

    public Event(String name,Date start_date){
        this.name = name;
        this.start_date= start_date;
    }
}