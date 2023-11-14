package christmas.domain.discount.strategy;

import static christmas.config.DiscountConfig.CHRISTMAS_DISCOUNT_INCREASE_AMOUNT;
import static christmas.config.DiscountConfig.CHRISTMAS_DISCOUNT_START_AMOUNT;
import static christmas.config.EventConfig.CHRISTMAS_DAY;

import christmas.domain.discount.DiscountContext;

public class ChristmasCountdownStrategy implements DiscountStrategy {

    @Override
    public int accept(DiscountContext context) {
        if (context.getDate() <= CHRISTMAS_DAY.getValue()) {
            return applyDiscount(context);
        }
        return 0;
    }

    @Override
    public int applyDiscount(DiscountContext context) {
        return (CHRISTMAS_DISCOUNT_START_AMOUNT.getAmount() +
                (context.getDate() * CHRISTMAS_DISCOUNT_INCREASE_AMOUNT.getAmount())
                - CHRISTMAS_DISCOUNT_INCREASE_AMOUNT.getAmount());
    }
}
