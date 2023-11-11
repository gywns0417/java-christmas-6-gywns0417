package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map.Entry;

public enum Drink implements Menu {
    제로콜라(3_000),
    레드와인(60_000),
    샴페인(25_000);

    private final int amount;

    Drink(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static int getTotalAmount(HashMap<String, Integer> orderedMenuQuantities) {
        int totalAmount = 0;
        for (Entry<String, Integer> menu : orderedMenuQuantities.entrySet()) {
            totalAmount += valueOf(menu.getKey()).getAmount() * menu.getValue();
        }
        return totalAmount;
    }

}
