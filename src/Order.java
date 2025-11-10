public class Order<T> {
    private final T item;
    private final boolean isVIP;
    public boolean isVIP(){
        return this.isVIP;
    }
    public Order(T item, boolean isVIP){
        this.item = item;
        this.isVIP = isVIP;
    }
    public T getItem(){
        return this.item;
    }
}
