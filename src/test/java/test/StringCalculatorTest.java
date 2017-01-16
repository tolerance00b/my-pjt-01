package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator cal;
	int number;

	@Before
	public void setup() {
		cal = new StringCalculator();
		number = 0;
	}

	@Test
	public void add_널() {
		assertEquals(0, cal.add(null));
		assertEquals(0, cal.add(""));
		System.out.println("널 또는 공백");
	}

	@Test
	public void add_쉼표() {
		String num = "1,2,3";
		assertEquals(6, cal.addComma(num));
		System.out.println("쉼표 : " + cal.addComma(num));
	}

	@Test
	public void add_콜론() {
		String num = "1:2:3";
		assertEquals(6, cal.addColon(num));
		System.out.println("콜론 : " + cal.addColon(num));
	}

	@Test
	public void add_커스텀() {
		String num = "//;\n1;2;3";
		assertEquals(6, cal.addCustom(num));
		System.out.println("커스텀 : " + cal.addCustom(num));
	}

	@Test(expected = RuntimeException.class)
	public void add_음수() {
		String num = "-1";
		cal.addMinus(num);
		System.out.println("음수 : " + cal.addMinus(num));
	}

}
