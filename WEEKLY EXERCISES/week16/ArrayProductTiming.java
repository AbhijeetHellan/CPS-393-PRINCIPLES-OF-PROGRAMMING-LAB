package week16;

import java.util.Random;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArrayProductTiming {

	public static void main(String[] args) {

		int[] sizes = { 1000, 5000, 10000 };

		for (int n : sizes) {
			System.out.println("Running test for n = " + n);

			int[] a = generateArray(n);
			int[][] b = generateMatrix(n);

			long startTime1 = System.nanoTime();
			long[] result1 = computeWithNestedLoops(a, b);
			long endTime1 = System.nanoTime();

			double nestedLoopTime = (endTime1 - startTime1) / 1_000_000_000.0;

			System.out.println("Nested loop time: " + nestedLoopTime + " seconds");

			long startTime2 = System.nanoTime();
			long[] result2 = computeWithThreadPool(a, b);
			long endTime2 = System.nanoTime();

			double threadPoolTime = (endTime2 - startTime2) / 1_000_000_000.0;

			System.out.println("Thread pool time: " + threadPoolTime + " seconds");

			boolean sameResults = Arrays.equals(result1, result2);
			System.out.println("Both results are same: " + sameResults);

			System.out.println();
		}
	}

	// Generate array a of size n
	public static int[] generateArray(int n) {
		Random random = new Random();
		int[] a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = random.nextInt(10) + 1; // values from 1 to 10
		}

		return a;
	}

	// Generate n arrays, each of size n
	public static int[][] generateMatrix(int n) {
		Random random = new Random();
		int[][] b = new int[n][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				b[j][i] = random.nextInt(10) + 1; // values from 1 to 10
			}
		}

		return b;
	}

	// Part 2(a): Using multiple nested loops
	public static long[] computeWithNestedLoops(int[] a, int[][] b) {

		int n = a.length;
		long[] result = new long[n];

		for (int j = 0; j < n; j++) {

			long sum = 0;

			for (int i = 0; i < n; i++) {
				sum += a[i] * b[j][i];
			}

			result[j] = sum;
		}

		return result;
	}

	// Part 2(b): Using a pool of threads equal to number of cores
	public static long[] computeWithThreadPool(int[] a, int[][] b) {

		int n = a.length;
		long[] result = new long[n];

		int cores = Runtime.getRuntime().availableProcessors();

		System.out.println("Number of cores used: " + cores);

		ExecutorService pool = Executors.newFixedThreadPool(cores);

		for (int j = 0; j < n; j++) {

			final int index = j;

			pool.submit(() -> {
				long sum = 0;

				for (int i = 0; i < n; i++) {
					sum += a[i] * b[index][i];
				}

				result[index] = sum;
			});
		}

		pool.shutdown();

		try {
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Thread pool was interrupted.");
		}

		return result;
	}
}