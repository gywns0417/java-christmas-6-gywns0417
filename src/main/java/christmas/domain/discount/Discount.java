package christmas.domain.discount;

import java.util.HashMap;

public class Discount {
    private final HashMap<DiscountType, Integer> discountTypeAmount;

    public Discount(HashMap<DiscountType, Integer> discountTypeAmount) {
        this.discountTypeAmount = new HashMap<>(discountTypeAmount);
    }

    public int calculateTotalDiscount() {
        return discountTypeAmount.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getChristmasDiscount() {
        return discountTypeAmount.get(DiscountType.CHRISTMAS_COUNTDOWN);
    }

    public int getWeekdayDiscount() {
        return discountTypeAmount.get(DiscountType.WEEKDAY);
    }

    public int getWeekendDiscount() {
        return discountTypeAmount.get(DiscountType.WEEKEND);
    }

    public int getSpecialDiscount() {
        return discountTypeAmount.get(DiscountType.SPECIAL);
    }

    public int getGiveawayDiscount() {
        return discountTypeAmount.get(DiscountType.GIVEAWAY);
    }

    public boolean hasAnyDiscount() {
        return calculateTotalDiscount() != 0;
    }
}
