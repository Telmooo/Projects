package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/* 
 * Class: Sorting Algorithm
 * Comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: ×
 * Best-Case Performance: Θ(n log(n))
 * Worst-Case Performance: Θ(n²)
 * Average Performance: Θ(n log(n))
 * Worst-Case Space Complexity: Θ(n)
 */

public class QuickSort implements Sorting {

	public static void quickSort(Array array, int start, int end) {

		if (start < end) {
			int pivot = partition(array, start, end);
			quickSort(array, start, pivot);
			quickSort(array, pivot + 1, end);
		}
	}

	public static int partition(Array array, int start, int end) {

		int pivotIndex = (int) (start + Math.random() * (end + 1 - start));
		int pivot = array.get(pivotIndex);
		int i = start - 1;
		int j = end + 1;

		array.setHighlighted(0, pivotIndex);

		while (true) {

			i++;
			array.setHighlighted(1, i);
			while (i < end && array.compareToValue(i, pivot) == -1) {
				i++;
				array.setHighlighted(1, i);
				sleep(1);
			}

			j--;
			array.setHighlighted(2, j);
			while (j > start && array.compareToValue(j, pivot) == 1) {
				j--;
				array.setHighlighted(2, j);
				sleep(1);
			}

			if (i < j) {
				swap(array, i, j);
			} else {
				return j;
			}
		}
	}

	@Override
	public String name() {
		return "Quick Sort";
	}

	@Override
	public void sort(Array array) {
		quickSort(array, 0, array.length - 1);
	}
}
