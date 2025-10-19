package calculator;

public class NumberParser {
    public static int[] changeNumber(String[] splitInput) {
        int[] numbers = new int[splitInput.length];
        for (int i = 0; i < splitInput.length; i++) {
            if (!splitInput[i].matches("\\d+")) {
                throw new IllegalArgumentException("형식을 벗어남");
            }
            numbers[i] = Integer.valueOf(splitInput[i]);
        }
        return numbers;
    }
}
