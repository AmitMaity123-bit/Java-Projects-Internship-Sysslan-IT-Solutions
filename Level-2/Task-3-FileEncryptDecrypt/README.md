# File Encryption & Decryption (Java)

## Overview

This project is a simple **File Encryption and Decryption system** built using Java. It demonstrates how file handling and basic cryptography concepts can be implemented using a **Caesar Cipher-style shifting technique**.

This project is developed as part of the internship program at **Sysslan IT Solutions** to strengthen understanding of file I/O operations and basic encryption logic in Java.

---

## Features

* Reads content from an input file
* Encrypts file content using character shifting
* Saves encrypted data into a separate file
* Decrypts the encrypted content back to original form
* Writes decrypted output into another file
* Simple and beginner-friendly implementation

---

## Technologies Used

* Java
* File Handling (BufferedReader, BufferedWriter)
* FileReader & FileWriter
* String Manipulation
* Basic Encryption Logic (Caesar Cipher concept)

---

## How It Works

The program uses a **fixed shift value of 3**:

* Each character in the input file is shifted by +3 for encryption
* The same shift is reversed (-3) for decryption

Example:

```
A → D
B → E
C → F
```

---

## Project Workflow

1. Read content from `input.txt`
2. Encrypt the content using shift logic
3. Save encrypted data to `encrypted.txt`
4. Decrypt the encrypted content
5. Save decrypted data to `decrypted.txt`
6. Display success messages

---

## File Structure

```
FileEncryptDecrypt/
│
├── FileEncryptDecrypt.java
├── FileEncryptDecrypt.class
├── input.txt
├── encrypted.txt
├── decrypted.txt
└── README.md
```

---

## Sample Input

**input.txt**

```
Hello World
Java Programming is fun
```

---

## Sample Output

**encrypted.txt**

```
Khoor Zruog
Mdyd Surjudpplqj lv ixq
```

**decrypted.txt**

```
Hello World
Java Programming is fun
```

---

## How to Run

### Step 1: Compile the Program

```bash
javac FileEncryptDecrypt.java
```

### Step 2: Run the Program

```bash
java FileEncryptDecrypt
```

---

## Key Concepts Learned

* File handling in Java
* Reading and writing files
* Exception handling (`IOException`)
* String manipulation
* Basic encryption and decryption logic
* Caesar Cipher concept

---

## Future Improvements

* User-defined encryption key
* Strong encryption using AES algorithm
* GUI-based file encryption tool
* Drag-and-drop file support
* Password-protected encryption system

---

## Author

**Internship Project - Sysslan IT Solutions**

Developed as part of Java Development Internship Program.

---

## License

This project is intended for educational and internship purposes only.
