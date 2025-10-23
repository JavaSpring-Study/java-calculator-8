package calculator;

import java.util.List;

public class Calculator {

    private final StringParser stringParser;

    public Calculator() {
        stringParser = new StringParser();
    }

    public int calculate(String input) {
        List<Integer> numbers = stringParser.parse(input);
        return sum(numbers);
    }

    private int sum(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

}
