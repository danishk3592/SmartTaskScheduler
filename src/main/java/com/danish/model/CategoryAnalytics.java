package com.danish.model;

public class CategoryAnalytics {

    private String categoryName;
    private int totalTasks;

    public CategoryAnalytics() {
    }

    public CategoryAnalytics(String categoryName, int totalTasks) {
        this.categoryName = categoryName;
        this.totalTasks = totalTasks;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }
}