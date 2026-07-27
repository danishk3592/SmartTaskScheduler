package com.danish.service;

import com.danish.model.DashboardStats;
import com.danish.repository.AnalyticsRepository;
import com.danish.repository.interfaces.IAnalyticsRepository;

import com.danish.model.CategoryAnalytics;
import java.util.List;

public class AnalyticsService {

    private final IAnalyticsRepository repository =
            new AnalyticsRepository();

    public DashboardStats getDashboardStats(int userId) {

        return repository.getDashboardStats(userId);

    }

    public List<CategoryAnalytics> getTasksByCategory(int userId) {

        return repository.getTasksByCategory(userId);

    }
}