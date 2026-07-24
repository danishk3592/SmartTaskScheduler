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
    }
}