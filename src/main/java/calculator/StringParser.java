package calculator;

import java.util.ArrayList;
import java.util.List;

public class StringParser {

    private final DelimiterExtractor delimiterExtractor;

    public StringParser() {
        this.delimiterExtractor = new DelimiterExtractor();
    }

    public List<Integer> parse(String input) {
        if (input.isEmpty()) {
            return new ArrayList<>();
        }

        String delimiter = delimiterExtractor.extractDelimiter(input);
        String numberString = delimiterExtractor.removeDelimiterPrefix(input);

        return parseNumbers(numberString, delimiter);
    }

    private List<Integer> parseNumbers(String input, String delimiter) {
        List<Integer> numbers = new ArrayList<>();
        String[] tokens = input.split(delimiter);

        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }

        return numbers;
    }

}
