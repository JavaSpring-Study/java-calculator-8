package calculator.model.validation;

import calculator.util.ExceptionMessages;

public class Validator {

    private Validator() {
    }

    /**
     * 입력값이 존재하고, 기본 구조가 올바른지 확인한다
     *
     * @param input 사용자 입력 문자열
     * @throws IllegalArgumentException 잘못된 형식일 경우
     */
    public static void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ExceptionMessages.INPUT_NULL.get()
            );
        }

        // 빈 문자열은 계산 결과 0으로 처리되므로 정상 처리
        if (input.isEmpty()) {
            return;
        }
    }
}