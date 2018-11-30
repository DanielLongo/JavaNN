import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
    public static void CELTests() {
        float[] a = new float[]{1,0,0,(float).4,1};
        float[] y = new float[]{1,0,1,0,0};
        float loss = utils.crossEntropyLoss(a, y);
        System.out.println ("Loss " + loss);
    }
    public static void main(String[] args) {

//        CELTests ();
        ShallowNNTests ();
    }

    public static void ShallowNNTests() {
        BetterArray x = new BetterArray(new float[][][]{{{2},{4},{9}},{{3},{2},{2}}});
        boolean[] y = new boolean[] {true,false};
        ShallowNN nn = new ShallowNN (x, y, 1);
        nn.optimize (10);
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
