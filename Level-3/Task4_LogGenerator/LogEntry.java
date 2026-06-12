import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {

    //  Log level constants 
    public static final String INFO     = "INFO    ";
    public static final String WARN     = "WARN    ";
    public static final String ERROR    = "ERROR   ";
    public static final String DEBUG    = "DEBUG   ";
    public static final String CRITICAL = "CRITICAL";

    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FILE_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    //   Private fields 
    private final LocalDateTime timestamp;
    private final String        level;
    private final String        category;
    private final String        message;
    private final String        source;      // e.g. class or module name

    //    Constructor 
    public LogEntry(String level, String category,
                    String message, String source) {
        this.timestamp = LocalDateTime.now();
        this.level     = level;
        this.category  = category.trim();
        this.message   = message.trim();
        this.source    = source.trim();
    }

    //  Constructor with explicit timestamp (for loading) 
    public LogEntry(LocalDateTime ts, String level,
                    String category, String message, String source) {
        this.timestamp = ts;
        this.level     = level;
        this.category  = category;
        this.message   = message;
        this.source    = source;
    }

    //    Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public String        getLevel()     { return level.trim(); }
    public String        getCategory()  { return category; }
    public String        getMessage()   { return message; }
    public String        getSource()    { return source; }

    //   Format for writing to log file 
    public String toLogLine() {
        return "[" + timestamp.format(FILE_FMT) + "]"
             + " [" + level + "]"
             + " [" + padRight(category, 12) + "]"
             + " [" + padRight(source, 15) + "] "
             + message;
    }

    //  Format for CSV backup 
    public String toCSV() {
        return timestamp.format(FILE_FMT) + "§"
             + level.trim() + "§"
             + category + "§"
             + source + "§"
             + message;
    }

    public static LogEntry fromCSV(String line) {
        String[] p = line.split("§", 5);
        if (p.length < 5) return null;
        try {
            LocalDateTime ts = LocalDateTime.parse(p[0].trim(), FILE_FMT);
            return new LogEntry(ts, padLevel(p[1].trim()),
                                p[2].trim(), p[4].trim(), p[3].trim());
        } catch (Exception e) { return null; }
    }

    //   Console display row 
    @Override
    public String toString() {
        return "  " + timestamp.format(DISPLAY_FMT)
             + "  [" + level + "]"
             + "  " + padRight(category, 12)
             + "  " + padRight(source, 15)
             + "  " + message;
    }

    //   Utilities 
    private static String padRight(String s, int w) {
        if (s.length() >= w) return s.substring(0, w);
        return s + " ".repeat(w - s.length());
    }

    private static String padLevel(String l) {
        return switch (l) {
            case "INFO"     -> INFO;
            case "WARN"     -> WARN;
            case "ERROR"    -> ERROR;
            case "DEBUG"    -> DEBUG;
            case "CRITICAL" -> CRITICAL;
            default          -> padRight(l, 8);
        };
    }
}