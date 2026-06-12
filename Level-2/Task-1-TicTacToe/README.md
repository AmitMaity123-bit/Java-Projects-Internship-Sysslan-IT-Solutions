# Tic Tac Toe Game (Java)

## Overview

This project is a simple **Tic Tac Toe** game developed in **Java** using a console-based interface. It allows two players to play alternately by selecting positions on a 3×3 board. The game automatically detects wins, draws, and invalid moves.

This project was developed as part of the internship program at **Sysslan IT Solutions** to demonstrate core Java programming concepts including arrays, loops, methods, conditional statements, and user input handling.

---

## Features

* Two-player gameplay (Player X and Player O)
* Interactive console-based interface
* Input validation for invalid moves
* Automatic winner detection
* Draw game detection
* Clean and simple code structure

---

## Technologies Used

* Java
* Scanner Class for user input
* 2D Arrays
* Object-Oriented Programming Concepts

---

## Project Structure

```text
TicTacToe/
│
├── TicTacToe.java
├── TicTacToe.class
└── README.md
```

---

## How the Game Works

1. The board is initialized with positions numbered from **1 to 9**.
2. Player **X** starts the game.
3. Players take turns entering a position number.
4. The selected position is replaced with the player's symbol (**X** or **O**).
5. The game checks for:

   * Winning combinations (rows, columns, diagonals)
   * Draw condition when all cells are filled
6. The game ends when a player wins or the match is drawn.

---

## Board Layout

```text
1 | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9
```

---

## Sample Output

```text
1 | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9

Player X choose position (1-9): 10
Invalid move. Try again.

1 | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9

Player X choose position (1-9): 1

X | 2 | 3
--+---+--
4 | 5 | 6
--+---+--
7 | 8 | 9

Player O choose position (1-9): 5

X | 2 | 3
--+---+--
4 | O | 6
--+---+--
7 | 8 | 9

Player X choose position (1-9): 4

X | 2 | 3
--+---+--
X | O | 6
--+---+--
7 | 8 | 9

Player O choose position (1-9): 7

X | 2 | 3
--+---+--
X | O | 6
--+---+--
O | 8 | 9

Player X choose position (1-9): 2

X | X | 3
--+---+--
X | O | 6
--+---+--
O | 8 | 9

Player O choose position (1-9): 3

X | X | O
--+---+--
X | O | 6
--+---+--
O | 8 | 9

Player O wins!
```

---

## Winning Conditions

A player wins if they successfully place three identical symbols in:

* Any row
* Any column
* Main diagonal
* Secondary diagonal

Example:

```text
X | X | X
--+---+--
O | O | 6
--+---+--
7 | 8 | 9

or

X | X | O
--+---+--
X | O | 6
--+---+--
O | 8 | 9

Player O wins!

```

---

## How to Run

### Compile the Program

```bash
javac TicTacToe.java
```

### Execute the Program

```bash
java TicTacToe
```

---

## Learning Outcomes

Through this project, the following Java concepts were practiced:

* Arrays and 2D Arrays
* Methods and Modular Programming
* Conditional Statements
* Loops
* User Input Handling
* Game Logic Implementation
* Problem Solving

---

## Future Enhancements

* Single-player mode with AI
* Graphical User Interface (GUI) using Java Swing
* Score tracking system
* Restart game option
* Multiplayer over network

---

## Author

**Internship Project - Sysslan IT Solutions**

Developed as part of the Java Development Internship Program.

---

## License

This project is created for educational and internship purposes.
