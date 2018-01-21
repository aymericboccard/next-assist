package fr.emse.pi.nextassist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Task {
    @Id@GeneratedValue
    public Long id;

    public String name;
    public Date start_date;
    public Time duration;
    public Date deadline;
    public Integer priority;

    public Task(String name,Date deadline){
        this.deadline= deadline;
        this.name= name;
    }
}
