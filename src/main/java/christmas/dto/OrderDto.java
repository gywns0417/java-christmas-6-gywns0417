package christmas.dto;

import java.util.HashMap;

public record OrderDto (HashMap<String, Integer> splitMenuQuantity,
                        HashMap<String, Integer> appetizers,
                        HashMap<String, Integer> dessert,
                        HashMap<String, Integer> drink,
                        HashMap<String, Integer> mainDish,
                        int totalPurchaseAmount) {

}
