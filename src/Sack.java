import java.util.*;

public class Sack {
    protected final CargoType cargoType;
    private final double weightInKG;
    public Sack(CargoType cargoType, double weight){
        this.cargoType = cargoType;
        this.weightInKG = weight;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sack)) return false;
        Sack other = (Sack) obj;
        return cargoType == other.cargoType
                && Double.compare(weightInKG, other.weightInKG) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargoType, weightInKG);
    }
    public double getWeightInKG(){
        return this.weightInKG;
    }
}
