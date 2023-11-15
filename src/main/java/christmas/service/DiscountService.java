package christmas.service;

import static christmas.config.DiscountConfig.FOR_DISCOUNT_MIN_PURCHASE_AMOUNT;

import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;
import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.dto.OrderDto;
import christmas.dto.VisitDateDto;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiscountService {
    private final DiscountContext context;
    public DiscountService(VisitDateDto visitDateDto, OrderDto orderDto) {
        this.context = new DiscountContext(
                visitDateDto.day(),
                visitDateDto.date(),
                orderDto.dessert(),
                orderDto.mainDish(),
                visitDateDto.star(),
                orderDto.totalPurchaseAmount());
    }

    public Discount createDiscount(List<DiscountStrategy> strategies) {
        if (context.getTotalPurchaseAmount() >= FOR_DISCOUNT_MIN_PURCHASE_AMOUNT.getAmount()) {
            return new Discount(createDiscountTypeAmount(strategies));
        }
        return new Discount(createEmptyDiscountTypeAmount());
    }

    private HashMap<DiscountType, Integer> createDiscountTypeAmount(List<DiscountStrategy> strategies) {
        Map<DiscountType, Integer> discountTypeAmount = strategies.stream()
                .collect(Collectors.toMap(
                        DiscountStrategy::getDiscountType,
                        strategy -> strategy.accept(context),
                        Integer::sum
                ));
        return (HashMap<DiscountType, Integer>) discountTypeAmount;
    }

    private HashMap<DiscountType, Integer> createEmptyDiscountTypeAmount() {
        Map<DiscountType, Integer> emptyDiscountTypeAmount = Arrays.stream(DiscountType.values())
                .collect(Collectors.toMap(Function.identity(), value -> 0));
        return (HashMap<DiscountType, Integer>) emptyDiscountTypeAmount;
    }
}