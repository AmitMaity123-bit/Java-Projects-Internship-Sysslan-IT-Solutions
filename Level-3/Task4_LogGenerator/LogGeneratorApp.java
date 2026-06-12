import java.util.*;
import java.io.*;

public class LogGeneratorApp {

    static LogManager manager = new LogManager();
    static Scanner    sc      = new Scanner(System.in);

    static final String[] LEVELS = {
        LogEntry.INFO, LogEntry.WARN,
        LogEntry.ERROR, LogEntry.DEBUG, LogEntry.CRITICAL
    };

    static final String[] CATEGORIES = {
        "AUTH", "DATABASE", "NETWORK", "UI", "PAYMENT",
        "FILE", "SCHEDULER", "CACHE", "API", "SYSTEM"
    };

    public static void main(String[] args) {
        printBanner();
        // Log startup event automatically
        manager.info("SYSTEM", "Application started", "LogGeneratorApp");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Choose: ", 1, 9);
            switch (choice) {
                case 1 -> manualLog();
                case 2 -> batchLog();
                case 3 -> simulateEvents();
                case 4 -> viewLogs();
                case 5 -> filterLogs();
                case 6 -> searchLogs();
                case 7 -> dashboard();
                case 8 -> exportAndSave();
                case 9 -> running = false;
            }
        }

        // Log shutdown and export on exit
        manager.info("SYSTEM", "Application shutdown initiated", "LogGeneratorApp");
        manager.exportCSV();
        System.out.println("\n    Log file  : " + manager.getCurrentLogFile());
        System.out.println("    CSV backup: " + manager.getCurrentCsvFile());
        System.out.println("  Total entries logged: " + manager.totalEntries());
        System.out.println("  Goodbye!\n");
        sc.close();
    }

    //      Banner 
    static void printBanner() {
        System.out.println();
        System.out.println("  --------------------------------------------");
        System.out.println("  |     TIMESTAMPED LOG FILE GENERATOR       |");
        System.out.println("  --------------------------------------------");
        System.out.println("  |  Log file → logs/app_<timestamp>.log      |");
        System.out.println("  |  CSV file → logs/app_<timestamp>.csv      |");
        System.out.println("  --------------------------------------------");
        System.out.println();
    }

    //     Menu 
    static void printMenu() {
        System.out.println();
        System.out.println("  ----------------------------------------");
        System.out.println("  |  1. Write a Log Entry (Manual)       |");
        System.out.println("  |  2. Batch Log (Multiple Entries)     |");
        System.out.println("  |  3. Simulate App Events              |");
        System.out.println("  |  4. View All Logs                    |");
        System.out.println("  |  5. Filter Logs (Level / Category)   |");
        System.out.println("  |  6. Search Logs (Keyword)            |");
        System.out.println("  |  7. Stats Dashboard                  |");
        System.out.println("  |  8. Export to File Now               |");
        System.out.println("  |  9. Exit & Save                      |");
        System.out.println("  ----------------------------------------");
        System.out.println("  Active log: " + manager.getCurrentLogFile()
                           + "  (" + manager.totalEntries() + " entries)");
    }

    //     1. Manual log entry
    static void manualLog() {
        System.out.println("\n  -- Write Log Entry ----------------------");

        // Pick level
        System.out.println("  Log Levels:");
        for (int i = 0; i < LEVELS.length; i++)
            System.out.printf("    %d. %s%n", i + 1, LEVELS[i].trim());
        int li = readInt("  Choose level: ", 1, LEVELS.length);
        String level = LEVELS[li - 1];

        // Pick category
        System.out.println("  Categories:");
        for (int i = 0; i < CATEGORIES.length; i++)
            System.out.printf("    %2d. %-12s", i + 1, CATEGORIES[i]);
        System.out.println("\n    " + (CATEGORIES.length + 1) + ". Custom");
        int ci = readInt("  Choose category: ", 1, CATEGORIES.length + 1);
        String category;
        if (ci <= CATEGORIES.length) {
            category = CATEGORIES[ci - 1];
        } else {
            category = readNonEmpty("  Custom category: ").toUpperCase();
        }

        String source  = readNonEmpty("  Source / Module : ");
        String message = readNonEmpty("  Log Message     : ");

        manager.log(level, category, message, source);
        System.out.println("   Entry logged at current timestamp.");
    }

    //    2. Batch log 
    static void batchLog() {
        int n = readInt("\n  How many entries to add? (1 to 20): ", 1, 20);
        System.out.println("  Format per entry: LEVEL | CATEGORY | SOURCE | MESSAGE");
        System.out.println("  Example: ERROR | DATABASE | UserService | Connection timeout");
        System.out.println();

        int success = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("  Entry " + i + ": ");
            String line = sc.nextLine().trim();
            String[] p  = line.split("\\|", 4);
            if (p.length < 4) {
                System.out.println("   Bad format — skipped. Need: LEVEL|CATEGORY|SOURCE|MESSAGE");
                continue;
            }
            String level    = resolveLevel(p[0].trim());
            String category = p[1].trim().toUpperCase();
            String source   = p[2].trim();
            String message  = p[3].trim();
            manager.log(level, category, message, source);
            success++;
        }
        System.out.println("  Right  " + success + " of " + n + " entries logged.");
    }

    //     3. Simulate events
    static void simulateEvents() {
        System.out.println("\n  Simulating application lifecycle events...\n");
        Object[][] events = {
            {LogEntry.INFO,     "SYSTEM",   "App",          "JVM initialized, heap: 256MB"},
            {LogEntry.INFO,     "DATABASE", "DBPool",       "Connection pool created (size=10)"},
            {LogEntry.DEBUG,    "DATABASE", "DBPool",       "Test query executed: SELECT 1"},
            {LogEntry.INFO,     "AUTH",     "AuthService",  "Authentication module loaded"},
            {LogEntry.INFO,     "CACHE",    "CacheManager", "Redis cache connected at localhost:6379"},
            {LogEntry.WARN,     "CACHE",    "CacheManager", "Cache hit ratio below threshold: 42%"},
            {LogEntry.INFO,     "API",      "APIGateway",   "REST API listening on port 8080"},
            {LogEntry.INFO,     "SCHEDULER","JobScheduler", "3 scheduled jobs registered"},
            {LogEntry.INFO,     "AUTH",     "LoginCtrl",    "User admin@library.com logged in"},
            {LogEntry.DEBUG,    "API",      "BookCtrl",     "GET /api/books → 200 OK (23ms)"},
            {LogEntry.DEBUG,    "DATABASE", "BookRepo",     "SELECT * FROM books WHERE genre='CS' (45ms)"},
            {LogEntry.WARN,     "NETWORK",  "HttpClient",   "Slow response from external API: 2300ms"},
            {LogEntry.ERROR,    "PAYMENT",  "PayService",   "Payment gateway timeout after 5000ms"},
            {LogEntry.WARN,     "PAYMENT",  "PayService",   "Retrying payment request (attempt 2/3)"},
            {LogEntry.INFO,     "PAYMENT",  "PayService",   "Payment processed successfully on retry"},
            {LogEntry.DEBUG,    "API",      "UserCtrl",     "POST /api/users → 201 Created (18ms)"},
            {LogEntry.INFO,     "FILE",     "FileService",  "Report generated: monthly_report.pdf (1.2MB)"},
            {LogEntry.CRITICAL, "DATABASE", "DBPool",       "All connections exhausted! Pool size: 0/10"},
            {LogEntry.ERROR,    "DATABASE", "DBPool",       "Failed to acquire connection after 30s"},
            {LogEntry.INFO,     "DATABASE", "DBPool",       "Connection pool recovered after restart"},
            {LogEntry.INFO,     "SYSTEM",   "HealthCheck",  "System health check PASSED"},
        };

        for (Object[] ev : events) {
            manager.log((String) ev[0], (String) ev[1],
                        (String) ev[3], (String) ev[2]);
            System.out.println("   +" + ev[0] + "  [" + ev[1] + "]  " + ev[3]);
            try { Thread.sleep(30); } catch (InterruptedException ignored) {}
        }
        System.out.println("\n  Right  " + events.length + " simulated events written.");
    }

    //       4. View all logs 
    static void viewLogs() {
        List<LogEntry> list = manager.all();
        if (list.isEmpty()) { System.out.println("  No entries yet."); return; }
        printLogTable(list, "All Log Entries");
    }

    //            5. Filter
    static void filterLogs() {
        System.out.println("\n  Filter by:");
        System.out.println("  1. Level      2. Category      3. Last N entries");
        int c = readInt("  Choose: ", 1, 3);
        switch (c) {
            case 1 -> {
                System.out.print("  Level (INFO/WARN/ERROR/DEBUG/CRITICAL): ");
                String lv = sc.nextLine().trim().toUpperCase();
                printLogTable(manager.byLevel(lv), "Filter: Level = " + lv);
            }
            case 2 -> {
                System.out.print("  Category: ");
                String cat = sc.nextLine().trim().toUpperCase();
                printLogTable(manager.byCategory(cat), "Filter: Category = " + cat);
            }
            case 3 -> {
                int n = readInt("  Show last N entries (1 to 500): ", 1, 500);
                printLogTable(manager.last(n), "Last " + n + " entries");
            }
        }
    }

    //      6. Search 
    static void searchLogs() {
        System.out.print("\n  Search keyword: ");
        String kw = sc.nextLine().trim();
        List<LogEntry> res = manager.search(kw);
        printLogTable(res, "Search: \"" + kw + "\"");
    }

    //     7. Dashboard 
    static void dashboard() {
        System.out.println("\n  ============================================");
        System.out.println("  |              LOG STATS DASHBOARD         |");
        System.out.println("  ============================================");
        System.out.printf( "  |  Total Entries   : %-22d|%n", manager.totalEntries());
        System.out.printf( "  |  INFO            : %-22d|%n", manager.countByLevel("INFO"));
        System.out.printf( "  |  DEBUG           : %-22d|%n", manager.countByLevel("DEBUG"));
        System.out.printf( "  |  WARN            : %-22d|%n", manager.countByLevel("WARN"));
        System.out.printf( "  |  ERROR           : %-22d|%n", manager.countByLevel("ERROR"));
        System.out.printf( "  |  CRITICAL        : %-22d|%n", manager.countByLevel("CRITICAL"));
        System.out.printf( "  |  Active Log File : %-22s|%n",
                manager.getCurrentLogFile().replace("logs/", ""));
        System.out.println("  ============================================");
    }

    //     8. Export now 
    static void exportAndSave() {
        manager.exportCSV();
        System.out.println("  .log file  : " + manager.getCurrentLogFile());
    }

    //     Print log table 
    static void printLogTable(List<LogEntry> list, String title) {
        System.out.println("\n  --- " + title + " -------------------");
        if (list.isEmpty()) {
            System.out.println("  No entries match."); return;
        }
        System.out.printf("  %-19s  %-10s  %-12s  %-15s  %s%n",
                "TIMESTAMP", "LEVEL", "CATEGORY", "SOURCE", "MESSAGE");
        System.out.println("  " + "─".repeat(90));
        list.forEach(e -> System.out.println(e));
        System.out.println("  " + "─".repeat(90));
        System.out.println("  " + list.size() + " entries.");
    }

    //        Helpers 
    static String resolveLevel(String raw) {
        return switch (raw.toUpperCase()) {
            case "INFO"     -> LogEntry.INFO;
            case "WARN"     -> LogEntry.WARN;
            case "WARNING"  -> LogEntry.WARN;
            case "ERROR"    -> LogEntry.ERROR;
            case "DEBUG"    -> LogEntry.DEBUG;
            case "CRITICAL" -> LogEntry.CRITICAL;
            default          -> LogEntry.INFO;
        };
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int v = sc.nextInt(); sc.nextLine();
                if (v >= min && v <= max) return v;
            } else { sc.nextLine(); }
            System.out.println("   Enter a number between " + min + " and " + max + ".");
        }
    }

    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (!v.isEmpty()) return v;
            System.out.println("   Cannot be empty.");
        }
    }
}