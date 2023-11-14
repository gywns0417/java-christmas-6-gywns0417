package christmas.dto;

public record DiscountDto (int christmasCountdownDiscount,
                           int weekdayDiscount,
                           int weekendDiscount,
                           int specialDiscount,
                           int giveawayDiscount,
                           int totalDiscount,
                           String badge) {
    public boolean hasAnyDiscount() {
        return totalDiscount != 0;
    }

}
