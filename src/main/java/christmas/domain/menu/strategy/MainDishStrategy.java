package christmas.domain.menu.strategy;

import christmas.domain.menu.MainDish;
import java.util.HashMap;

public class MainDishStrategy implements MenuStrategy {
    @Override
    public boolean acceptMenuName(String menuName) {
        return MenuPresenceChecker.isEnumPresent(MainDish.class, menuName);
    }

    @Override
    public void putMenu(HashMap<String, Integer> orderedMenuQuantities, String menuName, int quantity) {
        orderedMenuQuantities.put(menuName, quantity);
    }
}
