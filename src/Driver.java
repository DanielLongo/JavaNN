import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        BetterArray x = new BetterArray(new float[][][]{{{2},{4},{9}},{{3},{2},{2}}});
        int[] y = new int[] {1,2};
        ShallowNN nn = new ShallowNN (x, y, 10);
        nn.propagate ();
//        arrayTests ();
    }

    public static void arrayTests() {
        BetterArray a = new BetterArray(new float[][][]{
                {
                    {2}, {4}, {9}
                },
                {
                    {3}, {2}, {2}
                }
        });
        a.printShape ();
        BetterArray b = new BetterArray(new float [][][]{
                {
                    {2}, {3}, {2}, {17}
                },
                {
                    {4}, {4}, {9}, {23}
                },
                {
                    {7}, {3}, {11}, {23}
                }
        });
        b.printShape ();

        a.dot (b);
        b.printShape ();
//        BetterArray b = new BetterArray(new float [][][] {{{2}},{{3}},{{4}}});
//        b.printArray ();
//        BetterArray t =  b.broadcastArray (a);
//        t.printShape ();
//        t.printArray ();
//        a1.printShape ();
//        a1.randomizeArray (0,1);
//        a1.printShape ();
//        System.out.printf ("A: ");
//        a.printShape();
//        System.out.printf ("B: ");
//        b.printShape();
//        BetterArray c = a.dot(b);
//        System.out.printf ("C: ");
//        c.printShape ();
//        System.out.println ("c " + Arrays.deepToString (c.array));
////        System.out.println(a1.getValue (0,0,0));
//        BetterArray d = Activations.applyFuntion (c, "sigmoid");
//        System.out.println ("d " + Arrays.deepToString (d.array));
//        d = Activations.applyFuntion (c, "ReLU");
//        System.out.println ("d " + Arrays.deepToString (d.array));
//        d = Activations.applyFuntion (c, "tanh");
//        System.out.println ("d " + Arrays.deepToString (d.array));
//        System.out.printf ("Wow");
//        System.out.println ("d " + d.sum () );

    }
}
