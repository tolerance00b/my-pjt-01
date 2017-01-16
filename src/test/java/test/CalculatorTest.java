package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	Calculator cal;

	@Before
	public void setup() {
		cal = new Calculator();
	}

	@Test
	public void add() {
		assertThat(cal.add(3, 4), is(7));
		assertEquals(7, cal.add(3, 4));
	}

	@Test
	public void minuas() throws Exception {
		assertEquals(1, cal.minuas(4, 3));
	}

	@After
	public void teardown() {

	}

}
