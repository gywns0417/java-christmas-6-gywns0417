package christmas.service;

import christmas.domain.discount.Discount;
import christmas.domain.discount.TotalDiscount;
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;

public class TotalDiscountService {
    public TotalDiscount createTotalDiscount(DiscountDto discountDto, OrderDto orderDto) {
        return new TotalDiscount(discountDto.totalDiscount(), orderDto.totalPurchaseAmount());
    }
}
