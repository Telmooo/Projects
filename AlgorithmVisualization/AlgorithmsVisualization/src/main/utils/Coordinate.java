package main.utils;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 09-02-2019
 */
public class Coordinate {

	/**
	 * 
	 */
	public int x;

	/**
	 * 
	 */
	public int y;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 */
	public Coordinate clone() {
		return new Coordinate(this.x, this.y);
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	public double distance(Coordinate b) {
		double dx = x - b.x;
		double dy = y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double distance(Coordinate a, Coordinate b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public String toString() {
		return "Coordinate (x, y): (" + x + ", " + y + ")";
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || !(obj instanceof Coordinate))
			return false;

		Coordinate other = (Coordinate) obj;
		if (x != other.x || y != other.y)
			return false;

		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
}
