package main.sorting;

import static main.sorting.utils.ArrayUtils.swapnadd;

import main.Main;
import main.sorting.algorithms.*;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */
public class ArrayVisualization extends Main {

	/**
	 * 
	 */
	PApplet p;

	/**
	 * Text font
	 */
	static PFont font;

	/**
	 * Array Object {@link main.sorting.Array} containing the data to be sorted. The
	 * data consists of positive integers, changing the type of data or the range of
	 * values of the data will break some algorithms, as they require the data to
	 * sort to be positive integers.
	 */
	static Array array = new Array(800);

	/**
	 * Graphics controllers
	 */
	static boolean rainbowSpectrum = true;
	static boolean classicView = true;
	static boolean circleView = false;
	static boolean dotView = false;
	static boolean linkedDotView = false;
	static boolean pyramidView = false;
	static boolean invertedView = false;
	static boolean hoopsView = false;
	static boolean disparityView = false;

	static boolean shuffleAnimation = true;

	/**
	 * 
	 */
	static Thread sortThread;

	/**
	 * 
	 */
	static boolean running = false;
	static boolean shuffling = false;
	static double startTime = 0;
	static double sleepTime = 0;
	static double controlTime = 0;
	static double stopTime = 0;
	static double displayTime = 0;

	static double sleepRatio = 1.0;

	/**
	 * 
	 */
	static double percSorted = 1.0;
	static int sortErrors = 0;

	/**
	 * 
	 */
	static String heading = "";

	/**
	 * Sorting algorithms implemented
	 */
	static final String[] COMPARISON_SORTS = { "Bubble", "Insertion", "Selection", "Comb", "Cocktail Shaker", "Gnome",
			"Quick", "Merge", "Bogo" };
	static final String[] DISTRIBUTIVE_SORTS = { "Gravity", "Counting" };

	/**
	 * Constructor.
	 * 
	 * @param p
	 */
	public ArrayVisualization(final PApplet p) {
		this.p = p;
		font = p.createFont("OleoScript-Bold.ttf", 20);
	}

	/**
	 * 
	 * @param millis
	 */
	public static void sleep(double millis) {

		// invalid time
		if (millis <= 0)
			return;

		// time spent sleeping
		long elapsedTime = System.nanoTime();

		double sleepAmount = millis * (1e3 / array.length);
		sleepAmount *= 1 / sleepRatio;
		controlTime += sleepAmount;

		/*
		 * there's no point in delaying thread if the time to sleep is less than 1
		 * (decimal places don't matter)
		 */
		if (controlTime <= 1.0)
			return;

		try {
			Thread.sleep((long) controlTime);
			elapsedTime = System.nanoTime() - elapsedTime;
			if (running)
				sleepTime += elapsedTime;
			controlTime -= (double) elapsedTime * 1e-6;

		} catch (Exception e) {
		}
	}

	/**
	 * 
	 */
	public void display() {

		sortErrors = 0;
		float hue; // to generate gradient rainbow colours
		double circamt = array.length / 2.0;

		double error;

		float prevX = 0;
		float prevY = 0;

		p.background(34); // background colour

		// values for scaling the elements
		float sclX = (float) Main.WIDTH / (float) array.length;
		float sclY = (float) Main.HEIGHT / (float) array.length;

		// linear visualisation
		if (classicView) {
			p.pushStyle();
			for (int i = 0; i < array.length; i++) {
				if (i > 0 && array.array[i] < array.array[i - 1])
					sortErrors++;

				if (rainbowSpectrum) {
					hue = map(array.array[i], 1, array.length, 0, 255);
					p.colorMode(HSB);
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.fill(0);
						p.stroke(0);
					} else {
						p.fill(hue, 255, 255);
						if (dotView)
							p.stroke(hue, 255, 255);
						else if (sclX <= 1.0 || sclX % 1.0 != 0)
							p.noStroke();
						else
							p.stroke(0);
					}
				} else {
					p.colorMode(RGB);
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.fill(255, 0, 0);
						p.stroke(255, 0, 0);
					} else {
						p.fill(255);
						if (dotView)
							p.stroke(0, 255, 0);
						else if (sclX <= 1.0)
							p.stroke(255);
						else if (sclX % 1.0 == 0)
							p.stroke(0);
						else
							p.noStroke();
					}
				}

				if (dotView) {
					if (linkedDotView) {
						p.strokeWeight(1);
						if (i == 0) {
							p.point(i * sclX, Main.HEIGHT - array.array[i] * sclY);
							prevX = i * sclX;
							prevY = Main.HEIGHT - array.array[i] * sclY;
						} else {
							// crazy visualisation
							p.line(prevX, prevY, Main.HEIGHT - array.array[i] * sclY,
									Main.HEIGHT - array.array[i] * sclY);
							prevX = i * sclX;
							prevY = Main.HEIGHT - array.array[i] * sclY;
						}
					} else {
						p.strokeWeight(3);
						p.point(i * sclX, Main.HEIGHT - array.array[i] * sclY);
					}
				} else {
					p.rect(i * sclX, Main.HEIGHT - array.array[i] * sclY, sclX, Main.HEIGHT);
				}
			}
			p.popStyle();

		}
		// circle visualisation
		else if (circleView) {

			double radiusX = Main.WIDTH / 2.0 - 64.0;
			double radiusY = Main.HEIGHT / 2.0 - 128.0;
			double sinval;
			double cosval;

			p.push();

			p.translate(Main.WIDTH / 2.0f, Main.HEIGHT / 2.0f);
			p.ellipseMode(RADIUS);
			for (int i = 0; i < array.length; i++) {
				if (i > 0 && array.array[i] < array.array[i - 1])
					sortErrors++;

				// polar coordinates
				sinval = Math.sin(i * PI / circamt);
				cosval = Math.cos(i * PI / circamt);

				if (rainbowSpectrum) {
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.stroke(0);
						p.fill(0);
					} else {
						hue = map(array.array[i], 1, array.length, 0, 255);
						p.colorMode(HSB);
						p.stroke(hue, 255, 255);
						p.fill(hue, 255, 255);
					}
				} else {
					p.colorMode(RGB);
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.fill(255, 0, 0);
						p.stroke(255, 0, 0);
					} else {
						p.stroke(255);
					}
				}
				if (dotView) {
					if (disparityView) {
						error = 1.0 - Math.abs(14.0 * (i + 1 - array.array[i])) / (19.0 * array.length);
						if (linkedDotView) {
							p.strokeWeight(1);
							if (i == 0) {
								p.point((float) (cosval * radiusX * error), (float) (sinval * radiusY * error));
								prevX = (float) (cosval * radiusX * error);
								prevY = (float) (sinval * radiusY * error);
							} else {
								p.line(prevX, prevY, (float) (cosval * radiusX * error),
										(float) (sinval * radiusY * error));
								prevX = (float) (cosval * radiusX * error);
								prevY = (float) (sinval * radiusY * error);
							}
						} else {
							p.strokeWeight(3);
							p.point((float) (cosval * radiusX * error), (float) (sinval * radiusY * error));
						}
					} else {
						if (linkedDotView) {
							p.strokeWeight(1);
							if (i == 0) {
								p.point((float) (cosval * radiusX), (float) (sinval * radiusY));
								prevX = (float) (cosval * radiusX);
								prevY = (float) (sinval * radiusY);
							} else {
								p.line(prevX, prevY, (float) (cosval * radiusX), (float) (sinval * radiusY));
								prevX = (float) (cosval * radiusX);
								prevY = (float) (sinval * radiusY);
							}
						} else {
							p.strokeWeight(2.5f);
							p.point((float) (cosval * radiusX), (float) (sinval * radiusY));
						}
					}
				} else {
					if (disparityView) {
						error = 1.0 - (5.0 * Math.abs(i + 1 - array.array[i])) / (6.0 * array.length);

						// p.line(0, 0, (float) (cosval * radiusX * error), (float) (sinval * radiusY *
						// error));

					} else {
						if (i == 0) {
//							p.triangle(0, 0, (float) (Math.cos((i - 1) * PI / circamt) * radiusX),
//									(float) (Math.sin((i - 1) * PI / circamt) * radiusY), (float) (cosval * radiusX),
//									(float) (sinval * radiusY));
							p.arc(0, 0, (float) radiusX, (float) radiusY, (float) ((i - 1) * PI / circamt),
									(float) (i * PI / circamt));
							prevX = (float) (cosval * radiusX);
							prevY = (float) (sinval * radiusY);
						} else {
							// p.triangle(0, 0, prevX, prevY, (float) (cosval * radiusX), (float) (sinval *
							// radiusY));
							p.arc(0, 0, (float) radiusX, (float) radiusY, (float) ((i - 1) * PI / circamt),
									(float) (i * PI / circamt));
							prevX = (float) (cosval * radiusX);
							prevY = (float) (sinval * radiusY);
						}
						// p.line(0, 0, (float) (cosval * radiusX), (float) (sinval * radiusY));
					}
				}
			}
			p.pop();

		} else if (hoopsView) {

			double maxRadiusX = Main.WIDTH / 2.0 - 64.0;
			double maxRadiusY = Main.HEIGHT / 2.0 - 128.0;
			float radiusX, radiusY;

			p.push();
			p.noFill();
			p.translate(Main.WIDTH / 2, Main.HEIGHT / 2);
			p.ellipseMode(RADIUS);
			for (int i = 0; i < array.length; i++) {
				if (i > 0 && array.array[i] < array.array[i - 1])
					sortErrors++;

				radiusX = map(i, 0, array.length, 0, (float) maxRadiusX);
				radiusY = map(i, 0, array.length, 0, (float) maxRadiusY);

				if (rainbowSpectrum) {
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.stroke(0);
					} else {
						hue = map(array.array[i], 1, array.length, 0, 255);
						p.colorMode(HSB);
						p.stroke(hue, 255, 255);
					}
				} else {
					if (array.highlighted.contains(i)) {
						p.strokeWeight(3);
						p.stroke(255, 0, 0);
					} else {
						p.colorMode(RGB);
						p.stroke(255);
					}
				}

				p.ellipse(0, 0, radiusX, radiusY);

			}
			p.pop();
		}
		percSorted = 1.0 - sortErrors / (float) array.length;

		displayTime = (running) ? (System.nanoTime() - startTime - sleepTime) : (stopTime - startTime - sleepTime);
	}

	/**
	 * 
	 */
	public void shuffle() {
		shuffling = true;
		heading = "Shuffling...";
		array.reset();
		for (int i = 0; i < array.length; i++) {
			swapnadd(array, i, (int) (Math.random() * array.length));
			if (shuffleAnimation)
				sleep(1);
		}
		shuffling = false;
		heading = "";
	}

	/**
	 * 
	 */
	public void showInfo() {
		p.push();
		p.textFont(font);
		p.textSize(20);
		p.fill(255);
		p.stroke(255);
		p.textAlign(LEFT);
		p.text(heading, 10, 20);
		p.text(String.format("Array of %s integers", formatNum(array.length)), 10, 40);
		p.text(String.format(formatNum(array.arrayAccesses) + " Array Access%s",
				(array.arrayAccesses == 1) ? "" : "es"), 10, 60);
		p.text(String.format(formatNum(array.writes) + " Write%s", (array.writes == 1) ? "" : "s"), 10, 80);
		p.text(String.format(formatNum(array.comparisons) + " Comparison%s", (array.comparisons == 1) ? "" : "s"), 10,
				100);
		p.text(String.format("%.1f%% Sorted (%d left)", percSorted * 1e2, sortErrors), 10, 120);
		p.text("Time spent: " + formatTime(displayTime), 10, 140);
		p.text("Sleep time: " + formatTime(sleepTime), 10, 160);
		p.pop();
	}

	/**
	 * 
	 * @param key
	 */
	public void runSort(int key) {
		Sorting sort;
		switch (key) {

		case 0:
			sort = new BitonicSort();
			break;
		case 1:
			sort = new BogoSort();
			break;
		case 2:
			sort = new BubbleSort();
			break;
		case 3:
			sort = new CocktailShakerSort();
			break;
		case 4:
			sort = new CombSort();
			break;
		case 5:
			sort = new CountingSort();
			break;
		case 6:
			sort = new CycleSort();
			break;
		case 7:
			sort = new FlashSort();
			break;
		case 8:
			sort = new GnomeSort();
			break;
		case 9:
			sort = new GravitySort();
			break;
		case 10:
			sort = new HeapSort();
			break;
		case 11:
			sort = new InsertionSort();
			break;
		case 12:
			sort = new MergeSort();
			break;
		case 13:
			sort = new PancakeSort();
			break;
		case 14:
			sort = new QuickSort();
			break;
		case 15:
			sort = new SelectionSort();
			break;
		case 16:
			sort = new ShellSort();
			break;
		case 17:
			sort = new RadixLSDSort(10);
			break;
		case 18:
			sort = new DoubleSelectionSort();
			break;
		case 19:
			sort = new StoogeSort();
			break;
		case 20:
			sort = new SleepSort();
			break;
		case 21:
			sort = new SlowSort();
			break;
		case 22:
			sort = new PigeonholeSort();
			break;
		case 23:
			sort = new CircleSort();
			break;
		case 24:
			sort = new InPlaceRadixLSD(10);
			break;

		default:
			sort = new InPlaceRadixLSD(10);
			break;
		}
		heading = sort.name();

		sortThread = new Thread() {
			@Override
			public void run() {
				sleepTime = 0;
				startTime = System.nanoTime();
				running = true;
				sort.sort(array);
				running = false;
				stopTime = System.nanoTime();
				array.clearHighlighted();
			};
		};
		sortThread.start();
	}

	/**
	 * 
	 * @return
	 */
	public boolean running() {
		return running || shuffling;
	}

	/**
	 * 
	 * @param n
	 */
	public void setVisualization(int n) {

		switch (n) {

		case 0:
			if (classicView) {
				classicView = false;
				circleView = true;
			} else if (circleView) {
				circleView = false;
				hoopsView = true;
			} else {
				hoopsView = false;
				classicView = true;
			}
			break;
		case 1:
			dotView ^= true;
			break;
		case 2:
			linkedDotView ^= true;
			break;
		case 3:
			disparityView ^= true;
			break;
		case 4:
			shuffleAnimation ^= true;
			break;
		case 5:
			rainbowSpectrum ^= true;
			break;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getArrayLength() {
		return array.length;
	}

	/**
	 * 
	 * @param size
	 */
	public void setNewArray(int size) {
		array = new Array(size);
	}

	/**
	 * 
	 * @param amount
	 */
	public void speed(double amount) {
		sleepRatio += amount;
		if (sleepRatio <= 0)
			sleepRatio = 0.1;
		System.out.println(sleepRatio);
	}
}
