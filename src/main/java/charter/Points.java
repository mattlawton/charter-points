package charter;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Points {

    private static final Logger log = LoggerFactory.getLogger(Points.class);

    static List<Orders> getPointsByMonth(Customer customer) throws JSONException {

        List<Orders> pointsByMonth = new ArrayList<Orders>();
        boolean foundMonth = false;

        for (Orders inputObj : customer.getOrders()) {

            log.info("found order, month: " + inputObj.getMonth() + ", cost: " + inputObj.getCost());

            for (Orders parsedObj : pointsByMonth) {

                    if (parsedObj.getMonth().equals(inputObj.getMonth())) {
                        log.info("adding to " + parsedObj.getMonth() + "'s total");
                        parsedObj.setCost((parsedObj.getCost() + calculatePoints(inputObj.getCost())));
                        log.info(inputObj.getMonth() + "'s new total points is " + parsedObj.getCost());
                        foundMonth = true;
                    } else foundMonth = false;

            }
            if (!foundMonth) {
                log.info("creating new point sum for month " + inputObj.getMonth());
                pointsByMonth.add(new Orders(inputObj.getMonth(),  calculatePoints(inputObj.getCost())));
            }
        }

        return pointsByMonth;
    }

    static double getTotal(List<Orders> points) {
        double total = 0;
        for (Orders p : points) {
            total += p.getCost();
        }
        return total;
     }

     static double calculatePoints(double cost) {
        //  A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
         double total = 0;

         if (cost <= 50) {
             return 0;
         } else if (cost <= 100) {
             return cost - 50;
         } else {
             return 50 + (cost - 100) * 2;
         }
     }
}
