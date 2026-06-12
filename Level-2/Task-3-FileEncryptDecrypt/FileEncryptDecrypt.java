import java.io.*;

public class FileEncryptDecrypt {

    private static final int SHIFT = 3;

    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            result.append((char) (ch + SHIFT));
        }

        return result.toString();
    }

    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            result.append((char) (ch - SHIFT));
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String inputFile = "input.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(inputFile));

            StringBuilder content =
                    new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();

            String encrypted =
                    encrypt(content.toString());

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(encryptedFile));

            writer.write(encrypted);
            writer.close();

            String decrypted =
                    decrypt(encrypted);

            BufferedWriter writer2 =
                    new BufferedWriter(
                            new FileWriter(decryptedFile));

            writer2.write(decrypted);
            writer2.close();

            System.out.println("Encryption Successful!");
            System.out.println("Encrypted File: "
                    + encryptedFile);

            System.out.println("Decrypted File: "
                    + decryptedFile);

        } catch (IOException e) {
            System.out.println("Error: "
                    + e.getMessage());
        }
    }
}