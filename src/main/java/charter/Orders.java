package charter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Month;

public class Orders{
    @JsonProperty("month")
    private Month month;
    @JsonProperty("cost")
    private double cost;

    public Orders(Month month, double cost) {
        this.setMonth(month);
        this.setCost(cost);
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
