package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

	@Test
	public void canAddTwoPositiveIntegers() {
		int sum = CalculatorApp.add(1, 10);
		assertEquals(11, sum, "Two positive integers can be added");
	}

	@Test
	public void canAddTwoPositiveFloats() {
		float sum = CalculatorApp.add((float) 1.7, (float) 1.08);
		assertEquals(2.78, sum, 0.00001, "Two floats can be added");
	}

}
