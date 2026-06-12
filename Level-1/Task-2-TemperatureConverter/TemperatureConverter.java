import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("============================================");
        System.out.println("     Temperature Converter              ");
        System.out.println("============================================");

        while (running) {
            System.out.println("\nChoose conversion:");
            System.out.println("  1. Celsius to Fahrenheit");
            System.out.println("  2. Fahrenheit to Celsius");
            System.out.println("  3. Exit");
            System.out.print("Enter choice (1/2/3): ");

            int choice = getValidInt(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter temperature in Celsius: ");
                    double celsius = getValidDouble(scanner);
                    double fahrenheit = celsiusToFahrenheit(celsius);
                    System.out.printf("%.2f°C  =  %.2f°F%n", celsius, fahrenheit);
                    printTemperatureContext(fahrenheit, false);
                    break;

                case 2:
                    System.out.print("Enter temperature in Fahrenheit: ");
                    double fahr = getValidDouble(scanner);
                    double celsiusResult = fahrenheitToCelsius(fahr);
                    System.out.printf("%.2f°F  =  %.2f°C%n", fahr, celsiusResult);
                    printTemperatureContext(celsiusResult, true);
                    break;

                case 3:
                    System.out.println("Goodbye! Stay at the right temperature.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    // Converts Celsius to Fahrenheit.
    // Formula: (C × 9/5) + 32
    
    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    // Converts Fahrenheit to Celsius.
    // Formula: (F - 32) × 5/9
     
    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }


    static void printTemperatureContext(double temp, boolean isCelsius) {
        double celsius = isCelsius ? temp : fahrenheitToCelsius(temp);

        if (celsius <= 0) {
            System.out.println("Freezing cold! Bundle up.");
        } else if (celsius <= 10) {
            System.out.println("Very cold. Wear a heavy jacket.");
        } else if (celsius <= 20) {
            System.out.println("Cool and comfortable. Light jacket recommended.");
        } else if (celsius <= 30) {
            System.out.println(" Warm and pleasant. Enjoy the weather!");
        } else if (celsius <= 40) {
            System.out.println("Hot! Stay hydrated.");
        } else {
            System.out.println("Extreme heat! Stay indoors if possible.");
        }
    }

    static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid temperature: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}