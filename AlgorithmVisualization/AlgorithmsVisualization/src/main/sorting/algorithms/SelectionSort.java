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
 * Stable: ×
 * Best-Case Performance: Θ(n²) comparisons, Θ(n) swaps
 * Worst-Case Performance: Θ(n²) comparisons, Θ(n) swaps
 * Average Performance: Θ(n²) comparisons, Θ(n) swaps
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 02-02-2019
 */
public class SelectionSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void selectionSort(final Array array) {
		int minIndex;
		for (int i = 0; i < array.length - 1; i++) {
			minIndex = i;
			array.setHighlighted(0, minIndex);
			for (int j = i + 1; j < array.length; j++) {
				array.setHighlighted(1, j);
				if (array.compare(j, minIndex) == -1) {
					minIndex = j;
					array.setHighlighted(0, minIndex);
					sleep(2);
				}
			}
			swap(array, i, minIndex);
		}
	}

	@Override
	public String name() {
		return "Selection Sort";
	}

	@Override
	public void sort(Array array) {
		selectionSort(array);
	}
}
