package calculator;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.domain.Separator;
import calculator.message.ErrorMessage;

class SeparatorTest {

	@DisplayName("기본 구분자 - 쉼표")
	@Test
	void basic_separator_comma() {
		Separator separator = new Separator("1,2,3");
		List<Integer> numbers = separator.getNumbers();
		assertEquals(List.of(1, 2, 3), numbers);
	}

	@DisplayName("기본 구분자 - 개행")
	@Test
	void basic_separator_newline() {
		Separator separator = new Separator("1\n2\n3");
		List<Integer> numbers = separator.getNumbers();
		assertEquals(List.of(1, 2, 3), numbers);
	}

	@DisplayName("기본 구분자 - 세미클론")
	@Test
	void basic_seperator_semicolon() {
		Separator separator = new Separator("//;\\n1;2;3");
		List<Integer> numbers = separator.getNumbers();
		assertEquals(List.of(1, 2, 3), numbers);
	}

	@DisplayName("음수값 예외")
	@Test
	void exception_negative_number() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> new Separator("1,-2,3"));
		assertEquals(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED, exception.getMessage());
	}

	@DisplayName("숫자가 아닌 값이 들어왔을때 예외 처리")
	@Test
	void exception_entered_non_numeric_value() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> new Separator("1,a,3"));
		assertEquals(ErrorMessage.ENTERED_NON_NUMERIC_VALUE, exception.getMessage());
	}
}
