package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getExtremes;
import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.utils.ArrayUtils.swapWithValue;
import static main.sorting.ArrayVisualization.sleep;


import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Non comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: ×
 * Stable: ×
 * Best-Case Performance: Θ(n)
 * Worst-Case Performance: Θ(n²)
 * Average Performance: Θ(n + k)
 * Worst-Case Space Complexity: Θ(n)
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */
public class FlashSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void flashSort(final Array array) {

		int[] extremes = getExtremes(array);
		int min = extremes[0], max = extremes[1];

		if (min == max)
			return;

		double c = (array.length - 1) * (1.0 / (double) (max - min));

		int[] classes = new int[array.length];
		int index;

		for (int i = 0; i < array.length - 1; i++) {
			index = (int) (c * (array.get(i) - min));
			classes[index]++;
			array.setHighlighted(0, i);
			sleep(1);
		}

		for (int i = 1; i < array.length; i++) {
			classes[i] += classes[i - 1];
		}

		swap(array, 0, array.length - 1);

		int nmove = 0, j = 0;
		index = array.length - 1;

		while (nmove < array.length - 1) {
			array.setHighlighted(0, nmove);
			while (j > classes[index]) {
				array.setHighlighted(1, j);
				j++;
				index = (int) (c * (array.get(j) - min));
				sleep(1);
			}

			int flash = array.get(j);

			while (j != classes[index] + 1) {
				index = (int) (c * (flash - min));
				index = index > 800 ? 800 - 1 : index;
				flash = swapWithValue(array, classes[index], flash);
				classes[index]--;
				nmove++;
				array.setHighlighted(0, nmove);
				sleep(5);
			}

		}
		int aux;
		for (int i = array.length - 2; i >= 0; i--) {
			array.setHighlighted(0, i);
			array.setHighlighted(1, i + 1);
			if (array.compare(i + 1, i) == -1) {
				aux = array.get(i);
				j = i;
				while (j < array.length - 1 && array.compareToValue(j + 1, aux) == -1) {
					array.set(j, j + 1);
					array.setHighlighted(2, j);
					j++;
					sleep(4);
				}
				array.set(j, aux);
				sleep(2);
			}
		}

	}

	@Override
	public String name() {
		return "Flash Sort";
	}

	@Override
	public void sort(Array array) {
		flashSort(array);
	}

}
