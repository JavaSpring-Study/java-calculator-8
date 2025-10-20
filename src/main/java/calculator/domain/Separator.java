package calculator.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.message.ErrorMessage;

public class Separator {

	private final List<Integer> numbers;

	public Separator(String value) {
		this.numbers = split(value);
	}

	public List<Integer> split(String value) {
		String delimiter = "[,\n]"; // 기본 구분자 ,와 개행
		String numbersPart = value;

		// 커스텀 구분자 처리
		Pattern pattern = Pattern.compile("//(.*)\\\\n(.*)", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(value);

		if (matcher.matches()) {
			String customDelimiter = matcher.group(1);
			if (customDelimiter.isEmpty()) {
				throw new IllegalArgumentException(ErrorMessage.DELIMITER_MISSING);
			}
			delimiter = Pattern.quote(customDelimiter);
			numbersPart = matcher.group(2); // \n 뒤 숫자 문자열
		}

		String[] tokens = numbersPart.split(delimiter);

		List<Integer> result = new ArrayList<>();
		for (String token : tokens) {
			if (!token.isEmpty()) {
				try {
					int num = Integer.parseInt(token);
					if (num < 0) {
						throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED);
					}
					result.add(num);
				} catch (NumberFormatException e) {
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
