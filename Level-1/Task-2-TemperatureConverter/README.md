# Temperature Converter (Java)

## Overview

The **Temperature Converter** is a console-based Java application that allows users to convert temperatures between **Celsius (°C)** and **Fahrenheit (°F)**. The program provides accurate temperature conversions, validates user input, and offers weather-related context based on the converted temperature.

This project was developed as part of the **Java Development Internship Program at Sysslan IT Solutions** to demonstrate the implementation of mathematical formulas, user input handling, conditional logic, and menu-driven programming in Java.

---

## Features

* Convert Celsius to Fahrenheit
* Convert Fahrenheit to Celsius
* User-friendly menu-driven interface
* Input validation for integers and decimal values
* Weather condition suggestions based on temperature
* Continuous conversion until user chooses to exit
* Clean and modular code structure

---

## Technologies Used

* Java
* Scanner Class
* Conditional Statements
* Loops
* Methods and Functions
* Input Validation
* Mathematical Calculations

---

## Project Structure

```text
TemperatureConverter/
│
├── TemperatureConverter.java
├── TemperatureConverter.class
└── README.md
```

---

## How the Program Works

### Step 1: Choose Conversion Type

The user selects one of the following options:

```text
1. Celsius to Fahrenheit
2. Fahrenheit to Celsius
3. Exit
```

### Step 2: Enter Temperature

The user enters the temperature value to be converted.

### Step 3: Perform Conversion

The program applies the appropriate conversion formula and displays the result.

### Step 4: Display Temperature Context

Based on the converted temperature, the program provides practical weather guidance such as:

* Freezing cold
* Very cold
* Cool and comfortable
* Warm and pleasant
* Hot
* Extreme heat

### Step 5: Continue or Exit

The user can continue performing conversions until selecting the Exit option.

---

## Conversion Formulas

### Celsius to Fahrenheit

F=(C\times\frac{9}{5})+32

### Fahrenheit to Celsius

C=(F-32)\times\frac{5}{9}

---

## Sample Output

```text
============================================
     Temperature Converter              
============================================

Choose conversion:
  1. Celsius to Fahrenheit
  2. Fahrenheit to Celsius
  3. Exit
Enter choice (1/2/3): 1
Enter temperature in Celsius: 50
50.00?°C  =  122.00?°F
Extreme heat! Stay indoors if possible.

Choose conversion:
  1. Celsius to Fahrenheit
  2. Fahrenheit to Celsius
  3. Exit
Enter choice (1/2/3): 2
Enter temperature in Fahrenheit: 100
100.00?°F  =  37.78?°C
Hot! Stay hydrated.

Choose conversion:
  1. Celsius to Fahrenheit
  2. Fahrenheit to Celsius
  3. Exit
Enter choice (1/2/3): 2
Enter temperature in Fahrenheit: 50
50.00?°F  =  10.00?°C
Very cold. Wear a heavy jacket.

Choose conversion:
  1. Celsius to Fahrenheit
  2. Fahrenheit to Celsius
  3. Exit
Enter choice (1/2/3): 3
Exiting the program. Goodbye!
```

---

## Temperature Categories

| Temperature (°C) | Weather Condition    |
| ---------------- | -------------------- |
| ≤ 0°C            | Freezing cold        |
| 1°C – 10°C       | Very cold            |
| 11°C – 20°C      | Cool and comfortable |
| 21°C – 30°C      | Warm and pleasant    |
| 31°C – 40°C      | Hot                  |
| > 40°C           | Extreme heat         |

---

## Key Methods

### `celsiusToFahrenheit(double celsius)`

Converts Celsius temperature to Fahrenheit.

### `fahrenheitToCelsius(double fahrenheit)`

Converts Fahrenheit temperature to Celsius.

### `printTemperatureContext(double temp, boolean isCelsius)`

Provides weather-related guidance based on the temperature value.

### `getValidInt(Scanner scanner)`

Validates menu option input.

### `getValidDouble(Scanner scanner)`

Validates temperature input.

---

## Learning Outcomes

This project helped strengthen understanding of:

* Java Programming Fundamentals
* Mathematical Formula Implementation
* Method Creation and Reusability
* User Input Validation
* Menu-Driven Applications
* Conditional Logic
* Interactive Console Applications

---

## Future Enhancements

Potential improvements include:

* Support for Kelvin conversions
* Temperature history tracking
* Graphical User Interface (GUI) using Java Swing
* Unit conversion dashboard
* Temperature statistics and reports
* File-based storage of conversion history

---

## How to Run

### Compile the Program

```bash
javac TemperatureConverter.java
```

### Execute the Program

```bash
java TemperatureConverter
```

---

## Internship Project

**Organization:** Sysslan IT Solutions
**Program:** Java Development Internship

This project demonstrates practical Java programming skills including temperature conversion formulas, input validation, conditional processing, and modular application design.

---

## License

This project is developed for educational and internship purposes under the Java Development Internship Program at Sysslan IT Solutions.
