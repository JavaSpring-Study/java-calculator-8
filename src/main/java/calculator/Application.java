package calculator;

public class Application {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();
        Calculator calculator = new Calculator();

        String input = consoleView.getInput();
        int result = calculator.calculate(input);
        consoleView.displayResult(result);
    }
}
