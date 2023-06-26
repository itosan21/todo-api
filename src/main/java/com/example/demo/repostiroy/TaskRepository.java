package com.example.demo.repostiroy;

import com.example.demo.entity.Task;
import java.util.List;

public interface TaskRepository {

    Task findById(Long id);

    List<Task> findAll();

    Task save(Task task);

    Task update(Task task);

    void deleteById(Long id);
}
