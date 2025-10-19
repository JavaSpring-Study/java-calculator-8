package calculator.view;

import camp.nextstep.edu.missionutils.Console;
import calculator.util.Constants;

public class InputView {

    public String read() {
        System.out.println(Constants.INPUT_PROMPT.get());
        return Console.readLine();
    }
}