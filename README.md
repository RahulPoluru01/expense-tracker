# 💰 Expense Tracker Web Application

This is a personal expense tracker web app designed to help you monitor and manage your daily expenses. It provides a user-friendly interface to add and categorize your expenses, with filter functionality based on categories.

---

## 🛠️ Technologies Used

- **Spring Boot**: Backend framework for building Java-based web applications.
- **Thymeleaf**: Server-side Java template engine for dynamic HTML generation.
- **H2 Database**: In-memory relational database for real-time data storage.
- **Spring Security**: Secures REST endpoints with database-level authorization.
- **IDE**: IntelliJ IDEA (Community Edition)

---

## 🚀 Features

- Add expense entries with amount, description, category, and date.
- Filter expenses by category.
- Secure access using Spring Security (via database-based login).
- Auto-created tables in H2 database.

---

## ⚙️ Installation & Setup

1. **Install IntelliJ IDEA (Community Edition)** and complete the basic setup.
2. **Clone the repository**:
   ```bash
   git clone https://github.com/rahu1choudhary/expense-tracker.git
Open the project in IntelliJ IDEA.

Set the JDK version to 21 under Project Structure.

Configure the database connection in application.properties.

Run the project by executing the main() method in:

css
Copy
Edit
src/main/java/com/example/ExpenseTrackerApplication.java
Open your browser and navigate to:

arduino
Copy
Edit
http://localhost:8080
Visit the H2 Console to view tables:

bash
Copy
Edit
http://localhost:8080/h2-console/
🗃️ Database Info
Uses H2 in-memory database, so tables are created at runtime.

No manual DB setup required.

📌 Note
Make sure to configure the correct JDBC URL, username, and password in application.properties for the H2 console to work.

