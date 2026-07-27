package com.danish.service;

import com.danish.model.Task;
import com.danish.repository.interfaces.ITaskRepository;
import com.danish.repository.TaskRepository;
import java.util.List;
public class TaskService {

    private final ITaskRepository repository = new TaskRepository();

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

    public boolean updateTask(Task task) {

        if (task.getTitle().isBlank()) {
            System.out.println("Task title cannot be empty.");
            return false;
        }

        return repository.updateTask(task);
    }

    public boolean deleteTask(int taskId, int userId) {

        return repository.deleteTask(taskId, userId);

    }

    public List<Task> searchTasks(int userId, String keyword) {

        return repository.searchTasks(userId, keyword);

    }

    public List<Task> filterByStatus(int userId, String status) {

        return repository.filterByStatus(userId, status);

    }

    public List<Task> filterByPriority(int userId, String priority) {

        return repository.filterByPriority(userId, priority);

    }

    public List<Task> getTodayTasks(int userId) {

        return repository.getTodayTasks(userId);

    }

    public List<Task> getDueTasks(int userId) {

        return repository.getDueTasks(userId);

    }

    public boolean updateTaskStatus(int taskId, int userId, String status) {

        return repository.updateTaskStatus(taskId, userId, status);

    }
}

