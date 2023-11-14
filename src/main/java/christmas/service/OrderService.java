package christmas.service;

import static christmas.config.ErrorMessage.MENU_QUANTITY_INPUT_ERROR_MESSAGE;

import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.domain.order.Order;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderService { //TODO: 분리 고려
    private static final String orderMenuRegex = "^([가-힣]+-[1-9]\\d*(,\\s*[가-힣]+-[1-9]\\d*)*)$";
    private final List<MenuStrategy> strategies = List.of(
            new AppetizerStrategy(),
            new DessertStrategy(),
            new DrinkStrategy(),
            new MainDishStrategy()
    );

    private final HashMap<String, Integer> appetizers = new HashMap<>();
    private final HashMap<String, Integer> dessert = new HashMap<>();
    private final HashMap<String, Integer> drink = new HashMap<>();
    private final HashMap<String, Integer> mainDish = new HashMap<>();
    public Order start(Supplier<String> inputSupplier, Runnable messagePrinter, Consumer<String> errorMessagePrinter) {
        while (true) {
            try {
                messagePrinter.run();
                return createOrder(inputSupplier.get());
            } catch (IllegalArgumentException e) {
                errorMessagePrinter.accept(e.getMessage());
            }
        }
    }

    public Order createOrder(String inputMenu) {
        String inputOrderMenu = validateRegexOrderMenu(inputMenu);
        HashMap<String, Integer> splitMenuQuantity = splitOrderMenu(inputOrderMenu);
        for (Entry<String, Integer> menuQuantity : splitMenuQuantity.entrySet()) {
            String menuName = menuQuantity.getKey();
            int quantity = menuQuantity.getValue();

            MenuStrategy strategy = findStrategy(menuName); // 전략 검색
            HashMap<String, Integer> menuMap = getMenuMap(strategy);
            if (menuMap.containsKey(menuName)) {
                throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
            }
            strategy.putMenu(getMenuMap(strategy),menuName ,quantity); // 전략에 따라 해당하는 MenuMap에 put
        }
        if (appetizers.isEmpty() && dessert.isEmpty() && mainDish.isEmpty()) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
        return new Order(splitMenuQuantity, appetizers, dessert, drink, mainDish);
    }

    private String validateRegexOrderMenu(String inputOrderMenu) {
        if (!inputOrderMenu.matches(orderMenuRegex)) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
        return inputOrderMenu;
    }

    private HashMap<String, Integer> splitOrderMenu(String inputOrderMenu) {
        HashMap<String, Integer> splitMenuQuantity = new HashMap<>();
        StringTokenizer st = new StringTokenizer(inputOrderMenu, ",-");
        int menuAmount = 0;
        while (st.hasMoreTokens()) {
            String menuName = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            menuAmount += amount;
            if(menuAmount > 20) {
                throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
            }
            splitMenuQuantity.put(menuName, amount);
        }
        return splitMenuQuantity;
    }

    private MenuStrategy findStrategy(String menuName) {
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
