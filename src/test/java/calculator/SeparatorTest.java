package calculator;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import calculator.domain.Separator;
import calculator.message.ErrorMessage;

class SeparatorTest {

	@DisplayName("기본 구분자 - 쉼표")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3", ",1,2,3"})
	void basic_separator_comma(String inputs) {
		Separator separator = new Separator(inputs);
		List<Integer> numbers = separator.getNumbers();

		assertThat(numbers).isEqualTo(List.of(1, 2, 3));
	}

	@DisplayName("기본 구분자 - 개행")
	@ParameterizedTest
	@ValueSource(strings = {"1\n2\n3","\n1\n2\n3"})
	void basic_separator_newline(String inputs) {
		//given

		//when
		Separator separator = new Separator(inputs);
		List<Integer> numbers = separator.getNumbers();

		//then
		assertThat(numbers).isEqualTo(List.of(1, 2, 3));
	}

	@DisplayName("기본 구분자 - 세미클론")
	@Test
	void basic_seperator_semicolon() {
		Separator separator = new Separator("//;\\n1;2;3");
		List<Integer> numbers = separator.getNumbers();
		assertEquals(List.of(1, 2, 3), numbers);
	}

	@DisplayName("음수값 예외")
	@ParameterizedTest
	@ValueSource(strings = {"1,-2,3","-1,1,1","-1,-2,3"})
	void exception_negative_number(String inputs) {
		//given

		//when, then
		assertThatThrownBy(() -> new Separator(inputs))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED);

		// IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
		// 	() -> new Separator("1,-2,3"));
		// assertEquals(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED, exception.getMessage());
	}

	@DisplayName("숫자가 아닌 값이 들어왔을때 예외 처리")
	@ParameterizedTest
	@ValueSource(strings = {"//;\\na;2;3","a,2:3","@,2:3" })
	void exception_entered_non_numeric_value(String inputs) {
		//given

		//when, then
		assertThatThrownBy(() -> new Separator(inputs))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ErrorMessage.ENTERED_NON_NUMERIC_VALUE);

		// IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
		// 	() -> new Separator("1,a,3"));
		// assertEquals(ErrorMessage.ENTERED_NON_NUMERIC_VALUE, exception.getMessage());
	}
}
