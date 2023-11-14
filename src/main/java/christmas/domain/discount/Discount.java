package christmas.domain.discount;

import christmas.dto.DiscountDto;

public class Discount {
    private final int christmasCountdownDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final int giveawayDiscount;
    private final int totalDiscount;
    private final String badge;

    public Discount(
            int christmasCountdownDiscount,
            int weekdayDiscount,
            int weekendDiscount,
            int specialDiscount,
            int giveawayDiscount
    ) {
        this.christmasCountdownDiscount = christmasCountdownDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.giveawayDiscount = giveawayDiscount;
        this.totalDiscount = calculateTotalDiscount();
        this.badge = giveBadge();
    }

    public DiscountDto toDto() {
        return new DiscountDto(christmasCountdownDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayDiscount, totalDiscount, badge);
    }

    private int calculateTotalDiscount() {
        return christmasCountdownDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayDiscount;
    }
    // TODO: 매직넘버 상수화 및 배지 로직 다른 객체 부여 고려

    private String giveBadge() {
        if (totalDiscount >= 20_000) {
            return "산타";
        }
        else if (totalDiscount >= 10_000) {
            return "트리";
        }
        else if(totalDiscount >= 5_000) {
            return "별";
        }
        return "없음";
    }
}
