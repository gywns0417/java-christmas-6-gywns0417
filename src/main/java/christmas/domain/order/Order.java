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
import christmas.dto.OrderDto;
import java.util.HashMap;
public class Order {
    private final OrderMenu orderMenu;
    private final HashMap<String, Integer> appetizer;
    private final HashMap<String, Integer> dessert;
    private final HashMap<String, Integer> drink;
    private final HashMap<String, Integer> mainDish;

    public Order(OrderMenu orderMenu,
                 HashMap<String, Integer> appetizer,
                 HashMap<String, Integer> dessert,
                 HashMap<String, Integer> drink,
                 HashMap<String, Integer> mainDish) {
        this.orderMenu = orderMenu;
        this.appetizer = appetizer;
        this.dessert = dessert;
        this.drink = drink;
        this.mainDish = mainDish;
        validatePurchaseOnlyDrinks();
    }

    public int calculateTotalPurchaseAmount() {
        int appetizerAmount = Appetizer.getTotalAmount(appetizer);
        int dessertAmount = Dessert.getTotalAmount(dessert);
        int drinkAmount = Drink.getTotalAmount(drink);
        int mainDishAmount = MainDish.getTotalAmount(mainDish);
        return appetizerAmount + dessertAmount + drinkAmount + mainDishAmount;
    }

    public OrderDto toDto() {
        return new OrderDto(dessert, mainDish, calculateTotalPurchaseAmount(), orderMenu.getOrderMenuQuantity());
    }

    private void validatePurchaseOnlyDrinks() {
        if (appetizer.isEmpty() && dessert.isEmpty() && mainDish.isEmpty()) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
    }
}
