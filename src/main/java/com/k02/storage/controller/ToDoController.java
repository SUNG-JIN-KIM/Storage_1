package com.k02.storage.controller;

import com.k02.storage.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;


    @GetMapping("/todo")
    public String showToDoList(Model model) {
        model.addAttribute("todos", toDoService.getAllTasks());
        return "todolist";
    }

    @PostMapping("/todos")
    public String addTask(@RequestParam("task") String task,
                          @RequestParam("category") String category) {
        toDoService.addTask(task, category);
        return "redirect:/todo";
    }

    @PostMapping("/todos/toggle")
    public String toggleComplete(@RequestParam("taskId") int taskId) {
        toDoService.toggleTaskCompletion(taskId);
        return "redirect:/todo";
    }

    @PostMapping("/todos/update")
    public String updateTask(@RequestParam("taskId") int taskId,
                             @RequestParam("newDescription") String newDescription) {
        toDoService.updateTask(taskId, newDescription);
        return "redirect:/todo";
    }

    @PostMapping("/todos/delete")
    public String deleteTask(@RequestParam("taskId") int taskId) {
        toDoService.deleteTask(taskId);
        return "redirect:/todo";
    }
}
