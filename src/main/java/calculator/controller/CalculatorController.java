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
        Result result = processInput(input);
        printResult(result);
    }

    /** 사용자 입력 */
    private String readInput() {
        return inputView.read();
    }

    /** 입력 처리 로직 (공통 사용: run() + testRun()) */
    private Result processInput(String input) {
        Validator.validateInput(input);

        DelimiterParseResult parseResult = DelimiterParser.parse(input);
        String[] tokens = NumberParser.parse(parseResult.getBody(), parseResult.getDelimiters());

        return Calculator.calculate(tokens);
    }

    /** 결과 출력 */
    private void printResult(Result result) {
        outputView.printResult(result);
    }

    /** 테스트 전용 메서드 (콘솔 I/O 배제) */
    public String testRun(String input) {
        Result result = processInput(input);
        return "결과 : " + result.getValue();
    }
}