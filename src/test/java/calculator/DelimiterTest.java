package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import calculator.domain.Delimiter;
import calculator.message.ErrorMessage;

class DelimiterTest {

	@DisplayName("기본 구분자 사용")
	@Test
	void defaultDelimiterTest() {
		Delimiter delimiter = Delimiter.of("1,2,3");
		assertEquals("[,\n]", delimiter.getRegex());

		String numbers = delimiter.extractNumbers("1,2,3");
		assertEquals("1,2,3", numbers);
	}

	@DisplayName("커스텀 구분자 적용")
	@ParameterizedTest
	@ValueSource(strings = {"//;\\n1;2;3", "//*\\n4*5*6"})
	void customDelimiterTest(String input) {
		Delimiter delimiter = Delimiter.of(input);

		String numbers = delimiter.extractNumbers(input);
		assertNotNull(numbers);
		assertTrue(Pattern.matches("\\d+(;|\\*)\\d+(;|\\*)\\d+", numbers));
	}

	@DisplayName("커스텀 구분자가 비어있으면 예외")
	@ParameterizedTest
	@ValueSource(strings = {"//\\n1,2,3", "//\\n"})
	void emptyCustomDelimiterTest(String input) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Delimiter.of(input));
		assertEquals(ErrorMessage.DELIMITER_MISSING, exception.getMessage());
	}

	@DisplayName("잘못된 포맷 입력 시 기본 처리")
	@Test
	void invalidFormatTest() {
		Delimiter delimiter = Delimiter.of("1#2#3");
		assertEquals("[,\n]", delimiter.getRegex());
		assertEquals("1#2#3", delimiter.extractNumbers("1#2#3"));
	}
}
