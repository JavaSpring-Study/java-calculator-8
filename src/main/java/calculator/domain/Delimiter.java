package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.message.ErrorMessage;

public class Delimiter {
	private final String regex;
	private final String pattern = "//(.*)\\\\n(.*)";

	private Delimiter (String regax) {
		this.regex = regax;
	}
	public static Delimiter of(String value) {
		Pattern p = Pattern.compile("//(.*)\\\\\\\\n(.*)",Pattern.DOTALL);
		Matcher m = p.matcher(value);
		if (m.matches()) {
			String custom = m.group(1);
			if(custom.isEmpty()) { //커스텀 구분자가 빈 문자열이면 예외처리
				throw new IllegalArgumentException(ErrorMessage.DELIMITER_MISSING);
			}
			return new Delimiter(Pattern.quote(custom));
		}
		return new Delimiter("[,\n]");
	}
	public String extractNumbers(String value) {
		Matcher m = Pattern.compile(pattern, Pattern.DOTALL).matcher(value);
		return m.matches() ? m.group(2) : value;
	}
	public String getRegex() {
		return regex;
	}
}
