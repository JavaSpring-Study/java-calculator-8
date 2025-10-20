package calculator.model.parser;

import calculator.util.ExceptionMessages;
import java.util.List;

public class NumberParser {

    private NumberParser() {}

    public static String[] parse(String body, List<String> delimiters) {
        if (body.isEmpty()) {
            return new String[]{"0"};
        }

        // 구분자 정규식 생성
        String regex = DelimiterPattern.buildPattern(delimiters);

        // 구분자로 분리
        String[] tokens = body.split(regex);

        // 검증: 비숫자, 음수 체크
        for (String token : tokens) {
            if (!token.matches("^-?\\d+$")) {  // 음수 기호 허용
                throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_FORMAT.get());
            }
            if (Integer.parseInt(token) < 0) {
                throw new IllegalArgumentException(ExceptionMessages.NEGATIVE_NUMBER.get());
            }
        }

        return tokens;
    }
}