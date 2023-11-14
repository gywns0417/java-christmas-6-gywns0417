package christmas.dto;

public record DiscountDto (int christmasCountdownDiscount,
                           int weekdayDiscount,
                           int weekendDiscount,
                           int specialDiscount,
                           int giveawayDiscount,
                           int totalDiscount,
                           boolean hasAnyDiscount) {
}
