package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import calculator.message.ErrorMessage;

public class Separator {

	private final List<Integer> numbers;

	public Separator(String value) {
		this.numbers = split(value);
	}
	//입력받은 문자열을 숫자 리스트로 변환
	public List<Integer> split(String value){

		String delimiter = "[,\n]"; //초기 구분자 설정 , \n

		String numbersPart = value;
		//커스텀 구분자 확인
		if (value.startsWith("//")) {
			int delimiterEndIndex = value.indexOf("\n");
			if (delimiterEndIndex == -1){
				throw new IllegalArgumentException(ErrorMessage.FORMAT_INCORRECT);
			}
			//사용자가 사용한 구분자 꺼내옴
			String customDelimiter = value.substring(2, delimiterEndIndex);
			if (customDelimiter.isEmpty()) {
				throw new IllegalArgumentException(ErrorMessage.DELIMITER_MISSING);
			}
			delimiter = Pattern.quote(value.substring(2,delimiterEndIndex));
			numbersPart = value.substring(delimiterEndIndex+1);
		}
		//구분자를 기준으로 숫자 문자열을 나눠 배열로 저장
		String[] tokens = numbersPart.split(delimiter);

		List<Integer> result = new ArrayList<>();
		for (String token: tokens) {
			if (!token.isEmpty()){
				try{
					//String -> Int
					int num = Integer.parseInt(token);
					//음수인지 확인
					if (num < 0) {
						throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED);
					}
				}catch(NumberFormatException e){
					throw new IllegalArgumentException(ErrorMessage.ENTERED_NON_NUMERIC_VALUE);
				}
			}
		}
		return result;
	}
	public List<Integer> getNumbers() {
		return numbers;
	}
}
