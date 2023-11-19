package christmas.domain.discount;

import static christmas.config.BadgeConfig.BADGE_NONE;
import static christmas.config.BadgeConfig.FIRST_BADGE;
import static christmas.config.BadgeConfig.SECOND_BADGE;
import static christmas.config.BadgeConfig.THIRD_BADGE;
import static christmas.config.EventConfig.FOR_FIRST_BADGE_AMOUNT_MIN;
import static christmas.config.EventConfig.FOR_SECOND_BADGE_AMOUNT_MIN;
import static christmas.config.EventConfig.FOR_THIRD_BADGE_AMOUNT_MIN;

import christmas.dto.TotalDiscountDto;

public class TotalDiscount {
    private final int totalDiscount;
    private final int afterDiscountAmount;

    public TotalDiscount(int totalDiscount, int totalPurchaseAmount, int giveawayDiscount) {
        this.totalDiscount = totalDiscount;
        this.afterDiscountAmount = calculateAfterDiscountAmount(totalPurchaseAmount,
                totalDiscount, giveawayDiscount);
    }

    public TotalDiscountDto toDto() {
        return new TotalDiscountDto(totalDiscount, afterDiscountAmount, giveBadge());
    }

    private String giveBadge() {
        if (totalDiscount >= FOR_FIRST_BADGE_AMOUNT_MIN.getValue()) {
            return FIRST_BADGE.getBadge();
        }
        else if (totalDiscount >= FOR_SECOND_BADGE_AMOUNT_MIN.getValue()) {
            return SECOND_BADGE.getBadge();
        }
        else if(totalDiscount >= FOR_THIRD_BADGE_AMOUNT_MIN.getValue()) {
            return THIRD_BADGE.getBadge();
        }
        return BADGE_NONE.getBadge();
    }

    private int calculateAfterDiscountAmount(int totalPurchaseAmount, int totalDiscount, int giveawayDiscount) {
        return totalPurchaseAmount - totalDiscount + giveawayDiscount;
    }
}
