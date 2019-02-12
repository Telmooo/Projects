package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 07-02-2019
 */
public class CircleSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void circleSort(final Array array) {
		while (circleSort(array, 0, array.length - 1, false)) {
		}
	}

	/**
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @param changes
	 * @return
	 */
	public static boolean circleSort(final Array array, int start, int end, boolean changes) {

		if (start == end)
			return changes;

		int i = start, j = end, mid = (end - start) / 2;

		while (i < j) {
			if (array.compare(i, j) == 1) {
				swap(array, i, j);
				changes = true;
			}
			array.setHighlighted(0, i);
			array.setHighlighted(1, j);
			i++;
			j--;
			sleep(0.5);
		}
		
		if (i == j) {
			array.setHighlighted(1, j + 1);
			if (array.compare(i, j + 1) == 1) {
				swap(array, i, j + 1);
				changes = true;
			}
		}

		changes = circleSort(array, start, start + mid, changes);
		changes = circleSort(array, start + mid + 1, end, changes);

		return changes;
	}

	@Override
	public String name() {
		return "Circle Sort";
	}

	@Override
	public void sort(Array array) {
		circleSort(array);
	}

}
