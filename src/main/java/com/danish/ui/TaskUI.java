package com.danish.ui;

import com.danish.enums.Priority;
import com.danish.enums.TaskStatus;
import com.danish.model.Task;
import com.danish.model.User;
import com.danish.service.TaskService;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.ArrayList;

public class TaskUI {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskService service = new TaskService();

    public void addTask(User user) {

        System.out.println("\n========== ADD TASK ==========");

        System.out.println("Categories");
        System.out.println("1. Work");
        System.out.println("2. Study");
        System.out.println("3. Personal");
        System.out.println("4. Health");
        System.out.println("5. Shopping");

        System.out.print("Choose Category: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Priority (LOW/MEDIUM/HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Due Date (yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dueDate =
                LocalDateTime.parse(input, formatter);

        Task task = new Task(
                user.getUserId(),
                title,
                description,
                priority,
                TaskStatus.PENDING,
                dueDate
        );

        task.setCategoryId(categoryId);
        task.setCategoryId(categoryId);

        System.out.println("\n========== DEBUG ==========");
        System.out.println("User ID     : " + task.getUserId());
        System.out.println("Category ID : " + task.getCategoryId());
        System.out.println("===========================\n");

        if (service.addTask(task)) {

            System.out.println("\nTask Added Successfully!");

        } else {

            System.out.println("\nFailed to Add Task!");

        }
    }

    public void viewTasks(User user) {

        List<Task> tasks = service.getTasks(user.getUserId());

        if (tasks.isEmpty()) {

            System.out.println("\nNo Tasks Found!");

            return;
        }

        System.out.println("\n========== YOUR TASKS ==========");

        System.out.println("\n====================== YOUR TASKS ======================");

        System.out.printf("%-5s %-12s %-20s %-10s %-12s %-20s%n",
                "ID", "Category", "Title", "Priority", "Status", "Due Date");

        System.out.println("--------------------------------------------------------------------------");

        for (Task task : tasks) {

            System.out.printf("%-5d %-12s %-20s %-10s %-12s %-20s%n",
                    task.getTaskId(),
                    task.getCategoryName(),
                    task.getTitle(),
                    task.getPriority(),
                    task.getStatus(),
                    task.getDueDate());

        }

        System.out.println("--------------------------------");
    }

    public TaskService getService() {
        return service;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void updateTask(User user) {

        // First show all tasks
        viewTasks(user);

        System.out.println("\n========== UPDATE TASK ==========");

        System.out.print("Enter Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        System.out.print("New Title: ");
        String title = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("Priority (LOW/MEDIUM/HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Status (PENDING/IN_PROGRESS/COMPLETED): ");
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Due Date (yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dueDate =
                LocalDateTime.parse(input, formatter);

        Task task = new Task(
                user.getUserId(),
                title,
                description,
                priority,
                status,
                dueDate
        );

        task.setTaskId(taskId);

        if (service.updateTask(task)) {

            System.out.println("\n Task Updated Successfully!");

        } else {

            System.out.println("\n Failed to Update Task!");

        }
    }

    public void deleteTask(User user) {

        viewTasks(user);

        System.out.println("\n========== DELETE TASK ==========");

        System.out.print("Enter Task ID: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        System.out.print("Are you sure? (Y/N): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Deletion Cancelled.");
            return;
        }

        if (service.deleteTask(taskId, user.getUserId())) {

            System.out.println("\nTask Deleted Successfully!");

        } else {

            System.out.println("\nFailed to Delete Task!");

        }
    }

    public void searchTask(User user) {

        System.out.println("\n========== SEARCH TASK ==========");

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Task> tasks = service.searchTasks(user.getUserId(), keyword);

        if (tasks.isEmpty()) {

            System.out.println("\nNo Matching Tasks Found!");
            return;

        }

        System.out.println("\n========== SEARCH RESULTS ==========");

        for (Task task : tasks) {

            System.out.println("--------------------------------");
            System.out.println("Task ID    : " + task.getTaskId());
            System.out.println("Category   : " + task.getCategoryName());
            System.out.println("Title      : " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Priority   : " + task.getPriority());
            System.out.println("Status     : " + task.getStatus());
            System.out.println("Due Date   : " + task.getDueDate());

        }

        System.out.println("--------------------------------");

    }

    public void filterTasks(User user) {

        while (true) {

            System.out.println("\n========== FILTER TASKS ==========");
            System.out.println("1. Pending");
            System.out.println("2. In Progress");
            System.out.println("3. Completed");
            System.out.println("4. High Priority");
            System.out.println("5. Medium Priority");
            System.out.println("6. Low Priority");
            System.out.println("7. Back");

            System.out.print("Choose Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            List<Task> tasks = new ArrayList<>();

            switch (choice) {

                case 1:
                    tasks = service.filterByStatus(user.getUserId(), "PENDING");
                    break;

                case 2:
                    tasks = service.filterByStatus(user.getUserId(), "IN_PROGRESS");
                    break;

                case 3:
                    tasks = service.filterByStatus(user.getUserId(), "COMPLETED");
                    break;

                case 4:
                    tasks = service.filterByPriority(user.getUserId(), "HIGH");
                    break;

                case 5:
                    tasks = service.filterByPriority(user.getUserId(), "MEDIUM");
                    break;

                case 6:
                    tasks = service.filterByPriority(user.getUserId(), "LOW");
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid Choice!");
                    continue;
            }

            if (tasks.isEmpty()) {

                System.out.println("\nNo Tasks Found!");

            } else {

                System.out.println("\n========== FILTERED TASKS ==========");

                for (Task task : tasks) {

                    System.out.println("--------------------------------");
                    System.out.println("Task ID    : " + task.getTaskId());
                    System.out.println("Category   : " + task.getCategoryName());
                    System.out.println("Title      : " + task.getTitle());
                    System.out.println("Priority   : " + task.getPriority());
                    System.out.println("Status     : " + task.getStatus());
                    System.out.println("Due Date   : " + task.getDueDate());

                }

                System.out.println("--------------------------------");
            }
        }
    }

    public void changeTaskStatus(User user) {

        System.out.println("\n========== CHANGE TASK STATUS ==========");

        // Show user's tasks
        viewTasks(user);

        System.out.print("\nEnter Task ID: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nChoose New Status");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");

        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String status;

        switch (choice) {

            case 1:
                status = "PENDING";
                break;

            case 2:
                status = "IN_PROGRESS";
                break;

            case 3:
                status = "COMPLETED";
                break;

            default:
                System.out.println("Invalid Choice!");
                return;
        }

        if (service.updateTaskStatus(taskId, user.getUserId(), status)) {

            System.out.println("\nTask Status Updated Successfully!");

        } else {

            System.out.println("\nFailed to Update Task Status!");

        }
    }
}