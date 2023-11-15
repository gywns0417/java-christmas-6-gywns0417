package christmas.domain.order;

// 이 객체는 어떤 상태와 행위를 가지는가?
// 객체명 : 주문
// 상태 : 총 주문 금액, 메뉴별 주문 목록들(메뉴 이름, 개수)
// 행위 : 총 주문 금액 계산
import static christmas.config.ErrorMessage.MENU_QUANTITY_INPUT_ERROR_MESSAGE;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MainDish;
import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.dto.OrderDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Order {
    private final OrderMenu orderMenu;
    private final HashMap<String, Integer> appetizer = new HashMap<>();
    private final HashMap<String, Integer> dessert = new HashMap<>();
    private final HashMap<String, Integer> drink = new HashMap<>();
    private final HashMap<String, Integer> mainDish = new HashMap<>();

    public Order(OrderMenu orderMenu, List<MenuStrategy> strategies) {
        this.orderMenu = orderMenu;
        createOrder(orderMenu.getOrderMenuQuantity(), strategies);
        validatePurchaseOnlyDrinks();
    }

    public OrderDto toDto() {
        return new OrderDto(dessert, mainDish, calculateTotalPurchaseAmount(), orderMenu.getOrderMenuQuantity());
    }

    private int calculateTotalPurchaseAmount() {
        return Appetizer.getTotalAmount(appetizer) +  Dessert.getTotalAmount(dessert) +
                Drink.getTotalAmount(drink) + MainDish.getTotalAmount(mainDish);
    }

    private void createOrder(HashMap<String, Integer> orderMenuQuantity, List<MenuStrategy> strategies) {
        for (Entry<String, Integer> menuQuantity : orderMenuQuantity.entrySet()) {
            String menuName = menuQuantity.getKey();
            int quantity = menuQuantity.getValue();

            MenuStrategy strategy = findStrategy(menuName, strategies);
            HashMap<String, Integer> menuMap = getMenuMap(strategy);

            strategy.putMenu(menuMap, menuName ,quantity);
        }
    }

    private MenuStrategy findStrategy(String menuName, List<MenuStrategy> strategies) {
        return strategies.stream()
                .filter(strategy -> strategy.acceptMenuName(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage()));
    }

    private HashMap<String, Integer> getMenuMap(MenuStrategy menuStrategy) {
        if (menuStrategy instanceof AppetizerStrategy) return appetizer;
        if (menuStrategy instanceof DessertStrategy) return dessert;
        if (menuStrategy instanceof DrinkStrategy) return drink;
        if (menuStrategy instanceof MainDishStrategy) return mainDish;
        throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
    }

    private void validatePurchaseOnlyDrinks() {
        if (appetizer.isEmpty() && dessert.isEmpty() && mainDish.isEmpty()) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
    }
}
