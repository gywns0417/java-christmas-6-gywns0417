package christmas.domain.discount;

import christmas.dto.OrderDto;

public class TotalDiscount {
    private final int totalDiscount;

    public TotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String giveBadge() {
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

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getDiscountedPurchaseAmount(OrderDto orderDto) {
        return orderDto.totalPurchaseAmount() - getTotalDiscount();
    }
}
