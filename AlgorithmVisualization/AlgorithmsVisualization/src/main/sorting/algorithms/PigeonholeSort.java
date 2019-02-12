package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getExtremes;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/* 
 * Class: Sorting Algorithm
 * Comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: ×
 * Stable: √
 * Best-Case Performance: Θ(N + n) N being the range of key values
 * Worst-Case Performance: Θ(N + n)
 * Average Performance: Θ(N + n)
 * Worst-Case Space Complexity: Θ(N + n)
 */

public class PigeonholeSort implements Sorting {

	public static void pigeonholeSort(final Array array) {

		int[] extremes = getExtremes(array);
		int min = extremes[0], max = extremes[1];
		int range = max - min + 1;
		int[] holes = new int[range];
		for (int i = 0; i < array.length; i++) {
			holes[array.get(i) - min]++;
			array.setHighlighted(0, i);
			sleep(2);
		}

		int i = 0;
		array.setHighlighted(0, i);
		for (int k = 0; k < range; k++) {
			while (holes[k] > 0) {
				holes[k]--;
				array.set(i, k + min);
				i++;
				array.setHighlighted(0, i);
				sleep(5);
			}
		}
	}

	@Override
	public String name() {
		return "Pigeonhole Sort";
	}

	@Override
	public void sort(Array array) {
		pigeonholeSort(array);
	}

}
