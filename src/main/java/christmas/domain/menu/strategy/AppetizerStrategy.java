package christmas.domain.menu.strategy;

import christmas.domain.menu.Appetizer;
import java.util.HashMap;

public class AppetizerStrategy implements MenuStrategy {
    @Override
    public boolean acceptMenuName(String menuName) {
        return MenuPresenceChecker.isEnumPresent(Appetizer.class, menuName);
    }

    @Override
    public void putMenu(HashMap<String, Integer> orderedMenuQuantities, String menuName, int quantity) {
        orderedMenuQuantities.put(menuName, quantity);
    }
}
