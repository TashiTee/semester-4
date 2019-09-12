/**
 * Complex
 *
 * The Complex number represented by this class only consists of 2 doubles (a and b), the i is added manually
 * Represents complex numbers which are combinations of real and imaginary numbers (a + bi). i = sqrt(-1)
 * Accompanying document: https://www.mathsisfun.com/numbers/complex-numbers.html
 */
public class Complex {

    final double m_real;
    final double m_imaginary;


    // Constructors
    /** Creates a complex object for number 0 */
    public Complex() {
        this(0, 0);
    }

    /**
     * Creates a Complex object a + 0
     */
    /** Create a complex object with 0 for b */
    public Complex(double real) {
        this(real, 0);
    }

    /**
     *  Creates a complex object with a specified a + bi
    */
    public Complex(double real, double imaginary) {
        this.m_real = real;
        this.m_imaginary = imaginary;
    }

    /**
     * Method Getters
     * return a real part of complex number
     */
    public double getRealPart() {
        return m_real;
    }

    /** Return imaginary part of complex number */
    public double getImaginaryPart() {
        return m_imaginary;
    }

    /**
     * Adds the passed Complex number to the current object's Complex number.
     * Formula: (a+bi) + (c+di) = (a+c) + (b+d)i
     * returns a total when a is added to current object
     */
    public Complex add(Complex a) {
        return new Complex(this.getRealPart() + a.getRealPart(), this.getImaginaryPart() + a.getImaginaryPart());
    }

    /**
     * Subtract the passed Complex number from the current object's Complex number
     * Formula: (a+bi) - (c+di) = (a-c) + (b-d)i
     * @param a - Complex number to subtract from current object' Complex Number.
     * @return the difference between the current object's complex number and the passed complex number a.
     */
    public Complex subtract(Complex a) {
        return new Complex(this.getRealPart() - a.getRealPart(), this.getImaginaryPart() - a.getImaginaryPart());
    }

    /**
     * Multiples the current object's complex number with the passed complex number a.
     * Formula:
     *  - (a+bi)(c+di) = (ac−bd) + (ad+bc)i
     *  - (a+bi)(c+di) = ac + adi + bci + bd(i^2)
     * @param a - Complex number to multiply to.
     * @return
     */
//
    /** Multiply a complex number by this complex number
     * (a+bi)(c+di) = (ac−bd) + (ad+bc)i
     * */
    public Complex multiply(Complex secondComplex) {
        return new Complex(m_real * secondComplex.m_real - m_imaginary * secondComplex.m_imaginary,
                m_imaginary * secondComplex.m_real + m_real * secondComplex.m_imaginary);
    }

    /**
     * Divides the current object's complex number with the passed complex number a.
     *
     * Formula:
     * - (a+bi)/(c+di) = (ac + bd)/(c^2 + d^2) + (bc - ad)i/(c^2 + d^2)
     * @param a - CN to divide to
     * @return quotient
     */
//
    /** divide a complex number by this complex number */
    public Complex divide(Complex secondComplex) {
        return new Complex((m_real * secondComplex.m_real + m_imaginary * secondComplex.m_imaginary) /
                (Math.pow(secondComplex.m_real, 2) + Math.pow(secondComplex.m_imaginary, 2)),
                (m_imaginary * secondComplex.m_real - m_real * secondComplex.m_imaginary) /
                        (Math.pow(secondComplex.m_real, 2) +  Math.pow(secondComplex.m_imaginary, 2)));
    }

    /**
     * Returns the absolute value for a complex number
     *
     * Formula:
     * - |a + bi| = sqrt(a^2 + b^2)
     * @param a the complex number to get the absolute value of
     * @return the absolute value for the complex number passed
     */
    public static Complex abs(Complex a) {
        return new Complex(Math.sqrt((a.getRealPart() * a.getRealPart()) + (a.getImaginaryPart() * a.getImaginaryPart())));
    }


    /**
     * Returns a string representation of an object
     */
    /** Retrun a string description
     of this complex number */
    public String toString() {
        return m_imaginary == 0 ? m_real + "" : "(" + m_real + " + " + m_imaginary + "i)";
    }
}