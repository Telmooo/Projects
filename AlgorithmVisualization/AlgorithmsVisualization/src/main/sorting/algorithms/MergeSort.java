package main.sorting.algorithms;

import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/* 
 * Class: Sorting Algorithm
 * Comparison sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: √
 * Best-Case Performance: Θ(n log(n))
 * Worst-Case Performance: Θ(n log(n))
 * Average Performance: Θ(n log(n))
 * Worst-Case Space Complexity: Θ(n) auxiliary
 */

public class MergeSort implements Sorting {

	public static void mergeSort(final Array array, int start, int mid, int end) {

		if (start >= end - 1)
			return;

		mergeSort(array, start, (start + mid) / 2, mid);
		mergeSort(array, mid, (mid + end) / 2, end);

		int[] aux = new int[end - start];

		int lowerIndex = start;
		int topIndex = mid;
		array.setHighlighted(0, lowerIndex);
		array.setHighlighted(1, topIndex);
		for (int i = 0; i < array.length; i++) {

			if (lowerIndex >= mid && topIndex >= end)
				break;

			if (lowerIndex < mid && topIndex >= end) {
				aux[i] = array.get(lowerIndex);
				lowerIndex++;
				array.setHighlighted(0, lowerIndex);
			} else if (lowerIndex >= mid && topIndex < end) {
				aux[i] = array.get(topIndex);
				topIndex++;
				array.setHighlighted(1, topIndex);
			} else if (array.compare(lowerIndex, topIndex) == -1) {
				aux[i] = array.get(lowerIndex);
				lowerIndex++;
				array.setHighlighted(0, lowerIndex);
			} else {
				aux[i] = array.get(topIndex);
				topIndex++;
				array.setHighlighted(1, topIndex);
			}
			sleep(0.5);
		}
		array.setHighlighted(1, -1);

		for (int i = 0; i < aux.length; i++) {
			array.setHighlighted(0, start + i);
			array.set(start + i, aux[i]);
			sleep(0.5);
		}
	}

	@Override
	public String name() {
		return "Merge Sort";
	}

	@Override
	public void sort(Array array) {
		mergeSort(array, 0, array.length / 2, array.length);
	}
}
