package com.danish.repository.interfaces;

import com.danish.model.Task;
import java.util.List;

public interface IReportRepository {

    List<Task> getAllTasks(int userId);

    List<Task> getPendingTasks(int userId);

    List<Task> getCompletedTasks(int userId);

    List<Task> getHighPriorityTasks(int userId);

    List<Task> getTodayTasks(int userId);
}