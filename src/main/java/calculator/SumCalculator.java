package calculator;

public class SumCalculator {
    public static int sum (int[] numbers) {
        int total = 0;

        for (int number : numbers) {
            total += number;
        }

        return total;
    }
}
