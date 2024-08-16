class test {
    static int marks;
    static int numStudents;

    void set_marks(int marks) {
        this.numStudents = 100;
        this.marks = marks; // Line 1
    }
}

class MCQ {
    public static void main(String[] args) {
        test t = new test();
        t.set_marks(78); // Line 2
        System.out.println(test.marks); // Line 3
        t.numStudents = 200;
        System.out.println(test.numStudents);
    }
}