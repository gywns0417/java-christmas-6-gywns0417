package christmas.service;

import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    @DisplayName("올바른 주문 입력시 예외 발생 안함")
    @Test
    void success() {
        // give
        OrderService orderService = new OrderService();
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());
        Supplier<String> inputMenu = () -> "티본스테이크-1";
        Consumer<String> consumer = System.out::println;
        // when, then
        assertThatCode(() -> orderService.order(inputMenu, () -> {}, consumer, menuStrategies))
                .doesNotThrowAnyException();
    }
}