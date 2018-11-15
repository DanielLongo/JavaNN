import com.sun.istack.internal.localization.NullLocalizable;
import sun.util.resources.cldr.ar.CalendarData_ar_YE;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;

public class BetterArray {
    public float[][][] array;
    public int h;
    public int w;
    public int c;

    public BetterArray(int shape[], int value) {
        h = shape[0];
        w = shape[1];
        c = shape[2];
        array = new float[h][w][c];
        this.fillArray (value);
    }

    public BetterArray(float[][][] array) {

        c = array[0][0].length;
        w = array[0].length;
        h = array.length;
        this.array = array;
    }

    public float getRandomValue(int min, int max) {
        return (float) ((Math.random ( ) * (max - min)) + min);
    }

    public void randomizeArray(int min, int max) {
        for (int i = 0; i < h; i++) {
            for (int y = 0; y < w; y++) {
                for (int z = 0; z < c; z++) {
                    array[i][y][z] = getRandomValue (min, max);
                }
            }
        }
    }

    public void fillArray(int value) {
        for (int i = 0; i < h; i++) {
            for (int y = 0; y < w; y++) {
                for (int z = 0; z < c; z++) {

                    array[i][y][z] = value;
                }
            }
        }
    }

    public void printShape() {
        System.out.println ("Shape of array: (" + h + "," + w + "," + c + ")");
    }

    public float[][][] getArray() {
        return array;
    }

    public void setArray(float[][][] array) {
        this.array = array;
    }

    public int[] getShape() {
        return new int[]{h, w, c};
    }

    public float getValue(int w, int h, int c) {
        return array[w][h][c];
    }

    public void printArray() {
        for (int a = 0; a < h; a++) {
            for (int b = 0; b < w; b++) {
                for (int d = 0; d < c; d++) {
                    System.out.print (array[a][b][d]);
                    System.out.print (" ");
                }
                System.out.print ("  ");
            }
            System.out.print ("\n");
        }
    }

    public float innerProduct(float[] a, float[] b) {
        if (a.length != b.length) {
            System.out.println ("a length " + a.length + " b length " + b.length);
            throw new IllegalArgumentException ("length of arrays are not equal");
        }
        float sum = 0.0f;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public float[] squeeze(float[][] x) {
        float[] out = new float[x.length * x[0].length];
        int counter = 0;
        for (int a = 0; a < x.length; a++) {
            for (int b = 0; b < x[0].length; b++) {
                out[counter] = x[a][b];
                counter += 1;
            }
        }
        return out;
    }

    public float[] getElement(float[][] x, int index) {
        float[] out = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            out[i] = x[i][index];
        }
        return out;
    }

    public float[] getElement(float[][][] x, int colNum, int index) {
        float[] out = new float[x.length];
        for (int i = 0; i < x.length; i++) {
            out[i] = x[i][colNum][index];
        }
        return out;
    }

    public BetterArray broadcastArray(BetterArray x) {
        assert (this.h + this.c) == 2 : "Broadcasted Array is multidimensional";
        float curVal;
        BetterArray newArray = new BetterArray (x.array);
        printShape ();
//        printArray ();
        x.printShape ();
        for (int d = 0; d < c; d++) {
            for (int a = 0; a < h; a++) {
                System.out.println ("a " + a );
                System.out.println ("d " + d );
                curVal = this.array[d][0][a];
                for (int b = 0; b < w; b++) {
                    newArray.array[a][b][d] = curVal;
                }
            }
        }
        return newArray;
    }

    public float sum() {
        float sum = 0;
        for (int a = 0; a < h; a++) {
            for (int b = 0; b < w; b++) {
                for(int d = 0; d < c; d++) {
                    sum += this.array[a][b][d];
                }
            }
        }
        return sum;
    }

    public void checkIfCanDot(BetterArray x) {
        if (!((c == 1) && (x.getShape ()[2] == 1))) {
            this.printShape ();
            x.printShape ();
            throw new IllegalArgumentException ("invalid input channels");
        }
        else if (this.getShape ()[1] != x.getShape ()[0]) {
            System.out.println (this.getShape ()[1]);
            System.out.println (x.getShape ()[0] );
            this.printShape ();
            x.printShape ();
            throw new IllegalArgumentException ("Invalid input shapes. Inner dimensions do not match");
        }
    }

    public BetterArray dot(BetterArray x) {
        this.checkIfCanDot (x);
        int[] outShape = {this.getShape ()[0], x.getShape()[1], 1};
        BetterArray out = new BetterArray (outShape,1);
        float[] currColumn;
        float[] currRow;
        float currInner;
        for (int i = 0; i < out.getShape ()[0]; i++) {
            currColumn = this.getElement (this.array[i], 0);
//            System.out.println ("Curr column");
//            System.out.println (Arrays.toString (currColumn));

            for (int y = 0; y < out.getShape ()[1]; y++) {
                currRow = x.getElement (x.array, y,0);
//                System.out.println ("Curr row");
//                System.out.println (Arrays.toString (currRow));
                currInner = innerProduct (currColumn, currRow);
                out.array[i][y][0] = currInner;
            }
        }
        out.printArray ();
        return out;
    }
//    public BetterArray dot(BetterArray x) {
//        assert ((c == 1) && (x.getShape ( )[-1] == 1));
//        int[] outShape = {this.w, x.getShape ( )[1], 1};
//        float[] currColumn;
//        float[] currRow;
//        float[] currRowSqueeze;
//        float currInner;
//        BetterArray out = new BetterArray (outShape, 0);
//        for (int i = 0; i < this.w; i++) {
//            currColumn = array[0][i];
//            for (int y = 0; y < x.h; y++) {
//                currRow = x.getElement ((x.array)[0], y);
//                currInner = innerProduct (currColumn, currRow);
//                out.array[i][y][0] = currInner;
//            }
//        }
//        return out;
//    }
    public BetterArray add(BetterArray x) {
        BetterArray out = new BetterArray (x.array);
        for (int a = 0; a < h; a++) {
            for (int b = 0; b < w; b++) {
                for (int d = 0; d < c; d++) {
                    out.array[a][b][d] = this.array[a][b][d] + x.array[a][b][d];
                }
            }
        }
        return out;
    }
}