package main.path.finding;

import java.util.ArrayList;
import java.util.HashMap;

import main.Main;
import main.path.finding.algorithms.AStar;
import main.path.finding.algorithms.PathFinder;
import main.utils.Coordinate;
import processing.core.PApplet;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 09-02-2019
 */
public class PathFinding extends Main {

	/**
	 * 
	 */
	PApplet p;

	/**
	 * 
	 */
	public int rows, cols, scale;

	/**
	 * 
	 */
	public int[][] grid;

	/**
	 * 
	 */
	public ArrayList<Coordinate> walls;

	/**
	 * 
	 */
	public Coordinate start;
	public Coordinate goal;

	/**
	 * 
	 */
	public HashMap<Coordinate, Coordinate> currentPath;
	public ArrayList<Coordinate> bestPath;
	public ArrayList<Coordinate> visitedNodes;

	/**
	 * 
	 */
	static Thread findThread;

	/**
	 * 
	 */
	static boolean running = false;
	static double startTime = 0;
	static double sleepTime = 0;
	static double controlTime = 0;
	static double stopTime = 0;
	static double displayTime = 0;

	static double sleepRatio = 1.0;

	/**
	 * 
	 * @param millis
	 */
	public static void sleep(double millis) {

		if (millis <= 0)
			return;

		long elapsedTime = System.nanoTime();

		double sleepAmount = millis / sleepRatio;
		controlTime += sleepAmount;

		if (controlTime <= 1.0)
			return;

		try {
			Thread.sleep((long) sleepAmount);
			elapsedTime = System.nanoTime() - elapsedTime;
			if (running)
				sleepTime += elapsedTime;
			controlTime -= (double) elapsedTime * 1e-6;
		} catch (Exception e) {
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param scale
	 */
	public PathFinding(int scale, PApplet p) {
		this.p = p;
		this.scale = scale;
		rows = Main.HEIGHT / this.scale;
		cols = Main.WIDTH / this.scale;
		grid = new int[rows][cols];
		walls = new ArrayList<Coordinate>();
		bestPath = new ArrayList<Coordinate>();
		visitedNodes = new ArrayList<Coordinate>();
		currentPath = new HashMap<Coordinate, Coordinate>();
	}

	/**
	 * 
	 */
	public void displayGrid() {
		p.background(150);
		Coordinate coord;
		for (int y = 0; y < rows; y++) {

			for (int x = 0; x < cols; x++) {
				p.stroke(0);
				if ((coord = new Coordinate(x, y)).equals(start)) {
					p.fill(0, 0, 150);
				} else if (coord.equals(goal)) {
					p.fill(150, 0, 150);
				} else if (walls.contains(coord)) {
					p.fill(0);
				} else if (bestPath.contains(coord)) {
					p.fill(255, 192, 203);
				} else if (visitedNodes.contains(coord)) {
					p.fill(255, 0, 0);
				} else if (currentPath.containsKey(coord)) {
					p.fill(0, 255, 0);
				} else {
					p.fill(255);
				}
//				p.ellipseMode(CENTER);
//				p.ellipse((x + 0.5f) * scale, (y + 0.5f) * scale, scale, scale);
				p.rectMode(CORNER);
				p.rect(x * scale, y * scale, scale, scale);

			}

		}

	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void createWall(int x, int y) {
		Coordinate wall = new Coordinate(x / scale, y / scale);
		if (!walls.contains(wall) && !wall.equals(start) && !wall.equals(goal)) {
			walls.add(wall);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void destroyWall(int x, int y) {
		Coordinate wall = new Coordinate(x / scale, y / scale);
		if (walls.contains(wall)) {
			walls.remove(wall);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void createStart(int x, int y) {
		Coordinate temp = new Coordinate(x / scale, y / scale);
		if (temp.equals(start) || walls.contains(temp) || temp.equals(goal)) {
			return;
		}
		start = temp.clone();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void destroyStart(int x, int y) {
		Coordinate temp = new Coordinate(x / scale, y / scale);
		if (temp.equals(start)) {
			start = null;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void createGoal(int x, int y) {
		Coordinate temp = new Coordinate(x / scale, y / scale);
		if (temp.equals(goal) || walls.contains(temp) || temp.equals(start)) {
			return;
		}
		goal = temp.clone();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void destroyGoal(int x, int y) {
		Coordinate temp = new Coordinate(x / scale, y / scale);
		if (temp.equals(goal)) {
			goal = null;
		}
	}

	/**
	 * 
	 * @param key
	 */
	public void runPathFinder(int key) {

		PathFinder finder;
		clear();

		switch (key) {

		case 0:
			finder = new AStar();
			break;

		default:
			finder = new AStar();
			break;
		}

		final PathFinding pf = this;

		findThread = new Thread() {
			@Override
			public void run() {
				running = true;
				finder.findPath(pf);
				running = false;
			};
		};
		findThread.start();

	}

	/**
	 * 
	 * @return
	 */
	public boolean running() {
		return running;
	}

	/**
	 * 
	 */
	public void reset() {
		walls.clear();
		bestPath.clear();
		visitedNodes.clear();
		currentPath.clear();
	}

	/**
	 * 
	 */
	public void clear() {
		bestPath.clear();
		visitedNodes.clear();
		currentPath.clear();
	}

	/**
	 * 
	 * @param scale
	 */
	public void setNewScale(int scale) {
		if (this.scale > 5 && this.scale < 100) {
			this.scale = scale;
			rows = Main.HEIGHT / this.scale;
			cols = Main.WIDTH / this.scale;
			grid = new int[rows][cols];
			walls = new ArrayList<Coordinate>();
			bestPath = new ArrayList<Coordinate>();
			visitedNodes = new ArrayList<Coordinate>();
			currentPath = new HashMap<Coordinate, Coordinate>();
		}
	}
	
	public void speed(double amount) {
		sleepRatio += amount;
		if (sleepRatio <= 0)
			sleepRatio = 0.1;
		System.out.println(sleepRatio);
	}
}
