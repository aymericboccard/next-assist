package fr.emse.pi.nextassist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Task {
    @Id @GeneratedValue
    public Long id;

    public String name;
    public Timestamp start_date;
    public Time duration;
    public Timestamp deadline;
    public Integer priority;

    public Task(String name,Timestamp deadline){
        this.deadline= deadline;
        this.name= name;
    }
}
