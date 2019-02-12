package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getExtremesPosition;
import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/* 
 * Class: Sorting Algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: ×
 * Best-Case Performance: -
 * Worst-Case Performance: Θ(n)
 * Average Performance: Θ(n)
 * Worst-Case Space Complexity: Θ(log(n))
 */

public class PancakeSort implements Sorting {

	public static void pancakeSort(final Array array, int size, int direction) {

		if (size == 0)
			return;

		int[] extremesPos = getExtremesPosition(array, size);
		int bestRoutePos = extremesPos[direction];
		int alternativeRoutePos = extremesPos[1 - direction];

		boolean flipped = false;

		if (bestRoutePos == size - 1) {
			size--;
		} else if (bestRoutePos == 0) {
			flip(array, size - 1);
			size--;
		} else if (alternativeRoutePos == size - 1) {
			direction = 1 - direction;
			size--;
			flipped = true;
		} else {
			flip(array, bestRoutePos);
		}

		pancakeSort(array, size, direction);

		if (flipped) {
			flip(array, size);
		}
	}

	public static void flip(final Array array, int n) {
		for (int i = 0; i < (n + 1) / 2; i++) {
			swap(array, i, n - i);
			sleep(0.08);
		}
	}

	@Override
	public String name() {
		return "Pancake Sort";
	}

	@Override
	public void sort(Array array) {
		pancakeSort(array, array.length, 1);
	}

}
