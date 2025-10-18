package calculator.view;

import calculator.message.Message;
import camp.nextstep.edu.missionutils.Console;

public class Input {
	public String readInput() {
		System.out.println(Message.INPUT_MESSAGE);
		return Console.readLine();
	}
}
