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
 * Best-Case Performance: Θ(n)
 * Worst-Case Performance: Θ(n²)
 * Average Performance: Θ(n²)
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 02-09-2019
 */
public class GnomeSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void gnomeSort(final Array array) {
		int i = 0;

		while (i < array.length - 1) {
			array.setHighlighted(0, i);
			array.setHighlighted(0, i + 1);
			if (array.compare(i, i + 1) == 1) {
				swap(array, i, i + 1);
				sleep(0.05);
				if (i > 0)
					i -= 2;
			}
			i++;
		}
	}

	@Override
	public String name() {
		return "Gnome Sort";
	}

	@Override
	public void sort(Array array) {
		gnomeSort(array);
	}
}
