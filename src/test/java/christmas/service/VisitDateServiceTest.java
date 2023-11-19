package christmas.service;

import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.view.MessageOutputView;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisitDateServiceTest {
    @DisplayName("올바른 날짜 입력시 예외 발생 안함")
    @Test
    void success() {
        // give
        VisitDateService visitDateService = new VisitDateService();
        Supplier<String> inputDate = () -> "31";
        // when, then
        assertThatCode(() -> visitDateService.getDateInput(inputDate, MessageOutputView::printDateInputRequestMessage,
                MessageOutputView::printErrorMessage))
                .doesNotThrowAnyException();
    }
}