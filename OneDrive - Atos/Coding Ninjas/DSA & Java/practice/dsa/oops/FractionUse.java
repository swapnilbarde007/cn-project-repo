package dsa.oops;

public class FractionUse {

    public static void main(String[] args) {
        Fraction f1 = new Fraction(2, 6);
        f1.printFract();
        // f1.increment();
        // f1.printFract();
        Fraction f2 = new Fraction(1, 4);
        f2.printFract();
        f1.addFraction(f2);
        f1.printFract();
    }
}
