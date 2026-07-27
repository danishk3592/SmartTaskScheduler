package com.danish.ui;


import com.danish.model.Task;
import com.danish.service.ReportService;
import java.util.List;
import com.danish.model.User;

import java.util.Scanner;

public class ReportsUI {

    private final Scanner scanner = new Scanner(System.in);

    private final ReportService service = new ReportService();

    public void show(User user) {



        while (true) {

            System.out.println("\n========== REPORTS ==========");
            System.out.println("1. All Tasks Report");
            System.out.println("2. Pending Tasks Report");
            System.out.println("3. Completed Tasks Report");
            System.out.println("4. High Priority Tasks Report");
            System.out.println("5. Today's Tasks Report");
            System.out.println("6. Back");

            System.out.print("Choose Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    displayTasks(service.getAllTasks(user.getUserId()));
                    break;

                case 2:
                    displayTasks(service.getPendingTasks(user.getUserId()));
                    break;

                case 3:
                    displayTasks(service.getCompletedTasks(user.getUserId()));
                    break;

                case 4:
                    displayTasks(service.getHighPriorityTasks(user.getUserId()));
                    break;

                case 5:
                    displayTasks(service.getTodayTasks(user.getUserId()));
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void displayTasks(List<Task> tasks) {

        if (tasks.isEmpty()) {

            System.out.println("\nNo Tasks Found!");
            return;

        }

        System.out.println("\n========== REPORT ==========");

        for (Task task : tasks) {

            System.out.println("--------------------------------");
            System.out.println("Task ID     : " + task.getTaskId());
            System.out.println("Category    : " + task.getCategoryName());
            System.out.println("Title       : " + task.getTitle());
            System.out.println("Description : " + task.getDescription());
            System.out.println("Priority    : " + task.getPriority());
            System.out.println("Status      : " + task.getStatus());
            System.out.println("Due Date    : " + task.getDueDate());

        }

        System.out.println("--------------------------------");
    }


}