package com.danish.ui;
import com.danish.ui.TaskUI;
import com.danish.model.User;
import com.danish.ui.TaskUI;
import java.util.Scanner;

public class Dashboard {

    private final Scanner scanner = new Scanner(System.in);

    public void show(User user) {

        while (true) {

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
                    System.out.println("Categories Module (Coming Soon)");
                    break;

                case 3:
                    System.out.println("Workflow Module (Coming Soon)");
                    break;

                case 4:
                    System.out.println("Reports Module (Coming Soon)");
                    break;

                case 5:
                    System.out.println("Analytics Module (Coming Soon)");
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