package calculator.model.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DelimiterParser {

    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    private DelimiterParser() {}

    /**
     * 입력값에서 사용할 구분자 목록을 반환한다
     *
     * @param input 전체 입력 문자열
     * @return 기본 또는 커스텀 구분자 목록
     */
    public static List<String> parseDelimiters(String input) {
        if (!input.startsWith("//")) {
            return DEFAULT_DELIMITERS;
        }

        int newlineIndex = input.indexOf("\n");
        if (newlineIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        String customDelimiter = input.substring(2, newlineIndex);

        validateCustomDelimiter(customDelimiter);

        // 기본 구분자 + 커스텀 구분자 함께 반환
        return Arrays.asList(customDelimiter, ",", ":");
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