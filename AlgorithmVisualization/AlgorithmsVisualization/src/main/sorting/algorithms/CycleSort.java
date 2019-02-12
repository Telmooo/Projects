package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swapWithValue;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: √
 * Best-Case Performance: Θ(n²)
 * Worst-Case Performance: Θ(n²)
 * Average Performance: Θ(n²)
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */
public class CycleSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void cycleSort(final Array array) {

		int aux, index;

		for (int i = 0; i < array.length - 1; i++) {

			aux = array.get(i);
			array.setHighlighted(0, i);

			index = i;
			array.setHighlighted(1, index);

			for (int j = i + 1; j < array.length; j++) {
				if (array.compareToValue(j, aux) == -1)
					index++;
				array.setHighlighted(2, j);
				sleep(0.01);
			}
			array.setHighlighted(2, -1);

			if (i == index)
				continue;

			while (array.compareToValue(index, aux) == 0) {
				index++;
				array.setHighlighted(1, index);
				sleep(0.01);
			}

			aux = swapWithValue(array, index, aux);

			while (index != i) {

				index = i;
				array.setHighlighted(1, index);
				for (int j = i + 1; j < array.length; j++) {
					array.setHighlighted(2, j);
					if (array.compareToValue(j, aux) == -1) {
						index++;
						array.setHighlighted(1, index);
					}
					sleep(0.01);
				}
				while (array.compareToValue(index, aux) == 0) {
					index++;
					array.setHighlighted(1, index);
					sleep(0.01);
				}

				aux = swapWithValue(array, index, aux);
				sleep(0.01);
			}
		}
	}

	@Override
	public String name() {
		return "Cycle Sort";
	}

	@Override
	public void sort(Array array) {
		cycleSort(array);
	}

}
