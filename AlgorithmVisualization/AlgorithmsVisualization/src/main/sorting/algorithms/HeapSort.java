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
 * Stable: ×
 * Best-Case Performance: Θ(n log(n)) distinct keys, O(n) equal keys
 * Worst-Case Performance: Θ(n log(n))
 * Average Performance: Θ(n log(n))
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */
public class HeapSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void heapSort(final Array array) {

		heapify(array);

		for (int i = array.length - 1; i > 0;) {
			array.setHighlighted(0, i);
			
			swap(array, i, 0);

			pushDown(array, 0, --i);
		}
	}

	/**
	 * 
	 * @param array
	 */
	public static void heapify(final Array array) {
		int index = (array.length - 2) / 2;

		while (index >= 0) {
			array.setHighlighted(0, index);
			pushDown(array, index--, array.length - 1);
			sleep(1);
		}
	}

	/**
	 * 
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void pushDown(final Array array, int start, int end) {

		int root = start, child;
		array.setHighlighted(1, root);
		while (root * 2 + 1 <= end) {
			child = root * 2 + 1;
			array.setHighlighted(2, child);

			if (child + 1 <= end && array.compare(child, child + 1) == -1) {
				array.setHighlighted(2, child);
				array.setHighlighted(3, child + 1);
				child++;
			}
			
			array.setHighlighted(3, -1);

			if (array.compare(root, child) == -1) {
				swap(array, root, child);
				root = child;
				array.setHighlighted(1, root);
				sleep(1);
			} else
				return;
		}
	}

	@Override
	public String name() {
		return "Heap Sort";
	}

	@Override
	public void sort(Array array) {
		heapSort(array);
	}

}
