package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Recursive sorting algorithm (Multiply & Surrender)
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: √
 * Best-Case Performance: too damn slow
 * Worst-Case Performance: Θ(n^(log_2(n)/(2+ϵ)))
 * Average Performance: too damn slow
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 06-02-2019
 */
public class SlowSort implements Sorting {

	/**
	 * 
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void slowSort(final Array array, int start, int end) {

		if (start >= end)
			return;

		int mid = (start + end) / 2;
		array.setHighlighted(0, mid);
		slowSort(array, start, mid);
		slowSort(array, mid + 1, end);

		if (array.compare(mid, end) == 1)
			swap(array, mid, end);

		slowSort(array, start, end - 1);
	}

	@Override
	public String name() {
		return "Slow Sort";
	}

	@Override
	public void sort(Array array) {
		slowSort(array, 0, array.length - 1);
	}

}
