package main;

import static main.utils.Algorithm.calculateFitness;
import static main.utils.Algorithm.normalizeFitness;
import static main.utils.Algorithm.nextGen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import main.utils.Coordinate;
import main.utils.Vector;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.data.IntList;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 15-02-2019
 */
public class Main extends PApplet {

	/**
	 * 
	 */
	public static int WIDTH = 1440;
	public static int HEIGHT = 1000;

	/**
	 * 
	 */
	public static int populationSize = 500;
	public static double[] fitness;
	public static IntList[] population;
	public static double recordDist = Double.POSITIVE_INFINITY;
	public static double mutationRate = 0.1;
	public static int gen = 0;
	public static IntList currentPath;
	public static IntList bestPath;
	/**
	 * 
	 */
	PeasyCam camera;

	/**
	 * 
	 */
	public static Coordinate center = new Coordinate("Center", 0, 0);

	/**
	 * 
	 */
	protected static long sleepTime = 1;
	private static boolean running = false;
	private static boolean initialized = false;

	/**
	 * 
	 */
	PImage earth;
	PShape globe;
	public static double radius = 400;
	public double rotation = 0;

	/**
	 * 
	 */
	protected static ArrayList<String[]> locations;
	protected static ArrayList<Coordinate> points;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	/**
	 * 
	 */
	public void settings() {
		size(WIDTH, HEIGHT, P3D);
	}

	/**
	 * 
	 */
	public void setup() {
		camera = new PeasyCam(this, WIDTH / 2, HEIGHT / 2, 0, radius + 5e2);
		earth = loadImage(".\\data\\earth.jpg");
		noStroke();
		globe = createShape(SPHERE, (float) radius);
		globe.setTexture(earth);

		population = new IntList[populationSize];
		fitness = new double[populationSize];

		locations = new ArrayList<String[]>();
		points = new ArrayList<Coordinate>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(".\\data\\Locations"));
			String line;

			while ((line = br.readLine()) != null) {
				locations.add(line.split(","));
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		for (String[] location : locations) {
			try {
				double lat = Double.parseDouble(location[1]);
				double lon = Double.parseDouble(location[2]);
				if (Math.abs(lat) > 85 || Math.abs(lon) > 180)
					throw new IllegalArgumentException();

				points.add(new Coordinate(location[0], Math.toRadians(lon), Math.toRadians(lat)));
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				continue;
			}
		}
		IntList order = new IntList();
		for (int i = 0; i < points.size(); i++) {
			order.append(i);
		}
		IntList orderCopy = order.copy();
		for (int i = 0; i < populationSize; i++) {
			orderCopy.shuffle();
			population[i] = orderCopy;
		}
	}

	/**
	 * 
	 */
	public void draw() {
		background(0);
		translate(WIDTH / 2, HEIGHT / 2);

		lights();
		shape(globe);
		for (Coordinate coord : points) {
			double x = projX(radius, coord.lon, coord.lat);
			double y = projY(radius, coord.lat);
			double z = projZ(radius, coord.lon, coord.lat);

			push();
			stroke(255, 255, 0);
			strokeWeight(5);
			point((float) x, (float) y, (float) z);

			pop();
		}

		if (currentPath != null && currentPath.size() > 1) {
			push();
			noFill();
			strokeWeight(3);
			stroke(255, 0, 0);
			Vector prev = null;
			for (int i = 1; i < currentPath.size(); i++) {
				if (prev == null) {
					int prevIndex = currentPath.get(i - 1);
					double prevX = projX(radius, points.get(prevIndex).lon, points.get(prevIndex).lat);
					double prevY = projY(radius, points.get(prevIndex).lat);
					double prevZ = projZ(radius, points.get(prevIndex).lon, points.get(prevIndex).lat);
					prev = new Vector(prevX, prevY, prevZ);
				}
				int index = currentPath.get(i);
				double x = projX(radius, points.get(index).lon, points.get(index).lat);
				double y = projY(radius, points.get(index).lat);
				double z = projZ(radius, points.get(index).lon, points.get(index).lat);
				Vector pos = new Vector(x, y, z);
				ArrayList<Vector> arc = createArc(prev, pos, 7);
				beginShape();
				for (int p = 0; p < arc.size(); p++) {
					curveVertex((float) arc.get(p).x, (float) arc.get(p).y, (float) (float) arc.get(p).z);
				}
				endShape();

				prev.set(x, y, z);
			}
			pop();
		}

		if (bestPath != null && bestPath.size() > 1) {
			push();
			noFill();
			strokeWeight(3);
			stroke(0, 255, 0);
			Vector prev = null;
			for (int i = 1; i < bestPath.size(); i++) {
				if (prev == null) {
					int prevIndex = bestPath.get(i - 1);
					double prevX = projX(radius, points.get(prevIndex).lon, points.get(prevIndex).lat);
					double prevY = projY(radius, points.get(prevIndex).lat);
					double prevZ = projZ(radius, points.get(prevIndex).lon, points.get(prevIndex).lat);
					prev = new Vector(prevX, prevY, prevZ);
				}
				int index = bestPath.get(i);
				double x = projX(radius, points.get(index).lon, points.get(index).lat);
				double y = projY(radius, points.get(index).lat);
				double z = projZ(radius, points.get(index).lon, points.get(index).lat);
				Vector pos = new Vector(x, y, z);
				ArrayList<Vector> arc = createArc(prev, pos, 7);
				beginShape();
				for (int p = 0; p < arc.size(); p++) {
					curveVertex((float) arc.get(p).x, (float) arc.get(p).y, (float) (float) arc.get(p).z);
				}
				endShape();

				prev.set(x, y, z);
			}
			pop();
		}
		push();
		translate(0, 0, 0);
		stroke(255);
		fill(255);
		textSize(50);
		showGen();
		pop();
	}

	/**
	 * 
	 */
	void showGen() {
		camera();
		hint(DISABLE_DEPTH_TEST);
		noLights();
		textMode(MODEL);
		text("Generation: " + formatNum(gen), 20, 50);
		hint(ENABLE_DEPTH_TEST);
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public static String formatNum(long n) {
		if (n < 0)
			return "OVERFLOW";
		if (n < 1e3)
			return n + "";
		if (n < 1e6)
			return String.format("%.2fK", n * 1e-3);
		if (n < 1e9)
			return String.format("%.2fM", n * 1e-6);
		if (n < 1e12)
			return String.format("%.2fG", n * 1e-9);
		if (n < 1e15)
			return String.format("%.2fT", n * 1e-12);
		if (n < 1e18)
			return String.format("%.2fP", n * 1e-15);
		if (n < 1e21)
			return String.format("%.2fE", n * 1e-18);
		if (n < 1e24)
			return String.format("%.2fZ", n * 1e-21);
		if (n < 1e27)
			return String.format("%.2fY", n * 1e-24);

		return String.format("%.2fX", n * 1e-27);
	}

	/**
	 * 
	 */
	public void initialize() {
		new Thread() {
			@Override
			public void run() {
				while (running) {
					calculateFitness();
					normalizeFitness();
					nextGen();
				}
			};
		}.start();
	}

	/**
	 * 
	 */
	public void keyPressed() {
		switch (key) {
		case 'r':
		case 'R':
			if (!initialized) {
				initialize();
			}
			initialized ^= true;
			running ^= true;
			break;
		case '+':
			speed(-1);
			break;
		case '-':
			speed(1);
			break;
		}
	}

	/**
	 * 
	 * @param amount
	 */
	public void speed(int amount) {
		sleepTime = Math.max(0, sleepTime + amount);
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param detail
	 * @return
	 */
	public ArrayList<Vector> createArc(Vector start, Vector end, int detail) {
		ArrayList<Vector> arc = new ArrayList<Vector>();
		arc.add(start);
		ArrayList<Vector> auxArc = auxCreateArc(start, end, detail);
		if (auxArc != null)
			arc.addAll(auxArc);
		arc.add(end);
		return arc;
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param detail
	 * @return
	 */
	public ArrayList<Vector> auxCreateArc(Vector start, Vector end, int detail) {
		if (detail == 0 || start.equals(end))
			return null;

		ArrayList<Vector> arc = new ArrayList<Vector>();
		ArrayList<Vector> larc = new ArrayList<Vector>();
		ArrayList<Vector> harc = new ArrayList<Vector>();
		Vector mid = Vector.mid(start, end);
		mid.normalize();
		mid.mult(radius);
		larc = auxCreateArc(start, mid, detail - 1);
		harc = auxCreateArc(mid, end, detail - 1);
		if (larc != null)
			arc.addAll(larc);
		arc.add(mid);
		if (harc != null)
			arc.addAll(harc);

		return arc;
	}

	/**
	 * 
	 * @param radius
	 * @param lon
	 * @param lat
	 * @return
	 */
	public static double projX(final double radius, final double lon, final double lat) {
		return -radius * Math.cos(lat) * Math.cos(lon);
	}

	/**
	 * 
	 * @param radius
	 * @param lat
	 * @return
	 */
	public static double projY(final double radius, final double lat) {
		return -radius * Math.sin(lat);
	}

	/**
	 * 
	 * @param radius
	 * @param lon
	 * @param lat
	 * @return
	 */
	public static double projZ(final double radius, final double lon, final double lat) {
		return radius * Math.cos(lat) * Math.sin(lon);
	}
}
