import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Warehouse<T> {
    protected List<T> items = new ArrayList<T>();
    protected LinkedList<Order<T>> ordersQueue = new LinkedList<Order<T>>();
    public List<T> getItems(){
        return new ArrayList<>(this.items);
    }
    public void addOrder(Order item){
        this.ordersQueue.add(item);
    }
    public void addVIPOrder(Order item){
        this.ordersQueue.addFirst(item);
    }
    public T processOrder(){
        return ordersQueue.poll().getItem();
    }
}
