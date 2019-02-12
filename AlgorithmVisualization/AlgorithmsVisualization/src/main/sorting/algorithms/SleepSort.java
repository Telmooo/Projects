package main.sorting.algorithms;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import main.sorting.Array;

/**
 * Class: Sorting Algorithm
 * Array Accesses: ×
 * Writes: √
 * Comparisons: ×
 * Stable: ×
 * Best-Case Performance: -
 * Worst-Case Performance: -
 * Average Performance: -
 * Worst-Case Space Complexity: Θ(1) auxiliary
 * 
 * @author telmo
 * @version 1.0
 * @since 05-09-2019
 */
public class SleepSort implements Sorting {

	/**
	 * 
	 * @param array
	 */
	public static void sleepSort(final Array array) {

		final CountDownLatch latch = new CountDownLatch(array.length);
		final AtomicInteger index = new AtomicInteger(0);

		for (final int n : array.array) {

			new Thread() {
				@Override
				public void run() {
					latch.countDown();

					try {
						latch.await();

						Thread.sleep(n * 10);
						array.setHighlighted(0, index.intValue());
						array.set(index.getAndIncrement(), n);
					} catch (InterruptedException e) {

					}
				}
			}.start();
		}

		while (index.intValue() != array.length - 1) {
		}
		array.clearHighlighted();
	}

	@Override
	public String name() {
		return "Sleep Sort";
	}

	@Override
	public void sort(Array array) {
		sleepSort(array);
	}
}
