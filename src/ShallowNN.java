import javax.management.MBeanTrustPermission;
import java.util.Arrays;

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
        b = new BetterArray (new int[]{x.array.length, 1, 1},1);
        a = new BetterArray (new int[]{x.array.length, d, 1},0);
    }

    public boolean predict(BetterArray x) {
        this.x = x;
        propagate ();
        System.out.println ("this a " + this.a.array[0][0][0] );
        if (this.a.array[0][0][0] >= .5) {
            System.out.println ("Mooooooo" );
            return true;
        }
        System.out.println ("boooooooo" );
        return false;
    }

    public  void optimize(int numEpochs) {
        for (int i = 0; i < numEpochs; i++) {
            int[] indexes = utils.getRandomIndexes (x.array.length);
            x = utils.shuffleBetterArray (x, indexes);
            y = utils.shuffleLabels (y, indexes);
            propagate ();
            backpropagate ();
        }
    }

    public void propagate() {
//        System.out.print ("x " );
//        x.printShape ();
//        System.out.print ("w ");
//        w.printShape ();
        BetterArray z = x.dot (w);
//        System.out.print ("z ");
//        z.printShape ();
//        b.broadcastArray(z).printShape();
//        b.broadcastArray (z).printShape ();
        z = z.add (b.broadcastArray (z));
        this.a = Activations.applyFuntion (z , "sigmoid");
//        System.out.print("A: ");
//        a.printShape ();
//        a.printArray ();
    }

    public void backpropagate() {
        float[] logits = utils.flatten (this.a);
        float[] labels = new float[this.y.length];
        for (int i = 0; i < labels.length; i++) {
            if (y[i] == true) labels[i] = 1;
            else labels[i] = 0;
        }
//        System.out.println ("logits "  + Arrays.toString (logits));
//        System.out.println ("labels "  + Arrays.toString (labels));
        float loss = utils.crossEntropyLoss (logits, labels);
        System.out.println ("Loss: " + loss);
        float avg = (float)(1./(float)(logits.length)); //Jaba
//        x.printShape ();
//        System.out.println ("logits " + Arrays.toString (logits));
//        System.out.println ("labels " + Arrays.toString (labels));
//        System.out.println ("x " + Arrays.deepToString (x.transposeArray ().array ));
        BetterArray dw =  x.transposeArray ().dot(utils.unFlatten(utils.subtractArray (logits, labels)));
        dw.multByScalar (avg);
//        System.out.println ("avg " + avg);
//        System.out.print ("done" );
//        dw.printShape ();
        dw.multByScalar (lr);

        BetterArray db = utils.unFlatten(utils.subtractArray (logits, labels));
        db.multByScalar (avg);
//        System.out.println ("DW sum  " + dw.sum ());
//        System.out.println ("w sum " +  w.sum ());
        this.w = w.subtract(dw);
        this.b = b.subtract (db);
    }

//    public void backward() {

//    }
}
