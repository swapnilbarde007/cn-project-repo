package dsa.oops;

public class ComplexNumbers {
    int real;
    int imaginary;

    public ComplexNumbers(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void print() {
        System.out.println(real + " + i" + imaginary);
    }

    public void plus(ComplexNumbers c2) {
        this.real += c2.real;
        this.imaginary += c2.imaginary;
    }

    public void multiply(ComplexNumbers c2) {
        int tmp1 = (this.real * c2.real);
        int tmp2 = (this.real * c2.imaginary) + (this.imaginary * c2.real);
        int tmp3 = (this.imaginary * c2.imaginary) * -1;
        this.real = tmp1 + tmp3;
        this.imaginary = tmp2;
    }

}
