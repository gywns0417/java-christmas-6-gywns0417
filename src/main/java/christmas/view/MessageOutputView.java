package christmas.view;

import static christmas.config.EventMessage.DATE_INPUT_REQUEST_MESSAGE;
import static christmas.config.EventMessage.MENU_QUANTITY_INPUT_REQUEST_MESSAGE;
import static christmas.config.EventMessage.WELCOME_MESSAGE;

public class MessageOutputView {
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
}
