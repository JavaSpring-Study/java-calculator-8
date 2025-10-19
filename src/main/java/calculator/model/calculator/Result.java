package calculator.model.calculator;

public class Result {
    private final int value;

    public Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "결과 : " + value;
    }
}