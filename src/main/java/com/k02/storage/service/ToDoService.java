


package com.k02.storage.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ToDoService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    private static final String[] COLORS = {
            "#FFD700", "#FF7F50", "#87CEEB", "#90EE90", "#FFB6C1", "#DDA0DD", "#FFA07A", "#B0C4DE"
    };

    private String getRandomColor() {
        Random rand = new Random();
        return COLORS[rand.nextInt(COLORS.length)];
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(String description, String category) {
        String randomColor = getRandomColor();
        tasks.add(new Task(idCounter.getAndIncrement(), description, randomColor, category));
    }

    public void toggleTaskCompletion(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(!task.isCompleted());
                break;
            }
        }
    }

    public void updateTask(int taskId, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDescription(newDescription);
                break;
            }
        }
    }

    public void deleteTask(int taskId) {
        tasks.removeIf(t -> t.getId() == taskId);
    }

    public static class Task {
        private final int id;
        private String description;
        private boolean completed = false;
        private String color;
        private String category;


        public Task(int id, String description, String color, String category) {
            this.id = id;
            this.description = description;
            this.color = color;
            this.category = category;
        }

        public int getId() { return id; }
        public String getDescription() { return description; }
        public boolean isCompleted() { return completed; }
        public String getColor() { return color; }
        public String getCategory() { return category; }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setCategory(String category) {
            this.category = category;
        }


    }
}
