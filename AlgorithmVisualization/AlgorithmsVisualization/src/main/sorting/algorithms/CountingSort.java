package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getMax;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Non comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: ×
 * Stable: √
 * Best-Case Performance: Θ(n + k)
 * Worst-Case Performance: Θ(n + k)
 * Average Performance: Θ(n + k)
 * Worst-Case Space Complexity: Θ(n + k)
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */
public class CountingSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void countingSort(final Array array) {

		int max = getMax(array);

		int[] counts = new int[max + 1];

		for (int i = 0; i < array.length; i++) {
			counts[array.get(i)]++;
			array.setHighlighted(0, i);
			sleep(4);
		}
		int n = 0;
		for (int i = 0; i < array.length; i++) {
			if (counts[n] == 0)
				n++;
			array.set(i, n);
			array.setHighlighted(0, i);
			counts[n]--;
			sleep(4);
		}
	}

	@Override
	public String name() {
		return "Counting Sort";
	}

	@Override
	public void sort(Array array) {
		countingSort(array);
	}
}
