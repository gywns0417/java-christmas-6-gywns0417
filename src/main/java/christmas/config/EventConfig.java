package christmas.config;

public enum EventConfig {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    CHRISTMAS_DAY(25),
    GIVEAWAY_AMOUNT(1),
    MENU_TOTAL_QUANTITY_MAX(20);



    private final int value;

    EventConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
