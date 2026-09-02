package com.danish.ui;

import com.danish.enums.Priority;
import com.danish.enums.TaskStatus;
import com.danish.model.Task;
import com.danish.model.User;
import com.danish.service.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskUI {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskService service = new TaskService();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // ==================== ADD TASK ====================

    public void addTask(User user) {

        System.out.println("\n========== ADD TASK ==========");

        System.out.println("Categories");
        System.out.println("1. Work");
        System.out.println("2. Study");
        System.out.println("3. Personal");
        System.out.println("4. Health");
        System.out.println("5. Shopping");

        int categoryId = readIntInRange(
                "Choose Category: ", 1, 5
        );

        String title = readNonEmptyString("Title: ");

        String description = readNonEmptyString("Description: ");

        Priority priority = readPriority();

        LocalDateTime dueDate = readDateTime();

        Task task = new Task(
                user.getUserId(),
                title,
                description,
                priority,
                TaskStatus.PENDING,
                dueDate
        );

        task.setCategoryId(categoryId);

        if (service.addTask(task)) {
            System.out.println("\nTask Added Successfully!");
        } else {
            System.out.println("\nFailed to Add Task!");
        }
    }

    // ==================== VIEW TASKS ====================

    public void viewTasks(User user) {

        List<Task> tasks = service.getTasks(user.getUserId());

        if (tasks.isEmpty()) {
            System.out.println("\nNo Tasks Found!");
            return;
        }

        printTaskTable(tasks);
    }

    private void printTaskTable(List<Task> tasks) {

        System.out.println(
                "\n====================== YOUR TASKS ======================"
        );

        System.out.printf(
                "%-5s %-12s %-20s %-10s %-12s %-20s%n",
                "ID",
                "Category",
                "Title",
                "Priority",
                "Status",
                "Due Date"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Task task : tasks) {

            System.out.printf(
                    "%-5d %-12s %-20s %-10s %-12s %-20s%n",
                    task.getTaskId(),
                    task.getCategoryName(),
                    task.getTitle(),
                    task.getPriority(),
                    task.getStatus(),
                    task.getDueDate()
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------"
        );
    }

    // ==================== UPDATE TASK ====================

    public void updateTask(User user) {

        viewTasks(user);

        System.out.println("\n========== UPDATE TASK ==========");

        int taskId = readPositiveInt("Enter Task ID: ");

        String title = readNonEmptyString("New Title: ");

        String description = readNonEmptyString("New Description: ");

        Priority priority = readPriority();

        TaskStatus status = readTaskStatus();

        LocalDateTime dueDate = readDateTime();

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
            System.out.println("\nTask Updated Successfully!");
        } else {
            System.out.println("\nFailed to Update Task!");
        }
    }

    // ==================== DELETE TASK ====================

    public void deleteTask(User user) {

        viewTasks(user);

        System.out.println("\n========== DELETE TASK ==========");

        int taskId = readPositiveInt("Enter Task ID: ");

        System.out.print("Are you sure? (Y/N): ");
        String confirm = scanner.nextLine().trim();

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

    // ==================== SEARCH TASK ====================

    public void searchTask(User user) {

        System.out.println("\n========== SEARCH TASK ==========");

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("\nKeyword cannot be empty!");
            return;
        }

        List<Task> tasks =
                service.searchTasks(user.getUserId(), keyword);

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

    // ==================== FILTER TASKS ====================

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

            int choice = readIntInRange(
                    "Choose Option: ", 1, 7
            );

            List<Task> tasks = new ArrayList<>();

            switch (choice) {

                case 1:
                    tasks = service.filterByStatus(
                            user.getUserId(), "PENDING"
                    );
                    break;

                case 2:
                    tasks = service.filterByStatus(
                            user.getUserId(), "IN_PROGRESS"
                    );
                    break;

                case 3:
                    tasks = service.filterByStatus(
                            user.getUserId(), "COMPLETED"
                    );
                    break;

                case 4:
                    tasks = service.filterByPriority(
                            user.getUserId(), "HIGH"
                    );
                    break;

                case 5:
                    tasks = service.filterByPriority(
                            user.getUserId(), "MEDIUM"
                    );
                    break;

                case 6:
                    tasks = service.filterByPriority(
                            user.getUserId(), "LOW"
                    );
                    break;

                case 7:
                    return;
            }

            if (tasks.isEmpty()) {

                System.out.println("\nNo Tasks Found!");

            } else {

                System.out.println(
                        "\n========== FILTERED TASKS =========="
                );

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

    // ==================== CHANGE STATUS ====================

    public void changeTaskStatus(User user) {

        System.out.println(
                "\n========== CHANGE TASK STATUS =========="
        );

        viewTasks(user);

        int taskId = readPositiveInt("\nEnter Task ID: ");

        System.out.println("\nChoose New Status");
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");

        int choice = readIntInRange(
                "Choice: ", 1, 3
        );

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
                return;
        }

        if (service.updateTaskStatus(
                taskId,
                user.getUserId(),
                status
        )) {

            System.out.println(
                    "\nTask Status Updated Successfully!"
            );

        } else {

            System.out.println(
                    "\nFailed to Update Task Status!"
            );
        }
    }

    // ==================== INPUT HELPERS ====================

    private int readPositiveInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println(
                        "Please enter a number greater than 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private int readIntInRange(
            String message,
            int min,
            int max
    ) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Please enter a number between "
                                + min + " and " + max + "."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private String readNonEmptyString(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "This field cannot be empty."
            );
        }
    }

    private Priority readPriority() {

        while (true) {

            System.out.print(
                    "Priority (LOW/MEDIUM/HIGH): "
            );

            String input =
                    scanner.nextLine().trim().toUpperCase();

            try {

                return Priority.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Invalid priority. Choose LOW, MEDIUM, or HIGH."
                );
            }
        }
    }

    private TaskStatus readTaskStatus() {

        while (true) {

            System.out.print(
                    "Status (PENDING/IN_PROGRESS/COMPLETED): "
            );

            String input =
                    scanner.nextLine().trim().toUpperCase();

            try {

                return TaskStatus.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Invalid status. Choose PENDING, IN_PROGRESS, or COMPLETED."
                );
            }
        }
    }

    private LocalDateTime readDateTime() {

        while (true) {

            System.out.print(
                    "Due Date (yyyy-MM-dd HH:mm): "
            );

            String input =
                    scanner.nextLine().trim();

            try {

                return LocalDateTime.parse(
                        input,
                        FORMATTER
                );

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Invalid date format. Use: yyyy-MM-dd HH:mm"
                );
            }
        }
    }

    public TaskService getService() {
        return service;
    }

    public Scanner getScanner() {
        return scanner;
    }
}