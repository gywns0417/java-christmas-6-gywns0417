package christmas.domain.menu.strategy;

import christmas.domain.menu.Menu;
import java.util.Arrays;

public class MenuPresenceChecker {
    public static <T extends Enum<T> & Menu> boolean isEnumPresent(Class<T> enumClass, String enumName) {
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(enumName));
    }
}
