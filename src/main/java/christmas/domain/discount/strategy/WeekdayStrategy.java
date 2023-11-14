package christmas.domain.discount.strategy;

import static christmas.config.DiscountConfig.WEEKDAY_DESSERT_DISCOUNT_AMOUNT;

import christmas.domain.discount.DiscountContext;
import christmas.config.Weekday;
import christmas.domain.discount.DiscountType;
import java.util.Arrays;

public class WeekdayStrategy implements DiscountStrategy {

    @Override
    public int accept(DiscountContext context) {
        if (Arrays.stream(Weekday.values()) // 요일이 평일이라면
                .anyMatch(e -> e.name().equals(context.getDay()))) {
            return applyDiscount(context);
        }
        return 0;
    }

    @Override
    public int applyDiscount(DiscountContext context) {
        int totalQuantity = 0;
        for (int quantity : context.getDessert().values()) {
            totalQuantity += quantity;
        }
        return totalQuantity * WEEKDAY_DESSERT_DISCOUNT_AMOUNT.getAmount();
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.WEEKDAY;
    }
}
