package calculator.model.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DelimiterParser {

    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    private DelimiterParser() {}

    /**
     * 입력 문자열에서 구분자 목록과 본문(body)을 함께 추출한다
     *
     * @param input 전체 입력 문자열
     * @return 구분자 목록과 본문을 담은 DelimiterParseResult
     */
    public static DelimiterParseResult parse(String input) {
        input = input.replace("\\n", "\n");

        if (!input.startsWith("//")) {
            // 기본 구분자만 사용하는 경우
            return new DelimiterParseResult(DEFAULT_DELIMITERS, input);
        }

        int newlineIndex = input.indexOf("\n");
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        String customDelimiter = input.substring(2, newlineIndex);
        validateCustomDelimiter(customDelimiter);

        List<String> delimiters = Arrays.asList(customDelimiter, ",", ":");
        String body = input.substring(newlineIndex + 1);

        return new DelimiterParseResult(delimiters, body);
    }

    /**
     * 커스텀 구분자 유효성 검사
     */
    private static void validateCustomDelimiter(String delimiter) {
        // 전체가 비었거나, 공백이 포함된 경우
        if (delimiter.isBlank() || delimiter.contains(" ")) {
            throw new IllegalArgumentException("공백은 구분자로 사용할 수 없습니다.");
        }

        // 예약어 포함 여부
        if (delimiter.contains(",") || delimiter.contains(":") ||
                delimiter.contains("//") || delimiter.contains("\n")) {
            throw new IllegalArgumentException("예약어는 구분자로 사용할 수 없습니다.");
        }
    }

    /**
     * 구분자 선언부를 제외한 본문 문자열 반환
     */
    public static String extractBody(String input) {
        if (!input.startsWith("//")) {
            return input;
        }
        int newlineIndex = input.indexOf("\n");
        return input.substring(newlineIndex + 1);
    }
}