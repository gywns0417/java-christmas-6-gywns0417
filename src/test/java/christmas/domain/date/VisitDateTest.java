package christmas.domain.date;

import static christmas.config.ErrorMessage.DATE_INPUT_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.dto.VisitDateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    @DisplayName("올바른 않은 날짜 입력시 예외 발생 안함")
    @Test
    void success_validDate() {
        // give
        String date = "31";
        //when, then
        assertThatCode(() -> new VisitDate(date))
                .doesNotThrowAnyException();
    }

    @DisplayName("올바르지 않은 날짜 입력시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "-", "1-", "0", "-1" ,"32", "1.0", "1+1", "`1", "1/1"}) // give
    void fail_InvalidDate(String date) {
        // then, then
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DATE_INPUT_ERROR_MESSAGE.getMessage());
    }

    @DisplayName("올바른 값을 가진 VisitDateDto 생성시 통과")
    @Test
    void success_ToDto() {
        // give
        String date = "25";
        VisitDateDto dto = new VisitDate(date).toDto();
        // when, then
        assertThat(dto.date()).isEqualTo(25);
        assertThat(dto.day()).isEqualTo("MONDAY");
        assertThat(dto.star()).isTrue();
    }
}
