package christmas.service;

import christmas.domain.discount.Discount;
import christmas.domain.discount.DiscountContext;
import christmas.domain.discount.DiscountType;
import christmas.domain.discount.TotalDiscount;
import christmas.domain.discount.strategy.ChristmasCountdownStrategy;
import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.domain.discount.strategy.GiveawayStrategy;
import christmas.domain.discount.strategy.SpecialStrategy;
import christmas.domain.discount.strategy.WeekdayStrategy;
import christmas.domain.discount.strategy.WeekendStrategy;
import christmas.dto.OrderDto;
import christmas.dto.VisitDateDto;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiscountService {
    private final List<DiscountStrategy> strategies = List.of(
            new ChristmasCountdownStrategy(),
            new GiveawayStrategy(),
            new SpecialStrategy(),
            new WeekdayStrategy(),
            new WeekendStrategy()
    );
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

    public Discount createDiscount() {
        //TODO:enum
        if (context.getTotalPurchaseAmount() >= 10000) {
            Map<DiscountType, Integer> discountTypeAmount = strategies.stream()
                    .collect(Collectors.toMap(
                            DiscountStrategy::getDiscountType,
                            strategy -> strategy.accept(context),
                            Integer::sum
                    ));

            HashMap<DiscountType, Integer> discountTypeAmountHashMap = new HashMap<>(discountTypeAmount);
            return new Discount(discountTypeAmountHashMap);
        }

        Map<DiscountType, Integer> emptyDiscountTypeAmount = Arrays.stream(DiscountType.values())
                .collect(Collectors.toMap(Function.identity(), value -> 0));

        HashMap<DiscountType, Integer> emptyDiscountTypeAmountHashMAp = new HashMap<>(emptyDiscountTypeAmount);
        return new Discount(emptyDiscountTypeAmountHashMAp);
    }
    // TODO : dessertAmount 등은 값이 변하는지 확인 필요(필요시 dto)

    public TotalDiscount 현(Discount discount) {
        return new TotalDiscount(discount.calculateTotalDiscount());
    }
}

//TODO : 일단 전략 하나를 구현하기는 했는데, 이걸 어떻게 전략패턴으로 옮길지가 관건. accept() 마다 필요한게 지금 다르다. 좀 더 공부해봐야 할 듯
//TODO : 지금 원하는건 그냥 Order, Amount, Date를 던져줬을 때 알아서 할인된 금액들을 Discount 객체가 가지게 하는게 좋을 듯!
//TODO : Order과 Date, Amount 를 적절히 조합해서 전략 패턴을 만들었다면, Discount 객체가 가지게 하고 출력만 하면 될 듯
//TODO : 단, 이렇게 도메인 객체의 값을 DTO를 통해서라도 함부러 꺼내와도 되는지가 중요함
//TODO : 도메인 객체 내부의 값들을 변경할 방법이 없을 때만 getter 사용. 또한 getter로 가져온 값을 변경이 되는지 한번 더 외부클래스에서 확인(메모리 주소)
//TODO : 또한 원시값 포장, 불변 객체, 일급 콜렉션에 대해서도 확실히 학습해서 적용할 것
