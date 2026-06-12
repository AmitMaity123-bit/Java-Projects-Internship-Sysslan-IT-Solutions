# Number Grid Search Application (Java)

## Overview

The **Number Grid Search Application** is a console-based Java program that allows users to create a 3×3 number matrix and search for specific values within it. The application displays the positions of matching numbers, counts occurrences, and visually highlights all matches inside the grid.

This project was developed as part of the **Java Development Internship Program at Sysslan IT Solutions** to demonstrate proficiency in arrays, loops, user input validation, matrix operations, and search algorithms.

---

## Features

* Create and populate a 3×3 number grid
* Input validation for numeric entries
* Search for any number within the grid
* Display exact row and column positions of matches
* Count multiple occurrences of the searched number
* Visual position map highlighting matching values
* Option to perform multiple searches
* User-friendly console interface

---

## Technologies Used

* Java
* Scanner Class
* Two-Dimensional Arrays (2D Arrays)
* Loops and Conditional Statements
* Input Validation Techniques
* Search Algorithms

---

## Project Structure

```text
NumberGrid/
│
├── NumberGrid.java
├── NumberGrid.class
└── README.md
```

---

## How the Program Works

### Step 1: Fill the Grid

The user enters 9 numbers to populate a 3×3 matrix.

Example:

```text
1 2 3
4 5 6
7 8 9
```

### Step 2: Display Matrix

The program displays the completed matrix in a structured format.

### Step 3: Search for a Number

The user enters a target number to search.

The application:

* Scans the entire grid
* Finds all matching occurrences
* Displays row and column locations
* Counts total matches
* Highlights matching positions

### Step 4: Continue or Exit

The user can:

* Search for another number
* Exit the application by entering `-1`

---

## Sample Output

```text
============================================
      3*3 Number Grid â?? Fill & Find  
============================================

 Enter 9 numbers to fill the 3*3 matrix:
   (row by row, press Enter after each number)

  Enter number for [Row 1][Col 1]: 9
  Enter number for [Row 1][Col 2]: 8
  Enter number for [Row 1][Col 3]: 7

  Enter number for [Row 2][Col 1]: 6
  Enter number for [Row 2][Col 2]: 5
  Enter number for [Row 2][Col 3]: 4

  Enter number for [Row 3][Col 1]: 3
  Enter number for [Row 3][Col 2]: 2
  Enter number for [Row 3][Col 3]: 1

Your 3*3 Matrix:

  ================
   9    8    7    Row 1
   6    5    4    Row 2
   3    2    1    Row 3
  =================
    Col1 Col2 Col3


Enter a number to search (or -1 to quit): 9

============================================
  Search Result for: 9
============================================
  FOUND at  Row 1, Column 1  (index [0][0])

  Total occurrences: 1

  Position map (* = match):

  ================
[ 9*]   8    7 
   6    5    4 
   3    2    1 
  =================
============================================

Search another number? (y/n): ... (and so on) ... continues until user exits with -1
```

---

## Key Methods

### `displayGrid(int[][] grid)`

Displays the matrix in a formatted structure with row and column references.

### `searchAndReport(int[][] grid, int target)`

Searches the matrix for the target value and reports:

* Position(s)
* Number of occurrences
* Visual match map

### `getValidInt(Scanner scanner)`

Ensures only valid integer inputs are accepted from the user.

---

## Learning Outcomes

This project helped in understanding:

* Two-Dimensional Arrays
* Matrix Traversal Techniques
* Nested Loops
* User Input Validation
* Search Operations
* Method Creation and Reusability
* Console-Based User Interface Design

---

## Future Enhancements

Potential improvements include:

* Support for larger matrix sizes
* Dynamic grid dimensions
* Sorting functionality
* Row and column statistics
* Graphical User Interface (GUI) using Java Swing
* Save and load grid data from files

---

## How to Run

### Compile the Program

```bash
javac NumberGrid.java
```

### Run the Program

```bash
java NumberGrid
```

---

## Internship Project

**Organization:** Sysslan IT Solutions
**Program:** Java Development Internship

This project demonstrates practical implementation of Java programming concepts including matrix handling, searching algorithms, input validation, and structured program design.

---

## License

This project is developed for educational and internship purposes under the Java Development Internship Program at Sysslan IT Solutions.
