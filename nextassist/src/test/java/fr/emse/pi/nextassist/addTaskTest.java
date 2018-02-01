package fr.emse.pi.nextassist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class addTaskTest {

	@Autowired
	private EventRepository taskRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldNotBeValid() throws Exception {
		String name = "A Task";
		Duration duration = Duration.ofHours(2);
		LocalDateTime deadline = LocalDateTime.of(1980, 10, 21, 10, 0, 0);
		LocalDateTime start_date = LocalDateTime.of(1980, 11, 21, 10, 0, 0, 0);
		int priority = 4;

		//taskDAO.addTask(name, start_date, deadline, duration, priority);

		//assertThat(taskDAO.listTasks()).hasSize(0);

	}

}
