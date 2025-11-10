import java.util.*;

public class BoxWarehouse extends Warehouse<Box>{
    public List<Box> getSortedByLength(){
        return items.stream().sorted(Comparator.comparing(Box::getMaxSize)).toList();
    }
    public int getFragileAmount(){
        return (int)items.stream().filter(item -> item.isFragile).count();
    }
}
