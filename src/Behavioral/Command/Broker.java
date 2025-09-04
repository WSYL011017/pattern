package Behavioral.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用者/命令调用类 - 经纪人
 */
public class Broker {
    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}