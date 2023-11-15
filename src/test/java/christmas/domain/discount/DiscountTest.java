package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.CHRISTMAS_COUNTDOWN;
import static christmas.domain.discount.DiscountType.GIVEAWAY;
import static christmas.domain.discount.DiscountType.SPECIAL;
import static christmas.domain.discount.DiscountType.WEEKDAY;
import static christmas.domain.discount.DiscountType.WEEKEND;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.date.VisitDate;
import christmas.dto.DiscountDto;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    @DisplayName("받은 할인 정보를 토대로 DTO 생성시 통과")
    @Test
    void success_ToDto() {
        // give
        HashMap<DiscountType, Integer> discountTypeAmount = new HashMap<>();
        discountTypeAmount.put(CHRISTMAS_COUNTDOWN, 3400);
        discountTypeAmount.put(WEEKDAY, 2023);
        discountTypeAmount.put(WEEKEND, 0);
        discountTypeAmount.put(GIVEAWAY, 0);
        discountTypeAmount.put(SPECIAL, 0);
        //when
        DiscountDto dto = new Discount(discountTypeAmount).toDto();
        //then
        assertThat(dto.christmasCountdownDiscount()).isEqualTo(3400);
        assertThat(dto.weekdayDiscount()).isEqualTo(2023);
        assertThat(dto.weekendDiscount()).isEqualTo(0);
        assertThat(dto.giveawayDiscount()).isEqualTo(0);
        assertThat(dto.specialDiscount()).isEqualTo(0);
        assertThat(dto.hasAnyDiscount()).isTrue();
        assertThat(dto.totalDiscount()).isEqualTo(5423);
    }
}