package dsa.oops;

interface A {
    public void method();
}

interface B extends A {
    public void method1();
}

class One {
    public void method() {
        System.out.println("Class One method");
    }
}

class Two extends One implements A {
    public void method() {
        System.out.println("Class Two method");
    }
}

public class Solution extends Two {
    public static void main(String[] args) {
        A a = new Two();
        a.method();
        Two b = new Two();
        b.method();
    }
}