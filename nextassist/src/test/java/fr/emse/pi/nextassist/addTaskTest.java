package fr.emse.pi.nextassist;


import fr.emse.pi.nextassist.model.Event;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class addTaskTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventRepository taskRepository;

	@Autowired
	private TaskDAO taskDAO;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldNotBeValid() throws Exception {
		String name = "A Task";
		Time duration = new Time(1,0,0);
		Timestamp deadline = new Timestamp(1,0,0,0,1,0,0);
		Timestamp start_date = new Timestamp(0,0,0,0,1,0,0);
		int priority = 4;

		taskDAO.addTask(name, start_date, deadline, duration, priority);

		assertThat(taskDAO.listEvents().hasSize(0));

	}

	@Test
	public void shouldNotBeValid() throws Exception {
		String name = "A Task";
		Time duration = new Time(1,0,0);
		Timestamp deadline = new Timestamp(1,0,0,0,1,0,0);
		Timestamp start_date = new Timestamp(0,0,0,0,1,0,0);
		int priority = 4;

		taskDAO.addTask(name, start_date, deadline, duration, priority);

		assertThat(taskDAO.listEvents().hasSize(0));

	}






}
