package com.danish.service;

import com.danish.model.Category;
import com.danish.repository.CategoryRepository;

import java.util.List;

public class CategoryService {

    private final CategoryRepository repository = new CategoryRepository();

    public List<Category> getAllCategories() {
        return repository.getAllCategories();
    }
}