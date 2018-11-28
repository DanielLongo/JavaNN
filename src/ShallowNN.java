public class ShallowNN {
    private BetterArray w;
    private BetterArray b;
    private BetterArray a;
    private BetterArray x;
    private int[] y;
    private int d;
    public ShallowNN( BetterArray x, int[] y, int d) {
        this.d = d;
        this.x = x;
        this.y = y;
        initializeVariables ();
    }

    public void initializeVariables() {
        w = new BetterArray (new int[]{x.array[0].length, d, 1}, 0);
        b = new BetterArray (new int[]{x.array.length, d, 1},1);
        a = new BetterArray (new int[]{x.array.length, d, 1},0);
    }

    public void propagate() {
        System.out.print ("x " );
        x.printShape ();
        System.out.print ("w ");
        w.printShape ();
        BetterArray z = x.dot (w);
        System.out.print ("z ");
        z.printShape ();
        b.broadcastArray(z).printShape();
//        z = z.add (b.broadcastArray (z));
        BetterArray a = Activations.applyFuntion (z , "sigmoid");
        System.out.print("A: ");
        a.printShape ();
        a.printArray ();

    }

    public void backpropagate() {

    }

//    public void backward() {

//    }
}
