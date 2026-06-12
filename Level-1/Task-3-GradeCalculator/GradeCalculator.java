import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("      Student Grade Average Calculator     ");
        System.out.println("==============================================");

        System.out.print("How many students? ");
        int studentCount = getValidPositiveInt(scanner);

        System.out.print("How many subjects per student? ");
        int subjectCount = getValidPositiveInt(scanner);

        String[] names   = new String[studentCount];
        double[] averages = new double[studentCount];



        for (int i = 0; i < studentCount; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter student name: ");
            scanner.nextLine(); // flush buffer
            names[i] = scanner.nextLine().trim();
            if (names[i].isEmpty()) names[i] = "Student " + (i + 1);

            double total = 0;
            for (int j = 0; j < subjectCount; j++) {
                System.out.print("  Subject " + (j + 1) + " marks (0 to 100): ");
                double mark = getValidMark(scanner);
                total += mark;
            }

            averages[i] = total / subjectCount;
        }

        printReport(names, averages);

        printTopAndBottom(names, averages);

        scanner.close();
    }

    static void printReport(String[] names, double[] averages) {
        System.out.println("\n==============================================");
        System.out.println("               GRADE REPORT               ");
        System.out.println("==============================================");
        System.out.printf("%-20s %-10s %-10s %-10s%n",
                           "Name", "Average", "Grade", "Status");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < names.length; i++) {
            String grade  = getLetterGrade(averages[i]);
            String status = averages[i] >= 40 ? " Pass" : " Fail";
            System.out.printf("%-20s %-10.2f %-10s %-10s%n",
                               names[i], averages[i], grade, status);
        }

        System.out.println("==============================================");
    }

    static void printTopAndBottom(String[] names, double[] averages) {
        int topIndex    = 0;
        int bottomIndex = 0;

        for (int i = 1; i < averages.length; i++) {
            if (averages[i] > averages[topIndex])    topIndex    = i;
            if (averages[i] < averages[bottomIndex]) bottomIndex = i;
        }

        System.out.println("\nTop Performer   : " + names[topIndex]
                           + " (" + String.format("%.2f", averages[topIndex]) + ")");
        System.out.println("Needs Improvement: " + names[bottomIndex]
                           + " (" + String.format("%.2f", averages[bottomIndex]) + ")");
    }

    static String getLetterGrade(double avg) {
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        if (avg >= 40) return "E";
        return "F";
    }

    static double getValidMark(Scanner scanner) {
        double mark = -1;
        while (mark < 0 || mark > 100) {
            if (!scanner.hasNextDouble()) {
                System.out.print("   Enter a number between 0 and 100: ");
                scanner.next();
                continue;
            }
            mark = scanner.nextDouble();
            if (mark < 0 || mark > 100) {
                System.out.print("   Mark must be 0 to 100. Try again: ");
                mark = -1;
            }
        }
        return mark;
    }

    static int getValidPositiveInt(Scanner scanner) {
        int value = 0;
        while (value <= 0) {
            if (!scanner.hasNextInt()) {
                System.out.print("  Please enter a positive number: ");
                scanner.next();
                continue;
            }
            value = scanner.nextInt();
            if (value <= 0) System.out.print("  Must be greater than 0: ");
        }
        return value;
    }
}