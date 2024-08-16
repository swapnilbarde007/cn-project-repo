package dsa.oops;

public class Polynomial {
    DynamicArray da;
    int deg;
    int coff;

    public Polynomial() {
        this.da = new DynamicArray();
    }

    public void setCoefficient(int degree, int coeff) {
        int sz = this.da.size();
        if (degree > sz - 1) {
            for (int i = this.da.size(); i <= degree; i++) {
                this.da.add(0);
            }
            this.da.set(degree, coeff);
        } else {
            this.da.set(degree, coeff);
        }
    }

    public Polynomial(int deg, int coff) {
        this.da = new DynamicArray();
        for (int i = 0; i <= deg; i++) {
            this.da.add(0);
        }
        this.da.set(deg, coff);
    }

    public boolean setMonomial(int deg, int coff) {
        int sz = this.da.size() - 1;
        if (deg > sz) {
            for (int i = this.da.size(); i <= deg; i++) {
                this.da.add(0);
            }
            this.da.set(deg, coff);
        } else {
            this.da.set(deg, coff);
        }
        return true;
    }

    public void print() {
        int sz = da.size();
        if (da.get(0) != 0) {
            System.out.print(da.get(0) + " ");
        }
        for (int i = 1; i < sz; i++) {
            if (da.get(i) != -1 && da.get(i) != 0) {
                System.out.print(da.get(i) + "X" + i + " ");
            }
        }

    }

    public Polynomial add(Polynomial p) {
        Polynomial sum = new Polynomial();
        int pt1 = p.da.size();
        int pt2 = this.da.size();
        if (pt1 > pt2) {
            sum.setCoefficient(pt1 - 1, 0);
        } else {
            sum.setCoefficient(pt2 - 1, 0);
        }
        while (pt1 > 0 && pt2 > 0) {
            if (pt1 == pt2) {
                sum.setCoefficient(pt1 - 1, p.da.get(pt1 - 1) + this.da.get(pt2 - 1));
            } else {
                sum.setCoefficient(pt1 - 1, p.da.get(pt1 - 1));
                sum.setCoefficient(pt2 - 1, this.da.get(pt2 - 1));
            }
            pt1--;
            pt2--;
        }
        return sum;
    }
}
