package calculator;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = InputView.readInput();
        String[] splitInput = StringSplitter.splitInput(input);
        int[] numbers = NumberParser.changeNumber(splitInput);
        int result = SumCalculator.sum(numbers);
    }
}
