package christmas.domain.menu.strategy;

import christmas.domain.menu.Drink;
import java.util.HashMap;

public class DrinkStrategy implements MenuStrategy {
    @Override
    public boolean acceptMenuName(String menuName) {
        return MenuPresenceChecker.isEnumPresent(Drink.class, menuName);
    }

    @Override
    public void putMenu(HashMap<String, Integer> orderedMenuQuantities, String menuName, int quantity) {
        orderedMenuQuantities.put(menuName, quantity);
    }
}
