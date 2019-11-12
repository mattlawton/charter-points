package charter.test;

import charter.Customer;
import charter.CustomerMockedData;
import charter.Orders;
import charter.PointsController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PointsController.class, secure = false)
public class PointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerMockedData customerMockedData;

    List<Orders> orderList = new ArrayList<Orders>();

    public Customer mockCustomer(int id) {
        orderList.add(new Orders(Month.JUNE, 50));
        orderList.add(new Orders(Month.JUNE, 51));
        orderList.add(new Orders(Month.JUNE, 100));
        orderList.add(new Orders(Month.JULY, 120));
        orderList.add(new Orders(Month.JULY, 200));
        orderList.add(new Orders(Month.AUGUST, 101));
        orderList.add(new Orders(Month.AUGUST, 101));
        return new Customer(id, orderList);
    }

    @Test
    public void Test_ThreeMonthsFiveOrders_Monthly() throws Exception {

        Customer mockCustomer = mockCustomer(1);

        Mockito.when(customerMockedData.getCustomerById(Mockito.anyInt())).thenReturn(mockCustomer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/points/1/month").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = "[\n" +
                "    {\n" +
                "        \"month\": \"JUNE\",\n" +
                "        \"cost\": 51.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"month\": \"JULY\",\n" +
                "        \"cost\": 340.0\n" +
                "    },\n" +
                "    {\n" +
                "        \"month\": \"AUGUST\",\n" +
                "        \"cost\": 104.0\n" +
                "    }\n" +
                "]";

        JSONAssert.assertEquals(expectedJson.toString(),
                result.getResponse().getContentAsString(),
                false);
    }

    @Test
    public void Test_ThreeMonthsFiveOrders_Total() throws Exception {

        Customer mockCustomer = mockCustomer(1);

        Mockito.when(customerMockedData.getCustomerById(Mockito.anyInt())).thenReturn(mockCustomer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/points/1/total").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "723.0";

        JSONAssert.assertEquals(expected,
                result.getResponse().getContentAsString(),
                false);
    }

}
