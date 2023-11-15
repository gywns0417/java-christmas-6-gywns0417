package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.discount.strategy.ChristmasCountdownStrategy;
import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.domain.discount.strategy.GiveawayStrategy;
import christmas.domain.discount.strategy.SpecialStrategy;
import christmas.domain.discount.strategy.WeekdayStrategy;
import christmas.domain.discount.strategy.WeekendStrategy;
import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.service.VisitDateService;
import christmas.service.OrderService;
import christmas.service.TotalDiscountService;
import java.util.List;

public class EventPlannerFactory {
    public EventPlannerController createEventPlannerController() {
        List<MenuStrategy> menuStrategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
                new DrinkStrategy(), new MainDishStrategy());

        List<DiscountStrategy> discountStrategies = List.of(
                new ChristmasCountdownStrategy(), new GiveawayStrategy(),
                new SpecialStrategy(), new WeekdayStrategy(), new WeekendStrategy());

        OrderService orderService = new OrderService();
        VisitDateService visitDateService = new VisitDateService();
        TotalDiscountService totalDiscountService = new TotalDiscountService();

        return new EventPlannerController(orderService, visitDateService,
                totalDiscountService, discountStrategies, menuStrategies);
    }
}
