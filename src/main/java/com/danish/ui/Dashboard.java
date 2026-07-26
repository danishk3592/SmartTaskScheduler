package com.danish.ui;

import com.danish.model.User;

import java.util.Scanner;

public class Dashboard {

    private final Scanner scanner = new Scanner(System.in);

    public void show(User user) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("Welcome " + user.getFullName());
            System.out.println("=================================");

            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Logout");

            System.out.print("Choose Option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Add Task Module (Coming Next)");
                    break;

                case 2:
                    System.out.println("View Task Module (Coming Next)");
                    break;

                case 3:
                    System.out.println("Update Task Module (Coming Next)");
                    break;

                case 4:
                    System.out.println("Delete Task Module (Coming Next)");
                    break;

                case 5:
                    System.out.println("Logged Out Successfully!");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}