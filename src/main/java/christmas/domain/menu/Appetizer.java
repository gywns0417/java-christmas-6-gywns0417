package christmas.domain.menu;

import java.util.HashMap;

public enum Appetizer implements Menu {
    양송이수프(6_000),
    타파스(5_500),
    시저샐러드(8_000);

    private final int amount;

    Appetizer(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public static int getTotalAmount(HashMap<String, Integer> orderedMenuQuantities) {
        return Menu.getTotalAmount(Appetizer.class, orderedMenuQuantities);
    }
}