import java.io.*;
import java.util.*;

public class StudentRecordSystem {

    static final String FILE = "students.dat";
    static ArrayList<Student> students = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);

    // Subject list — shared for all students in this session
    static String[] defaultSubjects = {"Maths", "English", "Science", "History", "CS"};

    public static void main(String[] args) {
        loadFromFile();
        printBanner();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Choose: ", 1, 9);
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> viewReport();
                case 4 -> searchStudent();
                case 5 -> editStudent();
                case 6 -> deleteStudent();
                case 7 -> viewToppers();
                case 8 -> subjectAnalysis();
                case 9 -> running = false;
            }
        }
        System.out.println("\n   Records saved. Goodbye!\n");
        sc.close();
    }

    // ── Banner ──────────────────────────────────────────────────
    static void printBanner() {
        System.out.println();
        System.out.println("  =============================================");
        System.out.println("           STUDENT RECORD SYSTEM               ");
        System.out.println("  =============================================");
        System.out.println("  Students on record: " + students.size());
        System.out.println("  Subjects tracked  : " + String.join(", ", defaultSubjects));
        System.out.println();
    }

    // ── Menu ────────────────────────────────────────────────────
    static void printMenu() {
        System.out.println();
        System.out.println("  ====================================");
        System.out.println("     1. Add Student                   ");
        System.out.println("     2. View All Students             ");
        System.out.println("     3. Full Report (sorted by avg)   ");
        System.out.println("     4. Search Student                ");
        System.out.println("     5. Edit Student                  ");
        System.out.println("     6. Delete Student                ");
        System.out.println("     7. Top & Bottom Performers       ");
        System.out.println("     8. Subject-wise Analysis         ");
        System.out.println("     9. Exit                          ");
        System.out.println("  ====================================");
    }

    // ── 1. Add ──────────────────────────────────────────────────
    static void addStudent() {
        System.out.println("\n  ── Add New Student ──────────────────");
        String name = readNonEmpty("  Full Name   : ");
        String roll = readUniqueRoll("  Roll Number : ");

        double[] marks = new double[defaultSubjects.length];
        System.out.println("  Enter marks (0 to 100) for each subject:");
        for (int i = 0; i < defaultSubjects.length; i++) {
            marks[i] = readMark("    " + defaultSubjects[i] + ": ");
        }

        Student s = new Student(nextId++, name, roll,
                                Arrays.copyOf(defaultSubjects, defaultSubjects.length),
                                marks);
        students.add(s);
        saveToFile();
        System.out.println("   Student added. Average: "
                + String.format("%.2f", s.getAverage())
                + "  Grade: " + s.getGrade());
    }

    // ── 2. View All ─────────────────────────────────────────────
    static void viewAll() {
        if (students.isEmpty()) { noRecords(); return; }
        printTableHeader();
        students.forEach(System.out::println);
        printTableFooter(students.size());
    }

    // ── 3. Full Report (sorted by average desc) ─────────────────
    static void viewReport() {
        if (students.isEmpty()) { noRecords(); return; }
        System.out.println("\n  ── Full Class Report (highest average first) ──");
        printTableHeader();
        students.stream()
                .sorted((a, b) -> Double.compare(b.getAverage(), a.getAverage()))
                .forEach(System.out::println);
        printTableFooter(students.size());

        // Class average
        double classAvg = students.stream()
                .mapToDouble(Student::getAverage).average().orElse(0);
        System.out.printf("  Class Average: %.2f%n", classAvg);
    }

    // ── 4. Search ───────────────────────────────────────────────
    static void searchStudent() {
        System.out.print("\n  Search (name or roll number): ");
        String q = sc.nextLine().trim().toLowerCase();
        List<Student> res = students.stream()
            .filter(s -> s.getName().toLowerCase().contains(q)
                      || s.getRollNumber().toLowerCase().contains(q))
            .toList();
        if (res.isEmpty()) { System.out.println("   No match found."); return; }
        printTableHeader();
        res.forEach(s -> {
            System.out.println(s);
            String[] subj  = s.getSubjects();
            double[] marks = s.getMarks();
            for (int i = 0; i < subj.length; i++)
                System.out.printf("        %-12s : %.1f%n", subj[i], marks[i]);
        });
        printTableFooter(res.size());
    }

    // ── 5. Edit ─────────────────────────────────────────────────
    static void editStudent() {
        Student s = findById("  Enter ID to edit: ");
        if (s == null) return;
        System.out.println("  Editing: " + s.getName() + " (" + s.getRollNumber() + ")");
        System.out.println("  (Press Enter to keep current value)");

        String name = readOrKeep("  New Name  [" + s.getName() + "]: ", s.getName());
        String roll = readOrKeep("  New Roll  [" + s.getRollNumber() + "]: ", s.getRollNumber());
        s.setName(name);
        s.setRollNumber(roll);

        System.out.print("  Re-enter marks? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            double[] marks = new double[defaultSubjects.length];
            for (int i = 0; i < defaultSubjects.length; i++)
                marks[i] = readMark("    " + defaultSubjects[i] + ": ");
            s.setMarks(marks);
        }
        saveToFile();
        System.out.println("   Record updated.");
    }

    // ── 6. Delete ───────────────────────────────────────────────
    static void deleteStudent() {
        Student s = findById("  Enter ID to delete: ");
        if (s == null) return;
        System.out.println("  About to delete: " + s);
        System.out.print("  Confirm? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            students.remove(s);
            saveToFile();
            System.out.println("   Record deleted.");
        } else {
            System.out.println("  Cancelled.");
        }
    }

    // ── 7. Top & Bottom ─────────────────────────────────────────
    static void viewToppers() {
        if (students.isEmpty()) { noRecords(); return; }
        Student top = students.stream()
            .max(Comparator.comparingDouble(Student::getAverage)).get();
        Student bot = students.stream()
            .min(Comparator.comparingDouble(Student::getAverage)).get();
        long failing = students.stream()
            .filter(s -> s.getStatus().equals("FAIL")).count();

        System.out.println("\n   Top Performer      : " + top.getName()
                + " (" + String.format("%.2f", top.getAverage()) + " — " + top.getGrade() + ")");
        System.out.println("   Needs Attention    : " + bot.getName()
                + " (" + String.format("%.2f", bot.getAverage()) + " — " + bot.getGrade() + ")");
        System.out.println("   Failing Students   : " + failing);
    }

    // ── 8. Subject Analysis ─────────────────────────────────────
    static void subjectAnalysis() {
        if (students.isEmpty()) { noRecords(); return; }
        System.out.println("\n  ── Subject-wise Class Averages ──────");
        System.out.printf("  %-14s  %-8s  %-8s  %-8s%n",
                          "Subject","Avg","Min","Max");
        System.out.println("  " + "─".repeat(46));
        for (int i = 0; i < defaultSubjects.length; i++) {
            final int idx = i;
            DoubleSummaryStatistics stats = students.stream()
                .mapToDouble(s -> s.getMarks()[idx])
                .summaryStatistics();
            System.out.printf("  %-14s  %6.2f    %6.2f    %6.2f%n",
                defaultSubjects[i], stats.getAverage(),
                stats.getMin(), stats.getMax());
        }
    }

    // ── File I/O ────────────────────────────────────────────────
    static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Student s : students) pw.println(s.toCSV());
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
                Student s = Student.fromCSV(line);
                if (s != null) {
                    students.add(s);
                    if (s.getId() >= nextId) nextId = s.getId() + 1;
                    defaultSubjects = s.getSubjects(); // sync subjects
                }
            }
        } catch (IOException e) {
            System.out.println("   Load failed: " + e.getMessage());
        }
    }

    // ── UI Helpers ───────────────────────────────────────────────
    static void noRecords() { System.out.println("  No student records yet."); }

    static void printTableHeader() {
        System.out.println();
        System.out.printf("  %-5s %-10s %-22s %-8s %-6s %-6s%n",
                          "ID","ROLL","NAME","AVG","GRADE","STATUS");
        System.out.println("  " + "─".repeat(62));
    }

    static void printTableFooter(int n) {
        System.out.println("  " + "─".repeat(62));
        System.out.println("  " + n + " record(s).");
    }

    static Student findById(String prompt) {
        int id = readInt(prompt, 1, Integer.MAX_VALUE);
        return students.stream().filter(s -> s.getId() == id).findFirst()
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

    static double readMark(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                double v = sc.nextDouble(); sc.nextLine();
                if (v >= 0 && v <= 100) return v;
            } else { sc.nextLine(); }
            System.out.println("   Marks must be between 0 and 100.");
        }
    }

    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim();
            if (!v.isEmpty()) return v;
            System.out.println("   Field cannot be empty.");
        }
    }

    static String readUniqueRoll(String prompt) {
        while (true) {
            System.out.print(prompt);
            String v = sc.nextLine().trim().toUpperCase();
            if (v.isEmpty()) { System.out.println("   Roll number cannot be empty."); continue; }
            boolean dup = students.stream().anyMatch(s -> s.getRollNumber().equals(v));
            if (dup) { System.out.println("   Roll number already exists."); continue; }
            return v;
        }
    }

    static String readOrKeep(String prompt, String current) {
        System.out.print(prompt);
        String v = sc.nextLine().trim();
        return v.isEmpty() ? current : v;
    }
}