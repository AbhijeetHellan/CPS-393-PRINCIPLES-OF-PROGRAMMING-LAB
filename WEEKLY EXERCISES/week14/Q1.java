package week14;

public class Q1 {

	// Part 1(a): Create a task by using the Runnable interface
	static class SumRunnable implements Runnable {
		private long sum = 0;

		@Override
		public void run() {
			// The math done inside here happens in the background thread
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
		}

		// Get the final answer
		public long getSum() {
			return sum;
		}
	}

	// Part 1(b): Create a task by extending the Thread class directly
	static class SumThread extends Thread {
		private long sum = 0;

		@Override
		public void run() {
			for (int i = 1; i <= 100; i++) {
				sum += i;
			}
		}

		// Get the final answer
		public long getSum() {
			return sum;
		}
	}

	public static void main(String[] args) {
		try {
			// Setup the Runnable task
			SumRunnable task = new SumRunnable();
			Thread t1 = new Thread(task);

			long start1 = System.nanoTime();
			t1.start(); // Start the background thread
			t1.join(); // Make the main program wait until t1 finishes
			long end1 = System.nanoTime();

			System.out.println("1(a) Runnable sum = " + task.getSum());
			System.out.println("1(a) Time = " + (end1 - start1) + " ns\n");

			// Setup the Thread class task
			SumThread t2 = new SumThread();

			long start2 = System.nanoTime();
			t2.start(); //
			t2.join(); //
			long end2 = System.nanoTime();

			System.out.println("1(b) Thread sum = " + t2.getSum());
			System.out.println("1(b) Time = " + (end2 - start2) + " ns");

		} catch (InterruptedException e) {
			// Handle errors if the program is interrupted while waiting
			e.printStackTrace();
		}
	}
}