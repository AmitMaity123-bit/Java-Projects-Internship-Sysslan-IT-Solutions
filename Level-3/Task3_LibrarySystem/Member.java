import java.util.ArrayList;
import java.util.List;

public class Member {

    // ── Inner class — one borrow transaction
    public static class BorrowRecord {
        public final int    bookId;
        public final String bookTitle;
        public final String borrowDate;
        public       String returnDate;   // null means still borrowed

        public BorrowRecord(int bookId, String bookTitle, String borrowDate) {
            this.bookId     = bookId;
            this.bookTitle  = bookTitle;
            this.borrowDate = borrowDate;
            this.returnDate = null;
        }

        public boolean isActive() { return returnDate == null; }

        public String toCSV() {
            return bookId + "~" + bookTitle + "~" + borrowDate
                 + "~" + (returnDate == null ? "ACTIVE" : returnDate);
        }

        public static BorrowRecord fromCSV(String s) {
            String[] p = s.split("~", 4);
            if (p.length < 4) return null;
            BorrowRecord br = new BorrowRecord(
                Integer.parseInt(p[0].trim()), p[1], p[2]);
            if (!p[3].equals("ACTIVE")) br.returnDate = p[3];
            return br;
        }
    }

    // ── Private fields
    private int    id;
    private String name;
    private String email;
    private String phone;
    private List<BorrowRecord> borrows = new ArrayList<>();

    // ── Constructor 
    public Member(int id, String name, String email, String phone) {
        this.id    = id;
        this.name  = name.trim();
        this.email = email.trim();
        this.phone = phone.trim();
    }

    // ── Borrow / Return 
    public void addBorrow(int bookId, String bookTitle, String date) {
        borrows.add(new BorrowRecord(bookId, bookTitle, date));
    }

    public boolean markReturned(int bookId, String returnDate) {
        for (BorrowRecord br : borrows) {
            if (br.bookId == bookId && br.isActive()) {
                br.returnDate = returnDate;
                return true;
            }
        }
        return false;
    }

    public boolean hasActiveBorrow(int bookId) {
        return borrows.stream()
                      .anyMatch(br -> br.bookId == bookId && br.isActive());
    }

    public long activeBorrowCount() {
        return borrows.stream().filter(BorrowRecord::isActive).count();
    }

    // ── Getters 
    public int    getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<BorrowRecord> getBorrows() { return borrows; }

    // ── Setters 
    public void setName(String n)  { this.name  = n.trim(); }
    public void setEmail(String e) { this.email = e.trim(); }
    public void setPhone(String p) { this.phone = p.trim(); }

    // ── CSV 
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|")
          .append(name).append("|")
          .append(email).append("|")
          .append(phone).append("|");
        List<String> recs = borrows.stream().map(BorrowRecord::toCSV).toList();
        sb.append(String.join(";", recs));
        return sb.toString();
    }

    public static Member fromCSV(String line) {
        String[] p = line.split("\\|", 5);
        if (p.length < 4) return null;
        Member m = new Member(Integer.parseInt(p[0].trim()), p[1], p[2], p[3]);
        if (p.length == 5 && !p[4].isBlank()) {
            for (String s : p[4].split(";")) {
                if (!s.isBlank()) {
                    BorrowRecord br = BorrowRecord.fromCSV(s);
                    if (br != null) m.borrows.add(br);
                }
            }
        }
        return m;
    }

    // ── Summary row 
    @Override
    public String toString() {
        return String.format("  [%3d] %-22s %-28s %-15s  Borrowed:%d",
                id, name, email, phone, activeBorrowCount());
    }
}