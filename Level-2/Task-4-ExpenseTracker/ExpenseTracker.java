import java.io.*;
import java.util.*;

public class ExpenseTracker {

    static final String FILE_NAME = "expenses.txt";

    public static void addExpense(Scanner sc) {
        try {
            System.out.print("Enter Expense Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Amount: ");
            double amount = Double.parseDouble(sc.nextLine());

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(FILE_NAME, true));

            writer.write(name + "," + amount);
            writer.newLine();

            writer.close();

            System.out.println("Expense Added Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewExpenses() {

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(FILE_NAME));

            String line;

            System.out.println("\n===== EXPENSE LIST =====");

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                System.out.println(
                        "Expense: " + data[0] +
                        " | Amount: " + data[1]
                );
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No expense records found.");
        }
    }

    public static void calculateTotal() {

        double total = 0;

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(FILE_NAME));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                total += Double.parseDouble(data[1]);
            }

            reader.close();

            System.out.println("\nTotal Expense: " + total);

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total");
            System.out.println("4. Exit");

            System.out.print("Choose Option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    calculateTotal();
                    break;

                case 4:
                    System.out.println("Program Closed.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}