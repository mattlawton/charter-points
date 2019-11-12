package charter;

import java.util.List;

public class Customer {

    private int id;
    private List<Orders> orders;

    public Customer(int id, List<Orders> orders) {
        this.setId(id);
        this.setOrders(orders);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
