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

public class TaskUI {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskService service = new TaskService();

    public void addTask(User user) {

        System.out.println("\n========== ADD TASK ==========");

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

        if (service.addTask(task)) {

            System.out.println("\n✅ Task Added Successfully!");

        } else {

            System.out.println("\n❌ Failed to Add Task!");

        }
    }

    public void viewTasks(User user) {

        List<Task> tasks = service.getTasks(user.getUserId());

        if (tasks.isEmpty()) {

            System.out.println("\nNo Tasks Found!");

            return;
        }

        System.out.println("\n========== YOUR TASKS ==========");

        for (Task task : tasks) {

            System.out.println("--------------------------------");

            System.out.println("Task ID : " + task.getTaskId());
            System.out.println("Title : " + task.getTitle());
            System.out.println("Description : " + task.getDescription());
            System.out.println("Priority : " + task.getPriority());
            System.out.println("Status : " + task.getStatus());
            System.out.println("Due Date : " + task.getDueDate());

        }

        System.out.println("--------------------------------");
    }
}