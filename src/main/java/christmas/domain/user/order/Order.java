package christmas.domain.user.order;

import christmas.constant.ErrorMessage;
import christmas.domain.menu.Menu;
import christmas.util.StringConvertor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private static final int MIN_TOTAL_ORDER_COUNT_LIMIT = 1;
    private static final int MAX_TOTAL_ORDER_COUNT_LIMIT = 20;
    private final List<OrderDetail> orders;

    private Order(List<OrderDetail> orders) {
        this.orders = orders;
    }

    public static Order from(String input) {
        List<OrderDetail> orderDetails = Arrays.stream(StringConvertor.splitByComma(input))
                .map(OrderDetail::from)
                .collect(Collectors.toList());
        validateTotalOrderCount(orderDetails);
        validateOnlyOrderDrink(orderDetails);
        return new Order(orderDetails);
    }

    private static void validateTotalOrderCount(List<OrderDetail> orderDetails) {
        int totalOrderCount = getTotalOrderCount(orderDetails);
        if (totalOrderCount < MIN_TOTAL_ORDER_COUNT_LIMIT || totalOrderCount > MAX_TOTAL_ORDER_COUNT_LIMIT) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static int getTotalOrderCount(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToInt(OrderDetail::getQuantity)
                .sum();
    }

    private static void validateOnlyOrderDrink(List<OrderDetail> orderDetails) {
        OrderDetail firstMenuInfo = orderDetails.get(0);
        if (isOrderOnce(orderDetails) && Menu.isDrinkMenu(firstMenuInfo.getMenuName())) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static boolean isOrderOnce(List<OrderDetail> orderMenuInfos) {
        if (orderMenuInfos.size() == MIN_TOTAL_ORDER_COUNT_LIMIT) {
            return true;
        }
        return false;
    }

    public List<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableList(orders);
    }

    public int getTotalOrderPrice() {
        return orders.stream()
                .mapToInt(OrderDetail::getOrderPrice)
                .sum();
    }
}
