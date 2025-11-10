import java.util.*;

public class SackWarehouse extends Warehouse<Sack>{
    public Map<CargoType, List<Sack>> getSacksByTypes(){
        Map<CargoType, List<Sack>> sackTypesCounter = new HashMap<>();
        sackTypesCounter.put(CargoType.Cement, new ArrayList<Sack>());
        sackTypesCounter.put(CargoType.Plaster, new ArrayList<Sack>());
        sackTypesCounter.put(CargoType.Sand, new ArrayList<Sack>());
        for (Sack sack : items){
            sackTypesCounter.get(sack.cargoType).add(sack);
        }
        return sackTypesCounter;
    }
    public Set<CargoType> getEmptyTypes(){
        Set<CargoType> outOfStockTypes = new HashSet<>();
        outOfStockTypes.add(CargoType.Cement);
        outOfStockTypes.add(CargoType.Sand);
        outOfStockTypes.add(CargoType.Plaster);
        for (Sack sack : items){
            outOfStockTypes.remove(sack.cargoType);
        }
        return outOfStockTypes;
    }
}
