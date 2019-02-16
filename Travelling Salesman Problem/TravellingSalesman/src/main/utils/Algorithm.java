package main.utils;

import java.util.ArrayList;

import main.Main;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 15-02-2019
 */
public class Algorithm extends Main {

	/**
	 * 
	 * @param points
	 */
	public static void finder(final ArrayList<Coordinate> points) {

		if (points.size() <= 1)
			return;

		currentPath.clear();
		bestPath.clear();
		ArrayList<Coordinate> toVisit = new ArrayList<Coordinate>();

		for (Coordinate coord : points)
			toVisit.add(coord.clone());

		Coordinate pivot = points.get((int) (Math.random() * points.size())).clone();
		currentPath.add(pivot);
		currentPath.add(null);
		bestPath.add(pivot);
		toVisit.remove(pivot);
		int index = 1;
		Coordinate min = null;
		double minDist = Double.POSITIVE_INFINITY, aux;
		while (!toVisit.isEmpty()) {
			currentPath.set(1, min);
			bestPath.add(min);
			for (Coordinate coord : toVisit) {
				currentPath.set(1, coord);
				if ((aux = coord.distance(pivot)) < minDist || min == null) {
					minDist = aux;
					min = coord;
					bestPath.set(index, min);
				}
				sleep(10);
			}

			pivot = min;
			toVisit.remove(pivot);
			currentPath.set(0, pivot);
			minDist = Double.POSITIVE_INFINITY;
			min = null;
			index++;
			sleep(10);
		}
		currentPath.clear();
	}
}
