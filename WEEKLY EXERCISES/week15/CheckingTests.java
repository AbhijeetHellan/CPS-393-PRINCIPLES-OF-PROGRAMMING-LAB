package week15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//  Checking if three JUnit test methods written in the same testing class fails entirely even if one fails.
class CheckingTests {

	@Test
	void testOne() {
		assertEquals(5, 10);
	}

	@Test
	void testTwo() {
		assertEquals(4, 4);
	}

	@Test
	void testThree() {
		assertTrue(10 > 2);
	}
}