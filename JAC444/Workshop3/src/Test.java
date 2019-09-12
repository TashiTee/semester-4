/**
 * Test
 */
import java.util.Scanner;
public class Test {

    public static void main(String[] args) {

        double real;
        double imaginary;
       Scanner in = new Scanner(System.in);
        System.out.print("Enter the first complex number: ");
        real = in.nextDouble();
        imaginary = in.nextDouble();

        Complex result1 = new Complex(real, imaginary);

        System.out.print("Enter the second complex number: ");
        real = in.nextDouble();
        imaginary = in.nextDouble();

        // Create the second complex number
        Complex result2 = new Complex(real, imaginary);

 calculate(result1, result2);
    }


    /**
     * Does the calculations for the passed complex numbers
     * @param result1
     * @param result2
     */
    public static void calculate(Complex result1, Complex result2) {
        System.out.println(result1.toString() + " + " + result2.toString() + " = " + result1.add(result2).toString());
        System.out.println(result1.toString() + " - " + result2.toString() + " = " + result1.subtract(result2).toString());
        System.out.println(result1.toString() + " * " + result2.toString() + " = " + result1.multiply(result2).toString());
        System.out.println(result1.toString() + " / " + result2.toString() + " = " + result1.divide(result2).toString());
        System.out.println("|" + result1.toString() + "| = " + Complex.abs(result1).toString());
    }
}

