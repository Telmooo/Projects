package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm Comparison sorting algorithm Array Accesses: √
 * Writes: √ Comparisons: √ Stable: √ Best-Case Performance: Θ(n) Worst-Case
 * Performance: Θ(n²) Average Performance: Θ(n²) Worst-Case Space Complexity:
 * Θ(1)
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */
public class CocktailShakerSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void cocktailShakerSort(final Array array) {
		int i = 0;
		boolean changes;
		do {
			changes = false;

			// ascending order
			for (int j = i; j < array.length - 1; j++) {
				array.setHighlighted(0, j);
				array.setHighlighted(1, j + 1);
				if (array.compare(j, j + 1) == 1) {
					swap(array, j, j + 1);
					changes = true;
					sleep(0.05);
				}
			}
			// descending order
			for (int j = array.length - 1 - i; j > i; j--) {
				array.setHighlighted(0, j);
				array.setHighlighted(1, j + 1);
				if (array.compare(j, j - 1) == -1) {
					swap(array, j, j - 1);
					changes = true;
					sleep(0.05);
				}
			}
		} while (changes && ++i < array.length / 2);
	}

	@Override
	public String name() {
		return "Cocktail Shaker Sort";
	}

	@Override
	public void sort(Array array) {
		cocktailShakerSort(array);
	}
}
