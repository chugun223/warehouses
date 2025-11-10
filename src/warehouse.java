import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class warehouse<T> {
    private List<T> items = new ArrayList<T>();
    private Queue<T> ordersQueue = new LinkedList<T>();
    public abstract List<T> getItems();
    public abstract void addOrder(order item);
    public abstract void
}
