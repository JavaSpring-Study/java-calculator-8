package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
    	
    	//빈 입력시 0 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }

        input = input.replace("\\n", "\n");
        
        // java.util.regex 패키지의 Pattern import
        String delimiter = ",|:"; 
        String numbers = input;

        if (input.startsWith("//")) {	 
            int delimiterIndex = input.indexOf("\n");
            delimiter = Pattern.quote(input.substring(2, delimiterIndex));
            numbers = input.substring(delimiterIndex + 1);
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            int number = parseNumber(token);
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
            }
            sum += number;
        }

        return sum;
    }

    private static int parseNumber(String token) {
        if (token.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력: " + token);
        }
    }
}
