package christmas.domain.menu.strategy;

import christmas.domain.menu.Dessert;
import java.util.HashMap;

public class DessertStrategy implements MenuStrategy {
    @Override
    public boolean acceptMenuName(String menuName) {
        return MenuPresenceChecker.isEnumPresent(Dessert.class, menuName);
    }

    @Override
    public void putMenu(HashMap<String, Integer> orderedMenuQuantities, String menuName, int quantity) {
        orderedMenuQuantities.put(menuName, quantity);
    }
}
