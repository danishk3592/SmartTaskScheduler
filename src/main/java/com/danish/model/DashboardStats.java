package com.danish.model;

public class DashboardStats {

    private int totalTasks;
    private int pendingTasks;
    private int inProgressTasks;
    private int completedTasks;
    private int highPriorityTasks;
    private int dueTodayTasks;

    public DashboardStats() {}

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getPendingTasks() {
        return pendingTasks;
    }

    public void setPendingTasks(int pendingTasks) {
        this.pendingTasks = pendingTasks;
    }

    public int getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(int inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getHighPriorityTasks() {
        return highPriorityTasks;
    }

    public void setHighPriorityTasks(int highPriorityTasks) {
        this.highPriorityTasks = highPriorityTasks;
    }

    public int getDueTodayTasks() {
        return dueTodayTasks;
    }

    public void setDueTodayTasks(int dueTodayTasks) {
        this.dueTodayTasks = dueTodayTasks;
    }
}