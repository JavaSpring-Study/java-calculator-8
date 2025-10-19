package calculator.controller;

import calculator.model.calculator.Calculator;
import calculator.model.calculator.Result;
import calculator.model.parser.DelimiterParseResult;
import calculator.model.parser.DelimiterParser;
import calculator.model.parser.NumberParser;
import calculator.model.validation.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        String input = readInput();
        String[] tokens = parseInput(input);
        Result result = calculate(tokens);
        printResult(result);
    }

    /** 사용자 입력 */
    private String readInput() {
        return inputView.read();
    }

    /** 입력 검증 및 구분자/숫자 파싱 */
    private String[] parseInput(String input) {
        Validator.validateInput(input);
        DelimiterParseResult parseResult = DelimiterParser.parse(input);
        return NumberParser.parse(parseResult.getBody(), parseResult.getDelimiters());
    }

    /** 계산 로직 */
    private Result calculate(String[] tokens) {
        return Calculator.calculate(tokens);
    }

    /** 결과 출력 */
    private void printResult(Result result) {
        outputView.printResult(result);
    }
}