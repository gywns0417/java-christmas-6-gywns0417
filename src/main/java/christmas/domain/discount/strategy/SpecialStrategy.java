package christmas.domain.discount.strategy;

import static christmas.config.DiscountConfig.SPECIAL_DISCOUNT_AMOUNT;

import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;

public class SpecialStrategy implements DiscountStrategy {
    @Override
    public int accept(DiscountContext context) {
        if (context.getStar()) {
            return applyDiscount(context);
        }
        return 0;
    }

    @Override
    public int applyDiscount(DiscountContext context) {
        return SPECIAL_DISCOUNT_AMOUNT.getAmount();
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.SPECIAL;
    }

}
