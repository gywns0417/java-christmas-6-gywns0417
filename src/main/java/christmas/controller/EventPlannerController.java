package christmas.controller;

import christmas.domain.discount.strategy.DiscountStrategy;
import christmas.domain.menu.strategy.MenuStrategy;
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.TotalDiscountDto;
import christmas.dto.VisitDateDto;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.service.TotalDiscountService;
import christmas.service.VisitDateService;
import christmas.view.InputView;
import christmas.view.MessageOutputView;
import christmas.view.ResultOutputView;
import java.util.List;


public class EventPlannerController {
    private final OrderService orderService;
    private final VisitDateService visitDateService;
    private final TotalDiscountService totalDiscountService;
    private final List<DiscountStrategy> discountStrategies;
    private final List<MenuStrategy> menuStrategies;

    public EventPlannerController(OrderService orderService, VisitDateService visitDateService,
                                  TotalDiscountService totalDiscountService,
                                  List<DiscountStrategy> discountStrategies,
                                  List<MenuStrategy> menuStrategies) {
        this.orderService = orderService;
        this.visitDateService = visitDateService;
        this.totalDiscountService = totalDiscountService;
        this.discountStrategies = discountStrategies;
        this.menuStrategies = menuStrategies;
    }

    public void run() {
        MessageOutputView.printWelcomeMessage();
        VisitDateDto visitDateDto = visitDateService.getDateInput(InputView::getUserInput, MessageOutputView::printDateInputRequestMessage,
                MessageOutputView::printErrorMessage).toDto();
        OrderDto orderDto = orderService.order(InputView::getUserInput, MessageOutputView::printMenuQuantityInputRequestMessage,
                MessageOutputView::printErrorMessage, menuStrategies).toDto();
        DiscountService discountService = new DiscountService(visitDateDto, orderDto);
        DiscountDto discountDto = discountService.createDiscount(discountStrategies).toDto();
        TotalDiscountDto totalDiscountDto = totalDiscountService.createTotalDiscount(discountDto, orderDto).toDto();
        ResultOutputView.printResult(visitDateDto, orderDto, discountDto, totalDiscountDto);
    }
}
