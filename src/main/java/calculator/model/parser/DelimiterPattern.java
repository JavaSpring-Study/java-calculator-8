package calculator.model.parser;

import java.util.List;
import java.util.stream.Collectors;

public class DelimiterPattern {
    private static final String REGEX_SPECIALS = "([\\\\^$.|?*+()\\[\\]])";

    // 모든 구분자를 OR (|)로 연결해서 하나의 정규식 패턴 생성
    public static String buildPattern(List<String> delimiters) {
        return delimiters.stream()
                .map(DelimiterPattern::escapeSpecialChars)
                .collect(Collectors.joining("|"));
    }

    // 정규식 예약문자(\, *, [, ]) 등을 이스케이프 처리
    private static String escapeSpecialChars(String delimiter) {
        return delimiter.replaceAll(REGEX_SPECIALS, "\\\\$1");
    }
}