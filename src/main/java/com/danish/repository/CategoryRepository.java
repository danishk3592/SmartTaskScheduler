package com.danish.repository;

import com.danish.config.DatabaseConnection;
import com.danish.model.Category;
import com.danish.repository.interfaces.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {

    @Override
    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY category_name";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));

                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) {

        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));

                return category;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}