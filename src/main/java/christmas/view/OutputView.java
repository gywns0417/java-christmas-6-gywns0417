package christmas.view;

import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.EventMessage.DATE_INPUT_REQUEST_MESSAGE;
import static christmas.config.EventMessage.MENU_QUANTITY_INPUT_REQUEST_MESSAGE;
import static christmas.config.EventMessage.WELCOME_MESSAGE;
import static christmas.config.ResultMessage.RESULT_HEADER_MESSAGE;
import static christmas.config.ResultMessage.RESULT_ORDERED_MENU_MESSAGE;
import static christmas.config.ResultParagraph.RESULT_DISCOUNT_FIRST_PARAGRAPH;
import static christmas.config.ResultParagraph.RESULT_DISCOUNT_SECOND_PARAGRAPH;
import static christmas.config.ResultParagraph.RESULT_NO_DISCOUNT_PARAGRAPH;

import christmas.domain.date.VisitDate;
import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.VisitDateDto;
import java.util.HashMap;
import java.util.Map.Entry;

public class OutputView {
    //TODO: 가독성 향상

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
    }

    public static void printDateInputRequestMessage() {
        System.out.println(DATE_INPUT_REQUEST_MESSAGE.getMessage());
    }

    public static void printMenuQuantityInputRequestMessage() {
        System.out.println(MENU_QUANTITY_INPUT_REQUEST_MESSAGE.getFormatMessage());
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
    public static void printOrderedMenu(HashMap<String, Integer> splitMenuQuantity) {
        for (Entry<String, Integer> menuQuantity : splitMenuQuantity.entrySet()) {
            System.out.printf("%s %d개\n", menuQuantity.getKey(), menuQuantity.getValue());
        }
    }

    public static void printResult(DiscountDto discountDto, VisitDateDto visitDateDto, OrderDto orderDto) {
        if (discountDto.hasAnyDiscount()) {
            System.out.println(RESULT_HEADER_MESSAGE.getFormatMessage(EVENT_MONTH.getValue(), visitDateDto.date())
                    + RESULT_ORDERED_MENU_MESSAGE.getMessage());
            printOrderedMenu(orderDto.splitMenuQuantity());
            System.out.printf(RESULT_DISCOUNT_FIRST_PARAGRAPH.getParagraph(), orderDto.totalPurchaseAmount());
            if (discountDto.christmasCountdownDiscount() != 0) {
                System.out.printf("크리스마스 디데이 할인: -%s원\n", discountDto.christmasCountdownDiscount());
            }
            if (discountDto.weekdayDiscount() != 0) {
                System.out.printf("평일 할인: -%s원\n", discountDto.weekdayDiscount());
            }
            if (discountDto.specialDiscount() != 0) {
                System.out.printf("특별 할인: -%s원\n", discountDto.specialDiscount());
            }
            if (discountDto.giveawayDiscount() != 0) {
                System.out.printf("증정 이벤트: -%s원\n", discountDto.giveawayDiscount());
            }
            System.out.printf(RESULT_DISCOUNT_SECOND_PARAGRAPH.getParagraph(),
                    discountDto.totalDiscount(), orderDto.totalPurchaseAmount() - discountDto.totalDiscount(), discountDto.badge());
            return;
        }
            System.out.println(RESULT_HEADER_MESSAGE.getFormatMessage(EVENT_MONTH.getValue(), visitDateDto.date())
                    + RESULT_ORDERED_MENU_MESSAGE.getMessage());
            printOrderedMenu(orderDto.splitMenuQuantity());
            System.out.printf(RESULT_NO_DISCOUNT_PARAGRAPH.getParagraph(), orderDto.totalPurchaseAmount(),
                    orderDto.totalPurchaseAmount() - discountDto.totalDiscount(), discountDto.badge());
    }
}
