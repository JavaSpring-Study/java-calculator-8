package calculator.model.calculator;

import calculator.util.ExceptionMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void 정상_입력_합산() {
        String[] tokens = {"1", "2", "3"};
        Result result = Calculator.calculate(tokens);
        assertEquals(6, result.getValue());
    }

    @Test
    void 음수_입력_예외() {
        String[] tokens = {"1", "-2", "3"};
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate(tokens));
        assertEquals(ExceptionMessages.NEGATIVE_NUMBER.get(), e.getMessage());
    }

    @Test
    void 비숫자_입력_예외() {
        String[] tokens = {"1", "a", "3"};
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate(tokens));
        assertEquals(ExceptionMessages.INVALID_NUMBER_FORMAT.get(), e.getMessage());
    }

    @Test
    void 비어있는_토큰_배열_예외() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculate(new String[]{}));
        assertEquals(ExceptionMessages.INPUT_EMPTY.get(), e.getMessage());
    }
}