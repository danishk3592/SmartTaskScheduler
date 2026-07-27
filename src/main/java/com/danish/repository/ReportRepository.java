package com.danish.repository;

import com.danish.model.Task;
import com.danish.repository.interfaces.IReportRepository;

import java.util.ArrayList;
import java.util.List;

public class ReportRepository implements IReportRepository {

    @Override
    public List<Task> getAllTasks(int userId) {
        return new ArrayList<>();
    }

    @Override
    public List<Task> getPendingTasks(int userId) {
        return new ArrayList<>();
    }

    @Override
    public List<Task> getCompletedTasks(int userId) {
        return new ArrayList<>();
    }

    @Override
    public List<Task> getHighPriorityTasks(int userId) {
        return new ArrayList<>();
    }

    @Override
    public List<Task> getTodayTasks(int userId) {
        return new ArrayList<>();
    }
}