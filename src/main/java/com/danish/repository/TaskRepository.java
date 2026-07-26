package com.danish.repository;

import com.danish.config.DatabaseConnection;
import com.danish.model.Task;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.danish.enums.Priority;
import com.danish.enums.TaskStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskRepository {

    public boolean addTask(Task task) {

        String sql = """
                INSERT INTO tasks
                (user_id, title, description, priority, status, due_date)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, task.getUserId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());

            // Enum values are converted to String
            statement.setString(4, task.getPriority().name());
            statement.setString(5, task.getStatus().name());

            statement.setTimestamp(6, Timestamp.valueOf(task.getDueDate()));

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Task> getTasksByUserId(int userId) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
            SELECT * FROM tasks
            WHERE user_id = ?
            ORDER BY due_date
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));

                task.setPriority(
                        Priority.valueOf(rs.getString("priority"))
                );

                task.setStatus(
                        TaskStatus.valueOf(rs.getString("status"))
                );

                task.setDueDate(
                        rs.getTimestamp("due_date").toLocalDateTime()
                );

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}