package calculator.model.validation;

import calculator.util.ExceptionMessages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void 정상_입력_검증() {
        assertDoesNotThrow(() -> Validator.validateInput("1,2:3"));
    }

    @Test
    void null_입력_예외() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Validator.validateInput(null));
        assertEquals(ExceptionMessages.INPUT_NULL.get(), e.getMessage());
    }

    @Test
    void 빈_문자열_허용() {
        assertDoesNotThrow(() -> Validator.validateInput(""));
    }

    @Test
    void 커스텀_구분자_형식_정상() {
        assertDoesNotThrow(() -> Validator.validateInput("//;\n1;2;3"));
    }
}