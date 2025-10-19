package calculator.model.parser;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DelimiterParserTest {

    @Test
    void 기본_구분자_반환() {
        List<String> result = DelimiterParser.parseDelimiters("1,2:3");
        assertEquals(List.of(",", ":"), result);
    }

    @Test
    void 커스텀_구분자_정상_파싱() {
        List<String> result = DelimiterParser.parseDelimiters("//;\n1;2;3");
        assertTrue(result.contains(";"));
    }

    @Test
    void 여러문자_커스텀_구분자_허용() {
        List<String> result = DelimiterParser.parseDelimiters("//***\n1***2***3");
        assertTrue(result.contains("***"));
    }

    @Test
    void 공백_포함_예외() {
        assertThrows(IllegalArgumentException.class, () ->
                DelimiterParser.parseDelimiters("// ;\n1;2;3"));
    }

    @Test
    void 예약어_포함_예외() {
        assertThrows(IllegalArgumentException.class, () ->
                DelimiterParser.parseDelimiters("////\n1,2,3"));
    }

    @Test
    void 본문_추출() {
        String body = DelimiterParser.extractBody("//;\n1;2;3");
        assertEquals("1;2;3", body);
    }
}