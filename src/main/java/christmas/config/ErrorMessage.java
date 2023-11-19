package christmas.config;

import static christmas.config.EventConfig.MENU_TOTAL_QUANTITY_MAX;

public enum ErrorMessage {
    ERROR("[ERROR] "),
    DATE_INPUT_ERROR_MESSAGE((ERROR.message) + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_QUANTITY_INPUT_ERROR_MESSAGE((ERROR.message) + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    PURCHASE_ONLY_DRINK_ERROR_MESSAGE((ERROR.message) + "음료만 주문 시, 주문할 수 없습니다."),
    PURCHASE_QUANTITIES_OVER_MAX_ERROR_MESSAGE((ERROR.message)
            + String.format("메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다.", MENU_TOTAL_QUANTITY_MAX.getValue()));

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
