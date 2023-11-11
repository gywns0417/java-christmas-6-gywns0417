package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map.Entry;

public enum Dessert implements Menu {
    초코케이크(15_000),
    아이스크림(5_000);

    private final int amount;

    Dessert(int amount) {
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
