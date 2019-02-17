package main.utils;

import java.util.ArrayList;

import main.Main;
import processing.data.IntList;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 15-02-2019
 */
public class Algorithm extends Main {

	/**
	 * 
	 */
	public static void runIt() {
		calculateFitness();
		nextGen();
	}

	/**
	 * 
	 */
	public static void calculateFitness() {
		double temp = Double.POSITIVE_INFINITY;
		for (int i = 0; i < population.length; i++) {
			double dist = getDistance(points, population[i]);
			if (dist < recordDist) {
				recordDist = dist;
				bestPath = population[i];
			}
			if (dist < temp) {
				temp = dist;
				currentPath = population[i];
			}

			fitness[i] = 1.0 / (Math.pow(dist, 8) + 1);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		normalize();
	}

	/**
	 * 
	 * @param points
	 * @param order
	 * @return
	 */
	private static double getDistance(ArrayList<Coordinate> points, IntList order) {
		double dist = 0;
		for (int i = 0; i < order.size() - 1; i++) {
			int indexA = order.get(i);
			Coordinate pointA = points.get(indexA);
			int indexB = order.get(i + 1);
			Coordinate pointB = points.get(indexB);
			dist += pointA.distance(pointB);
		}
		return dist;
	}

	/**
	 * 
	 */
	public static void normalize() {
		double sum = 0;
		for (int i = 0; i < fitness.length; i++) {
			sum += fitness[i];
		}
		for (int i = 0; i < fitness.length; i++) {
			fitness[i] /= sum;
		}
	}

	/**
	 * 
	 */
	public static void nextGen() {
		IntList[] newPopulation = new IntList[populationSize];
		for (int i = 0; i < population.length; i++) {
			IntList parentA = getParent(population, fitness);
			IntList parentB = getParent(population, fitness);
			IntList child = crossover(parentA, parentB);
			mutate(child);
			newPopulation[i] = child;
		}
		population = newPopulation;
		gen++;
	}

	/**
	 * 
	 * @param population
	 * @param prob
	 * @return
	 */
	private static IntList getParent(IntList[] population, double[] prob) {
		int index = 0;
		double random = Math.random();

		while (random > 0) {
			random -= prob[index];
			index++;
		}
		index--;
		return population[index].copy();
	}

	/**
	 * 
	 * @param parentA
	 * @param parentB
	 * @return
	 */
	private static IntList crossover(IntList parentA, IntList parentB) {
		int start = (int) (Math.random() * parentA.size());
		int end = (int) (start + 1 + Math.random() * (parentA.size() - start - 1));
		IntList child = new IntList();
		for (int i = start; i < end; i++) {
			child.append(parentA.get(i));
		}
		int point;
		for (int i = 0; i < parentB.size(); i++) {
			if (!child.hasValue((point = parentB.get(i))))
				child.append(point);
		}
		return child;
	}

	/**
	 * 
	 * @param population
	 */
	private static void mutate(IntList population) {
		for (int i = 0; i < points.size(); i++) {
			if (Math.random() < mutationRate) {
				int indexA = (int) (Math.random() * population.size());
				int indexB = (int) (Math.random() * population.size());
				swap(population, indexA, indexB);
			}
		}
	}

	/**
	 * 
	 * @param population
	 * @param i
	 * @param j
	 */
	private static void swap(IntList population, int i, int j) {
		int temp = population.get(i);
		population.set(i, population.get(j));
		population.set(j, temp);
	}
}
