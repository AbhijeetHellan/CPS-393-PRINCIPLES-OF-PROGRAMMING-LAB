package week15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

	private BankAccount account;

	@BeforeEach
	void setUp() {
		// Starts each test with a fresh account of 100.0
		account = new BankAccount(100.0);
	}

	// 1. Add an @AfterEach annotation and method to delete the current bank account
	// to make it available for garbage collection
	@AfterEach
	void tearDown() {
		account = null;
	}

	@Test
	void testDeposit() {
		// 2. Deposit $50 and check that the balance is 150
		account.deposit(50.0);
		assertEquals(150.0, account.getBalance(), "Balance should be 150.0 after a $50 deposit");
	}

	@Test
	void testWithdraw() {
		// 3. withdraw $40 and check that the balance is $60; remember that each test is
		// done on a fresh instance of bank account
		account.withdraw(40.0);
		assertEquals(60.0, account.getBalance(), "Balance should be 60.0 after a $40 withdrawal");
	}

	@Test
	void testInvalidDeposit() {
		// 4. Deposit a negative amount and check if an exception is thrown
		assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(-10.0);
		});
	}

	@Test
	void testOverdraft() {
		// 5. Verify that Withdrawing more than the current balance throws an exception
		assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(150.0);
		});
	}

	@Test
	void testNegativeInitialBalance() {
		// 6. Add a test to check that an Exception is thrown when trying to create a
		// new bank account with a negative initial balance
		assertThrows(IllegalArgumentException.class, () -> {
			new BankAccount(-50.0);
		});
	}

	@Test
	void testTransfer() {
		// 1c. Test for the transfer method
		BankAccount targetAccount = new BankAccount(50.0);
		account.transfer(targetAccount, 25.0);

		// Check both accounts to ensure the money moved correctly
		assertEquals(75.0, account.getBalance());
		assertEquals(75.0, targetAccount.getBalance());
	}
}