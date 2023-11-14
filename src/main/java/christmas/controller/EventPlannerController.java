package christmas.controller;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.Discount;
import christmas.domain.order.Order;
import christmas.service.DateService;
import christmas.service.DiscountService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;


public class EventPlannerController {
    OrderService orderService = new OrderService();
    DateService dateService = new DateService();

    public void run() {
        OutputView.printWelcomeMessage();
        VisitDate visitDate = dateService.getDateInput(InputView::getUserInput, OutputView::printDateInputRequestMessage,
                OutputView::printErrorMessage);
        Order order = orderService.start(InputView::getUserInput, OutputView::printMenuQuantityInputRequestMessage,
                OutputView::printErrorMessage);
        DiscountService discountService = new DiscountService(visitDate.toDto(), order.toDto());
        Discount discount = discountService.createDiscount();
        OutputView.printResult(discount.toDto(), visitDate.toDto(), order.toDto());

    }
}
