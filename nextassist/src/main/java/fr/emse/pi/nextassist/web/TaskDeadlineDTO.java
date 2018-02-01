package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TaskDeadlineDTO {
    private final String name;
    private final String deadline;

    public TaskDeadlineDTO(Task task) {
        name = task.getName();
        deadline = task.getDeadline().toString();
    }

}
