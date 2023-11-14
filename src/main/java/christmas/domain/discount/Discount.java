package christmas.domain.discount;

import christmas.dto.DiscountDto;
import java.util.HashMap;

public class Discount {
    private final HashMap<DiscountType, Integer> discountTypeAmount;

    public Discount(HashMap<DiscountType, Integer> discountTypeAmount) {
        this.discountTypeAmount = new HashMap<>(discountTypeAmount);
    }

    public DiscountDto toDto() {
        return new DiscountDto(getChristmasDiscount(), getWeekdayDiscount(),
                getWeekendDiscount(), getSpecialDiscount(),
                getGiveawayDiscount(), calculateTotalDiscount(), hasAnyDiscount());
    }

    private int calculateTotalDiscount() {
        return discountTypeAmount.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getChristmasDiscount() {
        return discountTypeAmount.get(DiscountType.CHRISTMAS_COUNTDOWN);
    }

    private int getWeekdayDiscount() {
        return discountTypeAmount.get(DiscountType.WEEKDAY);
    }

    private int getWeekendDiscount() {
        return discountTypeAmount.get(DiscountType.WEEKEND);
    }

    private int getSpecialDiscount() {
        return discountTypeAmount.get(DiscountType.SPECIAL);
    }

    private int getGiveawayDiscount() {
        return discountTypeAmount.get(DiscountType.GIVEAWAY);
    }

    private boolean hasAnyDiscount() {
        return calculateTotalDiscount() != 0;
    }
}
