package main.sorting.algorithms;

import static main.sorting.utils.ArrayUtils.getHighestPower;
import static main.sorting.utils.ArrayUtils.getDigit;
import static main.sorting.utils.ArrayUtils.swapFromTo;
import static main.sorting.ArrayVisualization.sleep;

import main.sorting.Array;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 07-02-2019
 */
public class InPlaceRadixLSD implements Sorting {

	/**
	 * 
	 */
	private final int radix;

	/**
	 * 
	 * @param radix
	 */
	public InPlaceRadixLSD(int radix) {
		this.radix = radix;
	}

	/**
	 * 
	 * @param array
	 * @param radix
	 */
	public static void inPlaceRadixLSDSort(final Array array, int radix) {
		int pos, digit;
		int[] registers = new int[radix - 1];
		int maxPower = getHighestPower(array, radix);

		for (int p = 0; p <= maxPower; p++) {
			for (int i = 0; i < registers.length; i++)
				registers[i] = array.length - 1;
			pos = 0;
			for (int i = 0; i < array.length; i++) {
				array.setHighlighted(0, i);
				digit = getDigit(array.get(pos), p, radix);
				if (digit == 0) {
					pos++;
				} else {
					swapFromTo(array, pos, registers[digit - 1]);
					sleep(3);
					for (int j = digit - 1; j > 0; j--)
						registers[j - 1]--;
				}
			}
		}
	}

	@Override
	public String name() {
		return "In-Place Radix LSD Sort Base " + radix;
	}

	@Override
	public void sort(Array array) {
		inPlaceRadixLSDSort(array, radix);
	}

}
