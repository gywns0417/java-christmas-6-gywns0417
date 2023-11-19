package christmas.dto;

import java.util.HashMap;

public record OrderDto (HashMap<String, Integer> dessert,
                        HashMap<String, Integer> mainDish,
                        int totalPurchaseAmount,
                        HashMap<String, Integer> orderMenuQuantity) {

}
