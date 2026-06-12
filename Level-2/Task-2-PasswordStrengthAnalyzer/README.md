# Password Strength Analyzer (Java)

## Overview

This project is a **Password Strength Analyzer** developed in Java as part of an internship program at **Sysslan IT Solutions**. The application evaluates the strength of a user-entered password based on multiple security criteria such as length, uppercase letters, lowercase letters, digits, and special characters.

---

## Features

* Accepts password input from the user
* Checks for:

  * Minimum length requirement
  * Presence of uppercase letters
  * Presence of lowercase letters
  * Presence of numeric digits
  * Presence of special characters
* Generates a strength score
* Classifies password strength as:

  * WEAK
  * MEDIUM
  * STRONG
* Simple console-based interface

---

## Technologies Used

* Java
* Scanner Class (for user input)
* Character Class (for validation checks)
* Conditional Logic
* Loops

---

## How It Works

1. User enters a password.
2. The program scans each character in the password.
3. It checks for:

   * Uppercase letters
   * Lowercase letters
   * Digits
   * Special characters
4. A score is calculated based on security conditions.
5. Final password strength is displayed.

---

## Password Strength Criteria

| Criteria                   | Points |
| -------------------------- | ------ |
| Length ≥ 8 characters      | +1     |
| Contains uppercase letter  | +1     |
| Contains lowercase letter  | +1     |
| Contains digit             | +1     |
| Contains special character | +1     |

---

## Strength Classification

* **0 - 2 points** → WEAK
* **3 - 4 points** → MEDIUM
* **5 points** → STRONG

---

## Sample Output

```text id="pw1"
Enter Password: Hello@123

===== ANALYSIS RESULT =====
Length >= 8 : true
Uppercase   : true
Lowercase   : true
Number      : true
Special Char: true

Password Strength: STRONG
```

---

## How to Run

### Step 1: Compile the Program

```bash id="pw2"
javac PasswordStrengthAnalyzer.java
```

### Step 2: Run the Program

```bash id="pw3"
java PasswordStrengthAnalyzer
```

---

## Learning Outcomes

This project helped in understanding:

* String handling in Java
* Character classification methods
* Conditional logic building
* Looping through strings
* Basic security principles for password validation
* Real-world application of Java fundamentals

---

## Future Enhancements

* GUI version using Java Swing/JavaFX
* Password suggestion generator
* Real-time strength meter
* Regex-based validation improvements
* Integration into login systems

---

## Author

**Internship Project - Sysslan IT Solutions**

Developed as part of Java Development Internship Program.

---

## License

This project is created for educational and internship purposes.
