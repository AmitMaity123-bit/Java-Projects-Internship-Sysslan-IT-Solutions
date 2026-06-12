import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class LibrarySystem {

    //       Data stores 
    static final String BOOKS_FILE   = "books.dat";
    static final String MEMBERS_FILE = "members.dat";

    static ArrayList<Book>   books   = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();

    static int nextBookId   = 1;
    static int nextMemberId = 1;

    static Scanner sc = new Scanner(System.in);

    // 
    public static void main(String[] args) {
        loadAll();
        printBanner();
        mainLoop();
        sc.close();
    }

    //    Main navigation 
    static void mainLoop() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("  Choose: ", 1, 5);
            switch (choice) {
                case 1 -> bookMenu();
                case 2 -> memberMenu();
                case 3 -> borrowMenu();
                case 4 -> reportsMenu();
                case 5 -> running = false;
            }
        }
        System.out.println("\n   All data saved. Goodbye!\n");
    }

    //    MENUS 

    static void printMainMenu() {
        System.out.println();
        System.out.println("  ====================================");
        System.out.println("  |         MAIN MENU                |");
        System.out.println("  ====================================");
        System.out.println("  |  1. Book Management              |");
        System.out.println("  |  2. Member Management            |");
        System.out.println("  |  3. Borrow & Return              |");
        System.out.println("  |  4. Reports & Statistics         |");
        System.out.println("  |  5. Exit                         |");
        System.out.println("  ====================================");
    }

    //     BOOK MENU
    static void bookMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  -- Book Management --------------");
            System.out.println("  1. Add Book        2. View All Books");
            System.out.println("  3. Search Books    4. Edit Book");
            System.out.println("  5. Delete Book     6. View Book Detail");
            System.out.println("  7. Back");
            int c = readInt("  Choose: ", 1, 7);
            switch (c) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchBooks();
                case 4 -> editBook();
                case 5 -> deleteBook();
                case 6 -> viewBookDetail();
                case 7 -> back = true;
            }
        }
    }

    //    MEMBER MENU 
    static void memberMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  -- Member Management -------------");
            System.out.println("  1. Register Member   2. View All Members");
            System.out.println("  3. Search Member     4. Edit Member");
            System.out.println("  5. Member History    6. Back");
            int c = readInt("  Choose: ", 1, 6);
            switch (c) {
                case 1 -> registerMember();
                case 2 -> viewAllMembers();
                case 3 -> searchMember();
                case 4 -> editMember();
                case 5 -> viewMemberHistory();
                case 6 -> back = true;
            }
        }
    }

    //   BORROW MENU
    static void borrowMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  -- Borrow & Return --------------");
            System.out.println("  1. Issue Book (Borrow)");
            System.out.println("  2. Accept Book (Return)");
            System.out.println("  3. View Currently Borrowed Books");
            System.out.println("  4. Back");
            int c = readInt("  Choose: ", 1, 4);
            switch (c) {
                case 1 -> issueBook();
                case 2 -> returnBook();
                case 3 -> viewBorrowed();
                case 4 -> back = true;
            }
        }
    }

    //   REPORTS MENU 
    static void reportsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  -- Reports ----------------------");
            System.out.println("  1. Library Dashboard");
            System.out.println("  2. Books by Genre");
            System.out.println("  3. Most Borrowed Books");
            System.out.println("  4. Back");
            int c = readInt("  Choose: ", 1, 4);
            switch (c) {
                case 1 -> dashboard();
                case 2 -> booksByGenre();
                case 3 -> mostBorrowed();
                case 4 -> back = true;
            }
        }
    }

    //    BOOK OPERATIONS 
    static void addBook() {
        System.out.println("\n  -- Add New Book ----------------------");
        String isbn   = readNonEmpty("  ISBN          : ");
        String title  = readNonEmpty("  Title         : ");
        String author = readNonEmpty("  Author        : ");
        String genre  = readNonEmpty("  Genre         : ");
        int    year   = readInt("  Year           : ", 1000, LocalDate.now().getYear());
        int    copies = readInt("  Total Copies   : ", 1, 999);

        Book b = new Book(nextBookId++, isbn, title, author, genre, year, copies);
        books.add(b);
        saveAll();
        System.out.println("   Book added with ID " + b.getId()
                + " (" + copies + " copies)");
    }

    static void viewAllBooks() {
        if (books.isEmpty()) { noRecords("books"); return; }
        printBookHeader();
        books.stream()
             .sorted(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER))
             .forEach(System.out::println);
        printFooter(books.size(), "book");
    }

    static void searchBooks() {
        System.out.print("\n  Search books (title / author / genre / ISBN): ");
        String q = sc.nextLine().trim().toLowerCase();
        List<Book> res = books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(q)
                      || b.getAuthor().toLowerCase().contains(q)
                      || b.getGenre().toLowerCase().contains(q)
                      || b.getIsbn().toLowerCase().contains(q))
            .toList();
        if (res.isEmpty()) { System.out.println("   No books match '" + q + "''."); return; }
        printBookHeader();
        res.forEach(System.out::println);
        printFooter(res.size(), "book");
    }

    static void editBook() {
        Book b = findBookById("  Enter Book ID to edit: ");
        if (b == null) return;
        System.out.println("  Editing: " + b.getTitle());
        System.out.println("  (Press Enter to keep current value)");
        b.setTitle(readOrKeep("  Title  [" + b.getTitle()  + "]: ", b.getTitle()));
        b.setAuthor(readOrKeep("  Author [" + b.getAuthor() + "]: ", b.getAuthor()));
        b.setGenre(readOrKeep("  Genre  [" + b.getGenre()  + "]: ", b.getGenre()));
        System.out.print("  Year   [" + b.getYear() + "]: ");
        String y = sc.nextLine().trim();
        if (!y.isEmpty()) b.setYear(Integer.parseInt(y));
        System.out.print("  Total Copies [" + b.getTotalCopies() + "]: ");
        String cp = sc.nextLine().trim();
        if (!cp.isEmpty()) b.setTotalCopies(Integer.parseInt(cp));
        saveAll();
        System.out.println("   Book updated.");
    }

    static void deleteBook() {
        Book b = findBookById("  Enter Book ID to delete: ");
        if (b == null) return;
        if (b.getBorrowedCount() > 0) {
            System.out.println("  ⚠  Cannot delete — " + b.getBorrowedCount()
                    + " copy/copies currently borrowed. Accept returns first.");
            return;
        }
        System.out.println("  About to delete: " + b.getTitle());
        System.out.print("  Confirm? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            books.remove(b);
            saveAll();
            System.out.println("   Book deleted.");
        }
    }

    static void viewBookDetail() {
        Book b = findBookById("  Enter Book ID: ");
        if (b != null) b.printDetail();
    }

    //  MEMBER OPERATIONS

    static void registerMember() {
        System.out.println("\n -- Register New Member ---------------");
        String name  = readNonEmpty("  Full Name : ");
        String email = readEmail("  Email     : ");
        String phone = readPhone("  Phone     : ");
        Member m = new Member(nextMemberId++, name, email, phone);
        members.add(m);
        saveAll();
        System.out.println("   Member registered with ID " + m.getId());
    }

    static void viewAllMembers() {
        if (members.isEmpty()) { noRecords("members"); return; }
        printMemberHeader();
        members.stream()
               .sorted(Comparator.comparing(Member::getName, String.CASE_INSENSITIVE_ORDER))
               .forEach(System.out::println);
        printFooter(members.size(), "member");
    }

    static void searchMember() {
        System.out.print("\n  Search member (name or email): ");
        String q = sc.nextLine().trim().toLowerCase();
        List<Member> res = members.stream()
            .filter(m -> m.getName().toLowerCase().contains(q)
                      || m.getEmail().toLowerCase().contains(q))
            .toList();
        if (res.isEmpty()) { System.out.println("   No match."); return; }
        printMemberHeader();
        res.forEach(System.out::println);
        printFooter(res.size(), "member");
    }

    static void editMember() {
        Member m = findMemberById("  Enter Member ID to edit: ");
        if (m == null) return;
        m.setName(readOrKeep("  Name  [" + m.getName()  + "]: ", m.getName()));
        m.setEmail(readOrKeep("  Email [" + m.getEmail() + "]: ", m.getEmail()));
        m.setPhone(readOrKeep("  Phone [" + m.getPhone() + "]: ", m.getPhone()));
        saveAll();
        System.out.println("   Member updated.");
    }

    static void viewMemberHistory() {
        Member m = findMemberById("  Enter Member ID: ");
        if (m == null) return;
        List<Member.BorrowRecord> hist = m.getBorrows();
        System.out.println("\n  Borrow history for: " + m.getName());
        System.out.printf("  %-6s %-32s %-12s %-12s%n",
                          "BookID", "Title", "Borrowed", "Returned");
        System.out.println("  " + "─".repeat(66));
        if (hist.isEmpty()) {
            System.out.println("  No borrow history.");
        } else {
            hist.forEach(br -> System.out.printf("  %-6d %-32s %-12s %-12s%n",
                    br.bookId,
                    br.bookTitle.length() > 31 ? br.bookTitle.substring(0, 30) + "…" : br.bookTitle,
                    br.borrowDate,
                    br.returnDate == null ? "Active" : br.returnDate));
        }
    }

    //  BORROW & RETURN 
    static void issueBook() {
        System.out.println("\n  -- Issue Book (Borrow) ---------------");
        Member m = findMemberById("  Member ID : ");
        if (m == null) return;

        if (m.activeBorrowCount() >= 3) {
            System.out.println("  Wrong  " + m.getName()
                    + " already has 3 active borrows (maximum allowed).");
            return;
        }

        Book b = findBookById("  Book ID   : ");
        if (b == null) return;

        if (m.hasActiveBorrow(b.getId())) {
            System.out.println("   This member already has an active borrow for \""
                    + b.getTitle() + "\".");
            return;
        }

        if (!b.isAvailable()) {
            System.out.println("   No copies available. Book is fully borrowed out.");
            return;
        }

        String today = LocalDate.now().toString();
        b.borrow();
        m.addBorrow(b.getId(), b.getTitle(), today);
        saveAll();
        System.out.println("  Right  \"" + b.getTitle() + "\" issued to "
                + m.getName() + " on " + today);
        System.out.println("  Copies remaining: " + b.getAvailableCopies());
    }

    static void returnBook() {
        System.out.println("\n  -- Accept Book (Return) ------------");
        Member m = findMemberById("  Member ID : ");
        if (m == null) return;

        // Show active borrows for this member
        List<Member.BorrowRecord> active = m.getBorrows().stream()
                .filter(Member.BorrowRecord::isActive).toList();
        if (active.isEmpty()) {
            System.out.println("  " + m.getName() + " has no active borrows.");
            return;
        }
        System.out.println("  Active borrows for " + m.getName() + ":");
        active.forEach(br -> System.out.printf("    BookID %-4d  %s  (borrowed: %s)%n",
                br.bookId, br.bookTitle, br.borrowDate));

        Book b = findBookById("  Book ID to return: ");
        if (b == null) return;

        if (!m.hasActiveBorrow(b.getId())) {
            System.out.println("    No active borrow found for this book + member.");
            return;
        }

        String today = LocalDate.now().toString();
        b.returnBook();
        m.markReturned(b.getId(), today);
        saveAll();
        System.out.println("  Right  \"" + b.getTitle() + "\" returned by "
                + m.getName() + " on " + today);
        System.out.println("  Copies now available: " + b.getAvailableCopies());
    }

    static void viewBorrowed() {
        System.out.println("\n  -- Currently Borrowed Books ---------");
        System.out.printf("  %-10s %-22s %-32s %-12s%n",
                          "MemberID","Member Name","Book Title","Since");
        System.out.println("  " + "─".repeat(78));
        boolean any = false;
        for (Member m : members) {
            for (Member.BorrowRecord br : m.getBorrows()) {
                if (br.isActive()) {
                    System.out.printf("  %-10d %-22s %-32s %-12s%n",
                            m.getId(), truncate(m.getName(), 21),
                            truncate(br.bookTitle, 31), br.borrowDate);
                    any = true;
                }
            }
        }
        if (!any) System.out.println("  No books currently borrowed.");
    }

    //  REPORTS 

    static void dashboard() {
        long totalCopies    = books.stream().mapToLong(Book::getTotalCopies).sum();
        long available      = books.stream().mapToLong(Book::getAvailableCopies).sum();
        long borrowed       = totalCopies - available;
        long activeMembers  = members.stream()
                .filter(m -> m.activeBorrowCount() > 0).count();

        System.out.println("\n  =========================================");
        System.out.println("          📊  LIBRARY DASHBOARD           ");
        System.out.println("  =========================================");
        System.out.printf( "  |  Total Book Titles   : %-14d|%n", books.size());
        System.out.printf( "  |  Total Copies        : %-14d|%n", totalCopies);
        System.out.printf( "  |  Available Copies    : %-14d|%n", available);
        System.out.printf( "  |  Borrowed Copies     : %-14d|%n", borrowed);
        System.out.printf( "  |  Total Members       : %-14d|%n", members.size());
        System.out.printf( "  |  Members Borrowing   : %-14d|%n", activeMembers);
        System.out.println("  ========================================");
    }

    static void booksByGenre() {
        if (books.isEmpty()) { noRecords("books"); return; }
        Map<String, Long> byGenre = new TreeMap<>();
        books.forEach(b -> byGenre.merge(b.getGenre(), 1L, Long::sum));
        System.out.println("\n  -- Books by Genre ----------------------");
        System.out.printf("  %-20s %s%n", "Genre", "Titles");
        System.out.println("  " + "─".repeat(30));
        byGenre.forEach((g, n) -> System.out.printf("  %-20s %d%n", g, n));
    }

    static void mostBorrowed() {
        if (books.isEmpty()) { noRecords("books"); return; }
        System.out.println("\n  -- Most Borrowed Books ------------------");
        System.out.printf("  %-6s %-32s %-10s%n", "ID", "Title", "Borrowed");
        System.out.println("  " + "─".repeat(50));
        books.stream()
             .sorted((a, b) -> Integer.compare(b.getBorrowedCount(), a.getBorrowedCount()))
             .limit(10)
             .forEach(b -> System.out.printf("  %-6d %-32s %-10d%n",
                     b.getId(), truncate(b.getTitle(), 31), b.getBorrowedCount()));
    }

    //   FILE I/O 

    static void saveAll() {
        saveList(BOOKS_FILE, books.stream().map(Book::toCSV).toList());
        saveList(MEMBERS_FILE, members.stream().map(Member::toCSV).toList());
    }

    static void saveList(String filename, List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            lines.forEach(pw::println);
        } catch (IOException e) {
            System.out.println("   Save failed (" + filename + "): " + e.getMessage());
        }
    }

    static void loadAll() {
        // Load books
        File bf = new File(BOOKS_FILE);
        if (bf.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(bf))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.isBlank()) {
                        Book b = Book.fromCSV(line);
                        if (b != null) {
                            books.add(b);
                            if (b.getId() >= nextBookId) nextBookId = b.getId() + 1;
                        }
                    }
                }
            } catch (IOException e) { System.out.println("   Could not load books."); }
        }
        // Load members
        File mf = new File(MEMBERS_FILE);
        if (mf.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(mf))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.isBlank()) {
                        Member m = Member.fromCSV(line);
                        if (m != null) {
                            members.add(m);
                            if (m.getId() >= nextMemberId) nextMemberId = m.getId() + 1;
                        }
                    }
                }
            } catch (IOException e) { System.out.println("   Could not load members."); }
        }
    }

    //  BANNER 

    static void printBanner() {
        System.out.println();
        System.out.println("  ===========================================");
        System.out.println("         CONSOLE LIBRARY MANAGEMENT SYSTEM   ");
        System.out.println("  ===========================================");
        System.out.println("  Books   : " + books.size() + " titles loaded");
        System.out.println("  Members : " + members.size() + " members loaded");
        System.out.println();
    }

    //  HELPERS

    static void printBookHeader() {
        System.out.println();
        System.out.printf("  %-5s %-13s %-30s %-20s %-12s %-6s %-10s%n",
                "ID","ISBN","TITLE","AUTHOR","GENRE","YEAR","AVAILABLE");
        System.out.println("  " + "─".repeat(100));
    }

    static void printMemberHeader() {
        System.out.println();
        System.out.printf("  %-5s %-22s %-28s %-15s %-10s%n",
                "ID","NAME","EMAIL","PHONE","BORROWS");
        System.out.println("  " + "─".repeat(84));
    }

    static void printFooter(int n, String entity) {
        System.out.println("  " + "─".repeat(n > 0 ? 80 : 30));
        System.out.println("  " + n + " " + entity + (n != 1 ? "s" : "") + " shown.");
    }

    static void noRecords(String what) {
        System.out.println("  No " + what + " on record yet.");
    }

    static Book findBookById(String prompt) {
        int id = readInt(prompt, 1, Integer.MAX_VALUE);
        return books.stream().filter(b -> b.getId() == id).findFirst()
               .orElseGet(() -> { System.out.println("   Book ID not found."); return null; });
    }

    static Member findMemberById(String prompt) {
        int id = readInt(prompt, 1, Integer.MAX_VALUE);
        return members.stream().filter(m -> m.getId() == id).findFirst()
               .orElseGet(() -> { System.out.println("   Member ID not found."); return null; });
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int v = sc.nextInt(); sc.nextLine();
                if (v >= min && v <= max) return v;
            } else { sc.nextLine(); }
            System.out.println("   Enter a number (" + min + "–" + max + ").");
        }
    }

    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (!v.isEmpty()) return v;
            System.out.println("   This field cannot be empty.");
        }
    }

    static String readEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (v.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) return v;
            System.out.println("   Enter a valid email address.");
        }
    }

    static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (v.matches("[0-9+\\-\\s()]{7,15}")) return v;
            System.out.println("   Enter a valid phone number.");
        }
    }

    static String readOrKeep(String prompt, String current) {
        System.out.print(prompt);
        String v = sc.nextLine().trim();
        return v.isEmpty() ? current : v;
    }

    static String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 1) + "…" : s;
    }
}