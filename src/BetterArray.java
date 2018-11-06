public class BetterArray {
    public float[][][] array;
    private int w;
    private int h;
    private int c;

    public BetterArray(int shape[], int value) {
        w = shape[0];
        h = shape[1];
        c = shape[2];
        array = new float[w][h][c];
        this.fillArray(value);
    }

    public BetterArray(float[][][] array) {
        w = array.length;
        h = array[0].length;
        c = array[0][0].length;
        this.array = array;
    }
    public float getRandomValue(int min, int max) {
        return (float)((Math.random () * (max - min)) + min);
    }

    public void randomizeArray(int min, int max) {
        for (int i = 0; i < w; i ++) {
            for (int y = 0; y < h; y ++) {
                for (int z = 0; z < c; z++) {
                    array[i][y][z] = getRandomValue (min, max);
                }
            }
        }
    }
    public void fillArray(int value) {
        for (int i = 0; i < w; i ++) {
            for (int y = 0; y < h; y ++) {
                for (int z = 0; z < c; z++) {

                    array[i][y][z] = value;
                }
            }
        }
    }

    public void printShape() {
        System.out.println ("Shape of array: (" + w + "," + h + "," + c + ")");
    }
    public float[][][] getArray() {return array;}
    public void setArray(float[][][] array) {this.array = array;}
    public int[] getShape() {
        return new int[]{w,h,c};
    }
    public float getValue(int w, int h, int c) {
        return array[w][h][c];
    }

    public float innerProduct(float[] a, float[] b) {
        assert a.length == b.length : "length of arrays not equal in innerProduct";
        float sum = 0.0f;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public BetterArray dot(BetterArray x) {
        assert((c == 1) && (x.getShape()[-1] == 1));
        int[] outShape = {this.w, x.getShape()[1], 1};
        float[][] currColumn;
        float[][] currRow;
        float currInner;
        BetterArray out = new BetterArray(outShape ,0);
        out.printShape();
        for (int i = 0; i < this.w; i++) {
            currColumn = array[i];
            for (int y = 0; i <this.h; y++) {
                currRow = x.getArray()[y];
                currInner = innerProduct(currColumn[0], currRow[0]);
                out.array[i][y][0] = currInner;
            }
        }
        return out;
    }
}
