package dsa.oops;

public class DynamicArrayUse {

    public static void main(String[] args) {
        DynamicArray da = new DynamicArray();
        da.add(55);
        da.add(56);
        da.add(57);
        da.add(58);
        da.add(59);
        da.add(60);
        System.out.println(da.get(5));
        da.removeLast();
        System.out.println(da.get(5));
        da.add(60);
    }
}
