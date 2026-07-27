package com.danish.repository;

import com.danish.repository.interfaces.ITaskRepository;

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




public class TaskRepository implements ITaskRepository {



    public boolean addTask(Task task) {

        String sql = """
                INSERT INTO tasks
                (user_id, category_id, title, description, priority, status, due_date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, task.getUserId());
            statement.setInt(2, task.getCategoryId());
            statement.setString(3, task.getTitle());
            statement.setString(4, task.getDescription());
            statement.setString(5, task.getPriority().name());
            statement.setString(6, task.getStatus().name());
            statement.setTimestamp(7, Timestamp.valueOf(task.getDueDate()));

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
            
                SELECT
                                 t.task_id,
                                 c.category_name,
                                 t.title,
                                 t.description,
                                 t.priority,
                                 t.status,
                                 t.due_date
                             FROM tasks t
                             JOIN categories c
                             ON t.category_id = c.category_id
                             WHERE t.user_id = ?
                             ORDER BY t.due_date;
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
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

    @Override
    public List<Task> searchTasks(int userId, String keyword) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
        SELECT
            t.task_id,
            c.category_name,
            t.title,
            t.description,
            t.priority,
            t.status,
            t.due_date
        FROM tasks t
        JOIN categories c
            ON t.category_id = c.category_id
        WHERE t.user_id = ?
        AND (
            t.title LIKE ?
            OR t.description LIKE ?
            OR c.category_name LIKE ?
        )
        ORDER BY t.due_date
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            String search = "%" + keyword + "%";

            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(Priority.valueOf(rs.getString("priority")));
                task.setStatus(TaskStatus.valueOf(rs.getString("status")));
                task.setDueDate(rs.getTimestamp("due_date").toLocalDateTime());

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public boolean updateTask(Task task) {

        String sql = """
            UPDATE tasks
            SET title = ?,
                description = ?,
                priority = ?,
                status = ?,
                due_date = ?
            WHERE task_id = ? AND user_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getPriority().name());
            statement.setString(4, task.getStatus().name());
            statement.setTimestamp(5, Timestamp.valueOf(task.getDueDate()));

            statement.setInt(6, task.getTaskId());
            statement.setInt(7, task.getUserId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public boolean deleteTask(int taskId, int userId) {

        String sql = """
            DELETE FROM tasks
            WHERE task_id = ? AND user_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, taskId);
            statement.setInt(2, userId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Task> filterByStatus(int userId, String status) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
        SELECT
            t.task_id,
            c.category_name,
            t.title,
            t.description,
            t.priority,
            t.status,
            t.due_date
        FROM tasks t
        JOIN categories c
        ON t.category_id = c.category_id
        WHERE t.user_id = ?
        AND t.status = ?
        ORDER BY t.due_date
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, status);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(
                        Priority.valueOf(rs.getString("priority")));
                task.setStatus(
                        TaskStatus.valueOf(rs.getString("status")));
                task.setDueDate(
                        rs.getTimestamp("due_date").toLocalDateTime());

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public List<Task> filterByPriority(int userId, String priority) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
        SELECT
            t.task_id,
            c.category_name,
            t.title,
            t.description,
            t.priority,
            t.status,
            t.due_date
        FROM tasks t
        JOIN categories c
            ON t.category_id = c.category_id
        WHERE t.user_id = ?
        AND t.priority = ?
        ORDER BY t.due_date
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, priority);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(
                        Priority.valueOf(rs.getString("priority")));
                task.setStatus(
                        TaskStatus.valueOf(rs.getString("status")));
                task.setDueDate(
                        rs.getTimestamp("due_date").toLocalDateTime());

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    @Override
    public List<Task> getTodayTasks(int userId) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
        SELECT
            t.task_id,
            c.category_name,
            t.title,
            t.description,
            t.priority,
            t.status,
            t.due_date
        FROM tasks t
        JOIN categories c
        ON t.category_id = c.category_id
        WHERE t.user_id = ?
        AND DATE(t.due_date) = CURDATE()
        ORDER BY t.due_date;
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(
                        Priority.valueOf(rs.getString("priority")));
                task.setStatus(
                        TaskStatus.valueOf(rs.getString("status")));
                task.setDueDate(
                        rs.getTimestamp("due_date").toLocalDateTime());

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public List<Task> getDueTasks(int userId) {

        List<Task> tasks = new ArrayList<>();

        String sql = """
        SELECT
            t.task_id,
            c.category_name,
            t.title,
            t.description,
            t.priority,
            t.status,
            t.due_date
        FROM tasks t
        JOIN categories c
            ON t.category_id = c.category_id
        WHERE t.user_id = ?
          AND t.status <> 'COMPLETED'
          AND DATE(t.due_date) <= CURDATE()
        ORDER BY t.due_date;
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setCategoryName(rs.getString("category_name"));
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

    @Override
    public boolean updateTaskStatus(int taskId, int userId, String status) {

        String sql = """
            UPDATE tasks
            SET status = ?
            WHERE task_id = ? AND user_id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, taskId);
            statement.setInt(3, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

