package fr.emse.pi.nextassist.model;

import fr.emse.pi.nextassist.EventRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Event {
    @Id@GeneratedValue
    public Long id;

    public String name;
    public Timestamp start_date;
    public Timestamp end_date;
    public boolean movable;
    public String location;

    public Event(String name,Timestamp start_date, boolean movable){
        this.name = name;
        this.start_date= start_date;
        this.movable = movable;
    }




}


