package christmas.service;

import static christmas.config.ErrorMessage.MENU_QUANTITY_INPUT_ERROR_MESSAGE;

import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenu;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderService {
    private final List<MenuStrategy> strategies;
    private final HashMap<String, Integer> appetizers = new HashMap<>();
    private final HashMap<String, Integer> dessert = new HashMap<>();
    private final HashMap<String, Integer> drink = new HashMap<>();
    private final HashMap<String, Integer> mainDish = new HashMap<>();

    public OrderService(List<MenuStrategy> strategies) {
        this.strategies = strategies;
    }
    public Order order(Supplier<String> inputSupplier, Runnable messagePrinter, Consumer<String> errorMessagePrinter) {
        while (true) {
            try {
                messagePrinter.run();
                OrderMenu orderMenu = new OrderMenu(inputSupplier.get());
                return createOrder(orderMenu, orderMenu.getOrderMenuQuantity(), strategies);
            } catch (IllegalArgumentException e) {
                errorMessagePrinter.accept(e.getMessage());
            }
        }
    }

    private Order createOrder(OrderMenu orderMenu, HashMap<String, Integer> orderMenuQuantity, List<MenuStrategy> strategies) {
        for (Entry<String, Integer> menuQuantity : orderMenuQuantity.entrySet()) {
            String menuName = menuQuantity.getKey();
            int quantity = menuQuantity.getValue();

            MenuStrategy strategy = findStrategy(menuName, strategies); // 전략 검색
            HashMap<String, Integer> menuMap = getMenuMap(strategy);
            if (menuMap.containsKey(menuName)) {
                throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
            }
            strategy.putMenu(getMenuMap(strategy),menuName ,quantity); // 전략에 따라 해당하는 MenuMap에 put
        }
        return new Order(orderMenu, appetizers, dessert, drink, mainDish);
    }

    private MenuStrategy findStrategy(String menuName, List<MenuStrategy> strategies) {
        return strategies.stream()
                .filter(strategy -> strategy.acceptMenuName(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage()));
    }

    private HashMap<String, Integer> getMenuMap(MenuStrategy menuStrategy) {
        if (menuStrategy instanceof AppetizerStrategy) return appetizers;
        if (menuStrategy instanceof DessertStrategy) return dessert;
        if (menuStrategy instanceof DrinkStrategy) return drink;
        if (menuStrategy instanceof MainDishStrategy) return mainDish;
        throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
    }
}
