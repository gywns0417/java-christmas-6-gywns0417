package christmas.domain.discount;

import java.util.HashMap;

public class DiscountContext {
    private final String day;
    private final int date;
    private final HashMap<String, Integer> dessert;
    private final HashMap<String, Integer> mainDish;
    private final int totalPurchaseAmount;
    private final boolean star;

    public DiscountContext(String day, int date, HashMap<String, Integer> dessert,
                           HashMap<String, Integer> mainDish, Boolean star, int totalPurchaseAmount) {
        this.day = day;
        this.date = date;
        this.dessert = dessert;
        this.mainDish = mainDish;
        this.star = star;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public String getDay() {
        return day;
    }

    public int getDate() {
        return date;
    }

    public HashMap<String, Integer> getDessert() {
        return dessert;
    }


    public HashMap<String, Integer> getMainDish() {
        return mainDish;
    }


    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public boolean getStar() {
        return star;
    }
}
