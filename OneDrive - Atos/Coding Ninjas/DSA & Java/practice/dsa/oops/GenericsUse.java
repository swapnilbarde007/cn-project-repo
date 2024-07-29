package dsa.oops;

public class GenericsUse {
    public static void main(String[] args) {
        Generics1<Generics1<Integer, String>, String> gs = new Generics1<>();

        Generics1<Integer, String> gsParam = new Generics1<>();
        gsParam.setFirst(25);
        gsParam.setSecond("Swapnil");

        gs.setFirst(gsParam);
        gs.setSecond("Hello");

        System.out.println(gs.getFirst().getFirst());
        System.out.println(gs.getFirst().getSecond());
        System.out.println(gs.getSecond());

    }

}
