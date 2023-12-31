package christmas.domain.user;

import christmas.domain.user.order.Order;
import christmas.domain.user.order.OrderDetail;
import java.util.List;

public class User {
    private final VisitDate date;
    private final Order order;

    public User(VisitDate date, Order order) {
        this.date = date;
        this.order = order;
    }

    public int getTotalOrderPrice() {
        return order.getTotalOrderPrice();
    }

    public List<OrderDetail> getOrderDetails() {
        return order.getOrderDetails();
    }

    public boolean isTotalOrderPriceGreaterOrEqual(int price) {
        if (order.getTotalOrderPrice() >= price) {
            return true;
        }
        return false;
    }

    public int getVisitDate() {
        return date.getVisitDate();
    }

    public boolean isVisitWeekday() {
        return date.isWeekday();
    }

    public boolean isVisitSpecialDay() {
        return date.isSpecialDay();
    }

    public boolean isVisitChristmasDday() {
        return date.isChristmasDday();
    }
}
