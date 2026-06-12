import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {

    private static final long   MAX_SIZE_BYTES = 50_000;     // 50 KB per log file
    private static final String LOG_DIR        = "logs/";
    private static final DateTimeFormatter STAMP =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private final ArrayList<LogEntry> entries = new ArrayList<>();
    private       String              currentLogFile;
    private       String              currentCsvFile;

    //          Constructor 
    public LogManager() {
        new File(LOG_DIR).mkdirs();                    // ensure directory exists
        String base = LocalDateTime.now().format(STAMP);
        currentLogFile = LOG_DIR + "app_" + base + ".log";
        currentCsvFile = LOG_DIR + "app_" + base + ".csv";
    }

    //    Core: add a new log entry 
    public void log(String level, String category, String message, String source) {
        checkRotation();
        LogEntry e = new LogEntry(level, category, message, source);
        entries.add(e);
        appendToFile(e);
    }

    //       Convenience level methods
    public void info(String cat, String msg, String src)     { log(LogEntry.INFO,     cat, msg, src); }
    public void warn(String cat, String msg, String src)     { log(LogEntry.WARN,     cat, msg, src); }
    public void error(String cat, String msg, String src)    { log(LogEntry.ERROR,    cat, msg, src); }
    public void debug(String cat, String msg, String src)    { log(LogEntry.DEBUG,    cat, msg, src); }
    public void critical(String cat, String msg, String src) { log(LogEntry.CRITICAL, cat, msg, src); }

    //         Filtered queries 
    public List<LogEntry> all()                      { return new ArrayList<>(entries); }

    public List<LogEntry> byLevel(String level) {
        return entries.stream()
                .filter(e -> e.getLevel().equalsIgnoreCase(level))
                .toList();
    }

    public List<LogEntry> byCategory(String cat) {
        return entries.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(cat))
                .toList();
    }

    public List<LogEntry> search(String keyword) {
        String q = keyword.toLowerCase();
        return entries.stream()
                .filter(e -> e.getMessage().toLowerCase().contains(q)
                          || e.getCategory().toLowerCase().contains(q)
                          || e.getSource().toLowerCase().contains(q))
                .toList();
    }

    public List<LogEntry> last(int n) {
        int from = Math.max(0, entries.size() - n);
        return entries.subList(from, entries.size());
    }

    //    Statistics 
    public long countByLevel(String level) {
        return entries.stream()
                      .filter(e -> e.getLevel().equalsIgnoreCase(level))
                      .count();
    }

    //    Export to CSV backup 
    public void exportCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(currentCsvFile))) {
            pw.println("timestamp§level§category§source§message");
            entries.forEach(e -> pw.println(e.toCSV()));
            System.out.println("   CSV exported to: " + currentCsvFile);
        } catch (IOException ex) {
            System.out.println("   CSV export failed: " + ex.getMessage());
        }
    }

    //  Load from existing log CSV 
    public int loadFromCSV(String path) {
        File f = new File(path);
        if (!f.exists()) return 0;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    LogEntry e = LogEntry.fromCSV(line);
                    if (e != null) { entries.add(e); count++; }
                }
            }
        } catch (IOException ex) {
            System.out.println("   Load failed: " + ex.getMessage());
        }
        return count;
    }

    public String getCurrentLogFile() { return currentLogFile; }
    public String getCurrentCsvFile() { return currentCsvFile; }
    public int    totalEntries()      { return entries.size(); }

    //    Private: append one entry to .log file 
    private void appendToFile(LogEntry e) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(currentLogFile, true))) {
            pw.println(e.toLogLine());
        } catch (IOException ex) {
            System.err.println("   Log write failed: " + ex.getMessage());
        }
    }

    //  Private: rotate log file if it exceeds max size 
    private void checkRotation() {
        File f = new File(currentLogFile);
        if (f.exists() && f.length() >= MAX_SIZE_BYTES) {
            String base = LocalDateTime.now().format(STAMP);
            currentLogFile = LOG_DIR + "app_" + base + ".log";
            currentCsvFile = LOG_DIR + "app_" + base + ".csv";
            System.out.println("   Log rotated -> " + currentLogFile);
        }
    }
}