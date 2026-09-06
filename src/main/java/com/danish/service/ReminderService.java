package com.danish.service;

import com.danish.model.Task;
import com.danish.model.User;
import com.danish.notification.EmailService;

import java.time.LocalDateTime;
import java.util.List;

public class ReminderService {

    private final TaskService taskService = new TaskService();

    public void checkDueTasks(User user) {

        List<Task> tasks = taskService.getDueTasks(user.getUserId());

        System.out.println("Logged-in User : " + user.getEmail());
        System.out.println("Due Tasks Found: " + tasks.size());

        if (tasks.isEmpty()) {
            return;
        }

        System.out.println("\n=================================");
        System.out.println("        TASK REMINDERS");
        System.out.println("=================================");

        StringBuilder emailBody = new StringBuilder();

        emailBody.append("Hello ")
                .append(user.getFullName())
                .append(",\n\n");

        emailBody.append("You have ")
                .append(tasks.size())
                .append(" pending task(s) that require your attention.\n\n");

        emailBody.append("---------------------------------\n");

        for (Task task : tasks) {

            System.out.println("---------------------------------");
            System.out.println("Task : " + task.getTitle());
            System.out.println("Due  : " + task.getDueDate());

            String status;

            if (task.getDueDate().isBefore(LocalDateTime.now())) {
                status = "OVERDUE";
            } else {
                status = "DUE TODAY";
            }

            System.out.println("Status : " + status);

            emailBody.append("Task : ")
                    .append(task.getTitle())
                    .append("\n");

            emailBody.append("Priority : ")
                    .append(task.getPriority())
                    .append("\n");

            emailBody.append("Due Date : ")
                    .append(task.getDueDate())
                    .append("\n");

            emailBody.append("Status : ")
                    .append(status)
                    .append("\n");

            emailBody.append("---------------------------------\n");
        }

        System.out.println("---------------------------------");

        emailBody.append("\nPlease complete your pending tasks on time.\n\n");
        emailBody.append("Regards,\n");
        emailBody.append("Smart Task Scheduler");

        // Send reminder email
        EmailService.sendEmail(
                user.getEmail(),
                "Smart Task Scheduler - Task Reminder",
                emailBody.toString()
        );
    }
}