import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class utils {
    public static float crossEntropyLoss(float[] logits, float[] labels) {
        int m = labels.length;
//        System.out.println ("A " + sumArray (logits));
        logits = removeOnesAndZeros (logits);
//        System.out.println ("C " + sumArray (logits));
        float[] ones = broadcastArray (1,m);
        float chunckA = (float)-1.0/m;
//        chunckB = Y * np.log(a)
//        print("chunckB", sum(chunckB))
//        chunckC = (1.0 - Y)
//        print("chunckC", sum(chunckC))
//        chunckD = np.log(1.0 - a)
        float[] chunckB = multArray (labels, logArray (logits));
        float[] chunckC = subtractArray (ones, labels);
        float[] chunckD = logArray (subtractArray (ones, logits));

//        System.out.println ("Chunck A " + chunckA );
//        System.out.println ("Chunck B " + sumArray (chunckB));
//        System.out.println ("Chunck C " + sumArray (chunckC));
//        System.out.println ("Chunck D " + sumArray (chunckD));

        float loss = chunckA * sumArray (addArray (chunckB, multArray (chunckC,chunckD)));
//        System.out.println ("Chunck E " + loss);

//        System.out.println ( (
//                        sumArray (logArray (logits)))
//        );

//        float[] lArray = multArray (
//            addArray (
//                     multArray (labels,
//                             logArray (logits)),
//                    subtractArray (ones, labels)),
//            logArray (
//                    subtractArray (ones,logits))
//        );
//
//        System.out.println (Arrays.toString (lArray));

//        float loss = -(float)(1.0/m) * sumArray (lArray);
        return loss;
    }

    public static float[] removeOnesAndZeros(float[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 0) x[i] = (float).001;
            else if (x[i] == 1) x[i] = (float).999;
        }
        return x;
    }

    public static float[] addArray(float[] a, float[] b) {
        float[] out = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i] + b[i];
        }
        return out;
    }
    public static float[] subtractArray(float[] a, float[] b) {
        float[] out = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i] - b[i];
        }
        return out;
    }
    public static float[] multArray(float[] a, float[] b) {
        float[] out = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i] * b[i];
        }
        return out;
    }
    public static float[] divideArray(float[] a, float[] b) {
        float[] out = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i] / b[i];
        }
        return out;
    }

    public static float sumArray(float[] x) {
        float sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        return sum;
    }

    public static float[] broadcastArray(int val, int len) {
        float[] out = new float[len];
        for (int i = 0; i < len; i++) {
            out[i] = val;
        }
        return out;
    }

    public static float[] logArray(float[] x) {
        float[] out = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            out[i] = (float)Math.log (x[i]);
        }
        return out;
    }
    public static BetterArray loadPictures() {
        String filename = "/Users/DanielLongo 1/Desktop/SuprevisedML/Data/Logistic_Regression_Data/cows/";
        String[] filenames  = new String[]{"cow1", "cow2", "cow3", "cow4"};
        float[][] flattenedImages = new float[filenames.length][100*100*3];
        for (int i = 0; i < filenames.length; i++) {
            flattenedImages[i] = utils.flatten (loadPicture (filename + filenames[i] + ".jpg"));
        }
        float tiledMImages[][][] = new float[filenames.length][100*100*3][1];
        for (int i = 0; i < tiledMImages.length; i++) {
            for (int j = 0; j < tiledMImages[0].length; j++) {
                for (int k = 0; k < tiledMImages[0][0].length; k++) {
                    tiledMImages[i][j][k] = flattenedImages[i][j];

                }

            }

        }
        BetterArray out = new BetterArray (tiledMImages);
        return out;
    }
    public static BetterArray loadPicture(String loc) {
        BufferedImage srcimg = null;
        BufferedImage img = null;

        File f = null;
        try {
            f = new File (loc);
            srcimg = ImageIO.read (f);
            Image resize = srcimg.getScaledInstance (100,100,0);
            img = new BufferedImage (100,100,BufferedImage.TYPE_INT_RGB);
            img.getGraphics ().drawImage (resize,0,0,null);

        } catch (IOException e) {
            throw new RuntimeException ("Invalid Image " + loc);
        }


        int width = img.getWidth ( );
        int height = img.getHeight ( );

        float[][][] data = new float[width][height][3];
        for (int i = 0; i < width; i++) {
            for (int x = 0; x < height; x++) {
                Color pxlColor = new Color (img.getRGB (i, x));
                data[i][x][0] = pxlColor.getRed ( );
                data[i][x][1] = pxlColor.getGreen ( );
                data[i][x][2] = pxlColor.getBlue ( );
            }
        }
        return new BetterArray (data);
    }
    public static float[] flatten(BetterArray x) {
        float[][][] array = x.array;
        float[] out = new float[array.length * array[0].length * array[0][0].length];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int y = 0; y < array[0].length; y++) {
                for (int z = 0; z < array[0][0].length; z++) {
                    out[counter] = array[i][y][z];
                    counter += 1;
                }
            }
        }
        return out;
    }

    public static BetterArray unFlatten(float[] x) {
        float[][][] outArray = new float[x.length][1][1];
        for (int i = 0; i < x.length; i++) {
            outArray[i][0][0] = x[i];
        }
        BetterArray out = new BetterArray (outArray);
        return out;
    }


}
