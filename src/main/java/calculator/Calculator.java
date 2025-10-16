package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public int calculate(String input) {
        List<Integer> numbers = new ArrayList<>();
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
