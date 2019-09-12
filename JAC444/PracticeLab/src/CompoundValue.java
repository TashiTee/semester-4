import java.util.Scanner;

public class CompoundValue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);	// Create a new Scanner object.
        final double MONTHLY_INTEREST_RATE = 0.00417;	// Initialize constant value

        // Prompt the user to enter a montly saving amount
        System.out.print("Enter the monthly saving amount: ");
        double savingAmount = input.nextDouble();

        // Compute first month account value
        double total = 100 * (1 + MONTHLY_INTEREST_RATE);
        // Compute second month account value
        total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);
        // Compute third month account value
        total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);
        // Compute forth month account value
        total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);
        // Compute fifth month account value
        total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);
        // Compute sixth month account value
        total = (100 + total) * (1 + MONTHLY_INTEREST_RATE);

//        String s = String.format("%.2f", total); // can use string s to round up

        // Display result
        System.out.println("After the sixth month, the account value is " + Math.round(total * 100) / 100.0);
    }
}