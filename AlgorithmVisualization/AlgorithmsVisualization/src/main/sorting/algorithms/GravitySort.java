package main.sorting.algorithms;

import main.sorting.Array;

import static main.sorting.utils.ArrayUtils.getMax;
import static main.sorting.ArrayVisualization.sleep;

/**
 * Class: Sorting Algorithm Natural sorting algorithm Array Accesses: √ Writes:
 * √ Comparisons: × Stable: N/A Best-Case Performance: Θ(n) Worst-Case
 * Performance: Θ(S) S being the sum of all integers to be sorted Average
 * Performance: Θ(S) S being the sum of all integers to be sorted Worst-Case
 * Space Complexity: Θ(n²)
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */
public class GravitySort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void gravitySort(final Array array) {

		int max = getMax(array);

		int[][] abacus = new int[array.length][max];

		for (int i = 0; i < array.length; i++) {
			array.setHighlighted(0, i);
			for (int j = 0; j < array.get(i); j++) {
				abacus[i][max - 1 - j] = 1;
			}
			sleep(1);
		}
		array.setHighlighted(0, -1);

		int index;
		int accumulator;
		// apply gravity
		for (int j = 0; j < max; j++) {
			for (int i = 0; i < abacus.length; i++) {
				array.arrayAccesses++;
				if (abacus[i][j] == 1) {

					index = i;
					while (index + 1 < abacus.length && abacus[index][j] == 1) {
						index++;
						array.arrayAccesses++;
					}
					array.arrayAccesses++;
					if (abacus[index][j] == 0) {
						abacus[i][j] = 0;
						abacus[index][j] = 1;
					}
				}
			}

			for (int x = 0; x < abacus.length; x++) {
				accumulator = 0;
				for (int y = 0; y < max; y++) {
					accumulator += abacus[x][y];
					array.arrayAccesses++;
				}
				array.set(x, accumulator);
				sleep(0.01);
			}
		}
	}

	@Override
	public String name() {
		return "Gravity Sort";
	}

	@Override
	public void sort(Array array) {
		gravitySort(array);
	}
}
