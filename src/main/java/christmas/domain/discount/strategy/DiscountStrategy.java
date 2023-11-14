package christmas.domain.discount.strategy;

import christmas.domain.discount.DiscountContext;

public interface DiscountStrategy {
    int accept(DiscountContext context);

    int applyDiscount(DiscountContext context);
}
