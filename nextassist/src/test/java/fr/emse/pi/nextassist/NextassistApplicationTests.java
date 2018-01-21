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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventRepository eventRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void dbShouldContainEvent() {
		Assertions.assertThat(eventRepository.findAll()).isNotEmpty();
	}

	@Test
	public void shouldNotFoundMissingEvent() throws Exception {
		mockMvc.perform(get("/event/1000"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldFindStudent() throws Exception {
		Event event = eventRepository.findAll().stream().findAny().orElseThrow(() -> new IllegalStateException());
		mockMvc.perform(get("/event/"+event.getId()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath(
						"$.name",
						Matchers.not(Matchers.isEmptyOrNullString())));
	}


}
