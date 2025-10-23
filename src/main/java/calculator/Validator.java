package calculator;

public class Validator {

    public void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 null입니다.");
        }
    }

    public void validateNumber(String token) {
        try {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }

}
