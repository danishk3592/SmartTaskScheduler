package com.danish.repository;

import com.danish.config.DatabaseConnection;
import com.danish.model.DashboardStats;
import com.danish.repository.interfaces.IAnalyticsRepository;

import com.danish.model.CategoryAnalytics;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class AnalyticsRepository implements IAnalyticsRepository {

    @Override
    public DashboardStats getDashboardStats(int userId) {

        DashboardStats stats = new DashboardStats();

        String sql = """
        SELECT
            COUNT(*) AS totalTasks,

            SUM(CASE
                    WHEN status='PENDING'
                    THEN 1 ELSE 0
                END) AS pendingTasks,

            SUM(CASE
                    WHEN status='IN_PROGRESS'
                    THEN 1 ELSE 0
                END) AS inProgressTasks,

            SUM(CASE
                    WHEN status='COMPLETED'
                    THEN 1 ELSE 0
                END) AS completedTasks,

            SUM(CASE
                    WHEN priority='HIGH'
                    THEN 1 ELSE 0
                END) AS highPriorityTasks,

            SUM(CASE
                    WHEN DATE(due_date)=CURDATE()
                    THEN 1 ELSE 0
                END) AS dueTodayTasks

        FROM tasks
        WHERE user_id = ?;
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                stats.setTotalTasks(rs.getInt("totalTasks"));
                stats.setPendingTasks(rs.getInt("pendingTasks"));
                stats.setInProgressTasks(rs.getInt("inProgressTasks"));
                stats.setCompletedTasks(rs.getInt("completedTasks"));
                stats.setHighPriorityTasks(rs.getInt("highPriorityTasks"));
                stats.setDueTodayTasks(rs.getInt("dueTodayTasks"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stats;
    }

    @Override
    public List<CategoryAnalytics> getTasksByCategory(int userId) {

        List<CategoryAnalytics> analytics = new ArrayList<>();

        String sql = """
            SELECT
                c.category_name,
                COUNT(*) AS total_tasks
            FROM tasks t
            JOIN categories c
            ON t.category_id = c.category_id
            WHERE t.user_id = ?
            GROUP BY c.category_name
            ORDER BY total_tasks DESC
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                CategoryAnalytics category = new CategoryAnalytics();

                category.setCategoryName(rs.getString("category_name"));
                category.setTotalTasks(rs.getInt("total_tasks"));

                analytics.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return analytics;
    }
}