
package com.danish.ui;

import com.danish.export.PDFExportService;
import java.time.LocalDate;
import com.danish.export.ExportService;
import java.time.LocalDate;
import com.danish.model.Task;
import com.danish.model.User;
import com.danish.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportsUI {

    private final Scanner scanner = new Scanner(System.in);
    private final ReportService reportService = new ReportService();
    private final ExportService exportService = new ExportService();
    private final PDFExportService pdfExportService = new PDFExportService();

    public void showReports(User user) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("              REPORTS");
            System.out.println("======================================");

            System.out.println("1. All Tasks");
            System.out.println("2. Pending Tasks");
            System.out.println("3. Completed Tasks");
            System.out.println("4. High Priority Tasks");
            System.out.println("5. Today's Tasks");
            System.out.println("6. Export All Tasks (CSV)");
            System.out.println("7. Export All Tasks (PDF)");
            System.out.println("8. Back");

            System.out.print("Choose Option: ");

            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    displayReport(
                            "ALL TASKS",
                            reportService.getAllTasks(user.getUserId())
                    );
                    break;

                case 2:
                    displayReport(
                            "PENDING TASKS",
                            reportService.getPendingTasks(user.getUserId())
                    );
                    break;

                case 3:
                    displayReport(
                            "COMPLETED TASKS",
                            reportService.getCompletedTasks(user.getUserId())
                    );
                    break;

                case 4:
                    displayReport(
                            "HIGH PRIORITY TASKS",
                            reportService.getHighPriorityTasks(user.getUserId())
                    );
                    break;

                case 5:
                    displayReport(
                            "TODAY'S TASKS",
                            reportService.getTodayTasks(user.getUserId())
                    );
                    break;

                case 6:

                    List<Task> tasks = reportService.getAllTasks(user.getUserId());

                    String fileName =
                            "Tasks_Report_" + LocalDate.now() + ".csv";

                    if (exportService.exportTasksToCSV(tasks, fileName)) {

                        System.out.println("\n==================================");
                        System.out.println(" Report Exported Successfully!");
                        System.out.println(" Saved To: exports/" + fileName);
                        System.out.println("==================================");

                    } else {

                        System.out.println("\nExport Failed!");
                    }

                    break;

                case 7:

                    List<Task> pdfTasks =
                            reportService.getAllTasks(user.getUserId());

                    String pdfFileName =
                            "Tasks_Report_" + LocalDate.now() + ".pdf";

                    if (pdfExportService.exportTasksToPDF(
                            pdfTasks,
                            pdfFileName
                    )) {

                        System.out.println("\n==================================");
                        System.out.println(" PDF Exported Successfully!");
                        System.out.println(
                                " Saved To: pdfs/" + pdfFileName
                        );
                        System.out.println("==================================");

                    } else {

                        System.out.println("\nPDF Export Failed!");
                    }

                    break;

                case 8:
                    return;

                default:
                    System.out.println("\nInvalid Choice!");
            }
        }
    }

    private void displayReport(
            String reportTitle,
            List<Task> tasks
    ) {

        System.out.println(
                "\n========== " + reportTitle + " =========="
        );

        if (tasks.isEmpty()) {

            System.out.println("No tasks found.");
            return;
        }

        System.out.println(
                "Total Tasks: " + tasks.size()
        );

        System.out.println(
                "------------------------------------------------------------"
        );

        for (Task task : tasks) {

            System.out.println(
                    "Task ID    : " + task.getTaskId()
            );

            System.out.println(
                    "Category   : " + task.getCategoryName()
            );

            System.out.println(
                    "Title      : " + task.getTitle()
            );

            System.out.println(
                    "Priority   : " + task.getPriority()
            );

            System.out.println(
                    "Status     : " + task.getStatus()
            );

            System.out.println(
                    "Due Date   : " + task.getDueDate()
            );

            System.out.println(
                    "------------------------------------------------------------"
            );
        }
    }
}