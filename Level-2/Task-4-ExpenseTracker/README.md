# Personal Expense Tracker (Java)

## Overview

This project is a **Personal Expense Tracker** application developed in Java as part of the internship program at **Sysslan IT Solutions**. It allows users to record, view, and calculate their expenses using simple file handling operations.

The project demonstrates real-world usage of Java concepts such as file handling, exception handling, loops, and modular programming.

---

## Features

* Add new expenses with name and amount
* Store expenses in a file (`expenses.txt`)
* View all saved expenses
* Calculate total expenses
* Persistent data storage using file handling
* Simple console-based menu system

---

## Technologies Used

* Java
* File Handling (BufferedReader, BufferedWriter)
* FileReader & FileWriter
* Scanner Class
* Exception Handling
* Array and String Manipulation

---

## How It Works

The program stores all expenses in a text file in the format:

```
ExpenseName,Amount
```

Example:

```
Food,250.0
Transport,100.0
Shopping,500.0
```

The system performs three main operations:

### 1. Add Expense

* Takes user input for expense name and amount
* Appends data to `expenses.txt`

### 2. View Expenses

* Reads and displays all stored expenses

### 3. Calculate Total

* Reads all amounts from file
* Calculates and displays total expense

---

## Menu Options

```
===== PERSONAL EXPENSE TRACKER =====
1. Add Expense
2. View Expenses
3. Calculate Total
4. Exit
```

---

## File Structure

```
ExpenseTracker/
│
├── ExpenseTracker.java
├── ExpenseTracker.class
├── expenses.txt
└── README.md
```

---

## Sample Output

### Adding Expense

```
Enter Expense Name: Food
Enter Amount: 200
Expense Added Successfully!
```

### Viewing Expenses

```
===== EXPENSE LIST =====
Expense: Food | Amount: 200.0
Expense: Travel | Amount: 150.0
```

### Total Calculation

```
Total Expense: 350.0
```

---

## How to Run

### Step 1: Compile the Program

```bash
javac ExpenseTracker.java
```

### Step 2: Run the Program

```bash
java ExpenseTracker
```

---

## Key Concepts Learned

* File handling in Java
* Persistent data storage
* Exception handling
* Menu-driven programming
* Input validation
* Real-world application development

---

## Future Enhancements

* Delete or update expenses
* Monthly/weekly expense reports
* Graphical User Interface (GUI)
* Database integration (MySQL)
* Export reports as PDF or Excel
* Login system for multiple users

---

## Author

**Internship Project - Sysslan IT Solutions**

Developed as part of Java Development Internship Program.

---

## License

This project is created for educational and internship purposes only.
