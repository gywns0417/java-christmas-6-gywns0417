package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.strategy.ChristmasCountdownStrategy;
import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.domain.discount.strategy.GiveawayStrategy;
import christmas.domain.discount.strategy.SpecialStrategy;
import christmas.domain.discount.strategy.WeekdayStrategy;
import christmas.domain.discount.strategy.WeekendStrategy;
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.VisitDateDto;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {
    @DisplayName("주어진 값으로 만들어진 DiscountDto가 올바른 값을 가지고 있으면 통과")
    @Test
    void success_CreateDiscount() {
        // give
        VisitDateDto visitDateDto = new VisitDateDto(25, "MONDAY", true);
        HashMap<String, Integer> dessert = new HashMap<>();
        HashMap<String, Integer> mainDish = new HashMap<>();
        HashMap<String, Integer> orderMenuQuantity = new HashMap<>();
        dessert.put("초코케이크", 1);
        mainDish.put("티본스테이크", 1);
        orderMenuQuantity.put("초코케이크", 1);
        orderMenuQuantity.put("티본스테이크", 1);
        // when
        OrderDto orderDto = new OrderDto(dessert, mainDish, 70000, orderMenuQuantity);
        List<DiscountStrategy> discountStrategies = List.of(
                new ChristmasCountdownStrategy(), new GiveawayStrategy(),
                new SpecialStrategy(), new WeekdayStrategy(), new WeekendStrategy());
        DiscountService discountService = new DiscountService(visitDateDto, orderDto);
        DiscountDto discountDto = discountService.createDiscount(discountStrategies).toDto();

        //then
        assertThat(discountDto.christmasCountdownDiscount()).isEqualTo(3400);
        assertThat(discountDto.weekdayDiscount()).isEqualTo(2023);
        assertThat(discountDto.weekendDiscount()).isEqualTo(0);
        assertThat(discountDto.specialDiscount()).isEqualTo(1000);
        assertThat(discountDto.giveawayDiscount()).isEqualTo(0);
        assertThat(discountDto.totalDiscount()).isEqualTo(6423);
        assertThat(discountDto.hasAnyDiscount()).isTrue();
    }

    @DisplayName("총 주문 금액 1만원 이하로 할인 대상이 아닐 시 빈 할인 금액 담은 DiscountDto 생성")
    @Test
    void success_NoAnyDiscount_PurchaseAmountUnderMin() {
        // give
        VisitDateDto visitDateDto = new VisitDateDto(25, "MONDAY", true);
        HashMap<String, Integer> dessert = new HashMap<>();
        HashMap<String, Integer> mainDish = new HashMap<>();
        HashMap<String, Integer> orderMenuQuantity = new HashMap<>();
        dessert.put("아이스크림", 1);
        orderMenuQuantity.put("아이스크림", 1);
        // when
        OrderDto orderDto = new OrderDto(dessert, mainDish, 5000, orderMenuQuantity);
        List<DiscountStrategy> discountStrategies = List.of(
                new ChristmasCountdownStrategy(), new GiveawayStrategy(),
                new SpecialStrategy(), new WeekdayStrategy(), new WeekendStrategy());
        DiscountService discountService = new DiscountService(visitDateDto, orderDto);
        DiscountDto discountDto = discountService.createDiscount(discountStrategies).toDto();

        //then
        assertThat(discountDto.christmasCountdownDiscount()).isEqualTo(0);
        assertThat(discountDto.weekdayDiscount()).isEqualTo(0);
        assertThat(discountDto.weekendDiscount()).isEqualTo(0);
        assertThat(discountDto.specialDiscount()).isEqualTo(0);
        assertThat(discountDto.giveawayDiscount()).isEqualTo(0);
        assertThat(discountDto.totalDiscount()).isEqualTo(0);
        assertThat(discountDto.hasAnyDiscount()).isFalse();
    }

    @DisplayName("적용되는 할인이 없을 시 빈 할인 금액 담은 DiscountDto 생성")
    @Test
    void success_NoAnyDiscount() {
        // give
        VisitDateDto visitDateDto = new VisitDateDto(26, "TUESDAY", false);
        HashMap<String, Integer> dessert = new HashMap<>();
        HashMap<String, Integer> mainDish = new HashMap<>();
        HashMap<String, Integer> orderMenuQuantity = new HashMap<>();
        mainDish.put("티본스테이크", 1);
        orderMenuQuantity.put("티본스테이크", 1);
        // when
        OrderDto orderDto = new OrderDto(dessert, mainDish, 55000, orderMenuQuantity);
        List<DiscountStrategy> discountStrategies = List.of(
                new ChristmasCountdownStrategy(), new GiveawayStrategy(),
                new SpecialStrategy(), new WeekdayStrategy(), new WeekendStrategy());
        DiscountService discountService = new DiscountService(visitDateDto, orderDto);
        DiscountDto discountDto = discountService.createDiscount(discountStrategies).toDto();

        //then
        assertThat(discountDto.christmasCountdownDiscount()).isEqualTo(0);
        assertThat(discountDto.weekdayDiscount()).isEqualTo(0);
        assertThat(discountDto.weekendDiscount()).isEqualTo(0);
        assertThat(discountDto.specialDiscount()).isEqualTo(0);
        assertThat(discountDto.giveawayDiscount()).isEqualTo(0);
        assertThat(discountDto.totalDiscount()).isEqualTo(0);
        assertThat(discountDto.hasAnyDiscount()).isFalse();
    }
}