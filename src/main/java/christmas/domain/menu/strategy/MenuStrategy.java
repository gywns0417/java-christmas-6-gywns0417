package christmas.domain.menu.strategy;

import java.util.HashMap;

public interface MenuStrategy {
    boolean acceptMenuName(String menuName);
    void putMenu(HashMap<String, Integer> orderedMenuQuantities, String menuName, int quantity);
}
