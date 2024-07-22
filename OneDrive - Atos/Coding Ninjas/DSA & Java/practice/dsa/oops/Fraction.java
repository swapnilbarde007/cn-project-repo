package dsa.oops;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private void simplify() {
        int gcd = 1;
        int smaller = Math.min(numerator, denominator);
        for (int i = 2; i <= smaller; i++) {
            if (numerator % i == 0 && denominator % i == 0) {
                gcd = i;
            }
        }
        numerator = numerator / gcd;
        denominator = denominator / gcd;
    }

    public void increment() {
        numerator = numerator + denominator;
        simplify();
    }

    public void printFract() {
        System.out.println(numerator + "/" + denominator);
    }

    public void setNumerator(int numerator) {

        this.numerator = numerator;
        simplify();
    }

    public void setDenominator(int denominator) {

        this.denominator = denominator;
        simplify();
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void addFraction(Fraction b) {
        int r = this.numerator * b.denominator;
        int l = this.denominator * b.numerator;
        int base = this.denominator * b.denominator;
        setNumerator(l + r);
        setDenominator(base);
    }
}
