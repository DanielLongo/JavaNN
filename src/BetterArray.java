import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;

public class BetterArray {
    public float[][][] array;
    public int w;
    public int h;
    public int c;

    public BetterArray(int shape[], int value) {
        w = shape[0];
        h = shape[1];
        c = shape[2];
        array = new float[w][h][c];
        this.fillArray (value);
    }

    public BetterArray(float[][][] array) {
        c = array.length;
        w = array[0].length;
        h = array[0][0].length;
        this.array = array;
    }

    public float getRandomValue(int min, int max) {
        return (float) ((Math.random ( ) * (max - min)) + min);
    }

    public void randomizeArray(int min, int max) {
        for (int i = 0; i < w; i++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < c; z++) {
                    array[i][y][z] = getRandomValue (min, max);
                }
            }
        }
    }

    public void fillArray(int value) {
        for (int i = 0; i < w; i++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < c; z++) {

                    array[i][y][z] = value;
                }
            }
        }
    }

    public void printShape() {
        System.out.println ("Shape of array: (" + w + "," + h + "," + c + ")");
    }

    public float[][][] getArray() {
        return array;
    }

    public void setArray(float[][][] array) {
        this.array = array;
    }

    public int[] getShape() {
        return new int[]{w, h, c};
    }

    public float getValue(int w, int h, int c) {
        return array[w][h][c];
    }

    public void printArray() {
        for (int a = 0; a < w; a++) {
            for (int b = 0; b < h; b++) {
                for (int d = 0; d < c; d++) {
                    System.out.print (array[a][b][d]);
                }
                System.out.printf ("");
            }
        }
    }

    public float innerProduct(float[] a, float[] b) {
        assert a.length == b.length : "length of arrays not equal in innerProduct";
//        System.out.printf ("a " + a.length);
//        System.out.printf ("b " + b.length);
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

    public float[] getElement(float[][] array, int index) {
//        System.out.println ("index " + index);
//        System.out.println (Arrays.deepToString (array) );
        float[] out = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            out[i] = array[i][index];
        }
        return out;
    }

    public BetterArray dot(BetterArray x) {
        assert ((c == 1) && (x.getShape ( )[-1] == 1));
        int[] outShape = {this.w, x.getShape ( )[1], 1};
        float[] currColumn;
        float[] currRow;
        float[] currRowSqueeze;
        float currInner;
        BetterArray out = new BetterArray (outShape, 0);
//        System.out.printf ("w" + w + " h" + h + " c " + c);
        this.printShape ( );
        x.printShape ( );
        System.out.println (Arrays.deepToString (array));
        System.out.println (Arrays.deepToString (array[0]));
        for (int i = 0; i < this.w; i++) {
            currColumn = array[0][i];
            for (int y = 0; y < x.h; y++) {
//                System.out.println ("h of x " + x.h );
                currRow = x.getElement ((x.array)[0], y);
//                currRow = x.array[0];
//                currRow = x.getElement(x, 0);
//                System.out.println ("Row KSDJHFKLSJHDFK " + Arrays.deepToString (currRow));
//                currRowSqueeze = squeeze(currRow);

//                System.out.println ("Columns " + Arrays.deepToString (currColumn));
//                System.out.println ("cr " + currRow.length  + " cr [0] " + currRow.length);
//                System.out.println ("column " + currColumn.length );
//                System.out.println ("row " + currRow.length );
                currInner = innerProduct (currColumn, currRow);
//                System.out.println ("curr inner " + currInner);
                out.array[i][y][0] = currInner;
            }
        }
        return out;
    }
}