# 📝 Task 4 – Timestamped Log File Generator

> **Level 3 – OOP & Small Projects** | Java Learning Series

---

## 📌 Problem Statement

Build a **production-style log file generator** that lets users write structured log entries manually, simulate real application events, filter and search through logs, and automatically persist everything to timestamped `.log` and `.csv` files — mirroring how real Java applications use logging frameworks.

---

## 🏗️ Architecture — 3-Class Design

```
LogGeneratorApp.java     ← Main driver, UI menus, all user interactions
    │
    ├── LogManager.java  ← Manages log collection, file writes, rotation
    │
    └── LogEntry.java    ← Single log event — timestamp, level, message
```

---

## 💡 Full Feature List

| Feature | Detail |
|---|---|
| **Manual Entry** | Pick level + category from menu, enter source & message |
| **Batch Logging** | Log multiple entries using pipe-separated format |
| **Simulate Events** | 21 pre-built events: DB pools, auth, payment, CRITICAL failures |
| **View All Logs** | Full table: timestamp, level, category, source, message |
| **Filter by Level** | Show only INFO / WARN / ERROR / DEBUG / CRITICAL |
| **Filter by Category** | Show only AUTH / DATABASE / NETWORK / etc. |
| **Last N Entries** | View most recent N log lines |
| **Keyword Search** | Full-text search across message, category, source |
| **Stats Dashboard** | Count per level, total entries, active file name |
| **Export to CSV** | Structured CSV backup with column headers |
| **Auto-Save on Exit** | Always writes final state on shutdown |
| **Log Rotation** | New .log file auto-created if current exceeds 50 KB |

---

## 🧠 OOP Concepts Demonstrated

| Concept | Where |
|---|---|
| **Encapsulation** | `LogEntry` — all fields private, controlled via getters |
| **Abstraction** | `LogManager.log()` hides all file I/O complexity |
| **Single Responsibility** | Each class has one job: entry, manager, UI |
| **Constants as API** | `LogEntry.INFO`, `LogEntry.ERROR` etc. |
| **Method overloading** | `info()`, `warn()`, `error()` delegate to `log()` |
| **`LocalDateTime`** | Precise auto-timestamping of every entry |
| **Stream API** | `.filter()`, `.count()`, `.toList()` on log collections |
| **File I/O** | Append-mode writing, rotation logic, CSV export |

---

## 📂 File Structure

```
Task4_LogGenerator/
├── LogEntry.java         ← Single log event model
├── LogManager.java       ← Collection + file management
├── LogGeneratorApp.java  ← Main driver + UI
├── logs/                 ← Auto-created on first run
│   ├── app_20260612_143022.log   ← Human-readable log
│   └── app_20260612_143022.csv   ← Structured backup
└── README.md             ← You are here
```

---

## ▶️ How to Run

```bash
cd Task4_LogGenerator
javac LogEntry.java LogManager.java LogGeneratorApp.java
java LogGeneratorApp
```

**Requirements:** Java JDK 11+ (`List.toList()` needs Java 16; use `.collect(Collectors.toList())` for older JDK)

---

## 🖥️ Sample Output

### Console Menu
```
  ╔═══════════════════════════════════════════╗
  ║   📝  TIMESTAMPED LOG FILE GENERATOR       ║
  ╠═══════════════════════════════════════════╣
  ║  Log file → logs/app_<timestamp>.log      ║
  ╚═══════════════════════════════════════════╝

  ┌──────────────────────────────────────┐
  │  1. Write a Log Entry (Manual)       │
  │  2. Batch Log (Multiple Entries)     │
  │  3. Simulate App Events              │
  │  4. View All Logs                    │
  ...
```

### Simulated Events Sample
```
   +INFO      [SYSTEM]    JVM initialized, heap: 256MB
   +INFO      [DATABASE]  Connection pool created (size=10)
   +WARN      [CACHE]     Cache hit ratio below threshold: 42%
   +ERROR     [PAYMENT]   Payment gateway timeout after 5000ms
   +CRITICAL  [DATABASE]  All connections exhausted! Pool size: 0/10
```

### Generated .log File
```
[2026-06-12T14:30:22] [INFO    ] [SYSTEM      ] [LogGeneratorApp] Application started
[2026-06-12T14:30:45] [ERROR   ] [PAYMENT     ] [PayService     ] Payment gateway timeout
[2026-06-12T14:30:46] [CRITICAL] [DATABASE    ] [DBPool         ] All connections exhausted!
```

### Generated .csv File
```csv
timestamp§level§category§source§message
2026-06-12T14:30:22§INFO§SYSTEM§LogGeneratorApp§Application started
2026-06-12T14:30:45§ERROR§PAYMENT§PayService§Payment gateway timeout
```

---

## 🔢 Batch Log Format

When using **Batch Log**, enter one entry per line in this format:
```
ERROR | DATABASE | UserService | Connection timeout after 5000ms
WARN  | CACHE    | CacheManager | Low hit ratio detected
INFO  | AUTH     | LoginController | User login successful
```

---

## 📊 Log Levels

| Level | Meaning | Use When |
|---|---|---|
| `INFO` | Normal operation | App started, user logged in, task completed |
| `DEBUG` | Developer details | Query times, variable values during dev |
| `WARN` | Potential problem | Slow responses, low resources, retries |
| `ERROR` | Something failed | Exception caught, timeout, invalid state |
| `CRITICAL` | System in danger | DB down, all connections gone, data loss risk |

---

## 🚀 Extensions

- [ ] Add automatic log tailing (watch new entries in real time)
- [ ] Email alert when CRITICAL log is written
- [ ] Connect to a real logging framework (Log4j, SLF4J)
- [ ] Parse .log files and generate visual HTML reports
- [ ] Add thread-safe logging for multi-threaded apps

---

## 👨‍💻 Author

**[Amit Kumar Maity]** | Java Learner — Level 3  
📅31 June 2026

---
*Part of the [Java Learning Series – Level 3](../README.md)*