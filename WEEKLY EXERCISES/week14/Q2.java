package week14;

public class Q2 {

	// Thread-safe counter using the synchronized keyword
	static class Counter {
		private int count = 0;

		// Only one thread can run this method at a time
		public synchronized void increment() {
			count++;
		}

		// Get the current count
		public int getCount() {
			return count;
		}
	}

	// Unsafe counter without synchronization
	static class CounterNoSync {
		private int count = 0;

		// Multiple threads can run this at the same time, causing errors
		public void increment() {
			count++;
		}

		// Get the current count
		public int getCount() {
			return count;
		}
	}

	// Task that increments the safe counter 1000 times
	static class WorkerSync extends Thread {
		private final Counter counter;

		public WorkerSync(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			// Increment 1000 times in the background
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		}
	}

	// Task that increments the unsafe counter 1000 times
	static class WorkerNoSync extends Thread {
		private final CounterNoSync counter;

		public WorkerNoSync(CounterNoSync counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			// Increment 1000 times in the background
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		}
	}

	public static void main(String[] args) {
		caseAandB();
		caseC();
		caseD();
	}

	// Case 2(a) and 2(b): Threads run safely and main waits for them
	public static void caseAandB() {
		Counter counter = new Counter();
		WorkerSync[] threads = new WorkerSync[10];

		long start = System.nanoTime();

		// Start all 10 threads
		for (int i = 0; i < 10; i++) {
			threads[i] = new WorkerSync(counter);
			threads[i].start();
		}

		// Make the main program wait for all 10 threads to finish
		for (int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		long end = System.nanoTime();

		System.out.println("2(a)/(b) Synchronized with join:");
		System.out.println("Final counter = " + counter.getCount());
		System.out.println("Time = " + (end - start) + " ns\n");
	}

	// Case 2(c): Main prints the result before threads are finished
	public static void caseC() {
		Counter counter = new Counter();
		WorkerSync[] threads = new WorkerSync[10];

		long start = System.nanoTime();

		// Start all 10 threads
		for (int i = 0; i < 10; i++) {
			threads[i] = new WorkerSync(counter);
			threads[i].start();
		}

		long end = System.nanoTime();

		System.out.println("2(c) Synchronized without join:");
		System.out.println("Final counter may be less than 10000 when printed immediately = " + counter.getCount());
		System.out.println("Time = " + (end - start) + " ns\n");
	}

	// Case 2(d): Threads overwrite each other because there is no synchronization
	public static void caseD() {
		CounterNoSync counter = new CounterNoSync();
		WorkerNoSync[] threads = new WorkerNoSync[10];

		long start = System.nanoTime();

		// Start all 10 threads
		for (int i = 0; i < 10; i++) {
			threads[i] = new WorkerNoSync(counter);
			threads[i].start();
		}

		// Make the main program wait for all 10 threads to finish
		for (int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		long end = System.nanoTime();

		System.out.println("2(d) Not synchronized:");
		System.out.println("Final counter less than 10000 = " + counter.getCount());
		System.out.println("Time = " + (end - start) + " ns\n");
	}
}