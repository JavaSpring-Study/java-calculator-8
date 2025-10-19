package calculator.model.calculator;

import java.util.Arrays;

/**
 * 파싱된 문자열 토큰을 정수로 변환해 합산한다
 */
public class Calculator {

    private Calculator() {}

    public static Result calculate(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException("계산할 숫자가 없습니다.");
        }

        int sum = Arrays.stream(tokens)
                .mapToInt(Calculator::parseAndValidate)
                .sum();

        return new Result(sum);
    }

    private static int parseAndValidate(String token) {
        try {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }
}