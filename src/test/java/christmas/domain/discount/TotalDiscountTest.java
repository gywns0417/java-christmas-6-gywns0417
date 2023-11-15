package christmas.domain.discount;

import static christmas.config.BadgeConfig.BADGE_NONE;
import static christmas.config.BadgeConfig.FIRST_BADGE;
import static christmas.config.BadgeConfig.SECOND_BADGE;
import static christmas.config.BadgeConfig.THIRD_BADGE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.dto.TotalDiscountDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalDiscountTest {
    @DisplayName("올바른 값을 가진 DTO 생성 성공시 통과")
    @Test
    void success() {
        TotalDiscountDto totalDiscountDto =
                new TotalDiscount(28_400, 120_000, 25_000).toDto();

        assertThat(totalDiscountDto.totalDiscount()).isEqualTo(28_400);
        assertThat(totalDiscountDto.afterDiscountAmount()).isEqualTo(116_600);
        assertThat(totalDiscountDto.badge()).isEqualTo("산타");
    }

    @DisplayName("20_000원 이상 할인받으면 1등급 뱃지 수령")
    @Test
    void success_GetFirstBadge() {
        TotalDiscountDto totalDiscountDto =
                new TotalDiscount(28_400, 120_000, 25_000).toDto();

        assertThat(totalDiscountDto.badge()).isEqualTo(FIRST_BADGE.getBadge());
    }

    @DisplayName("10,000원 이상 20_000원 미만 할인받으면 2등급 뱃지 수령")
    @Test
    void success_GetSecondBadge() {
        TotalDiscountDto totalDiscountDto =
                new TotalDiscount(19_999, 30_000, 0).toDto();

        assertThat(totalDiscountDto.badge()).isEqualTo(SECOND_BADGE.getBadge());
    }

    @DisplayName("5,000원 이상 10,000원 미만 할인받으면 3등급 뱃지 수령")
    @Test
    void success_GetThirdBadge() {
        TotalDiscountDto totalDiscountDto =
                new TotalDiscount(9999, 30_000, 0).toDto();

        assertThat(totalDiscountDto.badge()).isEqualTo(THIRD_BADGE.getBadge());
    }

    @DisplayName("5,000원 미만 할인받으면 뱃지 없음")
    @Test
    void success_NoneBadge() {
        TotalDiscountDto totalDiscountDto =
                new TotalDiscount(4999, 30_000, 0).toDto();

        assertThat(totalDiscountDto.badge()).isEqualTo(BADGE_NONE.getBadge());
    }
}