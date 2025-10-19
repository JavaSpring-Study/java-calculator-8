package calculator.controller;

import calculator.model.calculator.Calculator;
import calculator.model.parser.DelimiterParseResult;
import calculator.model.parser.DelimiterParser;
import calculator.model.parser.NumberParser;
import calculator.model.validation.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

/**
 * 전체 애플리케이션의 흐름을 제어하는 컨트롤러
 */
public class CalculatorController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        String input = inputView.read();
        Validator.validateInput(input);
        DelimiterParseResult parsed = DelimiterParser.parse(input);
        String[] tokens = NumberParser.parse(parsed.getBody(), parsed.getDelimiters());
        outputView.printResult(Calculator.calculate(tokens));
    }
}