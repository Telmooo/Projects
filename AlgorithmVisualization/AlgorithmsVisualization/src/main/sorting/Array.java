package main.sorting;

import java.util.ArrayList;

public class Array {

	/**
	 * An array of positive integers used for visualising sorting algorithms Some
	 * sorting algorithms require the data to be positive integers, changing the
	 * type or the range of values might break some algorithms
	 * 
	 * @brief an array of positive integers
	 */
	public final int[] array;

	/**
	 * Numbers of elements of the array
	 * 
	 * @brief number of elements of the array
	 */
	public final int length;

	/**
	 * 
	 */
	public final ArrayList<Integer> highlighted;

	/**
	 * 
	 */
	public long arrayAccesses;

	/**
	 * 
	 */
	public long writes;

	/**
	 * 
	 */
	public long comparisons;

	/**
	 * Constructor for the array with a specific length Creates a new Array object
	 * which contains an array of integers with a specific length, counters to keep
	 * tracks of v
	 * 
	 * 
	 * @param length of the array
	 * @brief creates a new Array object with a specific length
	 */
	public Array(int length) {
		array = new int[length];
		this.length = length;
		highlighted = new ArrayList<Integer>();
		arrayAccesses = 0;
		writes = 0;
		comparisons = 0;

		for (int i = 0; i < array.length; i++) {
			array[i] = i + 1;
		}
	}

	/**
	 * Access an element of the array, this method increases the counter of array
	 * accesses to keep tracks of the array accesses each method does
	 * 
	 * @param index to the element (position in the array)
	 * @return
	 */
	public int get(int index) {
		arrayAccesses++;
		return array[index];
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public int getnadd(int index) {
		return array[index];
	}

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void set(int index, int value) {
		writes++;
		array[index] = value;
	}

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setnadd(int index, int value) {
		array[index] = value;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int compare(int i, int j) {
		comparisons++;
		arrayAccesses += 2;
		return Integer.compare(array[i], array[j]);
	}

	/**
	 * 
	 * @param i
	 * @param value
	 * @return
	 */
	public int compareToValue(int i, int value) {
		comparisons++;
		arrayAccesses++;
		return Integer.compare(array[i], value);
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int comparenadd(int i, int j) {
		return Integer.compare(array[i], array[j]);
	}

	/**
	 * 
	 */
	public void reset() {
		comparisons = 0;
		arrayAccesses = 0;
		writes = 0;
		highlighted.clear();
	}

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setHighlighted(int index, int value) {
		if (index >= highlighted.size())
			highlighted.add(value);
		else
			highlighted.set(index, value);
	}

	/**
	 * 
	 */
	public void clearHighlighted() {
		highlighted.clear();
	}
}
