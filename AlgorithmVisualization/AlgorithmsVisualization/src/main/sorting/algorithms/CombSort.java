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
 * Best-Case Performance: Θ(n log(n))
 * Worst-Case Performance: Θ(n²)
 * Average Performance: Θ(n²/2ᵖ)
 * Worst-Case Space Complexity: Θ(1)
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */
public class CombSort implements Sorting {

	/**
	 * 
	 * @param array
	 * @param gap
	 * @param shrinkFactor
	 */
	public static void combSort(final Array array, int gap, float shrinkFactor) {

		int i;
		boolean changes;

		do {
			changes = false;

			i = 0;
			while (i + gap < array.length) {
				// compare two elements of an array with a gap, swap'em if not in order
				array.setHighlighted(0, i);
				array.setHighlighted(1, i + gap);
				if (array.compare(i, i + gap) == 1) {
					swap(array, i, i + gap);
					changes = true;
					sleep(3);
				}
				i++;
			}
			// decrease the gap
			gap = (int) Math.max(Math.floor(gap / shrinkFactor), 1);

		} while (gap > 1 || changes);
	}

	@Override
	public String name() {
		return "Comb Sort";
	}

	@Override
	public void sort(Array array) {
		combSort(array, array.length - 1, 1.3f);
	}
}
