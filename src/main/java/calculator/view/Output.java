package calculator.view;

import java.util.List;

import calculator.message.SuccessMessage;

public class Output {
	public void output(List<Integer> numbers) {
		int sum = 0;
		for (Integer number : numbers) {
			sum += number;
		}
		System.out.println(SuccessMessage.OUTPUT_MESSAGE + sum);
	}
}
