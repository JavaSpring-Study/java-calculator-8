package calculator;

public class DelimiterExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\"+"n";
    private static final String DEFAULT_DELIMITER = ",|:";

    public String extractDelimiter(String input) {
        if (hasCustomDelimiterPrefix(input)) {
            return extractCustomDelimiter(input);
        }
        return DEFAULT_DELIMITER;
    }

    public String removeDelimiterPrefix(String input) {
        if (hasCustomDelimiterPrefix(input)) {
            int endIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            return input.substring(endIndex + CUSTOM_DELIMITER_SUFFIX.length());
        }
        return input;
    }

    private boolean hasCustomDelimiterPrefix(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String extractCustomDelimiter(String input) {
        int startIndex = CUSTOM_DELIMITER_PREFIX.length();
        int endIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

        if (endIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
        }

        return input.substring(startIndex, endIndex);
    }
}
