package week10;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/**
 * Submit to the TA, and he will assign you a grade based on a few selected
 * responses.
 */

public class Week10_labs {
	public static void main(String[] args) {
		List<String> fruit = Arrays.asList("cherry", "banana", "berry", "apple", "cherry", "kiwi", "fig", "date",
				"lemon", "honeydew", "cherry", "elderberry", "apple", "banana", "grape");

		// Collect elements into a Set
		// discards duplicates since Sets only hold unique elements.
		Set<String> fruitSet = fruit.stream().collect(Collectors.toSet());
		System.out.println("Fruit Set: " + fruitSet);

		// Collect the fruit into groups based on their first character
		// groupingBy uses charAt(0) as the key, so fruits sharing the same starting letter
		Map<Character, List<String>> byFirstChar = fruit.stream().collect(Collectors.groupingBy(f -> f.charAt(0)));
		System.out.println("Grouped by first char:" + byFirstChar);

		// Group fruit by the length of the name
		// String::length is the classifier, fruits of equal length share a group
		Map<Integer, List<String>> byLength = fruit.stream().collect(Collectors.groupingBy(String::length));
		System.out.println("Grouped by length: " + byLength);

		// Collect the fruit that has erry in it
		// filter() walks through every fruit and only keeps ones where contains("erry") is true.
		List<String> hasErry = fruit.stream().filter(f -> f.contains("erry")).collect(Collectors.toList());
		System.out.println("Contains 'erry': " + hasErry);

		// Create a partition of fruit based on if it contains erry
		// partitioningBy always creates exactly two groups labeled true and false.
		Map<Boolean, List<String>> erryPartition = fruit.stream()
				.collect(Collectors.partitioningBy(f -> f.contains("erry")));
		System.out.println("Partition by 'erry': " + erryPartition);

		// collect the fruit that has 5 or less symbols
		// filter() checks each fruit's length and discards anything longer than 5 characters.
		List<String> shortFruit = fruit.stream().filter(f -> f.length() <= 5).collect(Collectors.toList());
		System.out.println("5 or fewer characters: " + shortFruit);

		// find the total number of symbols in all the fruit stored
		// summingInt extracts the length of each fruit name and adds them all together.
		int totalChar = fruit.stream().collect(Collectors.summingInt(String::length));
		System.out.println("Total Characters:" + totalChar);

		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23,
				56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

		// Partition data based on if >=50
		// partitioningBy splits the list into two buckets: numbers >= 50 go to true,
		Map<Boolean, List<Integer>> partioned = data.stream().collect(Collectors.partitioningBy(s -> s >= 50));
		System.out.println("Partition by >=50:" + partioned);

		// divide data into groups based on the remainder when divided by 7
		// groupingBy uses the remainder (n % 7) as the key, creating up to 7 separate lists (0–6)
		Map<Integer, List<Integer>> byMod7 = data.stream().collect(Collectors.groupingBy(n -> n % 7));
		System.out.println("Grouped by mod 7: " + byMod7);

		// find the sum of the data
		// summingInt adds up every integer in the list in a single pass.
		// Integer::intValue takes each Integer object to a plain int for the addition
		int totalsum = data.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Sum:" + totalsum);

		// collect the unique values
		// Collecting into a Set removes all repeated values automatically.
		Set<Integer> dataSet = data.stream().collect(Collectors.toSet());
		System.out.println("Unique values: " + dataSet);

		// compute the cube of each values
		// map() replaces each number n with n*n*n (its cube).
		List<Integer> cubes = data.stream().map(n -> n * n * n).collect(Collectors.toList());
		System.out.println("Cubes: " + cubes);

		// find the sum of the cubes of each value
		// summingInt computes each cube inline and immediately adds it to total,
		int sumCubes = data.stream().collect(Collectors.summingInt(n -> n * n * n));
		System.out.println("Sum of Cubes: " + sumCubes);

		// increase the value of each element by 5
		// map(n -> n + 5) shifts every element up by 5 and stores results in a new list.
		List<Integer> increased = data.stream().map(n -> n + 5).collect(Collectors.toList());
		System.out.println("Each value + 5: " + increased);

		// compute the cube of the even values
		// filter() first removes all odd numbers, then map() cubes what remains.
		List<Integer> evenCubes = data.stream().filter(n -> n % 2 == 0).map(n -> n * n * n).collect(Collectors.toList());
		System.out.println("Cubes of even values: " + evenCubes);

	}
}
