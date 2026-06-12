public class Book {

    // ── Private fields (Encapsulation) ──────────────────────────
    private int    id;
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private int    year;
    private int    totalCopies;
    private int    availableCopies;

    // ── Constructor ─────────────────────────────────────────────
    public Book(int id, String isbn, String title, String author,
                String genre, int year, int totalCopies) {
        this.id              = id;
        this.isbn            = isbn.trim();
        this.title           = title.trim();
        this.author          = author.trim();
        this.genre           = genre.trim();
        this.year            = year;
        this.totalCopies     = totalCopies;
        this.availableCopies = totalCopies;   // all copies start available
    }

    // ── Full constructor (used when loading from file) ───────────
    public Book(int id, String isbn, String title, String author,
                String genre, int year, int totalCopies, int availableCopies) {
        this(id, isbn, title, author, genre, year, totalCopies);
        this.availableCopies = availableCopies;
    }

    // ── Borrow / Return logic ────────────────────────────────────
    public boolean borrow() {
        if (availableCopies <= 0) return false;
        availableCopies--;
        return true;
    }

    public boolean returnBook() {
        if (availableCopies >= totalCopies) return false;
        availableCopies++;
        return true;
    }

    public boolean isAvailable() { return availableCopies > 0; }

    // ── Getters ──────────────────────────────────────────────────
    public int    getId()              { return id; }
    public String getIsbn()           { return isbn; }
    public String getTitle()          { return title; }
    public String getAuthor()         { return author; }
    public String getGenre()          { return genre; }
    public int    getYear()           { return year; }
    public int    getTotalCopies()    { return totalCopies; }
    public int    getAvailableCopies(){ return availableCopies; }
    public int    getBorrowedCount()  { return totalCopies - availableCopies; }

    // ── Setters (for edit operation) ─────────────────────────────
    public void setTitle(String t)  { this.title  = t.trim(); }
    public void setAuthor(String a) { this.author = a.trim(); }
    public void setGenre(String g)  { this.genre  = g.trim(); }
    public void setYear(int y)      { this.year   = y; }
    public void setTotalCopies(int n) {
        int borrowed = this.totalCopies - this.availableCopies;
        this.totalCopies = n;
        this.availableCopies = Math.max(0, n - borrowed);
    }

    // ── CSV serialisation ────────────────────────────────────────
    public String toCSV() {
        return id + "|" + isbn + "|" + title + "|" + author + "|"
             + genre + "|" + year + "|" + totalCopies + "|" + availableCopies;
    }

    public static Book fromCSV(String line) {
        String[] p = line.split("\\|", 8);
        if (p.length < 8) return null;
        try {
            return new Book(
                Integer.parseInt(p[0].trim()),
                p[1], p[2], p[3], p[4],
                Integer.parseInt(p[5].trim()),
                Integer.parseInt(p[6].trim()),
                Integer.parseInt(p[7].trim())
            );
        } catch (NumberFormatException e) { return null; }
    }

    // ── Summary row (used in table listings) ─────────────────────
    @Override
    public String toString() {
        String avail = availableCopies > 0
                     ? "Right " + availableCopies + "/" + totalCopies
                     : "Wrong 0/" + totalCopies;
        return String.format("  [%3d] %-13s %-30s %-20s %-12s %4d  %s",
                id, isbn, truncate(title, 29), truncate(author, 19),
                truncate(genre, 11), year, avail);
    }

    // ── Detail card ──────────────────────────────────────────────
    public void printDetail() {
        System.out.println("\n  ==========================================");
        System.out.printf( "     ID       : %-28d│%n", id);
        System.out.printf( "     ISBN     : %-28s│%n", isbn);
        System.out.printf( "     Title    : %-28s│%n", truncate(title, 28));
        System.out.printf( "     Author   : %-28s│%n", truncate(author, 28));
        System.out.printf( "     Genre    : %-28s│%n", genre);
        System.out.printf( "     Year     : %-28d│%n", year);
        System.out.printf( "     Copies   : %d total, %d available, %d borrowed%s│%n",
                totalCopies, availableCopies, getBorrowedCount(),
                " ".repeat(Math.max(0, 16 - String.valueOf(totalCopies).length()
                        - String.valueOf(availableCopies).length()
                        - String.valueOf(getBorrowedCount()).length())));
        System.out.println("  ===========================================");
    }

    // ── Utility ──────────────────────────────────────────────────
    private static String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 1) + "…" : s;
    }
}