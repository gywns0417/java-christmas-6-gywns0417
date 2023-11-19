package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.dto.OrderDto;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("올바른 메뉴 입력시 예외 발생 안함")
    @Test
    void success_CreateOrder() {
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,아이스크림-2,양송이수프-1,레드와인-2");
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());
        assertThatCode(() -> new Order(orderMenu, menuStrategies))
                .doesNotThrowAnyException();
    }

    @DisplayName("올바른 메뉴 입력시 올바른 값을 가진 OrderDto 생성")
    @Test
    void success_CreateOrderDto() {
        // give
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,아이스크림-2,양송이수프-1,레드와인-2");
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());
        OrderDto orderDto = new Order(orderMenu, menuStrategies).toDto();
        HashMap<String, Integer> menuQuantity = new HashMap<>();
        HashMap<String, Integer> dessert = new HashMap<>();
        HashMap<String, Integer> mainDish = new HashMap<>();

        // when
        menuQuantity.put("티본스테이크", 1);
        menuQuantity.put("아이스크림", 2);
        menuQuantity.put("양송이수프", 1);
        menuQuantity.put("레드와인", 2);
        dessert.put("아이스크림", 2);
        mainDish.put("티본스테이크", 1);

        // then
        assertThat(orderDto.orderMenuQuantity()).isEqualTo(menuQuantity);
        assertThat(orderDto.dessert()).isEqualTo(dessert);
        assertThat(orderDto.mainDish()).isEqualTo(mainDish);
        assertThat(orderDto.totalPurchaseAmount()).isEqualTo(191_000);
    }

    @DisplayName("없는 메뉴 입력시 예외 발생")
    @Test
    void fail_InvalidMenu() {
        // give
        OrderMenu orderMenu = new OrderMenu("티본수테꾸-1");
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());
        // when, then
        assertThatThrownBy(() -> new Order(orderMenu, menuStrategies))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료만 입력시 예외 발생")
    @Test
    void fail_NotUniqueMenu() {
        // give
        OrderMenu orderMenu = new OrderMenu("레드와인-1,제로콜라-3");
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());
        // when, then
        assertThatThrownBy(() -> new Order(orderMenu, menuStrategies))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
    }

}