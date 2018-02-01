package fr.emse.pi.nextassist;


import fr.emse.pi.nextassist.model.Task;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void dbShouldContainTask() {
        Assertions.assertThat(taskRepository.findAll()).isNotEmpty();
    }

    @Test
    public void shouldNotFoundMissingTask() throws Exception {
        mockMvc.perform(get("/task/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindTask() throws Exception {
        Task task = taskRepository.findAll().stream().findAny().orElseThrow(() -> new IllegalStateException());
        mockMvc.perform(get("/task/"+task.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.name",
                        Matchers.not(Matchers.isEmptyOrNullString())));
    }


}

