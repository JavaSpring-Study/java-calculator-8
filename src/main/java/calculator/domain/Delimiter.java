package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.message.ErrorMessage;

public class Delimiter {
	private static final Pattern CUSTOM_PATTERN = Pattern.compile("//(.*?)\\\\n(.*) | //(.*?)\\n(.*)", Pattern.DOTALL); //group(1) / (2) : 커스텀 / 숫자 , group(3) / (4) : 커스텀 / 숫자

	private final String regex;

	private Delimiter (String regex) {
		this.regex = regex;
	}
	public static Delimiter of(String value) {
		Matcher matcher = CUSTOM_PATTERN.matcher(value);

		if (matcher.matches()) {
			String custom = matcher.group(1) != null ? matcher.group(1) : matcher.group(3);
			if(custom == null || custom.isEmpty()) { //커스텀 구분자가 빈 문자열이면 예외처리
				throw new IllegalArgumentException(ErrorMessage.DELIMITER_MISSING);
			}
			return new Delimiter(Pattern.quote(custom));
		}
		return new Delimiter("[,\n]");
	}
	//구분자 이후 숫자 문자열만
	public String extractNumbers(String value) {
		Matcher matcher = CUSTOM_PATTERN.matcher(value);
		if (matcher.matches()) {
			return matcher.group(2) != null ? matcher.group(2) : matcher.group(4);
		}
		return value;
	}
	public String getRegex() {
		return regex;
	}
}
