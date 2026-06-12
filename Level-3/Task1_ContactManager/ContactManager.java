import java.io.*;
import java.util.*;

public class ContactManager {

    static final String FILE = "contacts.csv";
    static ArrayList<Contact> contacts = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);

    // =================================================
    public static void main(String[] args) {
        loadFromFile();
        printBanner();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Choose: ", 1, 8);
            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewAll();
                case 3 -> searchContact();
                case 4 -> editContact();
                case 5 -> deleteContact();
                case 6 -> filterByGroup();
                case 7 -> viewStats();
                case 8 -> running = false;
            }
        }
        System.out.println("\n   Contacts saved. Goodbye!\n");
        sc.close();
    }

    // ── Banner ──────────────────────────────────────────────────
    static void printBanner() {
        System.out.println();
        System.out.println("  ========================================");
        System.out.println("          CONTACT MANAGEMENT SYSTEM     ");
        System.out.println("  ========================================");
        System.out.println("  Contacts loaded: " + contacts.size());
        System.out.println();
    }

    // ── Menu ────────────────────────────────────────────────────
    static void printMenu() {
        System.out.println();
        System.out.println("  ===============================");
        System.out.println("     1. Add Contact              ");
        System.out.println("     2. View All Contacts        ");
        System.out.println("     3. Search Contact           ");
        System.out.println("     4. Edit Contact             ");
        System.out.println("     5. Delete Contact           ");
        System.out.println("     6. Filter by Group          ");
        System.out.println("     7. Stats                    ");
        System.out.println("     8. Exit                     ");
        System.out.println("  ===============================");
    }

    // ── 1. Add ──────────────────────────────────────────────────
    static void addContact() {
        System.out.println("\n  ── Add New Contact ─────────────────");
        String name  = readNonEmpty("  Name        : ");
        String phone = readPhone("  Phone       : ");
        String email = readEmail("  Email       : ");
        String group = readNonEmpty("  Group (Work/Friend/Family/Other): ");

        Contact c = new Contact(nextId++, name, phone, email, group);
        contacts.add(c);
        saveToFile();
        System.out.println("   Contact added with ID " + c.getId());
    }

    // ── 2. View All ─────────────────────────────────────────────
    static void viewAll() {
        if (contacts.isEmpty()) { System.out.println("  No contacts yet."); return; }
        printHeader();
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);
        printFooter(contacts.size());
    }

    // ── 3. Search ───────────────────────────────────────────────
    static void searchContact() {
        System.out.print("\n  Search (name or phone or email): ");
        String q = sc.nextLine().trim().toLowerCase();
        List<Contact> results = contacts.stream()
            .filter(c -> c.getName().toLowerCase().contains(q)
                      || c.getPhone().contains(q)
                      || c.getEmail().toLowerCase().contains(q))
            .toList();

        if (results.isEmpty()) {
            System.out.println("   No matches for " + q + ".");
        } else {
            printHeader();
            results.forEach(System.out::println);
            printFooter(results.size());
        }
    }

    // ── 4. Edit ─────────────────────────────────────────────────
    static void editContact() {
        Contact c = findById("  Enter ID to edit: ");
        if (c == null) return;
        System.out.println("  Editing: " + c);
        System.out.println("  (Press Enter to keep current value)");

        String name  = readOrKeep("  New Name  [" + c.getName()  + "]: ", c.getName());
        String phone = readOrKeepPhone("  New Phone [" + c.getPhone() + "]: ", c.getPhone());
        String email = readOrKeepEmail("  New Email [" + c.getEmail() + "]: ", c.getEmail());
        String group = readOrKeep("  New Group [" + c.getGroup() + "]: ", c.getGroup());

        c.setName(name);
        c.setPhone(phone);
        c.setEmail(email);
        c.setGroup(group);
        saveToFile();
        System.out.println("   Contact updated.");
    }

    // ── 5. Delete ───────────────────────────────────────────────
    static void deleteContact() {
        Contact c = findById("  Enter ID to delete: ");
        if (c == null) return;
        System.out.println("  About to delete: " + c);
        System.out.print("  Confirm? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            contacts.remove(c);
            saveToFile();
            System.out.println("   Contact deleted.");
        } else {
            System.out.println("  Cancelled.");
        }
    }

    // ── 6. Filter by Group ──────────────────────────────────────
    static void filterByGroup() {
        // Gather distinct groups
        Set<String> groups = new TreeSet<>();
        contacts.forEach(c -> groups.add(c.getGroup()));
        if (groups.isEmpty()) { System.out.println("  No contacts yet."); return; }

        System.out.println("\n  Available groups: " + String.join(", ", groups));
        System.out.print("  Enter group name: ");
        String g = sc.nextLine().trim();

        List<Contact> filtered = contacts.stream()
            .filter(c -> c.getGroup().equalsIgnoreCase(g))
            .toList();

        if (filtered.isEmpty()) {
            System.out.println("  No contacts in group " + g + ".");
        } else {
            printHeader();
            filtered.forEach(System.out::println);
            printFooter(filtered.size());
        }
    }

    // ── 7. Stats ────────────────────────────────────────────────
    static void viewStats() {
        System.out.println("\n  ── Contact Statistics ──────────────");
        System.out.println("  Total contacts : " + contacts.size());
        Map<String, Long> byGroup = new TreeMap<>();
        contacts.forEach(c -> byGroup.merge(c.getGroup(), 1L, Long::sum));
        byGroup.forEach((g, n) ->
            System.out.printf("  %-15s : %d%n", g, n));
    }

    // ── File I/O ────────────────────────────────────────────────
    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Contact c : contacts) pw.println(c.toCSV());
        } catch (IOException e) {
            System.out.println("   Save failed: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        File f = new File(FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                Contact c = Contact.fromCSV(line);
                if (c != null) {
                    contacts.add(c);
                    if (c.getId() >= nextId) nextId = c.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("   Load failed: " + e.getMessage());
        }
    }

    // ── Helpers ─────────────────────────────────────────────────
    static void printHeader() {
        System.out.println();
        System.out.printf("  %-5s %-22s %-15s %-28s %s%n",
                          "ID","NAME","PHONE","EMAIL","GROUP");
        System.out.println("  " + "─".repeat(80));
    }
    static void printFooter(int n) {
        System.out.println("  " + "─".repeat(80));
        System.out.println("  " + n + " record(s) shown.");
    }

    static Contact findById(String prompt) {
        int id = readInt(prompt, 1, Integer.MAX_VALUE);
        return contacts.stream()
                       .filter(c -> c.getId() == id)
                       .findFirst()
                       .orElseGet(() -> { System.out.println("   ID not found."); return null; });
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
            System.out.println("   This field cannot be empty.");
        }
    }

    static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (v.matches("[0-9+\\-\\s()]{7,15}")) return v;
            System.out.println("   Enter a valid phone number (7 to 15 digits).");
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

    static String readOrKeep(String prompt, String current) {
        System.out.print(prompt);
        String v = sc.nextLine().trim();
        return v.isEmpty() ? current : v;
    }

    static String readOrKeepPhone(String prompt, String current) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (v.isEmpty()) return current;
            if (v.matches("[0-9+\\-\\s()]{7,15}")) return v;
            System.out.println("   Invalid phone. Try again.");
        }
    }

    static String readOrKeepEmail(String prompt, String current) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (v.isEmpty()) return current;
            if (v.matches("^[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$")) return v;
            System.out.println("   Invalid email. Try again.");
        }
    }
}