package christmas.config;

import static christmas.config.DiscountConfig.FOR_DISCOUNT_MIN_PURCHASE_AMOUNT;
import static christmas.config.EventConfig.EVENT_MONTH;

public enum EventMessage {
    WELCOME_MESSAGE(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", EVENT_MONTH.getValue())),
    DATE_INPUT_REQUEST_MESSAGE(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
            EVENT_MONTH.getValue())),
    MENU_QUANTITY_INPUT_REQUEST_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_NEEDS_MIN_PURCHASE_AMOUNT_MESSAGE("\n\n총주문 금액 %s원 이상부터 이벤트가 적용됩니다.");
    private final String message;

    EventMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormatMessage(Object... args) {
        return String.format(message, args);
    }


}
