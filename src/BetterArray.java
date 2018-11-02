public class BetterArray {
    private float[][][] array;
    private int w, h, c;

    public BetterArray(int shape[], int value) {
        w = shape[0];
        h = shape[1];
        c = shape[2];
        array = new float[w][h][c];
        this.fillArray(value);
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

    public int[] getShape() {
        return new int[]{w,h,c};
    }
    public float getValue(int w, int h, int c) {
        return array[w][h][c];
    }

    public float[][][] dot(BetterArray x) {
        assert((c == 1) && (x.getShape()[-1] == 1));
    }


}
