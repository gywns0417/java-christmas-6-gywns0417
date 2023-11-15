package christmas.config;

public enum ErrorMessage {
    ERROR("[ERROR] "),
    DATE_INPUT_ERROR_MESSAGE((ERROR.message) + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_QUANTITY_INPUT_ERROR_MESSAGE((ERROR.message) + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    PURCHASE_ONLY_DRINK_ERROR_MESSAGE((ERROR.message) + "음료만 주문 시, 주문할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
