package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map.Entry;

public enum MainDish implements Menu {
    티본스테이크(55_000),
    바비큐립(54_000),
    해산물파스타(35_000),
    크리스마스파스타(25_000);

    private final int amount;

    MainDish(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    public static int getTotalAmount(HashMap<String, Integer> orderedMenuQuantities) {
        return Menu.getTotalAmount(MainDish.class, orderedMenuQuantities);
    }
}
