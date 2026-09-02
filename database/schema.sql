CREATE DATABASE IF NOT EXISTS smart_task_scheduler;

USE smart_task_scheduler;

-- ==========================================
-- USERS TABLE
-- ==========================================

CREATE TABLE IF NOT EXISTS users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

-- ==========================================
-- CATEGORIES TABLE
-- ==========================================

CREATE TABLE IF NOT EXISTS categories (
                                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                                          category_name VARCHAR(50) NOT NULL UNIQUE
    );

-- ==========================================
-- DEFAULT CATEGORIES
-- ==========================================

INSERT IGNORE INTO categories (category_name)
VALUES
    ('Work'),
    ('Study'),
    ('Personal'),
    ('Health'),
    ('Shopping');

-- ==========================================
-- TASKS TABLE
-- ==========================================

CREATE TABLE IF NOT EXISTS tasks (
                                     task_id INT AUTO_INCREMENT PRIMARY KEY,

                                     user_id INT NOT NULL,

                                     category_id INT NOT NULL,

                                     title VARCHAR(200) NOT NULL,

    description TEXT,

    priority VARCHAR(20) NOT NULL,

    status VARCHAR(20) NOT NULL,

    due_date DATETIME NOT NULL,

    CONSTRAINT fk_tasks_user
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE,

    CONSTRAINT fk_tasks_category
    FOREIGN KEY (category_id)
    REFERENCES categories(category_id)
    ON DELETE CASCADE
    );

-- ==========================================
-- VERIFY DATABASE
-- ==========================================

SHOW TABLES;

SELECT * FROM categories;