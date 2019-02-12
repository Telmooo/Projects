package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.swap;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm × √ ∞
 * Recursive sorting algorithm
 * Array Accesses: √
 * Writes: √
 * Comparisons: √
 * Stable: ×
 * Best-Case Performance: Θ(nˡᵒᵍ⁡⁽³⁾/ˡᵒᵍ⁡⁽³⁄²⁾)
 * Worst-Case Performance: Θ(nˡᵒᵍ⁡⁽³⁾/ˡᵒᵍ⁡⁽³⁄²⁾)
 * Average Performance: Θ(nˡᵒᵍ⁡⁽³⁾/ˡᵒᵍ⁡⁽³⁄²⁾)
 * Worst-Case Space Complexity: Θ(n)
 * 
 * @author telmo
 * @version 1.0
 * @since 05-02-2019
 */
public class StoogeSort implements Sorting {
	
	/**
	 * 
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void stoogeSort(final Array array, int start, int end) {
		
		if (array.compare(start, end) == 1)
			swap(array, start, end);
		
		sleep(0.005);
		
		if (end - start > 1) {
			int separator = (end - start + 1) / 3;
			array.setHighlighted(0, start);
			array.setHighlighted(1, end - separator);
			array.setHighlighted(2, start + separator);
			stoogeSort(array, start, end - separator);
			stoogeSort(array, start + separator, end);
			stoogeSort(array, start, end - separator);
		}
	}
	
	@Override
	public String name() {
		return "Stooge Sort";
	}

	@Override
	public void sort(Array array) {
		stoogeSort(array, 0, array.length - 1);
	}

}
