package main.utils;

import static main.Main.radius;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 15-02-2019
 */
public class Coordinate {

	public String name;

	/**
	 * 
	 */
	public double lon;

	/**
	 * 
	 */
	public double lat;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(String name, double lon, double lat) {
		this.name = name;
		this.lon = lon;
		this.lat = lat;
	}

	/**
	 * 
	 */
	public Coordinate clone() {
		return new Coordinate(this.name, this.lon, this.lat);
	}

	/**
	 * Haversine function
	 * 
	 * @param angle
	 * @return
	 */
	public static double hav(double angle) {
		return (1 - Math.cos(angle)) / 2d;
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	public double distance(Coordinate b) {
		double dlon = Math.abs(lon - b.lon);
		double dlat = Math.abs(lat - b.lat);
		double centerAngle = 2d * Math.asin(Math.sqrt(hav(dlat) + Math.cos(lat) * Math.cos(b.lat) * hav(dlon)));
		return radius * centerAngle;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double distance(Coordinate a, Coordinate b) {
		double dlon = Math.abs(a.lon - b.lon);
		double dlat = Math.abs(a.lat - b.lat);
		return radius * 2d * Math.asin(Math.sqrt(hav(dlat) + Math.cos(a.lat) * Math.cos(b.lat) * hav(dlon)));
	}

	@Override
	public String toString() {
		return name + " (lat, lon): (" + lat + ", " + lon + ")";
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
		if (lon != other.lon || lat != other.lat)
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
		result = (int) (prime * result + lon);
		result = (int) (prime * result + lat);
		return result;
	}
}
