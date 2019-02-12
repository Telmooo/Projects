package main;

import main.path.finding.PathFinding;
import main.sorting.ArrayVisualization;
import processing.core.PApplet;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 01-02-2019
 */

public class Main extends PApplet {

	/**
	 * Size of the window
	 */
	public static int WIDTH = 800;
	public static int HEIGHT = 800;

	/**
	 * 
	 */
	static boolean shiftPressed = false;
	static boolean ctrlPressed = false;
	static boolean mouseDragging = false;

	/**
	 * 
	 */
	static boolean sortingVisualization = true;
	ArrayVisualization av;

	static boolean pathFindingVisualization = false;
	PathFinding pf;

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
		size(WIDTH, HEIGHT, FX2D);
	}

	/**
	 * 
	 */
	public void setup() {
		av = new ArrayVisualization(this);
		pf = new PathFinding(15, this);
		surface.setTitle("Beta Version");
		// surface.setResizable(true);
	}

	/**
	 * 
	 */
	public void draw() {
		if (sortingVisualization) {
			av.display();
			av.showInfo();
		} else if (pathFindingVisualization) {
			pf.displayGrid();
		}
	}

	/**
	 * 
	 */
	public void mousePressed() {
		if (sortingVisualization) {
			// block any new thread while it's shuffling or sorting
			if (av.running())
				return;

			new Thread() {
				@Override
				public void run() {
					av.shuffle();
				};
			}.start();
		} else if (pathFindingVisualization) {

			if (shiftPressed) {
				switch (mouseButton) {
				case RIGHT:
					pf.destroyStart(mouseX, mouseY);
					break;
				case LEFT:
					pf.createStart(mouseX, mouseY);
					break;
				}
			} else if (ctrlPressed) {
				switch (mouseButton) {
				case RIGHT:
					pf.destroyGoal(mouseX, mouseY);
					break;
				case LEFT:
					pf.createGoal(mouseX, mouseY);
					break;
				}
			} else {
				switch (mouseButton) {
				case RIGHT:
					pf.destroyWall(mouseX, mouseY);
					break;
				case LEFT:
					pf.createWall(mouseX, mouseY);
					break;
				}
				mouseDragging = true;
			}
		}
	}

	/**
	 * 
	 */
	public void mouseDragged() {
		if (mouseDragging) {
			switch (mouseButton) {
			case RIGHT:
				pf.destroyWall(mouseX, mouseY);
				break;
			case LEFT:
				pf.createWall(mouseX, mouseY);
				break;
			}
		}
	}

	/**
	 * 
	 */
	public void mouseReleased() {
		if (pathFindingVisualization)
			mouseDragging = false;
	}

	/**
	 * 
	 */
	public void keyTyped() {
		if (!av.running() && !pf.running()) {
			switch (key) {
			case 'm':
			case 'M':
				sortingVisualization ^= true;
				pathFindingVisualization ^= true;
				return;
			case ',':
				if (WIDTH < 1800) {
					WIDTH += 10;
					surface.setSize(WIDTH, HEIGHT);
				}
				break;
			case '.':
				if (HEIGHT < 1000) {
					HEIGHT += 10;
					surface.setSize(WIDTH, HEIGHT);
				}
				break;
			case '-':
				if (WIDTH > 200) {
					WIDTH -= 10;
					surface.setSize(WIDTH, HEIGHT);
				}
				break;
			case 'º':
				if (HEIGHT > 200) {
					HEIGHT -= 10;
					surface.setSize(WIDTH, HEIGHT);
					break;
				}
			}
		}

		if (sortingVisualization) {
			switch (key) {
			case 'z':
			case 'Z':
				av.setVisualization(0);
				break;
			case 'x':
			case 'X':
				av.setVisualization(1);
				break;
			case 'c':
			case 'C':
				av.setVisualization(2);
				break;
			case 'v':
			case 'V':
				av.setVisualization(3);
				break;
			case 'b':
			case 'B':
				av.setVisualization(4);
				break;
			case 'n':
			case 'N':
				av.setVisualization(5);
				break;
			case '*':
				av.speed(0.1);
				break;
			case '/':
				av.speed(-0.1);
				break;
			}

			if (av.running())
				return;

			switch (key) {
			case '0':
				av.runSort(0);
				break;
			case '1':
				av.runSort(1);
				break;
			case '2':
				av.runSort(2);
				break;
			case '3':
				av.runSort(3);
				break;
			case '4':
				av.runSort(4);
				break;
			case '5':
				av.runSort(5);
				break;
			case '6':
				av.runSort(6);
				break;
			case '7':
				av.runSort(7);
				break;
			case '8':
				av.runSort(8);
				break;
			case '9':
				av.runSort(9);
				break;
			case 'q':
			case 'Q':
				av.runSort(10);
				break;
			case 'w':
			case 'W':
				av.runSort(11);
				break;
			case 'e':
			case 'E':
				av.runSort(12);
				break;
			case 'r':
			case 'R':
				av.runSort(13);
				break;
			case 't':
			case 'T':
				av.runSort(14);
				break;
			case 'y':
			case 'Y':
				av.runSort(15);
				break;
			case 'u':
			case 'U':
				av.runSort(16);
				break;
			case 'i':
			case 'I':
				av.runSort(17);
				break;
			case 'o':
			case 'O':
				av.runSort(18);
				break;
			case 'p':
			case 'P':
				av.runSort(19);
				break;
			case 'a':
			case 'A':
				av.runSort(20);
				break;
			case 's':
			case 'S':
				av.runSort(21);
				break;
			case 'd':
			case 'D':
				av.runSort(22);
				break;
			case 'f':
			case 'F':
				av.runSort(23);
				break;
			case 'g':
			case 'G':
				av.runSort(24);
				break;

			case '<':
				av.runSort(-1);
				break;

			// experimental
			case '+':
				av.setNewArray(av.getArrayLength() + 10);
				break;
			case '«':
				if (av.getArrayLength() > 10)
					av.setNewArray(av.getArrayLength() - 10);
				break;
			}
		} else if (pathFindingVisualization) {
			if (pf.running())
				return;

			switch (key) {
			case 'r':
			case 'R':
				pf.runPathFinder(0);
				break;
			case 'c':
			case 'C':
				pf.reset();
				break;
			case '+':
				pf.setNewScale(pf.scale - 5);
				break;
			case '«':
				pf.setNewScale(pf.scale + 5);
				break;
			case '*':
				av.speed(0.1);
				break;
			case '/':
				av.speed(-0.1);
				break;
			}
		}
	}

	/**
	 * 
	 */
	public void keyPressed() {
		if (pathFindingVisualization) {
			if (key == CODED) {
				switch (keyCode) {
				case CONTROL:
					ctrlPressed = true;
					break;
				case SHIFT:
					shiftPressed = true;
					break;
				}
			}
		}
	}

	/**
	 * 
	 */
	public void keyReleased() {
		if (pathFindingVisualization) {
			if (key == CODED) {
				switch (keyCode) {
				case CONTROL:
					ctrlPressed = false;
					break;
				case SHIFT:
					shiftPressed = false;
					break;
				}
			}
		}
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
	 * @param time
	 * @return
	 */
	public static String formatTime(double time) {
		if (time < 0)
			return "OVERFLOW";
		if (time == 0)
			return time + "s";
		if (time < 1e-12)
			return String.format("%.2fys", time * 1e15);
		if (time < 1e-9)
			return String.format("%.2fzs", time * 1e12);
		if (time < 1e-6)
			return String.format("%.2fas", time * 1e9);
		if (time < 1e-3)
			return String.format("%.2ffs", time * 1e6);
		if (time < 1e0)
			return String.format("%.2ps", time * 1e3);
		if (time < 1e3)
			return String.format("%.2fns", time * 1e0);
		if (time < 1e6)
			return String.format("%.2fμs", time * 1e-3);
		if (time < 1e9)
			return String.format("%.2fms", time * 1e-6);
		if (time < 1e12)
			return String.format("%.2fs", time * 1e-9);
		if (time < 1e15)
			return String.format("%.2fKs", time * 1e-12);
		if (time < 1e18)
			return String.format("%.2fMs", time * 1e-15);
		if (time < 1e21)
			return String.format("%.2fGs", time * 1e-18);
		if (time < 1e24)
			return String.format("%.2fTs", time * 1e-21);
		if (time < 1e27)
			return String.format("%.2fPs", time * 1e-24);
		if (time < 1e30)
			return String.format("%.2fEs", time * 1e-27);
		if (time < 1e33)
			return String.format("%.2fZs", time * 1e-30);
		if (time < 1e36)
			return String.format("%.2fYs", time * 1e-33);

		return String.format("%.2fXs", time * 1e-36);
	}
}
