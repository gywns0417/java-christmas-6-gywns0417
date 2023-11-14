package christmas.config;

import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.EventConfig.GIVEAWAY_AMOUNT;

public enum ResultMessage {
    GIVEAWAY_ITEM("샴페인"),
    RESULT_HEADER_MESSAGE("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    RESULT_ORDERED_MENU_MESSAGE("\n<주문 메뉴>"),
    RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE("\n<할인 전 총주문 금액>\n"),
    RESULT_GIVEAWAY_MENU_MESSAGE("\n<증정 메뉴>\n"),
    RESULT_DISCOUNT_HISTORY_MESSAGE("\n<혜택 내역>\n"),
    RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE("\n<총혜택 금액>\n"),
    RESULT_EXPECT_PAYMENT_MESSAGE("\n<할인 후 예상 결제 금액>\n"),
    RESULT_BADGE_MESSAGE(String.format("\n<%d월 이벤트 배지>\n", EVENT_MONTH.getValue())),
    GIVEAWAY_ITEM_QUANTITY(String.format("%s %d개\n",
            GIVEAWAY_ITEM.getMessage(), GIVEAWAY_AMOUNT.getValue())),
    AMOUNT("%s원\n"),
    BADGE("%s"),
    NONE("없음\n");

    private final String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormatMessage(Object... args) {
        return String.format(message, args);
    }
}
