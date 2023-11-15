package christmas.controller;

import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
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
    private final OrderService orderService;
    private final DateService dateService;
    private final TotalDiscountService totalDiscountService;
    private final List<DiscountStrategy> discountStrategies;
    private final List<MenuStrategy> menuStrategies;

    public EventPlannerController(OrderService orderService, DateService dateService,
                                  TotalDiscountService totalDiscountService,
                                  List<DiscountStrategy> discountStrategies,
                                  List<MenuStrategy> menuStrategies) {
        this.orderService = orderService;
        this.dateService = dateService;
        this.totalDiscountService = totalDiscountService;
        this.discountStrategies = discountStrategies;
        this.menuStrategies = menuStrategies;
    }

    public void run() {
        MessageOutputView.printWelcomeMessage();
        VisitDateDto visitDateDto = dateService.getDateInput(InputView::getUserInput, MessageOutputView::printDateInputRequestMessage,
                MessageOutputView::printErrorMessage).toDto();
        OrderDto orderDto = orderService.order(InputView::getUserInput, MessageOutputView::printMenuQuantityInputRequestMessage,
                MessageOutputView::printErrorMessage, menuStrategies).toDto();
        DiscountService discountService = new DiscountService(visitDateDto, orderDto, discountStrategies);
        DiscountDto discountDto = discountService.createDiscount().toDto();
        TotalDiscountDto totalDiscountDto = totalDiscountService.createTotalDiscount(discountDto, orderDto).toDto();
        ResultOutputView.printResult(visitDateDto, orderDto, discountDto, totalDiscountDto);
    }
}
