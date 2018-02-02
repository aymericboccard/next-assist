package fr.emse.pi.nextassist.model;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Task {
    @Id @GeneratedValue
    public Long id;

    public String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime start_date;

    @DateTimeFormat(pattern = "'P'DD'D''T'HH'H'mm'M")
    public Duration duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime deadline;


    public int priority;

    public int complete;


    public Task(String name, LocalDateTime deadline){
        this.deadline= deadline;
        this.name= name;
        this.start_date = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
        this.priority = 2;
        this.complete = 0;
    }


    public Task() {
        this.start_date = LocalDateTime.now();
        this.duration = Duration.ofHours(1);
        this.priority = 2;
        this.complete = 0;
    }
}
