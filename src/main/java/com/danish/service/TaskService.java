package com.danish.service;

import com.danish.model.Task;
import com.danish.repository.TaskRepository;
import java.util.List;
public class TaskService {

    private final TaskRepository repository = new TaskRepository();

    public boolean addTask(Task task) {

        if (task.getTitle().isBlank()) {
            System.out.println("Task title cannot be empty.");
            return false;
        }

        if (task.getDescription().isBlank()) {
            System.out.println("Task description cannot be empty.");
            return false;
        }

        return repository.addTask(task);
    }

    public List<Task> getTasks(int userId) {

        return repository.getTasksByUserId(userId);

    }
}