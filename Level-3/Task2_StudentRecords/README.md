# 🎓 Task 2 – Student Record System with File Persistence

> **Level 3 – OOP & Small Projects** | Java Learning Series

---

## 📌 Problem Statement

Build a fully OOP-designed **Student Record System** that stores marks across multiple subjects, calculates averages and grades automatically, and persists all data to disk so records survive between program sessions.

---

## 💡 Features

| Feature | Detail |
|---|---|
| Add Student | Name, roll number (unique), marks per subject |
| View All | Summary table: ID, Roll, Name, Avg, Grade, Status |
| Full Report | Sorted by average descending + class average |
| Search | By name or roll number (partial match) |
| Edit | Update name, roll, or re-enter all marks |
| Delete | With confirmation prompt |
| Top & Bottom | Best performer, student needing attention, fail count |
| Subject Analysis | Per-subject avg / min / max across all students |
| File Persistence | Auto-saved to `students.dat` on every change |

---

## 🧠 OOP Concepts Demonstrated

| Concept | Implementation |
|---|---|
| **Encapsulation** | `Student` — private fields, getters/setters |
| **Computed methods** | `getAverage()`, `getGrade()`, `getStatus()` inside the class |
| **Serialisation** | `toCSV()` and `fromCSV()` as class responsibilities |
| **ArrayList** | Dynamic list of `Student` objects |
| **Stream API** | `sorted()`, `filter()`, `mapToDouble()`, `summaryStatistics()` |
| **File I/O** | `BufferedReader` / `PrintWriter` read-write cycle |

---

## 📂 File Structure

```
Task2_StudentRecords/
├── Student.java              ← OOP data model
├── Student.class             ← Compiled bytecode
├── StudentRecordSystem.java  ← Main driver
├── StudentRecordSystem.class ← Compiled bytecode
├── students.dat              ← Auto-generated persistent file
└── README.md                 ← You are here
```

---

## ▶️ How to Run

```bash
cd Task2_StudentRecords
javac Student.java StudentRecordSystem.java
java StudentRecordSystem
```

---

## 🖥️ Sample Output

```
  ╔═══════════════════════════════════════════╗
  ║     🎓  STUDENT RECORD SYSTEM              ║
  ╚═══════════════════════════════════════════╝
  Students on record: 0
  Subjects tracked  : Maths, English, Science, History, CS

  ── Add New Student ──────────────────
  Full Name   : Priya Sharma
  Roll Number : CS2026-01
  Enter marks (0–100) for each subject:
    Maths   : 88
    English : 76
    ...
  ✅  Student added. Average: 81.40  Grade: A

  ID    ROLL        NAME                   AVG      GRADE  STATUS
  ──────────────────────────────────────────────────────────────
  [  1] CS2026-01   Priya Sharma            81.40   A      PASS
```

---

## 📄 Persistence Format (students.dat)

```
1|Priya Sharma|CS2026-01|Maths,English,Science,History,CS|88.0,76.0,85.0,79.0,93.0
```

---

## 🔍 Grading Scale

| Average | Grade | Status |
|---|---|---|
| ≥ 90 | A+ | PASS |
| ≥ 80 | A  | PASS |
| ≥ 70 | B  | PASS |
| ≥ 60 | C  | PASS |
| ≥ 50 | D  | PASS |
| ≥ 40 | E  | PASS |
| < 40 | F  | FAIL |

---

## 🚀 Extensions

- [ ] Custom subject list per session
- [ ] Export full report to a PDF
- [ ] Add semester tracking
- [ ] Generate rank list with position numbers

---

## 👨‍💻 Author

**[Amit Kumar Maity]** | Java Learner — Level 3  
📅21 June 2026

---
*Part of the [Java Learning Series – Level 3](../README.md)*