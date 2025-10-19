package calculator.model.parser;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DelimiterParserTest {

    @Test
    void 기본_구분자만_반환() {
        DelimiterParseResult result = DelimiterParser.parse("1,2:3");
        assertEquals(List.of(",", ":"), result.getDelimiters());
        assertEquals("1,2:3", result.getBody());
    }

    @Test
    void 커스텀_구분자_정상_파싱() {
        DelimiterParseResult result = DelimiterParser.parse("//;\n1;2;3");
        assertTrue(result.getDelimiters().contains(";"));
        assertEquals("1;2;3", result.getBody());
    }

    @Test
    void 여러문자_커스텀_구분자_허용() {
        DelimiterParseResult result = DelimiterParser.parse("//***\n1***2***3");
        assertTrue(result.getDelimiters().contains("***"));
        assertEquals("1***2***3", result.getBody());
    }

    @Test
    void 공백_포함_예외() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                DelimiterParser.parse("// ;\n1;2;3"));
        assertEquals("공백은 구분자로 사용할 수 없습니다.", e.getMessage());
    }

    @Test
    void 예약어_포함_예외() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                DelimiterParser.parse("////\n1,2,3"));
        assertEquals("예약어는 구분자로 사용할 수 없습니다.", e.getMessage());
    }
}