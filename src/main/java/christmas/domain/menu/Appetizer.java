package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map.Entry;

public enum Appetizer implements Menu {
    양송이수프(6_000),
    타파스(5_500),
    시저샐러드(8_000);

    private final int amount;

    Appetizer(int amount) {
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