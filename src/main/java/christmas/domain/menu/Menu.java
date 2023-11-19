package christmas.domain.menu;

import java.util.HashMap;
import java.util.Map.Entry;

public interface Menu {
    int getAmount();
    static <T extends Enum<T> & Menu> int getTotalAmount(Class<T> menuClass,
                                                         HashMap<String, Integer> orderedMenuQuantities) {
        int totalAmount = 0;

        for (Entry<String, Integer> menu : orderedMenuQuantities.entrySet()) {
            totalAmount += Enum.valueOf(menuClass, menu.getKey()).getAmount() * menu.getValue();
        }
        return totalAmount;
    }
}
