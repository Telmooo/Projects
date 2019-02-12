package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.writeBack;
import static main.sorting.utils.ArrayUtils.getHighestPower;
import static main.sorting.utils.ArrayUtils.getDigit;
import static main.sorting.ArrayVisualization.sleep;

import java.util.ArrayList;
import main.sorting.Array;

/**
 * Class: Sorting Algorithm Non comparison sorting algorithm Array Accesses: √
 * Writes: √ Comparisons: × Stable: √ Best-Case Performance: - Worst-Case
 * Performance: Θ(w·n) Average Performance: Θ(w·n) Worst-Case Space Complexity:
 * Θ(w+n)
 * 
 * @author telmo
 * @version 1.0
 * @since 07-02-2019
 */
public class RadixLSDSort implements Sorting {

	/**
	 * 
	 */
	private final int radix;

	/**
	 * 
	 * @param radix
	 */
	public RadixLSDSort(int radix) {
		this.radix = radix;
	}

	/**
	 * 
	 * @param array
	 * @param radix
	 */
	public static void radixLSDSort(final Array array, int radix) {

		int maxPower = getHighestPower(array, radix);

		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] registers = new ArrayList[radix];
		for (int i = 0; i < radix; i++)
			registers[i] = new ArrayList<Integer>();

		int aux;

		for (int power = 0; power <= maxPower; power++) {
			for (int i = 0; i < array.length; i++) {
				array.setHighlighted(0, i);

				aux = array.get(i);
				registers[getDigit(aux, power, radix)].add(aux);

				sleep(1);
			}
			array.setHighlighted(0, -1);

			writeBack(array, registers);
		}
	}

	@Override
	public String name() {
		return "Radix LSD Sort Base " + radix;
	}

	@Override
	public void sort(Array array) {
		radixLSDSort(array, radix);
	}
}
