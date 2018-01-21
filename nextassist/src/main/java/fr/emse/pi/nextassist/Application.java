package fr.emse.pi.nextassist;

import fr.emse.pi.nextassist.model.Event;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Date;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {
	public static final Date DEBUT = new Date(2018, 1, 1);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public CommandLineRunner initDataTest(EventRepository eventRepository) {
		return (String... args) -> {
			String[] names = {"Creation", "Test1", "Test2"};
			Arrays.stream(names)
					.map(name -> new Event(name, DEBUT))
					.forEach(eventRepository::save);
		};
	}
}