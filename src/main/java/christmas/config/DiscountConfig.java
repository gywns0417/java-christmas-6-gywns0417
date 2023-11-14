package christmas.config;

import static christmas.config.EventConfig.EVENT_YEAR;

public enum DiscountConfig {
    FOR_DISCOUNT_MIN_PURCHASE_AMOUNT(10_000),
    CHRISTMAS_DISCOUNT_START_AMOUNT(1_000),
    CHRISTMAS_DISCOUNT_INCREASE_AMOUNT(100),
    WEEKDAY_DESSERT_DISCOUNT_AMOUNT(EVENT_YEAR.getValue()),
    WEEKEND_MAIN_DISH_DISCOUNT_AMOUNT(EVENT_YEAR.getValue()),
    SPECIAL_DISCOUNT_AMOUNT(1_000),
    FOR_GIVEAWAY_MIN_AMOUNT(120_000),
    GIVEAWAY_DISCOUNT_AMOUNT(25_000);

    private final int amount;

    DiscountConfig(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
