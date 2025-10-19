package calculator.util;

public enum ExceptionMessages {

    INPUT_NULL("입력값이 존재하지 않습니다."),
    INPUT_EMPTY("계산할 숫자가 없습니다."),

    INVALID_CUSTOM_FORMAT("커스텀 구분자 형식이 올바르지 않습니다."),
    INVALID_CUSTOM_WHITESPACE("공백은 구분자로 사용할 수 없습니다."),
    INVALID_CUSTOM_RESERVED("예약어는 구분자로 사용할 수 없습니다."),

    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),
    NEGATIVE_NUMBER("음수는 입력할 수 없습니다.");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}