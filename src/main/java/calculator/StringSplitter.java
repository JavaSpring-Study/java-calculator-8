package calculator;

public class StringSplitter {
    public static String[] splitInput(String input) {
        if (!Character.isDigit(input.charAt(0))) {
            String delimiter;
            delimiter = String.valueOf(input.charAt(2));
            String customRegex = "[" + delimiter + ",:]";
            return input.split(customRegex);
        }
        return input.split("[,:]");
    }
}
