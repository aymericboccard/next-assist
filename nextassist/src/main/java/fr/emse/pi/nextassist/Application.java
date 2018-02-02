package fr.emse.pi.nextassist;

import fr.emse.pi.nextassist.model.Event;
import fr.emse.pi.nextassist.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

	public static final LocalDateTime DEBUT = LocalDateTime.of(2018,02,02,13,30);
	public static final LocalDateTime FIN = LocalDateTime.of(2018,02,02,16,30);
	public static final LocalDateTime DEBUT1 = LocalDateTime.of(2018,02,02,17,00);
	public static final LocalDateTime FIN1 = LocalDateTime.of(2018,02,05,8,00);
	public static final LocalDateTime DEBUT2 = LocalDateTime.of(2018,02,10,20,00);
	public static final LocalDateTime FIN2 = LocalDateTime.of(2018,02,11,4,00);



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	//@Profile("test")
	public CommandLineRunner initDataTest(EventRepository eventRepository, TaskRepository taskRepository) {
		return (String... args) -> {
			Event event1 = new Event("Jacademie", DEBUT,FIN,"Saint-Etienne");
			Event event2 = new Event("Week-end", DEBUT1,FIN1,"Saint-Etienne");
			Event event3 = new Event("Gala des Mines", DEBUT2,FIN2,"Saint-Etienne");

			eventRepository.save(event1);
			eventRepository.save(event2);
			eventRepository.save(event3);


			Task task1 = new Task("QCM",FIN,1);
			Task task2 = new Task( "Devoir",DEBUT,2);
			Task task3 = new Task("Acheter une tenue pour le Gala",DEBUT2,3);

			taskRepository.save(task1);
			taskRepository.save(task2);
			taskRepository.save(task3);
		};
	}
}