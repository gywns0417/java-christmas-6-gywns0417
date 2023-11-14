package christmas.domain.discount.strategy;

import static christmas.config.DiscountConfig.FOR_GIVEAWAY_MIN_AMOUNT;
import static christmas.config.DiscountConfig.GIVEAWAY_DISCOUNT_AMOUNT;

import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;

public class GiveawayStrategy implements DiscountStrategy {
    @Override
    public int accept(DiscountContext context) {
        if (context.getTotalPurchaseAmount() >= FOR_GIVEAWAY_MIN_AMOUNT.getAmount()) {
            return applyDiscount(context);
        }
        return 0;
    }

    @Override
    public int applyDiscount(DiscountContext context) {
        return GIVEAWAY_DISCOUNT_AMOUNT.getAmount();
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.GIVEAWAY;
    }

}
