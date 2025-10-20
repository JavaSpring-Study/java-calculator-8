package calculator.message;

public abstract class ErrorMessage {

	//입출력 관련
	public final static String TRIM_ERROR = "문자열에 공백이 포함될 수 없습니다";
	public final static String FORMAT_INCORRECT = "입력 형식이 잘못되었습니다";
	//구분자 , 입력값 검증 관련
	public final static String ENTERED_NON_NUMERIC_VALUE = "숫자가 아닌 값이 포함되어 있습니다";
	public final static String NEGATIVE_NUMBER_NOT_ALLOWED = "음수는 입력할 수 없습니다";
	public final static String DELIMITER_MISSING = "커스텀 구분자는 한자리 이상 포함되어 있어야 합니다";


}
