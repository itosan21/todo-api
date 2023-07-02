package com.example.demo.repostiroy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.demo.entity.Task;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



@DataJpaTest
public class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setTitle("Test task");
        task.setDescription("This is a test task");
        task.setStatus("In progress");
        task.setDueDate(new Date());
        Task savedTask = taskRepository.save(task);

        Task retrievedTask = entityManager.find(Task.class, savedTask.getId());
        assertEquals(savedTask, retrievedTask);
    }

}