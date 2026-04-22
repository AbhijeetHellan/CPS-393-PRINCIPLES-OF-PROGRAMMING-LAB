package week14;

import java.util.Random;

public class Q3 {

	// Thread to calculate sum of squares for part of the array
	static class PartThread extends Thread {
		private final int[] a;
		private final int s;
		private final int e;
		private long sum = 0;

		// Sets array and range
		public PartThread(int[] a, int s, int e) {
			this.a = a;
			this.s = s;
			this.e = e;
		}

		// Calculates partial sum
		@Override
		public void run() {
			for (int i = s; i < e; i++) {
				sum += (long) a[i] * a[i];
			}
		}

		// Returns partial sum
		public long getSum() {
			return sum;
		}
	}

	public static void main(String[] args) {
		// Checks command-line input
		if (args.length != 1) {
			System.out.println("Usage: java week14.Q3 <n>");
			return;
		}

		// Reads n
		int n = Integer.parseInt(args[0]);

		// Creates array
		int[] a = new int[n];
		Random r = new Random();

		// Fills array with values 1 to 100
		for (int i = 0; i < n; i++) {
			a[i] = r.nextInt(100) + 1;
		}

		// Times normal loop
		long t1 = System.nanoTime();
		long sum1 = 0;
		for (int i = 0; i < n; i++) {
			sum1 += (long) a[i] * a[i];
		}
		long t2 = System.nanoTime();

		// Splits array in half
		int m = n / 2;

		// Creates two threads
		PartThread p1 = new PartThread(a, 0, m);
		PartThread p2 = new PartThread(a, m, n);

		// Times threaded version
		long t3 = System.nanoTime();
		p1.start();
		p2.start();

		try {
			p1.join();
			p2.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		long sum2 = p1.getSum() + p2.getSum();
		long t4 = System.nanoTime();

		// Prints results
		System.out.println("n = " + n);
		System.out.println("Normal sum = " + sum1);
		System.out.println("Normal time(in nanosecond) = " + (t2 - t1) + " ns");
		System.out.println("Normal time(in millisecond) = " + ((t2 - t1) / 1_000_000.0) + " ms");
		System.out.println("Thread sum = " + sum2);
		System.out.println("Thread time(in nanosecond) = " + (t4 - t3) + " ns");
		System.out.println("Thread time(in millisecond) = " + ((t4 - t3) / 1_000_000.0) + " ms");
		
		
		// For small n, the normal method is faster because threads add extra overhead.
		// For large n, the threaded method can be faster because the work is divided between threads.
	}
}