package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskRestController {

    private final TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    // タスク一覧表示
    @GetMapping
    public String list(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list";  // list.htmlを表示
    }

    // タスク作成画面表示
    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/new";  // new.htmlを表示
    }

    // タスク作成処理
    @PostMapping
    public String create(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";  // タスク一覧にリダイレクト
    }

    // タスク編集画面表示
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "tasks/edit";  // edit.htmlを表示
    }

    // タスク編集処理
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.updateTask(task);
        return "redirect:/tasks";  // タスク一覧にリダイレクト
    }

}
