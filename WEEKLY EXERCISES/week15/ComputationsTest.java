package week15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputationsTest {

	@Test
	void testFibonacci() {
		// Check known valid Fibonacci sequence outputs
		assertEquals(0, Computations.fibonacci(0));
		assertEquals(1, Computations.fibonacci(2));
		assertEquals(1, Computations.fibonacci(1));
		assertEquals(55, Computations.fibonacci(10));

		// Ensure passing a negative number throws an error
		assertThrows(IllegalArgumentException.class, () -> {
			Computations.fibonacci(-1);
		});
	}

	@Test
	void testIsPrime() {
		// Verify numbers 1 and below are correctly flagged as not prime
		assertFalse(Computations.isPrime(-5));
		assertFalse(Computations.isPrime(0));
		assertFalse(Computations.isPrime(1));

		// Verify small, known prime numbers return true
		assertTrue(Computations.isPrime(2));
		assertTrue(Computations.isPrime(3));

		// Verify known composite (non-prime) numbers return false
		assertFalse(Computations.isPrime(4));
		assertFalse(Computations.isPrime(9));

		// Verify a larger known prime number returns true
		assertTrue(Computations.isPrime(97));
	}

	@Test
	void testIsEven() {
		// Test positive, zero, and negative even numbers
		assertTrue(Computations.isEven(2));
		assertTrue(Computations.isEven(0));
		assertTrue(Computations.isEven(-4));

		// Ensure an odd number returns false
		assertFalse(Computations.isEven(3));
	}

	@Test
	void testIsOdd() {
		// Test positive and negative odd numbers
		assertTrue(Computations.isOdd(3));
		assertTrue(Computations.isOdd(-7));

		// Ensure even numbers and zero return false
		assertFalse(Computations.isOdd(2));
		assertFalse(Computations.isOdd(0));
	}

	@Test
	void testToCelsius() {
		// Test the freezing and boiling points of water converting to Celsius
		assertEquals(0.0, Computations.toCelsius(32.0), 0.001);
		assertEquals(100.0, Computations.toCelsius(212.0), 0.001);
	}

	@Test
	void testToFahrenheit() {
		// Test the freezing and boiling points of water converting to Fahrenheit
		assertEquals(32.0, Computations.toFahrenheit(0.0), 0.001);
		assertEquals(212.0, Computations.toFahrenheit(100.0), 0.001);
	}
}