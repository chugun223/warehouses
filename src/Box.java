public class Box {
    private final double length;
    private final double width;
    private final double height;
    protected final boolean isFragile;
    public Box(double length, double width, double height, boolean isFragile){
        this.length = length;
        this.width = width;
        this.height = height;
        this.isFragile = isFragile;
    }
    public double getMaxSize(){
        return Math.max(this.width, Math.max(this.height, this.length));
    }
}
