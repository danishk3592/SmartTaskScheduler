package com.danish.ui;

import com.danish.model.DashboardStats;
import com.danish.service.AnalyticsService;
import com.danish.ui.ReportsUI;
import com.danish.service.ReminderService;

import com.danish.ui.TaskUI;
import com.danish.model.User;
import com.danish.ui.TaskUI;
import java.util.Scanner;
import com.danish.ui.AnalyticsUI;

public class Dashboard {

    private final Scanner scanner = new Scanner(System.in);

    public void show(User user) {

        AnalyticsService analyticsService = new AnalyticsService();


        ReminderService reminderService = new ReminderService();

        reminderService.checkDueTasks(user.getUserId());

        while (true) {

            DashboardStats stats =
                    analyticsService.getDashboardStats(user.getUserId());

            System.out.println("\n========== DASHBOARD ==========");

            System.out.println("📋 Total Tasks      : " + stats.getTotalTasks());
            System.out.println("⏳ Pending          : " + stats.getPendingTasks());
            System.out.println("🔄 In Progress      : " + stats.getInProgressTasks());
            System.out.println("✅ Completed        : " + stats.getCompletedTasks());
            System.out.println("🔥 High Priority    : " + stats.getHighPriorityTasks());
            System.out.println("📅 Due Today        : " + stats.getDueTodayTasks());

            System.out.println("===============================\n");

            System.out.println("\n========================================");
            System.out.println("        SMART TASK SCHEDULER");
            System.out.println("========================================");
            System.out.println("Logged in as : " + user.getFullName());

            System.out.println("\n1. Task Management");
            System.out.println("2. Categories");
            System.out.println("3. Workflow");
            System.out.println("4. Reports");
            System.out.println("5. Analytics");
            System.out.println("6. Logout");

            System.out.print("\nChoose Option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    TaskManagementMenu menu = new TaskManagementMenu();
                    menu.show(user);

                    break;

                case 2:
                    CategoryUI categoryUI = new CategoryUI();
                    categoryUI.show();
                    break;

                case 3:
                    System.out.println("Workflow Module (Coming Soon)");
                    break;

                case 4:

                    ReportsUI reportsUI = new ReportsUI();
                    reportsUI.show(user);

                    break;

                case 5:

                    AnalyticsUI analyticsUI = new AnalyticsUI();
                    analyticsUI.show(user);

                    break;

                case 6:
                    System.out.println("Logged Out Successfully!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}