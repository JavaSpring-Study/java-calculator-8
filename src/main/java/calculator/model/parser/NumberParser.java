package calculator.model.parser;

import java.util.Arrays;
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
            if (!token.matches("\\d+")) {
                throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
            }
            if (Integer.parseInt(token) < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
        }

        return tokens;
    }
}