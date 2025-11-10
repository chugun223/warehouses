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
    public void addOrder(Order<T> order){
        if(!order.isVIP()){
            this.ordersQueue.addLast(order);
        }
        else{
            this.addVIPOrder(order);
            System.out.println("Ваш заказ был VIP и поставлен в начало очереди");
        }
    }
    public void addVIPOrder(Order<T> order){
        if (order.isVIP()){
            this.ordersQueue.addFirst(order);
        }
        else{
            this.addOrder(order);
            System.out.println("Ваш заказ не был VIP и будет поставлен в конец очереди");
        }
    }
    public T processOrder(){
        Order order = ordersQueue.pollFirst();
        if(order == null){
            System.out.println("Очередь заказов пуста");
            return null;
        }
        T item = (T) order.getItem();
        boolean removed = items.remove(item);
        if (!removed) {
            System.out.println("Товар на складе не найден");
            return null;
        }
        return item;
    }
}
