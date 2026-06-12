import java.util.Scanner;

public class NumberGrid {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[3][3];

        System.out.println("============================================");
        System.out.println("      3*3 Number Grid — Fill & Find  ");
        System.out.println("============================================");

        // ── Step 1: User fills the matrix ──────────────────────
        System.out.println("\n Enter 9 numbers to fill the 3*3 matrix:");
        System.out.println("   (row by row, press Enter after each number)\n");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("  Enter number for [Row " + (i + 1)
                                 + "][Col " + (j + 1) + "]: ");
                grid[i][j] = getValidInt(scanner);
            }
            System.out.println();   
        }

        System.out.println("Your 3*3 Matrix:");
        displayGrid(grid);

        boolean keepSearching = true;
        while (keepSearching) {
            System.out.print("\nEnter a number to search (or -1 to quit): ");
            int target = getValidInt(scanner);

            if (target == -1) {
                System.out.println(" Exiting. Goodbye!");
                break;
            }

            searchAndReport(grid, target);

            System.out.print("\nSearch another number? (y/n): ");
            String answer = scanner.next().trim().toLowerCase();
            keepSearching = answer.equals("y") || answer.equals("yes");
        }

        scanner.close();
    }

    static void displayGrid(int[][] grid) {
        System.out.println();
        System.out.println("  ================");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf(" %3d ", grid[i][j]);
            }
            System.out.println("   Row " + (i + 1));
        }
        System.out.println("  =================");
        System.out.println("    Col1 Col2 Col3");
        System.out.println();
    }

    static void searchAndReport(int[][] grid, int target) {
        boolean found = false;
        int matchCount = 0;

        System.out.println("\n============================================");
        System.out.println("  Search Result for: " + target);
        System.out.println("============================================");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == target) {
                    found = true;
                    matchCount++;
                    System.out.println("  FOUND at  Row " + (i + 1)
                                       + ", Column " + (j + 1)
                                       + "  (index [" + i + "][" + j + "])");
                }
            }
        }

        if (!found) {
            System.out.println("  Number " + target
                               + " does NOT exist in the grid.");
        } else {
            System.out.println("\n  Total occurrences: " + matchCount);
        }

        if (found) {
            System.out.println("\n  Position map (* = match):\n");
            System.out.println("  ================");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == target) {
                        System.out.printf("[%2d*]", grid[i][j]);
                    } else {
                        System.out.printf(" %3d ", grid[i][j]);
                    }
                }
                System.out.println("");
            }
            System.out.println("  =================");
        }
        System.out.println("============================================");
    }

    static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("   Not a number. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}