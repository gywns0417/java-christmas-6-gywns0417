package christmas.domain.order;

import static christmas.config.ErrorMessage.MENU_QUANTITY_INPUT_ERROR_MESSAGE;
import static christmas.config.EventConfig.MENU_TOTAL_QUANTITY_MAX;

import java.util.HashMap;
import java.util.StringTokenizer;

public class OrderMenu {
    private static final String orderMenuRegex = "^([가-힣]+-[1-9]\\d*(,\\s*[가-힣]+-[1-9]\\d*)*)$";
    private static final String splitDelim = ",-";

    private final HashMap<String, Integer> orderMenuQuantity;

    public OrderMenu(String inputOrderMenuQuantity) {
        String validaOrderMenu = validateRegexOrderMenu(inputOrderMenuQuantity);
        this.orderMenuQuantity = splitOrderMenu(validaOrderMenu);
        validateMenuQuantities();
    }

    private String validateRegexOrderMenu(String inputOrderMenu) {
        if (!inputOrderMenu.matches(orderMenuRegex)) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
        return inputOrderMenu;
    }

    private HashMap<String, Integer> splitOrderMenu(String inputOrderMenu) {
        HashMap<String, Integer> splitMenuQuantity = new HashMap<>();
        StringTokenizer st = new StringTokenizer(inputOrderMenu, splitDelim);

        while (st.hasMoreTokens()) {
            String menuName = st.nextToken();
            int quantity = Integer.parseInt(st.nextToken());

            validateMenuUnique(splitMenuQuantity, menuName);
            splitMenuQuantity.put(menuName, quantity);
        }
        return splitMenuQuantity;
    }

    private void validateMenuUnique(HashMap<String, Integer> splitMenuQuantity, String menuName) {
        if (splitMenuQuantity.containsKey(menuName)) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    private void validateMenuQuantities() {
        boolean isOverMenuQuantityMax = orderMenuQuantity.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > MENU_TOTAL_QUANTITY_MAX.getValue();
        if (isOverMenuQuantityMax) {
            throw new IllegalArgumentException(MENU_QUANTITY_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    public HashMap<String, Integer> getOrderMenuQuantity() {
        return orderMenuQuantity;
    }
}
