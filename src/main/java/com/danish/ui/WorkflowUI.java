package com.danish.ui;

import com.danish.model.User;
import com.danish.service.TaskService;

import java.util.Scanner;

public class WorkflowUI {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskService taskService = new TaskService();

    public void show(User user) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("              WORKFLOW");
            System.out.println("======================================");

            System.out.println("1. Change Task Status");
            System.out.println("2. Back");

            System.out.print("Choose Option: ");

            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    changeTaskStatus(user);
                    break;

                case 2:
                    return;

                default:
                    System.out.println("\nInvalid Choice!");
            }
        }
    }

    private void changeTaskStatus(User user) {

        System.out.print("\nEnter Task ID: ");

        String input = scanner.nextLine().trim();

        int taskId;

        try {
            taskId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid Task ID!");
            return;
        }

        System.out.println("\nSelect New Status:");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");

        System.out.print("Choose Status: ");

        String statusInput = scanner.nextLine().trim();

        String status;

        switch (statusInput) {

            case "1":
                status = "PENDING";
                break;

            case "2":
                status = "IN_PROGRESS";
                break;

            case "3":
                status = "COMPLETED";
                break;

            default:
                System.out.println("\nInvalid Status!");
                return;
        }

        boolean updated = taskService.updateTaskStatus(
                taskId,
                user.getUserId(),
                status
        );

        if (updated) {
            System.out.println("\n=================================");
            System.out.println(" Task Status Updated Successfully!");
            System.out.println(" New Status : " + status);
            System.out.println("=================================");
        } else {
            System.out.println("\nTask not found or update failed.");
        }
    }
}