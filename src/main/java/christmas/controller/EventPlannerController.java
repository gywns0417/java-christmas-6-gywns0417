package christmas.controller;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.Discount;
import christmas.domain.discount.TotalDiscount;
import christmas.domain.menu.strategy.AppetizerStrategy;
import christmas.domain.menu.strategy.DessertStrategy;
import christmas.domain.menu.strategy.DrinkStrategy;
import christmas.domain.menu.strategy.MainDishStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.domain.order.Order;
import christmas.service.DateService;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.service.TotalDiscountService;
import christmas.view.InputView;
import christmas.view.MessageOutputView;
import christmas.view.ResultOutputView;
import java.util.List;


public class EventPlannerController {
    List<MenuStrategy> strategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
            new DrinkStrategy(), new MainDishStrategy());
    //TODO: 의존성 주입 구현해야함
    OrderService orderService = new OrderService(strategies);
    DateService dateService = new DateService();
    TotalDiscountService totalDiscountService = new TotalDiscountService();

    public void run() {
        MessageOutputView.printWelcomeMessage();
        VisitDate visitDate = dateService.getDateInput(InputView::getUserInput, MessageOutputView::printDateInputRequestMessage,
                MessageOutputView::printErrorMessage);
        Order order = orderService.order(InputView::getUserInput, MessageOutputView::printMenuQuantityInputRequestMessage,
                MessageOutputView::printErrorMessage);
        DiscountService discountService = new DiscountService(visitDate.toDto(), order.toDto());
        Discount discount = discountService.createDiscount();
        TotalDiscount totalDiscount = totalDiscountService.createTotalDiscount(discount.toDto(), order.toDto());
        ResultOutputView.printResult(visitDate.toDto(), order.toDto(), discount.toDto(), totalDiscount.toDto());

    }
}
