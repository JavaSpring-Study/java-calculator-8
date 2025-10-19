package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
    	System.out.println("덧셈할 문자열을 입력해 주세요.");
    	
    	// missionutils의 Console 클래스를 통해 문자열 입력받기
        String input = Console.readLine();

        // 함수호출로 메서드 전달해서 Result 계산
        int result = StringCalculator.add(input);

        System.out.println("결과 : " + result);
    }
}
