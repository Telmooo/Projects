package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: ×
 * Best-Case Performance: Θ(n)
 * Worst-Case Performance: Θ(∞)
 * Average Performance: Θ((n+1)!)
 * Worst-Case Space Complexity: Θ(n)
 * 
 * Algorithm:
 * 
 * 1. Check if it's sorted;
 * 2. If it's not shuffle the array randomly, and go back to step 1.
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 *
 */

public class BogoSort implements Sorting {
	
	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 */
	public static void bogoSort(final Array array) {

		// get a random shot and verify if it's sorted
		while (!isSorted(array)) {
			for (int i = 0; i < array.length; i++) {
				swap(array, i, (int) (Math.random() * array.length));
				sleep(0.08);
			}
		}
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @return 
	 */
	public static boolean isSorted(final Array array) {
		for (int i = 0; i < array.length - 1; i++) {
			sleep(0.08);
			if (array.compare(i, i + 1) == 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String name() {
		return "Bogo Sort";
	}

	@Override
	public void sort(Array array) {
		bogoSort(array);
	}
}
