package christmas.domain.discount.strategy;

import static christmas.config.DiscountConfig.WEEKEND_MAIN_DISH_DISCOUNT_AMOUNT;

import christmas.config.Weekend;
import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;
import java.util.Arrays;

public class WeekendStrategy implements DiscountStrategy {

    @Override
    public int accept(DiscountContext context) {
        if (Arrays.stream(Weekend.values()) // 요일이 평일이라면
                .anyMatch(e -> e.name().equals(context.getDay()))) {
            return applyDiscount(context);
        }
        return 0;
    }

    @Override
    public int applyDiscount(DiscountContext context) {
        int totalQuantity = 0;
        for (int quantity : context.getMainDish().values()) {
            totalQuantity += quantity;
        }
        return totalQuantity * WEEKEND_MAIN_DISH_DISCOUNT_AMOUNT.getAmount();
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.WEEKEND;
    }
}
