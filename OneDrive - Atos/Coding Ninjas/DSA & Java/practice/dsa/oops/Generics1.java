package dsa.oops;

public class Generics1<T, U> {
    T first;
    U second;

    public Generics1() {

    }

    public Generics1(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return this.second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public void print() {
        System.out.println(this.getFirst() + " " + this.second);
    }

}
