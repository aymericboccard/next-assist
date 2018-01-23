package fr.emse.pi.nextassist;

import fr.emse.pi.nextassist.model.Event;
import fr.emse.pi.nextassist.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {
	public static final Timestamp DEBUT = new Timestamp(0000,01,01,01,01,01,0);
	public static final Timestamp FIN = new Timestamp(1000,12,31,23,59,59,59);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public CommandLineRunner initDataTest(EventRepository eventRepository, TaskRepository taskRepository) {
		return (String... args) -> {
			String[] names = {"Creation", "Test1", "Test2"};
			Arrays.stream(names)
					.map(name -> new Event(name, DEBUT))
					.forEach(eventRepository::save);
			Arrays.stream(names)
					.map(name -> new Task(name,FIN))
					.forEach(taskRepository::save);
		};
	}
}