package christmas.config;

import static christmas.config.ResultMessage.AMOUNT;
import static christmas.config.ResultMessage.BADGE;
import static christmas.config.ResultMessage.GIVEAWAY_ITEM_QUANTITY;
import static christmas.config.ResultMessage.NONE;
import static christmas.config.ResultMessage.RESULT_BADGE_MESSAGE;
import static christmas.config.ResultMessage.RESULT_DISCOUNT_HISTORY_MESSAGE;
import static christmas.config.ResultMessage.RESULT_EXPECT_PAYMENT_MESSAGE;
import static christmas.config.ResultMessage.RESULT_GIVEAWAY_MENU_MESSAGE;
import static christmas.config.ResultMessage.RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE;
import static christmas.config.ResultMessage.RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE;

public enum ResultParagraph {
    RESULT_DISCOUNT_FIRST_PARAGRAPH(RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE.getMessage()
            + AMOUNT.getMessage()
            + RESULT_GIVEAWAY_MENU_MESSAGE.getMessage()
            + GIVEAWAY_ITEM_QUANTITY.getMessage()
            + RESULT_DISCOUNT_HISTORY_MESSAGE.getMessage()),

    RESULT_DISCOUNT_SECOND_PARAGRAPH(RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE.getMessage()
            + AMOUNT.getMessage()
            + RESULT_EXPECT_PAYMENT_MESSAGE.getMessage()
            + AMOUNT.getMessage()
            + RESULT_BADGE_MESSAGE.getMessage()
            + BADGE.getMessage()),

    RESULT_NO_DISCOUNT_PARAGRAPH(RESULT_TOTAL_PURCHASE_AMOUNT_MESSAGE.getMessage()
            + AMOUNT.getMessage()
            + RESULT_GIVEAWAY_MENU_MESSAGE.getMessage()
            + NONE.getFormatMessage()
            + RESULT_DISCOUNT_HISTORY_MESSAGE.getMessage()
            + NONE.getMessage()
            + RESULT_TOTAL_DISCOUNT_RESULT_MESSAGE.getMessage()
            + "0원\n"
            + RESULT_EXPECT_PAYMENT_MESSAGE.getMessage()
            + AMOUNT.getMessage()
            + RESULT_BADGE_MESSAGE.getMessage()
            + BADGE.getMessage().trim());

    private final String paragraph;

    ResultParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getParagraph() {
        return paragraph;
    }
}
