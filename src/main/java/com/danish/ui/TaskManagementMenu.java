package com.danish.ui;

import com.danish.model.User;

import java.util.Scanner;

public class TaskManagementMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskUI taskUI = new TaskUI();

    public void show(User user) {

        while (true) {

            System.out.println("\n============== TASK MANAGEMENT ==============");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Search Task");
            System.out.println("6. Back");

            System.out.print("Choose Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    taskUI.addTask(user);
                    break;

                case 2:
                    taskUI.viewTasks(user);
                    break;

                case 3:
                    System.out.println("Update Task Module (Coming Next)");
                    break;

                case 4:
                    System.out.println("Delete Task Module (Coming Next)");
                    break;

                case 5:
                    System.out.println("Search Task Module (Coming Next)");
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}