# Student Grade Average Calculator (Java)

## Overview

The **Student Grade Average Calculator** is a console-based Java application designed to calculate and analyze student academic performance. The program accepts marks for multiple students across multiple subjects, calculates average scores, assigns letter grades, determines pass/fail status, and identifies the top performer as well as the student needing improvement.

This project was developed as part of the **Java Development Internship Program at Sysslan IT Solutions** to demonstrate practical implementation of arrays, loops, input validation, data processing, and report generation in Java.

---

## Features

* Manage multiple students and subjects
* Calculate average marks automatically
* Assign letter grades based on performance
* Display Pass/Fail status
* Generate a formatted grade report
* Identify the top-performing student
* Identify the student needing improvement
* Input validation for marks and numeric entries
* User-friendly console interface

---

## Technologies Used

* Java
* Scanner Class
* Arrays
* Loops and Conditional Statements
* Methods and Functions
* Input Validation
* Console-Based Application Development

---

## Project Structure

```text
GradeCalculator/
│
├── GradeCalculator.java
├── GradeCalculator.class
└── README.md
```

---

## How the Program Works

### Step 1: Enter Student Information

The user specifies:

* Number of students
* Number of subjects per student

### Step 2: Input Student Marks

For each student:

* Enter the student's name
* Enter marks for each subject (0–100)

### Step 3: Calculate Average

The program calculates:

Average = Total Marks ÷ Number of Subjects

### Step 4: Generate Grade Report

The application displays:

* Student Name
* Average Marks
* Letter Grade
* Pass/Fail Status

### Step 5: Performance Analysis

The program identifies:

* Top Performer
* Student Needing Improvement

---

## Grade Classification System

| Average Marks | Grade |
| ------------- | ----- |
| 90 – 100      | A+    |
| 80 – 89       | A     |
| 70 – 79       | B     |
| 60 – 69       | C     |
| 50 – 59       | D     |
| 40 – 49       | E     |
| Below 40      | F     |

---

## Pass/Fail Criteria

| Average Score | Status |
| ------------- | ------ |
| 40 and Above  | Pass   |
| Below 40      | Fail   |

---

## Sample Output

```text
==============================================
      Student Grade Average Calculator     
==============================================
How many students? 3
How many subjects per student? 4

--- Student 1 ---
Enter student name: Amit Maity
  Subject 1 marks (0 to 100): 50
  Subject 2 marks (0 to 100): 60
  Subject 3 marks (0 to 100): 65
  Subject 4 marks (0 to 100): 80

--- Student 2 ---
Enter student name: Shreemayee Raj
  Subject 1 marks (0 to 100): 60
  Subject 2 marks (0 to 100): 65
  Subject 3 marks (0 to 100): 80
  Subject 4 marks (0 to 100): 70

--- Student 3 ---
Enter student name: Bandhan Tung
  Subject 1 marks (0 to 100): 30
  Subject 2 marks (0 to 100): 50
  Subject 3 marks (0 to 100): 60
  Subject 4 marks (0 to 100): 70

==============================================
               GRADE REPORT               
==============================================
Name                 Average    Grade      Status    
----------------------------------------------
Amit Maity           63.75      C           Pass     
Shreemayee Raj       68.75      C           Pass     
Bandhan Tung         52.50      D           Pass     
==============================================

Top Performer   : Shreemayee Raj (68.75)
Needs Improvement: Bandhan Tung (52.50)
```

---

## Key Methods

### `printReport(String[] names, double[] averages)`

Generates and displays the complete grade report.

### `printTopAndBottom(String[] names, double[] averages)`

Identifies the highest and lowest-performing students.

### `getLetterGrade(double avg)`

Converts average marks into a letter grade.

### `getValidMark(Scanner scanner)`

Ensures entered marks are between 0 and 100.

### `getValidPositiveInt(Scanner scanner)`

Validates positive integer inputs.

---

## Learning Outcomes

This project helped develop skills in:

* Array Management
* Data Processing
* Student Record Handling
* Input Validation Techniques
* Conditional Logic
* Report Generation
* Method Design and Reusability
* Console Application Development

---

## Future Enhancements

Potential improvements include:

* Grade report export to CSV or Excel
* Student ranking system
* Subject-wise performance analysis
* Graphical User Interface (GUI) using Java Swing
* Database integration for record storage
* Search and update student records
* Percentage and GPA calculation

---

## How to Run

### Compile the Program

```bash
javac GradeCalculator.java
```

### Run the Program

```bash
java GradeCalculator
```

---

## Internship Project

**Organization:** Sysslan IT Solutions
**Program:** Java Development Internship

This project demonstrates practical Java programming concepts including data collection, average calculation, grade evaluation, report generation, input validation, and performance analysis.

---

## License

This project is developed for educational and internship purposes under the Java Development Internship Program at Sysslan IT Solutions.
