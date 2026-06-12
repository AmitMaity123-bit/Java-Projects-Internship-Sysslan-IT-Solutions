# Secure Password Generator (Java)

## Overview

The **Secure Password Generator** is a console-based Java application that generates strong and customizable passwords based on user preferences. Users can choose password length, character types, and whether to exclude ambiguous characters. The application generates multiple secure password suggestions and evaluates their strength.

This project was developed as part of the **Java Development Internship Program at Sysslan IT Solutions** to demonstrate practical implementation of randomization, string manipulation, input validation, and security-focused programming concepts in Java.

---

## Features

* Generate secure random passwords
* Customizable password length (8–64 characters)
* Include or exclude:

  * Lowercase letters
  * Uppercase letters
  * Numbers
  * Special characters
* Option to exclude ambiguous characters (`0`, `O`, `1`, `l`, `I`)
* Generate multiple password suggestions
* Password strength evaluation
* User-friendly console interface
* Input validation for password length

---

## Technologies Used

* Java
* Scanner Class
* Random Class
* String Manipulation
* Conditional Logic
* Input Validation
* Security-Oriented Programming

---

## Project Structure

```text
PasswordGenerator/
│
├── PasswordGenerator.java
├── PasswordGenerator.class
└── README.md
```

---

## How the Program Works

### Step 1: Choose Password Length

The user enters a password length between **8 and 64 characters**.

### Step 2: Select Character Types

The user chooses whether to include:

* Lowercase letters (a-z)
* Uppercase letters (A-Z)
* Digits (0-9)
* Special characters (!, @, #, $, etc.)

### Step 3: Exclude Ambiguous Characters (Optional)

The user can remove commonly confusing characters:

```text
0, O, 1, l, I
```

This improves readability and reduces typing mistakes.

### Step 4: Generate Passwords

The application generates **five unique password suggestions** using random character selection.

### Step 5: Strength Analysis

Each generated password is evaluated and labeled as:

* Weak
* Fair
* Good
* Strong
* Very Strong

---

## Character Sets Used

### Lowercase Letters

```text
abcdefghijklmnopqrstuvwxyz
```

### Uppercase Letters

```text
ABCDEFGHIJKLMNOPQRSTUVWXYZ
```

### Digits

```text
0123456789
```

### Special Characters

```text
!@#$%^&*()-_=+[]{}|;:,.<>?
```

---

## Password Strength Criteria

The strength score is calculated based on:

| Criteria                    | Points |
| --------------------------- | ------ |
| Length ≥ 12 characters      | +1     |
| Contains lowercase letters  | +1     |
| Contains uppercase letters  | +1     |
| Contains digits             | +1     |
| Contains special characters | +1     |

### Strength Levels

| Score | Strength    |
| ----- | ----------- |
| 0–1   | Weak        |
| 2     | Fair        |
| 3     | Good        |
| 4     | Strong      |
| 5     | Very Strong |

---

## Sample Output

```text
==============================================
       Secure Password Generator
==============================================

Enter desired password length (8 to 64): 12

Include lowercase letters? (y/n): y
Include uppercase letters? (y/n): y
Include digits? (y/n): y
Include special characters? (y/n): y
Exclude ambiguous characters (0,O,l,1,I)? (y/n): y

==============================================
          Generated Passwords
==============================================

1. aX8@Pq7#Lm$2    Very Strong
2. Hf7!Zw9$Kr@5    Very Strong
3. Mx4#Tg8&Qn@6    Very Strong
4. Bk9$Vp3!Ys@7    Very Strong
5. Rt6@Nm8#Xq$4    Very Strong

==============================================
Tip: Use a password manager to store your passwords safely.
```

---

## Key Methods

### `generatePassword(String pool, int length, Random random)`

Generates a random password using the selected character pool.

### `removeAmbiguous(String pool)`

Removes confusing characters such as:

```text
0, O, 1, l, I
```

### `calculateStrength(String password)`

Calculates a password strength score based on length and character diversity.

### `getStrengthLabel(int score)`

Returns a human-readable strength label.

### `getValidLength(Scanner scanner)`

Ensures password length is between 8 and 64 characters.

### `askYesNo(Scanner scanner, String prompt)`

Handles user preference selection for password options.

---

## Learning Outcomes

This project helped develop knowledge and skills in:

* Random Password Generation
* String Processing
* Security Best Practices
* Input Validation
* User Interaction Design
* Method Reusability
* Conditional Logic
* Console-Based Application Development

---

## Future Enhancements

Potential improvements include:

* Guaranteed inclusion of selected character types
* Password copy-to-clipboard functionality
* Password history tracking
* Save passwords to encrypted storage
* Graphical User Interface (GUI) using Java Swing
* Password entropy calculation
* SecureRandom implementation for enhanced security

---

## How to Run

### Compile the Program

```bash
javac PasswordGenerator.java
```

### Run the Program

```bash
java PasswordGenerator
```

---

## Internship Project

**Organization:** Sysslan IT Solutions
**Program:** Java Development Internship

This project demonstrates practical Java programming concepts including randomization, password security, string manipulation, user input validation, and application design.

---

## License

This project is developed for educational and internship purposes under the Java Development Internship Program at Sysslan IT Solutions.
