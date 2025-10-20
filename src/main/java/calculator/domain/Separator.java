package calculator.domain;

import java.util.ArrayList;
import java.util.List;

import calculator.message.ErrorMessage;

public class Separator {

	private final Delimiter delimiter;
	private final List<Integer> numbers;

	public Separator(String value) {
			this.delimiter = Delimiter.of(value);
			this.numbers = parseNumbers(delimiter.extractNumbers(value));
	}

	private List<Integer> parseNumbers(String nums) {
		List<Integer> result = new ArrayList<>();
		for (String token: nums.split(delimiter.getRegex())){
			if(!token.isEmpty()) {
				int num = Integer.parseInt(token);
				//음수인경우 예외처리
				if (num < 0) {
					throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED);
				}
				result.add(num);
			}
		}
		return result;
	}
	public List<Integer> getNumbers(){
		return numbers;
	}
}
