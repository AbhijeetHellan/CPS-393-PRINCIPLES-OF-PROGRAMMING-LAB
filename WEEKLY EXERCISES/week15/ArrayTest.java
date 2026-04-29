package week15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

	@Test
	void test() {
		int[] values = { 20, 25, 30, 40 };

		for (int value : values) {
			assertTrue(value >= 20);
		}
	}
}