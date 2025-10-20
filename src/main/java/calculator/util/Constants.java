package calculator.util;

/**
 * 문자열 계산기에서 사용되는 상수 모음
 */
public enum Constants {

    // 기본 구분자
    DEFAULT_DELIMITER_COMMA(","),
    DEFAULT_DELIMITER_COLON(":"),

    // 커스텀 구분자
    CUSTOM_PREFIX("//"),
    CUSTOM_NEWLINE_ESCAPE("\\n"), // 입력에 들어오는 "\n"
    CUSTOM_NEWLINE_ACTUAL("\n"), // 실제 개행 문자
    CUSTOM_SEPARATOR_PATTERN("//(.)\n(.*)"),

    // 입출력 메시지
    INPUT_PROMPT("덧셈할 문자열을 입력해 주세요."),
    RESULT_PREFIX("결과 : "),

    // 기본값
    EMPTY_INPUT_RESULT("0");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}