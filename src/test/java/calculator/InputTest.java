package calculator;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.message.ErrorMessage;
import calculator.view.Input;

class InputTest {

	private final PrintStream originalOut = System.out;
	private final InputStream originalInput = System.in;
	private ByteArrayOutputStream outContent;

	private Input inputView;

	@BeforeEach
	void setUp() {
		inputView = new Input();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() {
		System.setOut(originalOut);
		System.setIn(originalInput);
	}

	@Test
	@DisplayName("정상 입력하는 경우")
	void testInput_success() {
		String dummy = "//;\\n1;2;3";
		System.setIn(new ByteArrayInputStream(dummy.getBytes()));

		String result = inputView.readInput();

		assertEquals("//;\\n1;2;3", result);
	}

	@Test
	@DisplayName("값을 입력하지 않은 경우 0을 반환한다")
	void null_input() {
		String dummy = "\n"; //아무것도 입력하지않고 Enter 친 경우
		System.setIn(new ByteArrayInputStream(dummy.getBytes()));

		String result = inputView.readInput();

		assertEquals("0", result);
	}

	@Test
	@DisplayName("문자열에 공백이 포함될 경우 예외가 발생한다")
	void include_spaces() {
		String dummy = "// 1 ; 2 ";
		System.setIn(new ByteArrayInputStream(dummy.getBytes()));

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> inputView.readInput()
		);
		assertEquals(ErrorMessage.TRIM_ERROR, exception.getMessage());
	}

	@Test
	@DisplayName("문자열에 공백만 입력한 경우 예외가 발생한다")
	void only_include_spaces() {
		String dummy = "    ";
		System.setIn(new ByteArrayInputStream(dummy.getBytes()));

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> inputView.readInput()
		);
		assertEquals(ErrorMessage.TRIM_ERROR, exception.getMessage());
	}

}
