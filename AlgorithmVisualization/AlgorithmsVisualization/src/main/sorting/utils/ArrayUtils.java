package main.sorting.utils;

import static main.sorting.ArrayVisualization.sleep;

import java.util.ArrayList;
import main.sorting.Array;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */
public class ArrayUtils {

	/**
	 * Swaps two elements of the array, increasing the counters on the array, for a
	 * version without counter incremental @see #swapnadd(Array, int, int)
	 * 
	 * @param array Array object {@link main.sorting.Array}
	 * @param i     index of first element to swap
	 * @param j     index of second element to swap
	 * @brief swaps two elements of the array
	 */
	public static void swap(final Array array, int i, int j) {
		int aux = array.get(i);
		array.set(i, array.get(j));
		array.set(j, aux);
	}

	/**
	 * Swaps an element of the array with a value, increasing the counters on the
	 * array, and returns the value previously stored on the position i
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @param i     index of element to swap
	 * @param value value to be swapped
	 * @return value stored on the array at position i
	 * @brief swaps an element of the array with a value
	 */
	public static int swapWithValue(final Array array, int i, int value) {
		int aux = array.get(i);
		array.set(i, value);
		return aux;
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @param from  position where the process begins
	 * @param to    position where the process ends
	 */
	public static void swapFromTo(final Array array, int from, int to) {
		if (to - from > 0)
			for (int i = from; i < to; i++)
				swap(array, i, i + 1);
		else
			for (int i = from; i > to; i--)
				swap(array, i, i - 1);
	}

	/**
	 * Swaps two elements of the array, without increasing the counters on the
	 * array, for a version with counter incremental @see #swap(Array, int, int)
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @param i     index of first element to swap
	 * @param j     index of second element to swap
	 * @brief swaps to elements of the array
	 */
	public static void swapnadd(final Array array, int i, int j) {
		int aux = array.getnadd(i);
		array.setnadd(i, array.getnadd(j));
		array.setnadd(j, aux);
	}

	/**
	 * 
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @return element with the highest value
	 * @brief get max element of the array
	 */
	public static int getMax(final Array array) {
		int max = 0;

		for (int i = 0; i < array.length; i++) {

			if (array.get(i) > max) {
				max = array.get(i);
			}
		}
		return max;
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @return array with the lowest and highest value of the array
	 * @brief get the min and max element of the array
	 */
	public static int[] getExtremes(final Array array) {
		int min, max, aux;
		min = max = array.get(0);

		for (int i = 1; i < array.length; i++) {
			aux = array.get(i);
			if (aux > max)
				max = aux;
			if (aux < min)
				min = aux;
		}
		return new int[] { min, max };
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @return array with the positions of the lowest and highest value of the array
	 * @brief get the index of min and max element of the array
	 */
	public static int[] getExtremesPosition(final Array array) {
		int min, max, minPos = 0, maxPos = 0, aux;
		min = max = array.get(0);

		for (int i = 1; i < array.length; i++) {
			aux = array.get(i);
			if (aux > max) {
				max = aux;
				maxPos = i;
			}
			if (aux < min) {
				min = aux;
				minPos = i;
			}
		}
		return new int[] { minPos, maxPos };
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @param limit
	 * @return
	 */
	public static int[] getExtremesPosition(final Array array, int limit) {
		int min, max, minPos = 0, maxPos = 0, aux;
		min = max = array.get(0);

		for (int i = 1; i < limit; i++) {
			aux = array.get(i);
			if (aux > max) {
				max = aux;
				maxPos = i;
			}
			if (aux < min) {
				min = aux;
				minPos = i;
			}
		}
		return new int[] { minPos, maxPos };
	}

	/**
	 * 
	 * @param array Array Object {@link main.sorting.Array}
	 * @param base
	 * @return
	 */
	public static int getHighestPower(final Array array, int base) {

		int n = 0, aux;

		for (int i = 0; i < array.length; i++) {

			aux = array.get(i);

			if ((int) (Math.log(aux) / Math.log(base)) > n) {
				n = (int) (Math.log(aux) / Math.log(base));
			}
		}
		return n;
	}

	/**
	 * 
	 * @param array     Array Object {@link main.sorting.Array}
	 * @param registers
	 */
	public static void writeBack(Array array, ArrayList<Integer>[] registers) {

		int[] temp = new int[array.length];
		boolean[] tempWrite = new boolean[array.length];
		int base = registers.length;
		writeToArray(registers, temp);

		int register, index;

		for (int i = 0; i < array.length; i++) {
			register = i % base;

			if (register == 0)
				sleep(base);

			index = (int) (((double) register * ((double) array.length / base)) + ((double) i / base));

			if (!tempWrite[index]) {
				array.set(index, temp[index]);
				tempWrite[index] = true;
			}

		}

		for (int i = 0; i < array.length; i++) {
			if (!tempWrite[i]) {
				array.set(i, temp[i]);
				sleep(0.05);
			}
		}
	}

	/**
	 * 
	 * @param registers
	 * @param array
	 */
	public static void writeToArray(ArrayList<Integer>[] registers, int[] array) {

		int index = 0;
		for (int i = 0; i < registers.length; i++) {
			for (int j = 0; j < registers[i].size(); j++) {
				array[index] = registers[i].get(j);
				index++;
			}
			registers[i].clear();
		}
	}

	/**
	 * 
	 * @param number
	 * @param power
	 * @param base
	 * @return
	 */
	public static int getDigit(int number, int power, int base) {
		return (int) (number / Math.pow(base, power)) % base;
	}

	/**
	 * 
	 * @param array
	 * @return
	 */
	public static int getLengthMaxPower(final Array array) {
		int power = 0, n = array.length;

		while (n > 1) {
			power++;
			n >>= 1;
		}
		return power;
	}

	/**
	 * 
	 * @param limit
	 * @return
	 */
	public static int getLengthMaxPower(int limit) {
		int power = 0, n = limit;

		while (n > 1) {
			power++;
			n >>= 1;
		}
		return power;
	}
}
