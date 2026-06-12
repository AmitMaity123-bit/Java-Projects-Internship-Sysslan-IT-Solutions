# 📒 Task 1 – Contact Management System

> **Level 3 – OOP & Small Projects** | Java Learning Series

---

## 📌 Problem Statement

Build a fully interactive **Contact Management System** using OOP principles — separate `Contact` class with encapsulation, an `ArrayList` for in-memory storage, and CSV file persistence so contacts survive between program runs.

---

## 💡 Features

| Feature | Description |
|---|---|
| Add Contact | Name, phone (validated), email (regex), group |
| View All | Sorted A–Z by name in a formatted table |
| Search | Matches name, phone, or email (partial match) |
| Edit | Update any field; press Enter to keep current value |
| Delete | Confirmation prompt before removal |
| Filter by Group | Show only Work / Friend / Family / etc. |
| Statistics | Contact count broken down by group |
| Auto-Save | Writes to `contacts.csv` after every change |
| Auto-Load | Reads existing `contacts.csv` on startup |

---

## 🧠 OOP Concepts Demonstrated

| Concept | Where Used |
|---|---|
| **Encapsulation** | `Contact` — private fields, public getters/setters |
| **Abstraction** | `toCSV()` / `fromCSV()` hide serialisation detail |
| **Single Responsibility** | `Contact` owns data; `ContactManager` owns logic |
| **`ArrayList`** | Dynamic in-memory contact storage |
| **Stream API** | `.filter()`, `.sorted()`, `.forEach()` on list |
| **File I/O** | `BufferedReader` / `PrintWriter` for CSV read/write |

---

## 📂 File Structure

```
Task1_ContactManager/
├── Contact.java         ← Data model (OOP entity)
├── Contact.class        ← Compiled bytecode 
├── ContactManager.java  ← Main driver + all logic
├── ContactManager.class ← Compiled bytecode
├── contacts.csv         ← Auto-generated persistent storage
└── README.md            ← You are here
```

---

## ▶️ How to Run

```bash
cd Task1_ContactManager
javac Contact.java ContactManager.java
java ContactManager
```

---

## 🖥️ Sample Output

```
  ╔══════════════════════════════════════╗
  ║     📒  CONTACT MANAGEMENT SYSTEM    ║
  ╚══════════════════════════════════════╝
  Contacts loaded: 3

  ┌─────────────────────────────┐
  │  1. Add Contact             │
  │  2. View All Contacts       │
  ...

  ID    NAME                   PHONE           EMAIL                        GROUP
  ────────────────────────────────────────────────────────────────────────────────
  [  1] Amit Maity              +91-8967314945  maityamit5564@gmail.com      Family
  [  2] Shreemayee Raj          +91-9123456780  shreemayee.raj@gmail.com     Friend
```

---

## 📄 CSV Storage Format

```csv
1,Amit Maity,+91-8967314945,maityamit5564@gmail.com,Family
2,Shreemayee Raj,+91-9123456780,shreemayee.raj@gmail.com,Friend
```

---

## 🔍 Validation Rules

| Field | Rule |
|---|---|
| Name | Non-empty string |
| Phone | 7–15 characters, digits + `+`, `-`, `()`, spaces |
| Email | Must match standard email regex pattern |
| Group | Non-empty string (defaults to "General") |

---

## 🚀 Extensions

- [ ] Export contacts to `.vcf` (vCard format)
- [ ] Sort by any column
- [ ] Import from CSV / Google Contacts export
- [ ] Add birthday reminders

---

## 👨‍💻 Author

**[Amit Kumar Maity]** | Java Learner — Level 3  
📅11 June 2026

---
*Part of the [Java Learning Series – Level 3](../README.md)*