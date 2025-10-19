package calculator.model.parser;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NumberParserTest {

    @Test
    void 기본_구분자로_문자열_분리() {
        List<String> delimiters = List.of(",", ":");
        String[] result = NumberParser.parse("1,2:3", delimiters);
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 커스텀_구분자로_문자열_분리() {
        List<String> delimiters = List.of("***");
        String[] result = NumberParser.parse("1***2***3", delimiters);
        assertArrayEquals(new String[]{"1", "2", "3"}, result);
    }

    @Test
    void 음수_입력_예외() {
        List<String> delimiters = List.of(",", ":");
        assertThrows(IllegalArgumentException.class, () ->
                NumberParser.parse("1,-2,3", delimiters));
    }

    @Test
    void 비숫자_입력_예외() {
        List<String> delimiters = List.of(",", ":");
        assertThrows(IllegalArgumentException.class, () ->
                NumberParser.parse("1,a,3", delimiters));
    }

    @Test
    void 빈_입력은_0으로_처리() {
        List<String> delimiters = List.of(",", ":");
        String[] result = NumberParser.parse("", delimiters);
        assertArrayEquals(new String[]{"0"}, result);
    }
}