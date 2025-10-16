package calculator;

import java.util.ArrayList;
import java.util.List;

public class StringParser {

    private static final String DEFAULT_DELIMITERS = ",|:";

    public List<Integer> parse(String input) {
        if (input.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> numbers = new ArrayList<>();
        String[] tokens = input.split(DEFAULT_DELIMITERS);

        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }

        return numbers;
    }

}
