package com.danish.repository.interfaces;

import com.danish.model.Category;
import java.util.List;

public interface ICategoryRepository {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);
}