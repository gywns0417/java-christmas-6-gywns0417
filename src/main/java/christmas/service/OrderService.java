package christmas.service;

import christmas.domain.menu.strategy.MenuStrategy;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderService {

    public Order order(Supplier<String> inputSupplier, Runnable messagePrinter,
                       Consumer<String> errorMessagePrinter, List<MenuStrategy> strategies) {
        while (true) {
            try {
                messagePrinter.run();
                OrderMenu orderMenu = new OrderMenu(inputSupplier.get());
                return createOrder(orderMenu, strategies);
            } catch (IllegalArgumentException e) {
                errorMessagePrinter.accept(e.getMessage());
            }
        }
    }

    private Order createOrder(OrderMenu orderMenu, List<MenuStrategy> strategies) {
        return new Order(orderMenu, strategies);
    }
}
