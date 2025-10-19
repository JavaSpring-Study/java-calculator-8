package calculator.view;

import calculator.model.calculator.Result;
import calculator.util.Constants;

public class OutputView {

    public void printResult(Result result) {
        System.out.println(Constants.RESULT_PREFIX.get() + result.getValue());
    }
}