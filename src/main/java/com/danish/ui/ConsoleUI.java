package com.danish.ui;

import com.danish.model.User;
import com.danish.service.UserService;

import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService service = new UserService();

    public void start() {

        System.out.println("========== SMART TASK SCHEDULER ==========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {

            System.out.print("Full Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = new User(name, email, password);

            boolean success = service.register(user);

            if (success) {
                System.out.println("✅ Registration Successful!");
            } else {
                System.out.println("❌ Registration Failed!");
            }
        }

        else if (choice == 2) {

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = service.login(email, password);

            if (user != null) {

                System.out.println("\n==================================");
                System.out.println("Welcome " + user.getFullName());
                System.out.println("Login Successful!");
                System.out.println("==================================");

            } else {

                System.out.println("Invalid Email or Password!");

            }
        }
    }
}