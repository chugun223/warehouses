import java.util.*;

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

    public double getLength(){
        return this.length;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Box)) return false;
        Box other = (Box) obj;
        return Double.compare(length, other.length) == 0
                && Double.compare(width, other.width) == 0
                && Double.compare(height, other.height) == 0
                && isFragile == other.isFragile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height, isFragile);
    }
}
