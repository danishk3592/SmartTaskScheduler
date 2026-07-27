package com.danish.repository.interfaces;

import com.danish.model.DashboardStats;
import com.danish.model.CategoryAnalytics;
import java.util.List;

public interface IAnalyticsRepository {

    DashboardStats getDashboardStats(int userId);

    List<CategoryAnalytics> getTasksByCategory(int userId);

}