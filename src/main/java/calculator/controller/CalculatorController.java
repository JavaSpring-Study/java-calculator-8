package calculator.controller;

import java.util.List;

import calculator.domain.Separator;
import calculator.view.Input;

public class CalculatorController {
	public void run(){
		Input input = new Input();
		String value = input.readInput();

		//Separator
		Separator separator = new Separator(value);
		List<Integer> numbers = separator.getNumbers();
		System.out.println("numbers = " + numbers);




	}
}
