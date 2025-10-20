package calculator.model.parser;

import java.util.List;

public class DelimiterParseResult {
    private final List<String> delimiters;
    private final String body;

    public DelimiterParseResult(List<String> delimiters, String body) {
        this.delimiters = delimiters;
        this.body = body;
    }

    public List<String> getDelimiters() {
        return delimiters;
    }

    public String getBody() {
        return body;
    }
}