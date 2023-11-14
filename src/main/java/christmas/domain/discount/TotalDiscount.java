package christmas.domain.discount;

import christmas.dto.TotalDiscountDto;

public class TotalDiscount {
    private final int totalDiscount;
    private final int afterDiscountAmount;

    public TotalDiscount(int totalDiscount, int totalPurchaseAmount) {
        this.totalDiscount = totalDiscount;
        this.afterDiscountAmount = calculateDiscountedPurchaseAmount(totalPurchaseAmount);
    }

    public TotalDiscountDto toDto() {
        return new TotalDiscountDto(totalDiscount, afterDiscountAmount, giveBadge());
    }

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

    private int calculateDiscountedPurchaseAmount(int totalPurchaseAmount) {
        return totalPurchaseAmount - totalDiscount;
    }
}
