package charter;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CustomerMockedData {
    //list of customer posts
    private List<Customer> customers;

    private static CustomerMockedData instance = null;
    public static CustomerMockedData getInstance() throws IOException {
        if(instance == null){
            instance = new CustomerMockedData();
        }
        return instance;
    }


    public CustomerMockedData() throws IOException {
        customers = new ArrayList<Customer>();
        List<Orders> orderList = new ArrayList<Orders>();
        orderList.add(new Orders(Month.JUNE, 50));
        orderList.add(new Orders(Month.JUNE, 51));
        orderList.add(new Orders(Month.JUNE, 100));
        orderList.add(new Orders(Month.JULY, 120));
        orderList.add(new Orders(Month.JULY, 200));
        orderList.add(new Orders(Month.AUGUST, 101));
        orderList.add(new Orders(Month.AUGUST, 101));

        customers.add(new Customer(1, orderList));
    }

    // return all customers
    public List<Customer> fetchCustomers() {
        return customers;
    }

    // return customer by id
    public Customer getCustomerById(int id) {
        for(Customer b: customers) {
            if(b.getId() == id) {
                return b;
            }
        }
        return null;
    }

}