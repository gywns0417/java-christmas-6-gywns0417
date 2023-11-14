package christmas.domain.date;

import static christmas.config.ErrorMessage.DATE_INPUT_ERROR_MESSAGE;
import static christmas.config.EventConfig.CHRISTMAS_DAY;
import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.EventConfig.EVENT_YEAR;

import christmas.domain.discount.Weekday;
import christmas.dto.VisitDateDto;
import java.time.LocalDate;
import java.time.YearMonth;

public class VisitDate {
    private final int date;
    private final String day;
    private final Boolean star;

    public VisitDate(String date) {
        this.date = validateDate(date);
        this.day = LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), this.date)
                .getDayOfWeek().toString();
        this.star = hasStar();
    }

    private int validateDate(String date) {
        int localDate;

        if (date.startsWith("0")) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }

        try {
            localDate = Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }

        if (localDate < 1 || localDate > YearMonth.of(
                EVENT_YEAR.getValue(), EVENT_MONTH.getValue()).lengthOfMonth()) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }
        return localDate;
    }

    public VisitDateDto toDto() {
        return new VisitDateDto(date, day, star);
    }

    private boolean hasStar() { // TODO: 변동 기준이라 enum 적용?
        return day.equals(Weekday.SUNDAY.name()) || date == CHRISTMAS_DAY.getValue();
    }
}
