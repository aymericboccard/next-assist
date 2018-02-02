package fr.emse.pi.nextassist.web;

import fr.emse.pi.nextassist.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TaskDTO {
    private final String name;
    private final String deadline;
    private final int complete;
    private final int priority;
    private final String id;
    private final String start_date;
    private final String duration;

    public TaskDTO(Task task) {
        name = task.getName();
        deadline = task.getDeadline().toString().replace('T', ' ');
        complete = task.getComplete();
        priority = task.getPriority();
        id = task.getId().toString();
        start_date = task.getStart_date().toString().replace('T', ' ');
        duration = task.getDuration().toString();
    }

}
