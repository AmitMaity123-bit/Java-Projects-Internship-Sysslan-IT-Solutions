import java.util.Scanner;
import java.util.Random;

public class PasswordGenerator {

    static final String LOWERCASE   = "abcdefghijklmnopqrstuvwxyz";
    static final String UPPERCASE   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String DIGITS      = "0123456789";
    static final String SPECIALS    = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    static final String AMBIGUOUS   = "0O1lI";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("       Secure Password Generator            ");
        System.out.println("==============================================");

        System.out.print("Enter desired password length (8 to 64): ");
        int length = getValidLength(scanner);

        boolean useLower   = askYesNo(scanner, "Include lowercase letters? (y/n): ");
        boolean useUpper   = askYesNo(scanner, "Include uppercase letters? (y/n): ");
        boolean useDigits  = askYesNo(scanner, "Include digits? (y/n): ");
        boolean useSpecial = askYesNo(scanner, "Include special characters? (y/n): ");
        boolean noAmbiguous = askYesNo(scanner, "Exclude ambiguous characters (0,O,l,1,I)? (y/n): ");

        StringBuilder pool = new StringBuilder();
        if (useLower)   pool.append(LOWERCASE);
        if (useUpper)   pool.append(UPPERCASE);
        if (useDigits)  pool.append(DIGITS);
        if (useSpecial) pool.append(SPECIALS);

        String charPool = pool.toString();
        if (noAmbiguous) {
            charPool = removeAmbiguous(charPool);
        }

        if (charPool.isEmpty()) {
            System.out.println("  No character types selected. Using lowercase by default.");
            charPool = LOWERCASE;
        }

        System.out.println("\n==============================================");
        System.out.println("          Generated Passwords               ");
        System.out.println("==============================================");

        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            String password = generatePassword(charPool, length, random);
            int strength = calculateStrength(password);
            String strengthLabel = getStrengthLabel(strength);
            System.out.printf("  %d. %s  %s%n", i, password, strengthLabel);
        }

        System.out.println("==============================================");
        System.out.println(" Tip: Use a password manager to store your passwords safely.");

        scanner.close();
    }

    static String generatePassword(String pool, int length, Random random) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(pool.length());
            password.append(pool.charAt(index));
        }
        return password.toString();
    }

    static String removeAmbiguous(String pool) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : pool.toCharArray()) {
            if (AMBIGUOUS.indexOf(c) == -1) {
                cleaned.append(c);
            }
        }
        return cleaned.toString();
    }

    static int calculateStrength(String password) {
        int score = 0;
        if (password.length() >= 12) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()\\-_=+\\[\\]{}|;:,.<>?].*")) score++;
        return score;
    }

    static String getStrengthLabel(int score) {
        if (score <= 1) return " Weak";
        if (score == 2) return " Fair";
        if (score == 3) return " Good";
        if (score == 4) return " Strong";
        return " Very Strong";
    }

    static int getValidLength(Scanner scanner) {
        int length = 0;
        while (length < 8 || length > 64) {
            if (!scanner.hasNextInt()) {
                System.out.print(" Enter a number between 8 and 64: ");
                scanner.next();
                continue;
            }
            length = scanner.nextInt();
            if (length < 8 || length > 64)
                System.out.print(" Length must be 8 to 64. Try again: ");
        }
        return length;
    }

    static boolean askYesNo(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String response = scanner.next().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
}