package christmas.domain.date;

import static christmas.config.ErrorMessage.DATE_INPUT_ERROR_MESSAGE;
import static christmas.config.EventConfig.CHRISTMAS_DAY;
import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.EventConfig.EVENT_YEAR;

import christmas.config.Weekday;
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
        validateDateStartsZero(date);
        int localDate = validateDateNumeric(date);
        validateDateRange(localDate);
        return localDate;
    }

    private void validateDateStartsZero(String date) {
        if (date.startsWith("0")) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    private int validateDateNumeric(String date) {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    private void validateDateRange(int localDate) {
        if (localDate < 1 || localDate > YearMonth.of(
                EVENT_YEAR.getValue(), EVENT_MONTH.getValue()).lengthOfMonth()) {
            throw new IllegalArgumentException(DATE_INPUT_ERROR_MESSAGE.getMessage());
        }
    }

    public VisitDateDto toDto() {
        return new VisitDateDto(date, day, star);
    }

    private boolean hasStar() {
        return day.equals(Weekday.SUNDAY.name()) || date == CHRISTMAS_DAY.getValue();
    }
}
