package main;

import static main.utils.Algorithm.finder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import main.utils.Coordinate;
import main.utils.Vector;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

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
	PeasyCam camera;

	/**
	 * 
	 */
	public static ArrayList<Coordinate> currentPath;
	public static ArrayList<Coordinate> bestPath;

	/**
	 * 
	 */
	public static Coordinate center = new Coordinate("Center", 0, 0);

	/**
	 * 
	 */
	private static double sleepRatio = 1.0;
	private static double controlTime = 0;
	private static boolean running = false;

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
	static ArrayList<String[]> locations;
	static ArrayList<Coordinate> points;

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

		bestPath = new ArrayList<Coordinate>();
		currentPath = new ArrayList<Coordinate>();

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
	}

	/**
	 * 
	 */
	public void draw() {
		background(0);
		translate(WIDTH / 2, HEIGHT / 2);
		rotation += 0.01;

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

		if (bestPath.size() > 1) {
			push();
			noFill();
			strokeWeight(3);
			Vector prev = null;
			for (int i = 1; i < bestPath.size(); i++) {
				if (bestPath.get(i) == null)
					break;
				if (prev == null) {
					double prevX = projX(radius, bestPath.get(i - 1).lon, bestPath.get(i - 1).lat);
					double prevY = projY(radius, bestPath.get(i - 1).lat);
					double prevZ = projZ(radius, bestPath.get(i - 1).lon, bestPath.get(i - 1).lat);
					prev = new Vector(prevX, prevY, prevZ);
				}
				double x = projX(radius, bestPath.get(i).lon, bestPath.get(i).lat);
				double y = projY(radius, bestPath.get(i).lat);
				double z = projZ(radius, bestPath.get(i).lon, bestPath.get(i).lat);
				Vector pos = new Vector(x, y, z);
				ArrayList<Vector> arc = createArc(prev, pos, 7);
				beginShape();
				for (int p = 0; p < arc.size(); p++) {
					if (p >= arc.size() - 3) {
						stroke(0, 0, 255);
					} else {
						stroke(0, 255, 0);
					}
					curveVertex((float) arc.get(p).x, (float) arc.get(p).y, (float) (float) arc.get(p).z);
				}
				endShape();

				prev.set(x, y, z);
			}
			pop();
		}

		if (currentPath.size() == 2) {
			if (currentPath.get(1) != null && currentPath.get(0) != null) {
				push();
				noFill();
				strokeWeight(3);
				stroke(255, 0, 0);
				double a = projX(radius, currentPath.get(0).lon, currentPath.get(0).lat);
				double b = projY(radius, currentPath.get(0).lat);
				double c = projZ(radius, currentPath.get(0).lon, currentPath.get(0).lat);
				Vector pivot = new Vector(a, b, c);
				double x = projX(radius, currentPath.get(1).lon, currentPath.get(1).lat);
				double y = projY(radius, currentPath.get(1).lat);
				double z = projZ(radius, currentPath.get(1).lon, currentPath.get(1).lat);
				Vector pos = new Vector(x, y, z);
				ArrayList<Vector> arc = createArc(pivot, pos, 7);
				beginShape();
				for (Vector v : arc) {
					curveVertex((float) v.x, (float) v.y, (float) v.z);
				}
				endShape();
				pop();
			}
		}
	}

	/**
	 * 
	 */
	public void keyPressed() {
		if (!running) {
			switch (key) {
			case 'r':
			case 'R':
				new Thread() {
					@Override
					public void run() {
						running = true;
						finder(points);
						running = false;
					};
				}.start();
				break;
			case '+':
				speed(0.1);
				break;
			case '-':
				speed(-0.1);
				break;
			}
		}
	}

	/**
	 * 
	 * @param amount
	 */
	public void speed(double amount) {
		sleepRatio += amount;
		if (sleepRatio <= 0)
			sleepRatio = 0.1;
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

	/**
	 * 
	 * @param millis
	 */
	public static void sleep(double millis) {

		if (millis <= 0)
			return;

		long elapsedTime = System.nanoTime();

		double sleepAmount = millis * points.size() / sleepRatio;
		controlTime += sleepAmount;

		if (controlTime <= 1.0)
			return;

		try {
			Thread.sleep((long) sleepAmount);
			elapsedTime = System.nanoTime() - elapsedTime;
			controlTime -= (double) elapsedTime * 1e-6;
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
