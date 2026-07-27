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
            System.out.println("6. Filter Tasks");
            System.out.println("7. Change Task Status");
            System.out.println("8. Back"); //change back to filter task

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
                    taskUI.updateTask(user);
                    break;

                case 4:
                    taskUI.deleteTask(user);
                    break;

                case 5:
                    taskUI.searchTask(user);
                    break;

                case 6:
                    taskUI.filterTasks(user);
                    break;

                case 7:
                    taskUI.changeTaskStatus(user);
                    break;

                case 8:
                    return;

                default:
                System.out.println("Invalid Choice!");
            }
        }
    }
}