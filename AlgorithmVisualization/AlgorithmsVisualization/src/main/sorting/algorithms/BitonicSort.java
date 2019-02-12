package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getLengthMaxPower;
import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Parallel sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: ×
 * Best-Case Performance: Θ(log²(n))
 * Worst-Case Performance: Θ(log²(n))
 * Average Performance: Θ(log²(n))
 * Worst-Case Space Complexity: Θ(n log²(n))
 * 
 * @author telmo
 * @version 1.0
 * @since 03-02-2019
 */

public class BitonicSort implements Sorting {

	/**
	 * 
	 * @param array
	 * @param start
	 */
	public static void bitonicSort(final Array array, int start) {

		int highestPower = getLengthMaxPower(array.length - start);
		int offset = array.length - start - (1 << highestPower);
		for (int i = 0; i < highestPower; i++) {
			for (int j = 0; j <= i; j++) {
				bitonicArrange(array, i, j, start, start + (1 << highestPower));
			}
		}
		array.clearHighlighted();

		if (offset > 1) {
			bitonicSort(array, array.length - offset);
			bitonicMerge(array, start, array.length - offset, array.length);
		}

	}

	/**
	 * 
	 * @param array
	 * @param n
	 * @param m
	 * @param start
	 * @param limit
	 */
	public static void bitonicArrange(final Array array, int n, int m, int start, int limit) {

		int d = 1 << (n - m);
		boolean upwards;

		for (int i = start; i < limit; i++) {
			upwards = ((i >> n) & 2) == 0;
			array.setHighlighted(0, i);
			array.setHighlighted(1, i | d);
			if ((i & d) == 0 && (array.compare(i, i | d) == 1) == upwards) {
				swap(array, i, i | d);
			}
			sleep(0.2);
		}

	}

	/**
	 * 
	 * @param array
	 * @param start
	 * @param separator
	 * @param end
	 */
	public static void bitonicMerge(final Array array, int start, int separator, int end) {
		int i = start, target = separator;

		while (target < end) {
			array.setHighlighted(0, target);

			while (i < separator && array.compare(i, target) != 1) {
				array.setHighlighted(1, i);
				i++;
				sleep(1);
			}
			if (i > separator)
				break;

			pushUp(array, i, target, end);
			target++;
		}
	}

	/**
	 * 
	 * @param array
	 * @param start
	 * @param target
	 * @param end
	 */
	public static void pushUp(final Array array, int start, int target, int end) {

		for (int i = start; i < target; i++) {
			if (array.compare(i, target) == 1) {
				swap(array, i, target);
			}
			array.setHighlighted(1, i);
			sleep(0.05);
		}

	}

	@Override
	public String name() {
		return "Bitonic Sort";
	}

	@Override
	public void sort(Array array) {
		bitonicSort(array, 0);
	}

}
