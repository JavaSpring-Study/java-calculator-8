package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
    	
    	//빈 입력시 0 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }
        //문자열 내 "\\n"이 그대로 들어올 경우 실제 개행 줄바꿈 문자("\n")로 변환 
        input = input.replace("\\n", "\n");
        
       
        //기본 구분자
        String delimiter = ",|:"; 
        String numbers = input;

        // 커스텀 구분자 지정
        // java.util.regex 패키지의 Pattern import
        
        // 입력이 "//"로 시작하면, 첫 줄의 구분자를 읽고 실제 숫자 부분 분리
        if (input.startsWith("//")) {	 
            int delimiterIndex = input.indexOf("\n");
            
            // "//"와 "\n" 사이 문자열을 구분자로
            delimiter = Pattern.quote(input.substring(2, delimiterIndex));
            
            // 구분자 이후 부분 남기기
            numbers = input.substring(delimiterIndex + 1);
        }

        // 위에서 나온 문자열을 지정된 구분자(",|,:")를 기준으로 split
        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        
        for (String token : tokens) {
        	// 각 토큰을 숫자로 변환 (문자열을 정수로 변화하는 함수호출) 
            int number = parseNumber(token);
            
            //음수 입력시 예외 처리 
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
            }
            sum += number;
        }

        return sum;
    }

    
    //필터역할 
    private static int parseNumber(String token) {
    	// 비어있으면 0 반환 
        if (token.isEmpty()) {
            return 0;
        }
        // 숫자 이외의 값은 예외 처리 
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 숫자가 아닙니다: " + token);
        }
    }
}
