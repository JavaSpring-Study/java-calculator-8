package calculator.view;

import calculator.message.ErrorMessage;
import calculator.message.SuccessMessage;
import camp.nextstep.edu.missionutils.Console;

public class Input {
	public String readInput() {
		System.out.println(SuccessMessage.INPUT_MESSAGE);
		String input = Console.readLine();

		//입력값이 null인 경우 0을 반환
		if (input == null || input.isEmpty()) {
			return "0";
		}
		//공백만 입력하거나 공백을 문자에 포함한 경우의 예외 처리
		if (input.trim().isEmpty() || input.contains(" ") ) {
			throw new IllegalArgumentException(ErrorMessage.TRIM_ERROR);
		}

		return input;
	}
}
