package calculator.domain;

import java.util.ArrayList;
import java.util.List;

import calculator.message.ErrorMessage;

public class Separator {

	private final Delimiter delimiter;
	private final List<Integer> numbers;

	public Separator(String value) {
			this.delimiter = Delimiter.of(value);//구분자 객체 생성
			this.numbers = parseNumbers(delimiter.extractNumbers(value)); //숫자 변환
	}

	private List<Integer> parseNumbers(String nums) {
		List<Integer> result = new ArrayList<>();
		for (String token: nums.split(delimiter.getRegex())){
			if(!token.isEmpty()) {
				int num;
				try{
					num = Integer.parseInt(token);
				}catch (NumberFormatException e){ //숫자가 아닌 경우 예외처리
					throw new IllegalArgumentException(ErrorMessage.ENTERED_NON_NUMERIC_VALUE);
				}
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
