package calculator.model.parser;

import calculator.util.Constants;
import calculator.util.ExceptionMessages;
import java.util.Arrays;
import java.util.List;

public class DelimiterParser {

    private static final List<String> DEFAULT_DELIMITERS = List.of(
            Constants.DEFAULT_DELIMITER_COMMA.get(),
            Constants.DEFAULT_DELIMITER_COLON.get()
    );

    private DelimiterParser() {}

    /**
     * 입력 문자열에서 구분자 목록과 본문(body)을 함께 추출한다
     *
     * @param input 전체 입력 문자열
     * @return 구분자 목록과 본문을 담은 DelimiterParseResult
     */
    public static DelimiterParseResult parse(String input) {
        // 개행 문자 이스케이프 처리
        input = input.replace(
                Constants.CUSTOM_NEWLINE_ESCAPE.get(),
                Constants.CUSTOM_NEWLINE_ACTUAL.get()
        );

        // 커스텀 구분자가 없는 경우
        if (!input.startsWith(Constants.CUSTOM_PREFIX.get())) {
            return new DelimiterParseResult(DEFAULT_DELIMITERS, input);
        }

        int newlineIndex = input.indexOf(Constants.CUSTOM_NEWLINE_ACTUAL.get());
        if (newlineIndex == -1) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CUSTOM_FORMAT.get());
        }

        String customDelimiter = input.substring(
                Constants.CUSTOM_PREFIX.get().length(),
                newlineIndex
        );

        validateCustomDelimiter(customDelimiter);

        List<String> delimiters = Arrays.asList(
                customDelimiter,
                Constants.DEFAULT_DELIMITER_COMMA.get(),
                Constants.DEFAULT_DELIMITER_COLON.get()
        );

        String body = input.substring(newlineIndex + 1);

        return new DelimiterParseResult(delimiters, body);
    }

    /**
     * 커스텀 구분자 유효성 검사
     */
    private static void validateCustomDelimiter(String delimiter) {
        if (delimiter.isBlank() || delimiter.contains(" ")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CUSTOM_WHITESPACE.get());
        }

        if (delimiter.contains(Constants.DEFAULT_DELIMITER_COMMA.get())
                || delimiter.contains(Constants.DEFAULT_DELIMITER_COLON.get())
                || delimiter.contains(Constants.CUSTOM_PREFIX.get())
                || delimiter.contains(Constants.CUSTOM_NEWLINE_ACTUAL.get())) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CUSTOM_RESERVED.get());
        }
    }
}