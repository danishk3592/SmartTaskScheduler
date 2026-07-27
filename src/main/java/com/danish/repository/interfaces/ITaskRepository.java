package com.danish.repository.interfaces;

import com.danish.model.Task;

import java.util.List;

public interface ITaskRepository {

    boolean addTask(Task task);

    List<Task> getTasksByUserId(int userId);

    boolean updateTask(Task task);

    boolean deleteTask(int taskId, int userId);

    List<Task> searchTasks(int userId, String keyword);

    List<Task> filterByStatus(int userId, String status);

    List<Task> filterByPriority(int userId, String priority);

    List<Task> getTodayTasks(int userId);

    List<Task> getDueTasks(int userId);

    boolean updateTaskStatus(int taskId, int userId, String status);
}