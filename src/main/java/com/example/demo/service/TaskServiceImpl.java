package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.repostiroy.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskRepository.findById(id));
        return optionalTask.orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task toggleTaskStatus(Long id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskRepository.findById(id));
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(task.getStatus().equals("未完了") ? "完了" : "未完了");
            return taskRepository.save(task);
        }
        return null;
    }
}

