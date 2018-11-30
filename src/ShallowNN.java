public class ShallowNN {
    private BetterArray w;
    private BetterArray b;
    private BetterArray a;
    private BetterArray x;
    private boolean[] y;
    private int d;
    private float lr;
    private float loss;
    public ShallowNN( BetterArray x, boolean[] y, int d) {
        this.d = d;
        this.x = x;
        this.y = y;
        this.lr = (float).01;
        initializeVariables ();
    }

    public void initializeVariables() {
        w = new BetterArray (new int[]{x.array[0].length, d, 1}, 0);
        b = new BetterArray (new int[]{x.array.length, d, 1},1);
        a = new BetterArray (new int[]{x.array.length, d, 1},0);
    }

    public  void optimize(int numEpochs) {
        for (int i = 0; i < numEpochs; i++) {
            propagate ();
            backpropagate ();

        }
    }

    public void propagate() {
//        System.out.print ("x " );
        x.printShape ();
        System.out.print ("w ");
        w.printShape ();
        BetterArray z = x.dot (w);
//        System.out.print ("z ");
        z.printShape ();
//        b.broadcastArray(z).printShape();
//        z = z.add (b.broadcastArray (z));
        BetterArray a = Activations.applyFuntion (z , "sigmoid");
//        System.out.print("A: ");
        a.printShape ();
        a.printArray ();
    }

    public void backpropagate() {
        float[] logits = utils.flatten (this.a);
        float[] labels = new float[this.y.length];
        for (int i = 0; i < labels.length; i++) {
            if (y[i] == true) labels[i] = 1;
            else labels[i] = 0;
        }
        loss = utils.crossEntropyLoss (logits, labels);
        System.out.println ("KSGGHKJSDHFGKJSDHKJGHSKDJGKJDSFHGLKJSKDFH: " + loss);
        float avg = (1/ logits.length);
        System.out.println ( "HERERERERER");
        x.printShape ();
        x.transposeArray ().printShape ();
        utils.unFlatten(utils.subtractArray (logits, labels)).printShape ();
        System.out.print ( "KLSJHKJSHAFKJSAHDFKLJ");
        BetterArray dw =  x.transposeArray ().dot(utils.unFlatten(utils.subtractArray (logits, labels)));
        dw.multByScalar (avg);
        System.out.print ("done" );
        dw.printShape ();
        dw.multByScalar (lr);
        System.out.println ("KSJDFKJSDFKJSDKFJHSDJKFHKJSDHFJKHKJFHDS THIS IS REAL " + dw.sum ());
        this.w = w.subtract(dw);
    }

//    public void backward() {

//    }
}
