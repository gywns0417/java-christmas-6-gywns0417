package christmas.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.discount.TotalDiscount;
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.TotalDiscountDto;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalDiscountServiceTest {
    @DisplayName("주어진 값으로 만들어진 TotalDiscountDto가 올바른 값을 가지고 있으면 통과")
    @Test
    void success() {
        // give
        HashMap<String, Integer> dessert = new HashMap<>();
        HashMap<String, Integer> mainDish = new HashMap<>();
        HashMap<String, Integer> orderMenuQuantity = new HashMap<>();
        int totalPurchaseAmount = 60000;
        dessert.put("아이스크림", 1);
        mainDish.put("티본스테이크", 1);
        // when
        DiscountDto discountDto = new DiscountDto(3400, 2023, 0,
                1000, 0, 5423, true);
        OrderDto orderDto = new OrderDto(dessert, mainDish, totalPurchaseAmount, orderMenuQuantity);
        TotalDiscountService totalDiscountService = new TotalDiscountService();
        TotalDiscountDto totalDiscountDto = totalDiscountService.createTotalDiscount(discountDto, orderDto).toDto();
        // then
        assertThat(totalDiscountDto.totalDiscount()).isEqualTo(5423);
        assertThat(totalDiscountDto.afterDiscountAmount()).isEqualTo(54577);
        assertThat(totalDiscountDto.badge()).isEqualTo("별");
    }
}