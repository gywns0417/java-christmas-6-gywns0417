package christmas.view;

import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.ResultMessage.GIVEAWAY_ITEM_QUANTITY;
import static christmas.config.ResultMessage.MENU_QUANTITY_FORMAT;
import static christmas.config.ResultMessage.NONE;
import static christmas.config.ResultMessage.RESULT_BADGE_MESSAGE;
import static christmas.config.ResultMessage.RESULT_CHRISTMAS_DISCOUNT;
import static christmas.config.ResultMessage.RESULT_DISCOUNT_HISTORY_MESSAGE;
import static christmas.config.ResultMessage.RESULT_EXPECT_PAYMENT_MESSAGE;
import static christmas.config.ResultMessage.RESULT_GIVEAWAY_DISCOUNT;
import static christmas.config.ResultMessage.RESULT_GIVEAWAY_MENU_MESSAGE;
import static christmas.config.ResultMessage.RESULT_HEADER_MESSAGE;
import static christmas.config.ResultMessage.RESULT_ORDERED_MENU_MESSAGE;
import static christmas.config.ResultMessage.RESULT_SPECIAL_DISCOUNT;
import static christmas.config.ResultMessage.RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE;
import static christmas.config.ResultMessage.RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE;
import static christmas.config.ResultMessage.RESULT_WEEKDAY_DISCOUNT;
import static christmas.config.ResultMessage.RESULT_WEEKEND_DISCOUNT;

import christmas.dto.DiscountDto;
import christmas.dto.OrderDto;
import christmas.dto.TotalDiscountDto;
import christmas.dto.VisitDateDto;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map.Entry;

public class ResultOutputView {
    private static final DecimalFormat amountFormat = new DecimalFormat("#,###");
    private static final DecimalFormat discountFormat = new DecimalFormat("-#,###");

    public static void printResult(VisitDateDto visitDateDto, OrderDto orderDto, DiscountDto discountDto,
                                   TotalDiscountDto totalDiscountDto) {
        printHeaderMessage(visitDateDto.date());
        printOrderedMenu(orderDto.orderMenuQuantity());
        printTotalPurchaseAmount(orderDto.totalPurchaseAmount());
        printGiveaway(discountDto.giveawayDiscount());
        printDiscountHistory(discountDto);
        printTotalDiscount(totalDiscountDto.totalDiscount());
        printExpectPurchaseAmount(totalDiscountDto.afterDiscountAmount());
        printBadge(totalDiscountDto.badge());
    }
    private static void printHeaderMessage(int date) {
        System.out.printf(RESULT_HEADER_MESSAGE.getMessage(), EVENT_MONTH.getValue(), date);
    }
    private static void printOrderedMenu(HashMap<String, Integer> splitMenuQuantity) {
        System.out.print(RESULT_ORDERED_MENU_MESSAGE.getMessage());
        for (Entry<String, Integer> menuQuantity : splitMenuQuantity.entrySet()) {
            System.out.printf(MENU_QUANTITY_FORMAT.getMessage(), menuQuantity.getKey(), menuQuantity.getValue());
        }
    }

    private static void printTotalPurchaseAmount(int totalPurchaseAmount) {
        System.out.printf(RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE.getMessage(), amountFormat.format(totalPurchaseAmount));

    }

    private static void printGiveaway(int giveawayDiscount) {
        if (giveawayDiscount != 0) {
            System.out.printf(RESULT_GIVEAWAY_MENU_MESSAGE.getMessage(), GIVEAWAY_ITEM_QUANTITY.getMessage());
            return;
        }
        System.out.printf(RESULT_GIVEAWAY_MENU_MESSAGE.getMessage(), NONE.getMessage());
    }

    private static void printDiscountHistory(DiscountDto discountDto) {
        System.out.print(RESULT_DISCOUNT_HISTORY_MESSAGE.getMessage());
        if (discountDto.christmasCountdownDiscount() != 0) {
            System.out.printf(RESULT_CHRISTMAS_DISCOUNT.getMessage(),
                    discountFormat.format(discountDto.christmasCountdownDiscount()));
        }
        if (discountDto.weekdayDiscount() != 0) {
            System.out.printf(RESULT_WEEKDAY_DISCOUNT.getMessage(),
                    discountFormat.format(discountDto.weekdayDiscount()));
        }
        if (discountDto.weekendDiscount() != 0) {
            System.out.printf(RESULT_WEEKEND_DISCOUNT.getMessage(),
                    discountFormat.format(discountDto.weekendDiscount()));
        }
        if (discountDto.specialDiscount() != 0) {
            System.out.printf(RESULT_SPECIAL_DISCOUNT.getMessage(),
                    discountFormat.format(discountDto.specialDiscount()));
        }
        if (discountDto.giveawayDiscount() != 0) {
            System.out.printf(RESULT_GIVEAWAY_DISCOUNT.getMessage(),
                    discountFormat.format(discountDto.giveawayDiscount()));
        }
        if (!discountDto.hasAnyDiscount()) {
            System.out.print(NONE.getMessage());
        }
    }

    private static void printTotalDiscount(int totalDiscount) {
        System.out.printf(RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE.getMessage(), amountFormat.format(totalDiscount));
    }
    private static void printExpectPurchaseAmount(int discountedPurchaseAmount) {
        System.out.printf(RESULT_EXPECT_PAYMENT_MESSAGE.getMessage(), amountFormat.format(discountedPurchaseAmount));
    }

    private static void printBadge(String badge) {
        System.out.printf(RESULT_BADGE_MESSAGE.getMessage(), EVENT_MONTH.getValue(), badge);
    }
}
