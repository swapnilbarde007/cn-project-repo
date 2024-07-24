package dsa.oops;

public class PolynomialUse {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.setCoefficient(2, 3);
        p1.setCoefficient(3, 3);
        p1.setCoefficient(0, 2);

        Polynomial p2 = new Polynomial();
        p2.setCoefficient(2, 8);
        p2.setCoefficient(3, 2);
        p2.setCoefficient(0, 6);

        Polynomial p3 = p1.add(p2);

        p3.print();
    }
}
