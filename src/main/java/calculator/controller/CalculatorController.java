package calculator.controller;

import java.util.List;

import calculator.domain.Separator;
import calculator.view.Input;
import calculator.view.Output;

public class CalculatorController {
	public void run(){
		Input input = new Input();
		String value = input.readInput();

		//Separator
		Separator separator = new Separator(value);
		List<Integer> numbers = separator.getNumbers();

		//print
		Output output = new Output();
		output.output(numbers);


	}
}
