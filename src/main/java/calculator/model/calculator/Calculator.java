package calculator.model.calculator;

import calculator.util.ExceptionMessages;
import java.util.Arrays;

/**
 * 파싱된 문자열 토큰을 정수로 변환해 합산한다
 */
public class Calculator {

    private Calculator() {}

    public static Result calculate(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException(ExceptionMessages.INPUT_EMPTY.get());
        }

        int sum = Arrays.stream(tokens)
                .mapToInt(Calculator::parseAndValidate)
                .sum();

        return new Result(sum);
    }

    private static int parseAndValidate(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.INPUT_EMPTY.get());
        }

        try {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new IllegalArgumentException(ExceptionMessages.NEGATIVE_NUMBER.get());
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_FORMAT.get());
        }
    }
}