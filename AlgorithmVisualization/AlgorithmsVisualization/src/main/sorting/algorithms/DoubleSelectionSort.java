package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 05-02-2019
 */
public class DoubleSelectionSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void doubleSelectionSort(final Array array) {

		int lowBound = 0, highBound = array.length - 1, min = 0, max = 0;

		while (lowBound < highBound) {
			array.setHighlighted(0, min);
			array.setHighlighted(1, max);

			for (int i = lowBound + 1; i <= highBound; i++) {
				array.setHighlighted(2, i);
				
				if (array.compare(i, min) == -1) {
					min = i;
					array.setHighlighted(0, min);
				}
				if (array.compare(i, max) == 1) {
					max = i;
					array.setHighlighted(1, max);
				}

				sleep(0.05);
			}
			array.setHighlighted(2, -1);

			if (min == lowBound && max == highBound) {

			} else if (min == lowBound) {
				swap(array, max, highBound);
			} else if (max == highBound) {
				swap(array, min, lowBound);
			} else if (min == highBound && max == lowBound) {
				swap(array, min, max);
			} else if (min == highBound) {
				swap(array, min, lowBound);
				swap(array, max, highBound);
			} else {
				swap(array, max, highBound);
				swap(array, min, lowBound);
			}

			lowBound++;
			highBound--;
			min = max = lowBound;

			sleep(0.05);
		}
	}

	@Override
	public String name() {
		return "Double Selection Sort";
	}

	@Override
	public void sort(Array array) {
		doubleSelectionSort(array);
	}

}
