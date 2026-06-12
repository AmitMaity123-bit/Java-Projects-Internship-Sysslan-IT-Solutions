# ☕ Java Learning Series – Level 3: OOP & Small Projects

> *"Good code is not written — it's designed."*

---

## 🎯 About This Level

Level 3 is where Java stops feeling like syntax exercises and starts feeling like **real software engineering**.

Every task here involves at least 2 classes, data that persists to disk, real-world domain logic, and systems that someone could actually use — a librarian, a student, a developer. This is the level where OOP principles stop being concepts on paper and become muscle memory.

---

## 📂 Project Structure

```
java-level3/
├── Task1_ContactManager/
│   ├── Contact.java
│   ├── ContactManager.java
│   └── README.md
│
├── Task2_StudentRecords/
│   ├── Student.java
│   ├── StudentRecordSystem.java
│   └── README.md
│
├── Task3_LibrarySystem/
│   ├── Book.java
│   ├── Member.java
│   ├── LibrarySystem.java
│   └── README.md
│
├── Task4_LogGenerator/
│   ├── LogEntry.java
│   ├── LogManager.java
│   ├── LogGeneratorApp.java
│   └── README.md
│
└── README.md  ← You are here
```

---

## 📋 Tasks Overview

| # | Task | Classes | Key Concepts | Complexity |
|---|------|---------|--------------|------------|
| 1 | [Contact Manager](./Task1_ContactManager/) | Contact, ContactManager | Encapsulation, ArrayList, CSV I/O | ⭐⭐ |
| 2 | [Student Records](./Task2_StudentRecords/) | Student, StudentRecordSystem | Computed fields, Stream, File I/O | ⭐⭐ |
| 3 | [Library System](./Task3_LibrarySystem/) | Book, Member, BorrowRecord, LibrarySystem | Composition, Inner class, Multi-file I/O | ⭐⭐⭐ |
| 4 | [Log Generator](./Task4_LogGenerator/) | LogEntry, LogManager, LogGeneratorApp | SRP, LocalDateTime, Append I/O, Rotation | ⭐⭐⭐ |

---

## 🧠 OOP Skills Mastered in Level 3

| OOP Principle | Task(s) | How It's Applied |
|---|---|---|
| **Encapsulation** | All | Private fields + getters/setters in every model class |
| **Abstraction** | T3, T4 | `borrow()`, `log()` hide complex logic behind simple methods |
| **Composition** | T3 | `Member` has-a list of `BorrowRecord` (inner class) |
| **Single Responsibility** | T4 | `LogEntry` = data, `LogManager` = I/O, App = UI |
| **Data Persistence** | All | CSV/pipe-delimited files read on startup, saved on change |
| **Collections** | All | `ArrayList`, `Stream API`, `Map`, sorted views |
| **`LocalDateTime`** | T4 | Auto-timestamping without external libraries |
| **Method Delegation** | T4 | `info()` → `warn()` → `error()` all delegate to `log()` |

---

## ▶️ How to Run Any Task

```bash
# Navigate to the task
cd Task3_LibrarySystem

# Compile all Java files in the folder
javac *.java

# Run the main class
java LibrarySystem
```

| Task | Main Class |
|---|---|
| Task 1 | `java ContactManager` |
| Task 2 | `java StudentRecordSystem` |
| Task 3 | `java LibrarySystem` |
| Task 4 | `java LogGeneratorApp` |

**Requirements:** Java JDK 11+ (JDK 16+ recommended for `List.toList()`)

---

## 📁 Auto-Generated Files

Each task creates data files automatically — no setup needed:

| Task | File(s) Created |
|---|---|
| Contact Manager | `contacts.csv` |
| Student Records | `students.dat` |
| Library System | `books.dat`, `members.dat` |
| Log Generator | `logs/app_<timestamp>.log`, `logs/app_<timestamp>.csv` |

---

## 🗺️ Learning Roadmap

```
Level 1 ✅  →  Core Java (arrays, loops, conditionals)
Level 2 ✅  →  Intermediate (file I/O, validation, console games)
Level 3 ✅  →  OOP & Projects (classes, collections, persistence)
Level 4 ⏳  →  Exception Handling, Generics, Interfaces
Level 5 ⏳  →  Full Applications (REST APIs, database, GUI)
```

---

## 🤝 Connect With Me

- 💼 [LinkedIn](https://www.linkedin.com/in/amit-kumar-maity8976/)
- 🐙 [GitHub](https://github.com/AmitMaity123-bit)

📅 Completed:31 June 2026