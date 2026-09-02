# SmartTaskScheduler

> Not just a To-Do Application, it's an enhanced Task Scheduler.

SmartTaskScheduler is a Java-based task management application designed to help users efficiently organize, manage, track, and prioritize their daily tasks.

The application uses a layered architecture with Java, JDBC, MySQL, and Maven to provide reliable task management and database-driven functionality.

---

## 🚀 Features

### 👤 User Management
- User registration
- Secure user login
- Password hashing using BCrypt
- User-specific task management

### 📝 Task Management
- Add new tasks
- View all tasks
- Update existing tasks
- Delete tasks
- Change task status
- Set task priority
- Set task due dates

### 🗂️ Task Categories
Tasks can be organized into predefined categories:

- Work
- Study
- Personal
- Health
- Shopping

### 🔎 Search & Filtering
- Search tasks by keyword
- Filter by task status
- Filter by priority
- View pending tasks
- View in-progress tasks
- View completed tasks

### 📊 Analytics
- Task statistics
- Task progress tracking
- Category-based task information

---

## 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| JDBC | Database connectivity |
| MySQL | Relational database |
| Maven | Dependency management |
| IntelliJ IDEA | Development environment |
| Git & GitHub | Version control |
| BCrypt | Password hashing |

---

## 🏗️ Project Architecture

The project follows a layered architecture:

```text
SmartTaskScheduler
│
├── config
│   └── DatabaseConnection
│
├── enums
│   ├── Priority
│   └── TaskStatus
│
├── model
│   ├── User
│   └── Task
│
├── repository
│   ├── UserRepository
│   ├── TaskRepository
│   └── ...
│
├── service
│   ├── UserService
│   └── TaskService
│
├── ui
│   ├── LoginUI
│   ├── TaskUI
│   └── ...
│
└── Main.java
