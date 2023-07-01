package com.example.demo.service;


import com.example.demo.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Long id);
    Task toggleTaskStatus(Long id);

}