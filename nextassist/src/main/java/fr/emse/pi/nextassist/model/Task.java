package fr.emse.pi.nextassist.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Task {
    @Id @GeneratedValue
    public Long id;

    public String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime start_date;

    @DateTimeFormat(pattern = "HH:mm")
    public Duration duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime deadline;


    public int priority;


    public Task(String name, LocalDateTime deadline){
        this.deadline= deadline;
        this.name= name;
        this.start_date = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
        this.priority = 2;
    }

    public Task(String name,LocalDateTime start_date, LocalDateTime deadline, Duration duration, int priority){
        this.deadline= deadline;
        this.name= name;
        this.priority = priority;
        this.start_date = start_date;
        this.duration = duration;
    }

    public Task() {
        this.start_date = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
        this.priority = 2;
    }
}
