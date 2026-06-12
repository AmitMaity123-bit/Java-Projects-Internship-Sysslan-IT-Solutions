# 📚 Task 3 – Console-Based Library Management System

> **Level 3 – OOP & Small Projects** | Java Learning Series

---

## 📌 Problem Statement

Build a **complete library management system** using OOP principles — separate `Book` and `Member` classes with encapsulation, an inner `BorrowRecord` class for transaction history, real borrow/return logic that tracks availability, and full file persistence so the library's state survives between program runs.

---

## 🏗️ Architecture — 3 Classes, 1 System

```
LibrarySystem.java     ← Main driver, menus, file I/O, all operations
    │
    ├── Book.java      ← Encapsulates book entity + borrow/return state
    │
    └── Member.java    ← Encapsulates member entity
            └── BorrowRecord (inner class) ← Single borrow transaction
```

---

## 💡 Full Feature List

### 📖 Book Management
| Operation | Detail |
|---|---|
| Add Book | ISBN, title, author, genre, year, copy count |
| View All | Sorted A–Z, shows availability per title |
| Search | Matches title / author / genre / ISBN |
| Edit | Update any field; Enter to keep current |
| Delete | Blocked if copies are currently borrowed |
| View Detail | Full info card for one book |

### 👤 Member Management
| Operation | Detail |
|---|---|
| Register | Name, email (validated), phone (validated) |
| View All | Sorted A–Z, shows active borrow count |
| Search | By name or email |
| Edit | Update name, email, phone |
| Borrow History | Full transaction log per member |

### 🔄 Borrow & Return
| Rule | Detail |
|---|---|
| Issue Book | Checks availability, max 3 borrows/member |
| Accept Return | Updates member record + book availability |
| Active Borrows | Lists all currently borrowed books across all members |
| Duplicate Guard | Cannot borrow same book twice without returning |

### 📊 Reports
| Report | Detail |
|---|---|
| Dashboard | Total titles, copies, members, active borrows |
| Books by Genre | Count grouped by genre category |
| Most Borrowed | Top 10 books sorted by borrow count |

---

## 🧠 OOP Concepts Demonstrated

| Concept | Where |
|---|---|
| **Encapsulation** | `Book` + `Member` — private fields, getters/setters |
| **Abstraction** | `borrow()` / `returnBook()` hide availability logic |
| **Composition** | `Member` *has-a* list of `BorrowRecord` |
| **Inner Class** | `Member.BorrowRecord` belongs exclusively to `Member` |
| **Overloaded Constructor** | `Book` has 2 constructors (new vs loaded) |
| **ArrayList + Stream** | Filter, sort, statistics on collections |
| **File I/O** | Pipe-delimited persistence, auto-save on every change |

---

## 📂 File Structure

```
Task3_LibrarySystem/
├── Book.java           ← Book entity (OOP model)
├── Member.java         ← Member entity + inner BorrowRecord class
├── LibrarySystem.java  ← Main driver, menus, file I/O
├── books.dat           ← Auto-generated book persistence file
├── members.dat         ← Auto-generated member persistence file
└── README.md           ← You are here
```

---

## ▶️ How to Run

```bash
cd Task3_LibrarySystem
javac Book.java Member.java LibrarySystem.java
java LibrarySystem
```

**Requirements:** Java JDK 11+ (uses `List.toList()` from Java 16; use `collect(Collectors.toList())` for older JDK)

---

## 🖥️ Sample Session

```
  ╔══════════════════════════════════════════╗
  ║   📚  CONSOLE LIBRARY MANAGEMENT SYSTEM  ║
  ╚══════════════════════════════════════════╝
  Books   : 5 titles loaded
  Members : 3 members loaded

  ╔══════════════════════════════════╗
  ║         MAIN MENU                ║
  ╠══════════════════════════════════╣
  ║  1. Book Management              ║
  ║  2. Member Management            ║
  ║  3. Borrow & Return              ║
  ║  4. Reports & Statistics         ║
  ║  5. Exit                         ║
  ╚══════════════════════════════════╝

  ── Issue Book (Borrow) ─────────────
  Member ID : 1
  Book ID   : 3
  ✅  "Clean Code" issued to Rahul Sen on 2026-06-12
  Copies remaining: 1
```

---

## 💾 Persistence File Formats

**books.dat** (pipe-delimited):
```
1|978-0132350884|Clean Code|Robert C. Martin|Programming|2008|2|1
```

**members.dat** (pipe-delimited, borrows semicolon-separated):
```
1|Rahul Sen|rahul@email.com|+91-9876543210|3~Clean Code~2026-06-12~ACTIVE
```

---

## 🚀 Extensions

- [ ] Overdue detection (compare borrow date vs today)
- [ ] Fine calculation (₹ per day overdue)
- [ ] Book reservation queue when unavailable
- [ ] Export member history to text/PDF report
- [ ] ISBN API lookup to auto-fill book details

---

## 👨‍💻 Author

**[Amit Kumar Maity]** | Java Learner — Level 3  
📅23 June 2026

---
*Part of the [Java Learning Series – Level 3](../README.md)*