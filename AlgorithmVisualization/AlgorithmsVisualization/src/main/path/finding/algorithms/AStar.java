package main.path.finding.algorithms;

import static main.path.finding.utils.PathFindingUtils.getNeighbours;
import static main.path.finding.PathFinding.sleep;

import java.util.ArrayList;
import java.util.HashMap;

import main.path.finding.PathFinding;
import main.utils.Coordinate;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 09-02-2019
 */
public class AStar implements PathFinder {

	/**
	 * 
	 * @param pf
	 * @param start
	 * @param goal
	 */
	public static void aStar(final PathFinding pf, Coordinate start, Coordinate goal) {
		
		if (start == null || goal == null)
			return;
		
		ArrayList<Coordinate> toVisit = new ArrayList<Coordinate>();

		HashMap<Coordinate, Double> costNode = new HashMap<Coordinate, Double>();
		HashMap<Coordinate, Double> costTotal = new HashMap<Coordinate, Double>();

		Coordinate[] neighbours;
		Coordinate pivot;

		double tempCost;

		toVisit.add(start);

		costNode.put(start, 0d);
		costTotal.put(start, cost(start, goal));

		while (!toVisit.isEmpty()) {

			pivot = minCostNode(toVisit, costTotal);
			if (pivot.equals(goal)) {
				buildFinalPath(pf.currentPath, pf.bestPath, pivot);
				return;
			}

			toVisit.remove(pivot);
			pf.visitedNodes.add(pivot);
			neighbours = getNeighbours(pf, pivot);

			for (Coordinate neighbour : neighbours) {

				if (neighbour == null)
					break;
				
				if (pf.visitedNodes.contains(neighbour) || pf.walls.contains(neighbour))
					continue;

				tempCost = getCost(costNode, pivot) + cost(pivot, neighbour);

				if (!toVisit.contains(neighbour)) {
					toVisit.add(neighbour);
				} else if (tempCost >= getCost(costNode, neighbour)) {
					continue;
				}

				pf.currentPath.put(neighbour, pivot);
				costNode.put(neighbour, tempCost);
				costTotal.put(neighbour, getCost(costNode, neighbour) + cost(neighbour, goal));
			
				sleep(5);
			}
		}

	}

	/**
	 * 
	 * @param choices
	 * @param costValues
	 * @return
	 */
	public static Coordinate minCostNode(final ArrayList<Coordinate> choices,
			final HashMap<Coordinate, Double> costValues) {

		Coordinate min = choices.get(0);
		double minCost = getCost(costValues, min), aux;

		for (Coordinate coord : choices) {
			if ((aux = getCost(costValues, coord)) < minCost) {
				min = coord;
				minCost = aux;
			}
		}
		return min;
	}

	/**
	 * 
	 * @param node
	 * @param goal
	 * @return
	 */
	public static double cost(final Coordinate node, final Coordinate goal) {
		return node.distance(goal);
	}

	/**
	 * 
	 * @param costs
	 * @param key
	 * @return
	 */
	public static double getCost(final HashMap<Coordinate, Double> costs, Coordinate key) {
		return (costs.containsKey(key)) ? costs.get(key) : Double.POSITIVE_INFINITY;
	}

	/**
	 * 
	 * @param path
	 * @param deposit
	 * @param node
	 */
	public static void buildFinalPath(HashMap<Coordinate, Coordinate> path, ArrayList<Coordinate> deposit,
			Coordinate node) {
		deposit.add(node);
		Coordinate aux = node.clone();
		while (path.containsKey(aux)) {
			aux = path.get(aux).clone();
			deposit.add(aux);
			sleep(5);
		}
	}

	@Override
	public String name() {
		return "A*";
	}

	@Override
	public void findPath(PathFinding pf) {
		aStar(pf, pf.start, pf.goal);
	}
}
