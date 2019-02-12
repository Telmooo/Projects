package main.sorting.algorithms;

import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm Comparison sorting algorithm Array Accesses: √
 * Writes: √ Comparisons: √ Stable: × Best-Case Performance: Θ(n log(n))
 * Worst-Case Performance: Unknown for this implementation (depends on the
 * sequence) Average Performance: Unknown for this implementation (depends on
 * the sequence) Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 02-02-2019
 */
public class ShellSort implements Sorting {

	/**
	 * Marcin Ciura's gap sequence
	 */
	static int[] gapSequence = { 995, 409, 156, 57, 23, 10, 4, 1 };

	/**
	 * 
	 * @param array
	 */
	public static void shellSort(final Array array) {

		int i, j, aux;

		for (int gap : gapSequence) {

			for (i = gap; i < array.length; i++) {

				aux = array.get(i);
				array.setHighlighted(0, i);

				for (j = i; j >= gap && array.compareToValue(j - gap, aux) == 1; j -= gap) {
					array.setHighlighted(1, j);
					array.setHighlighted(2, j - gap);
					array.set(j, array.get(j - gap));
					sleep(2);
				}

				array.set(j, aux);
			}

		}

	}

	@Override
	public String name() {
		return "Shell Sort";
	}

	@Override
	public void sort(Array array) {
		shellSort(array);
	}

}
