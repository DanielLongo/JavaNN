import java.lang.reflect.Method;

public class Activations {
    public static float sigmoid(float x) {
        return (float)( 1/ (1 + Math.pow(Math.E, -x)));
    }

    public static float DSigmoid(float dA, float x) {
        float s = (float)(1/(1 + Math.pow(-(double)(x),2)));
        return s;
    }

    public static float ReLU(float x) {
        if (x < 0) return 0;
        else return x;
    }

    public static float tanh(float x ) {
        return (float) Math.tanh (x);
    }

    public static BetterArray applyFuntion(BetterArray x, String operation) {
        BetterArray out = new BetterArray (x.array);
        for (int a = 0; a < x.h; a++) {
            for (int b = 0; b < x.w; b++) {
                for (int c = 0; c < x.c; c++) {
                    if (operation == "sigmoid") out.array[a][b][c] = sigmoid(x.array[a][b][c]);
                    else if (operation == "ReLU") out.array[a][b][c] = ReLU(x.array[a][b][c]);
                    else out.array[a][b][c] = tanh(x.array[a][b][c]);
                }
            }
        }
        return out;
    }
}
