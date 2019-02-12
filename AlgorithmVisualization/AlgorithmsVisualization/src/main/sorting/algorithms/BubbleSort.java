package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm × √ ∞
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

public class BubbleSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void bubbleSort(final Array array) {

		boolean changes;
		int n = array.length;

		do {
			changes = false;
			n--;

			for (int i = 0; i < n; i++) {
				// sleep
				sleep(0.02);
				// if they're not in order swap'em
				array.setHighlighted(0, i);
				array.setHighlighted(1, i + 1);
				if (array.compare(i, i + 1) == 1) {
					swap(array, i, i + 1);
					changes = true;
				}
			}
			
		} while (changes);

	}

	@Override
	public String name() {
		return "Bubble Sort";
	}

	@Override
	public void sort(Array array) {
		bubbleSort(array);
	}
}
