package dsa.oops;

public class Generics4 {
    public static <T extends DynamicArray> void print(T arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].print();
        }
    }

    public static void main(String[] args) {
        DynamicArray ds1 = new DynamicArray();
        ds1.add(2);
        ds1.add(3);
        ds1.add(4);

        DynamicArray ds2 = new DynamicArray();
        ds2.add(9);
        ds2.add(10);
        ds2.add(11);

        DynamicArray[] dsArr = new DynamicArray[2];
        dsArr[0] = ds1;
        dsArr[1] = ds2;

        Generics4 gs = new Generics4();
        gs.print(dsArr);

    }
}
