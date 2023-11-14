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
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.TotalDiscountDto;
import christmas.dto.VisitDateDto;
import christmas.service.DateService;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.service.TotalDiscountService;
import christmas.view.InputView;
import christmas.view.MessageOutputView;
import christmas.view.ResultOutputView;
import java.util.List;


public class EventPlannerController {
    //TODO : 금액에 콤마 넣기
    List<MenuStrategy> strategies = List.of(new AppetizerStrategy(), new DessertStrategy(),
            new DrinkStrategy(), new MainDishStrategy());
    //TODO: 의존성 주입 구현해야함
    OrderService orderService = new OrderService(strategies);
    DateService dateService = new DateService();
    TotalDiscountService totalDiscountService = new TotalDiscountService();

    public void run() {
        MessageOutputView.printWelcomeMessage();
        VisitDateDto visitDateDto = dateService.getDateInput(InputView::getUserInput, MessageOutputView::printDateInputRequestMessage,
                MessageOutputView::printErrorMessage).toDto();
        OrderDto orderDto = orderService.order(InputView::getUserInput, MessageOutputView::printMenuQuantityInputRequestMessage,
                MessageOutputView::printErrorMessage).toDto();
        DiscountService discountService = new DiscountService(visitDateDto, orderDto);
        DiscountDto discountDto = discountService.createDiscount().toDto();
        TotalDiscountDto totalDiscountDto = totalDiscountService.createTotalDiscount(discountDto, orderDto).toDto();
        ResultOutputView.printResult(visitDateDto, orderDto, discountDto, totalDiscountDto);
    }
}
