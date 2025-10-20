package calculator.controller;

import calculator.util.ExceptionMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerTest {

    @Test
    void 정상_입력_흐름_테스트() {
        CalculatorController controller = new CalculatorController();
        // given
        String input = "1,2:3";
        // when
        String result = controller.testRun(input);
        // then
        assertEquals("결과 : 6", result);
    }

    @Test
    void 커스텀_구분자_입력_흐름_테스트() {
        CalculatorController controller = new CalculatorController();
        String input = "//;\n1;2;3";
        String result = controller.testRun(input);
        assertEquals("결과 : 6", result);
    }

    @Test
    void 비정상_입력_예외_테스트() {
        CalculatorController controller = new CalculatorController();
        String input = "1,a,3";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> controller.testRun(input));
        assertEquals(ExceptionMessages.INVALID_NUMBER_FORMAT.get(), e.getMessage());
    }
}