package com.danish.service;

import com.danish.model.Task;
import com.danish.repository.ReportRepository;
import com.danish.repository.interfaces.IReportRepository;

import java.util.List;

public class ReportService {

    private final TaskService taskService = new TaskService();

    public List<Task> getAllTasks(int userId) {
        return taskService.getTasks(userId);
    }

    public List<Task> getPendingTasks(int userId) {
        return taskService.filterByStatus(userId, "PENDING");
    }

    public List<Task> getCompletedTasks(int userId) {
        return taskService.filterByStatus(userId, "COMPLETED");
    }

    public List<Task> getHighPriorityTasks(int userId) {
        return taskService.filterByPriority(userId, "HIGH");
    }

    public List<Task> getTodayTasks(int userId) {

        return taskService.getTodayTasks(userId);

    }
}