package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.message.ErrorMessage;

public class Delimiter {
	private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.*?)\\\\?n(.*)", Pattern.DOTALL);

	private final String regex;

	private Delimiter (String regex) {
		this.regex = regex;
	}
	public static Delimiter of(String value) {
		Matcher matcher = CUSTOM_PATTERN.matcher(value);

		if (matcher.matches()) {
			String custom = matcher.group(1);
			if(custom.isEmpty()) { //커스텀 구분자가 빈 문자열이면 예외처리
				throw new IllegalArgumentException(ErrorMessage.DELIMITER_MISSING);
			}
			return new Delimiter(Pattern.quote(custom));
		}
		return new Delimiter("[,\n]");
	}
	public String extractNumbers(String value) {
		Matcher matcher = CUSTOM_PATTERN.matcher(value);
		return matcher.matches() ? matcher.group(2) : value;
	}
	public String getRegex() {
		return regex;
	}
}
