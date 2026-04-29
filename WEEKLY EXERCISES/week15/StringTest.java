package week15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTest {

	@Test
	void test() {
		String strOne = "hello";
		String strTwo = "hello";

		assertEquals(strOne, strTwo);
	}
}