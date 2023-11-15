package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {
    @DisplayName("올바르게 입력값 분리에 성공하면 통과")
    @Test
    void success() {
        // give
        String inputOrderMenuQuantity = "티본스테이크-1,아이스크림-2";
        OrderMenu orderMenu = new OrderMenu(inputOrderMenuQuantity);
        HashMap<String, Integer> orderMenuQuantity = new HashMap<>();
        orderMenuQuantity.put("티본스테이크", 1);
        orderMenuQuantity.put("아이스크림", 2);
        // when, then
        assertThat(orderMenu.getOrderMenuQuantity()).isEqualTo(orderMenuQuantity);
    }

    @DisplayName("형식에 맞지 않은 입력시 예외 발생")
        @Test
        void fail_InvalidInput() {
            // give
            String inputOrderMenuQuantity = "티본스테이크1";
            // when, then
            assertThatThrownBy(() -> new OrderMenu(inputOrderMenuQuantity))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 메뉴 입력시 예외 발생")
    @Test
    void fail_NotUnique() {
        // give
        String inputOrderMenuQuantity = "티본스테이크-1,티본스테이크-2";
        // when, then
        assertThatThrownBy(() -> new OrderMenu(inputOrderMenuQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력된 총 메뉴 수가 최대 제한을 넘을 시 예외 발생")
    @Test
    void fail_InvalidQuantity() {
        // give
        String inputOrderMenuQuantity = "티본스테이크-21";
        // when, then
        assertThatThrownBy(() -> new OrderMenu(inputOrderMenuQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}