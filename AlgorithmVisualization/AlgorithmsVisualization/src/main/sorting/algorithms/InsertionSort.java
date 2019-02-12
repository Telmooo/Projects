package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: √
 * Best-Case Performance: Θ(n) comparisons, Θ(1) swaps
 * Worst-Case Performance: Θ(n²) comparisons, Θ(n²) swaps
 * Average Performance: Θ(n²) comparisons, Θ(n²) swaps
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */
public class InsertionSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void insertionSort(final Array array) {
		int pos;
		for (int i = 1; i < array.length; i++) {
			pos = i;
			array.setHighlighted(0, pos);
			array.setHighlighted(1, pos - 1);
			while (pos > 0 && array.compare(pos, pos - 1) == -1) {
				swap(array, pos, pos - 1);
				pos--;
				array.setHighlighted(0, pos);
				array.setHighlighted(0, pos - 1);
				sleep(0.05);
			}
		}
	}

	@Override
	public String name() {
		return "Insertion Sort";
	}

	@Override
	public void sort(Array array) {
		insertionSort(array);
	}
}
