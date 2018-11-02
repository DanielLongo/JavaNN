public class Driver {
    public static void main(String[] args) {
        BetterArray a1 = new BetterArray (new int[]{10,10,1}, 0);
        a1.printShape ();
        a1.randomizeArray (0,1);
        a1.printShape ();
        System.out.println(a1.getValue (0,0,0));
        System.out.printf ("Wow");
    }
}
