package com.danish.ui;

import com.danish.model.User;
import com.danish.service.UserService;

import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService service = new UserService();

    public void start() {

        while (true) {

            System.out.println("\n========== SMART TASK SCHEDULER ==========");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Full Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    User newUser = new User(name, email, password);

                    boolean success = service.register(newUser);

                    if (success) {
                        System.out.println("\nRegistration Successful!");
                    } else {
                        System.out.println("\nRegistration Failed!");
                    }
                    break;

                case 2:

                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("Password: ");
                    password = scanner.nextLine();

                    User loggedInUser = service.login(email, password);

                    if (loggedInUser != null) {

                        System.out.println("\n==================================");
                        System.out.println("Welcome " + loggedInUser.getFullName());
                        System.out.println("Login Successful!");
                        System.out.println("==================================");

                        Dashboard dashboard = new Dashboard();
                        dashboard.show(loggedInUser);

                        // After logout, execution returns here and the loop
                        // automatically shows the main menu again.

                    } else {

                        System.out.println("\n Invalid Email or Password!");

                    }
                    break;

                case 3:

                    System.out.println("\nThank you for using Smart Task Scheduler!");
                    System.out.println("Goodbye 👋");
                    return;

                default:

                    System.out.println("\nInvalid Choice! Please try again.");

            }
        }
    }
}