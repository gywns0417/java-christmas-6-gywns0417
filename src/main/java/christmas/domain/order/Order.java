package christmas.domain.order;

// 이 객체는 어떤 상태와 행위를 가지는가?
// 객체명 : 주문
// 상태 : 총 주문 금액, 메뉴별 주문 목록들(메뉴 이름, 개수)
// 행위 : 총 주문 금액 계산

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.MainDish;
import christmas.dto.OrderDto;
import java.util.HashMap;
public class Order { //TODO : 파라미터 객체 고려 및 일급 컬렉션 적용
    private final HashMap<String, Integer> splitMenuQuantity;
    private final HashMap<String, Integer> appetizer;
    private final HashMap<String, Integer> dessert;
    private final HashMap<String, Integer> drink;
    private final HashMap<String, Integer> mainDish;
    private final int totalPurchaseAmount;

    public Order(HashMap<String, Integer> splitMenuQuantity,
                 HashMap<String, Integer> appetizer,
                 HashMap<String, Integer> dessert,
                 HashMap<String, Integer> drink,
                 HashMap<String, Integer> mainDish) {
        this.splitMenuQuantity = splitMenuQuantity;
        this.appetizer = appetizer;
        this.dessert = dessert;
        this.drink = drink;
        this.mainDish = mainDish;
        this.totalPurchaseAmount = calculateTotalPurchaseAmount();
    }

    private int calculateTotalPurchaseAmount() {
        int appetizerAmount = Appetizer.getTotalAmount(appetizer);
        int dessertAmount = Dessert.getTotalAmount(dessert);
        int drinkAmount = Drink.getTotalAmount(drink);
        int mainDishAmount = MainDish.getTotalAmount(mainDish);
        return appetizerAmount + dessertAmount + drinkAmount + mainDishAmount;
    }

    public OrderDto toDto() {
        return new OrderDto(splitMenuQuantity, appetizer, dessert, drink, mainDish, totalPurchaseAmount);
    }

}
