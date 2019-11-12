package charter;

import org.json.JSONException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PointsController {

    CustomerMockedData customerMockedData = CustomerMockedData.getInstance();

    public PointsController() throws IOException {}

    @RequestMapping("/customers")
    public List<Customer> customers(){
        return customerMockedData.fetchCustomers();
    }

    @RequestMapping("/customers/{id}")
    public Customer customer(@PathVariable String id) {
        int customerId = Integer.parseInt(id);
        return customerMockedData.getCustomerById(customerId);
    }

    @RequestMapping("points/{id}/month")
    public List<Orders> points(@PathVariable String id) throws JSONException {
        int customerId = Integer.parseInt(id);
        Customer c = customerMockedData.getCustomerById(customerId);
        return Points.getPointsByMonth(c);
    }

    @RequestMapping("points/{id}/total")
    public double totalPoints(@PathVariable String id) {
        int customerId = Integer.parseInt(id);
        Customer c = customerMockedData.getCustomerById(customerId);
        return Points.getTotal(c.getOrders());
    }
}