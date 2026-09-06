# Smart Task Scheduler

A Java-based task management and productivity application designed to help users create, organize, track, analyze, and manage tasks efficiently.

The application provides task management, workflow tracking, analytics, reports, automated reminders, email notifications, and CSV/PDF export functionality.

---

## 🚀 Features

### 🔐 User Authentication
- User registration
- Secure login
- Password hashing
- User-specific task management

### 📋 Task Management
- Create tasks
- View tasks
- Update tasks
- Delete tasks
- Search tasks
- Filter tasks
- Task priorities
- Task descriptions
- Due dates

### 🔄 Task Workflow

Tasks can move through different states:

PENDING → IN_PROGRESS → COMPLETED

### 🗂️ Categories

Tasks can be organized using categories such as:

- Work
- Study
- Personal
- Health
- Shopping

### 📊 Dashboard & Analytics

The dashboard provides:

- Total tasks
- Pending tasks
- In-progress tasks
- Completed tasks
- High-priority tasks
- Tasks due today

### 📧 Email Reminders

The application can identify pending/overdue tasks and send reminder emails to the logged-in user.

### 📑 Reports

Generate reports for:

- All tasks
- Pending tasks
- Completed tasks
- High-priority tasks
- Today's tasks

### 📤 Export

Export task reports as:

- CSV
- PDF

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Core application |
| JDBC | Database connectivity |
| MySQL | Database |
| Maven | Dependency management |
| Jakarta Mail | Email notifications |
| Apache PDFBox | PDF generation |
| Git | Version control |
| GitHub | Project hosting |

---

## 🏗️ Architecture

The project follows a layered architecture:

UI Layer
↓
Service Layer
↓
Repository Layer
↓
Database

### Main Layers

**UI**
- Handles user interaction and menus.

**Service**
- Contains application/business logic.

**Repository**
- Handles database operations using JDBC.

**Model**
- Represents application entities.

**Notification**
- Handles email functionality.

**Export**
- Handles CSV and PDF report generation.

---

## 🗄️ Database

The application uses MySQL.

Database:

```text
smart_task_scheduler
