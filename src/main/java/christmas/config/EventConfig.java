package christmas.config;

public enum EventConfig {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    CHRISTMAS_DAY(25),
    GIVEAWAY_AMOUNT(1),
    MENU_TOTAL_QUANTITY_MAX(20),
    FOR_FIRST_BADGE_AMOUNT_MIN(20_000),
    FOR_SECOND_BADGE_AMOUNT_MIN(10_000),
    FOR_THIRD_BADGE_AMOUNT_MIN(5_000);



    private final int value;

    EventConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
