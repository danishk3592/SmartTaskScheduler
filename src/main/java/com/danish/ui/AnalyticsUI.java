package com.danish.ui;

import com.danish.model.CategoryAnalytics;
import com.danish.model.User;
import com.danish.service.AnalyticsService;

import java.util.List;

public class AnalyticsUI {

    private final AnalyticsService service = new AnalyticsService();

    public void show(User user) {

        List<CategoryAnalytics> analytics =
                service.getTasksByCategory(user.getUserId());

        System.out.println("\n========== ANALYTICS ==========");

        if (analytics.isEmpty()) {

            System.out.println("No Tasks Found.");
            return;
        }

        System.out.println("\nTasks by Category\n");

        for (CategoryAnalytics category : analytics) {

            System.out.printf("%-15s : %d%n",
                    category.getCategoryName(),
                    category.getTotalTasks());

        }

        System.out.println("\n===============================");
    }
}