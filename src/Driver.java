import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void CELTests() {
        float[] a = new float[]{1,0,0,(float).4,1};
        float[] y = new float[]{1,0,1,0,0};
        float loss = utils.crossEntropyLoss(a, y);
        System.out.println ("Loss " + loss);
    }

    public static void utilsTests() {
        int[] indexes = utils.getRandomIndexes (10);
        System.out.println (Arrays.toString (indexes) );

    }

    public static void imageLoaderTest() {
        BetterArray x = utils.loadPictures();
        int[] indexes = utils.getRandomIndexes (x.array.length);
        x = utils.shuffleBetterArray (x, indexes);
//        float[] z = utils.flatten (x);
//        x.printShape ();
//        System.out.println ("z " + z.length );
        boolean[] y = new boolean[] {true, false, true, false};
        y = utils.shuffleLabels (y, indexes);
        ShallowNN nn = new ShallowNN (x, y, 1);
        nn.optimize (1000);
    }
    public static void main(String[] args) {
//        utilsTests ();
//        CELTests ();
        ShallowNNTests ();
//        imageLoaderTest();
//        try{ImageViewer aaa = new ImageViewer("C:\\Users\\alex\\Desktop\\mark-basarab-122141-unsplash.jpg",false);}catch(IOException e){throw new RuntimeErrorException(new Error(e.toString()));}

    }

    public static void ShallowNNTests() {
//        BetterArray x = new BetterArray(new float[][][]{{{2},{4},{9}},{{3},{2},{2}}});
        DataFetch imageLoader = new DataFetch (75,75, true);
//        System.out.println(Arrays.deepToString (imageLoader.images.));
        BetterArray x = imageLoader.images;
        boolean [] y = imageLoader.labels;

//        boolean[] y = new boolean[] {true,false};
//        x.printShape ();
//        System.out.println (Arrays.toString (y) );
        ShallowNN nn = new ShallowNN (x, y, 14);
        x.printShape ();
        nn.optimize (1);
        DataFetch imageLoaderTest = new DataFetch (10,10, true);
        int loc = 0;
        BetterArray currImage;
        boolean[] currLabel = new boolean[1];
        String currFilePath = "Error";
        while (loc < 20) {
            if (loc % 2 == 1) {
                currFilePath = DataFetch.DATASAMPLE_COW_DIRECTORY + "cow" + ((loc/2) + 1) + ".jpg" ;
                currLabel[0] = true;
            }
            else if (loc % 2 == 0) {
                currFilePath = DataFetch.DATASAMPLE_NOT_COW_DIRECTORY + "notcow" + (loc/2) + ".jpg";
                currLabel[0] = false;
            }

            currImage = utils.convert2DtoBetterArray (imageLoaderTest.images.array[loc]);
        //System.out.println ("Pred " +  nn.predict (xTest));
//            currImage.printShape ();
           try{
                ImageViewer a = new ImageViewer(currFilePath, (nn.predict(currImage)));
            } catch (IOException e) {}

            Scanner a = new Scanner(System.in);
           System.out.println("Press enter to continue");
           String buffer = a.nextLine ();


        loc += 1;
        }

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
