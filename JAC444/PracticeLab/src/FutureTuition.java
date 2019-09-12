public class FutureTuition {
    public static void main(String[] args) {

        int totalCost = 0;		// Accumulate total cost of four years tution
        int tuition = 10000;
        int tuitionTenthYear = 0;

        for (int year = 1; year <= 14; year++) {
            // Increase tution by 5% every year
            tuition += (tuition * 0.05);

            if (year > 10) // Test for after 10 years
                totalCost += tuition; // Accumulate tution cost

            // Cost of tution in ten years
            if (year == 10)
                tuitionTenthYear = tuition;
        }

        // Display the cost of tution in ten years
        System.out.println("Tuition in ten years: $" + tuitionTenthYear);

        // Display the cost of four years' worth of tution after tenth year
        System.out.println("Total cost for four years' worth of tuition" +
                " after the tenth year: $" + totalCost);
    }
}