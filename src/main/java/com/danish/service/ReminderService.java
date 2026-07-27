package com.danish.service;

import com.danish.model.Task;

import java.util.List;

public class ReminderService {

    private final TaskService taskService = new TaskService();

    public void checkDueTasks(int userId) {



        List<Task> tasks = taskService.getDueTasks(userId);

        System.out.println("Logged-in User ID : " + userId);
        System.out.println("Due Tasks Found   : " + tasks.size());

        System.out.println("Tasks Found = " + tasks.size());



        if (tasks.isEmpty()) {
            return;
        }

        System.out.println("\n=================================");
        System.out.println("        TASK REMINDERS");
        System.out.println("=================================");

        for (Task task : tasks) {

            System.out.println("---------------------------------");
            System.out.println("Task : " + task.getTitle());
            System.out.println("Due  : " + task.getDueDate());

            if (task.getDueDate().isBefore(java.time.LocalDateTime.now())) {
                System.out.println("Status : OVERDUE");
            } else {
                System.out.println("Status : DUE TODAY");
            }
        }

        System.out.println("---------------------------------");
    }


}