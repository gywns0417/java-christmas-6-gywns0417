package christmas.domain.discount.strategy;

import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;

public interface DiscountStrategy {
    int accept(DiscountContext context);

    int applyDiscount(DiscountContext context);
    DiscountType getDiscountType();
}
