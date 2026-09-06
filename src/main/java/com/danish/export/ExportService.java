package com.danish.export;

import com.danish.model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportService {

    public boolean exportTasksToCSV(List<Task> tasks, String fileName) {

        try {

            // Create exports directory if it doesn't exist
            Path exportDirectory = Path.of("exports");
            Files.createDirectories(exportDirectory);

            Path filePath = exportDirectory.resolve(fileName);

            try (FileWriter writer = new FileWriter(filePath.toFile())) {

                // CSV Header
                writer.append("Task ID,Category,Title,Description,Priority,Status,Due Date\n");

                // Task data
                for (Task task : tasks) {

                    writer.append(csvValue(String.valueOf(task.getTaskId()))).append(",");
                    writer.append(csvValue(task.getCategoryName())).append(",");
                    writer.append(csvValue(task.getTitle())).append(",");
                    writer.append(csvValue(task.getDescription())).append(",");
                    writer.append(csvValue(task.getPriority().name())).append(",");
                    writer.append(csvValue(task.getStatus().name())).append(",");
                    writer.append(csvValue(String.valueOf(task.getDueDate()))).append("\n");
                }
            }

            return true;

        } catch (IOException e) {

            System.out.println("CSV Export Error: " + e.getMessage());
            return false;
        }
    }

    // Makes text safe for CSV files
    private String csvValue(String value) {

        if (value == null) {
            return "";
        }

        // Escape quotes
        value = value.replace("\"", "\"\"");

        // Wrap values containing commas, quotes, or new lines
        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")
                || value.contains("\r")) {

            return "\"" + value + "\"";
        }

        return value;
    }
}