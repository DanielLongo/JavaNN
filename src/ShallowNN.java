public class ShallowNN {
    private BetterArray w;
    private BetterArray b;
    private BetterArray a;
    private BetterArray x;
    private int[] y;
    private int d;
    public ShallowNN( BetterArray x, int[] y, int d) {
        this.d = d;
        this.x = x;
        this.y = y;
    }

    public void initializeVariables() {
        w = new BetterArray (new int[]{d, x.array[0].length, 1}, 0);
        b = new BetterArray (new int[]{x.array.length, d, 1},0);
        a = new BetterArray (new int[]{x.array.length, d, 1},0);
    }

    public void propagate() {
        BetterArray z = (x.dot(w)).array + b.array;
        BetterArray a = Activations.applyFuntion (z, "sigmoid");
    }
}
