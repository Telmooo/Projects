package main.utils;

/**
 * 
 * @author telmo
 * @version 1.0
 * @since 15-02-2019
 */
public class Vector {

	/**
	 * 
	 */
	public double x;
	public double y;
	public double z;

	/**
	 * Constructor for 2D Vector
	 * 
	 * @param x coordinate in space
	 * @param y coordinate in space
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor for 3D Vector
	 * 
	 * @param x coordinate in space
	 * @param y coordinate in space
	 * @param z coordinate in space
	 */
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Create a copy of a vector
	 * 
	 */
	public Vector clone() {
		return new Vector(x, y, z);
	}

	/**
	 * Changes the values of this vector with values of a given vector
	 * 
	 * @param v Vector holding the new values
	 */
	public Vector set(Vector v) {
		x = v.x;
		y = v.y;
		z = v.z;
		return this;
	}

	/**
	 * Changes the values of this 2D vector with values of given coordinates
	 * 
	 * @param x coordinate in space
	 * @param y coordinate in space
	 */
	public Vector set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	/**
	 * Changes the values of this 3D vector with values of given coordinates
	 * 
	 * @param x coordinate in space
	 * @param y coordinate in space
	 * @param z coordinate in space
	 */
	public Vector set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	/**
	 * Creates a new unit Vector from a given angle
	 * 
	 * @param angle
	 * @return new vector using polar coordinates
	 */
	public static Vector fromAngle(double angle) {
		return new Vector(Math.cos(angle), Math.sin(angle));
	}

	/**
	 * Generates a random 2D Vector with coordinates between -1 and 1
	 * 
	 * @return new random 2D Vector
	 */
	public static Vector random2D() {
		return new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1);
	}

	/**
	 * Generates a random 3D Vector with coordinates between -1 and 1
	 * 
	 * @return new random 3D Vector
	 */
	public static Vector random3D() {
		return new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @param u
	 * @return
	 */
	public static Vector add(Vector v, Vector u) {
		return new Vector(v.x + u.x, v.y + u.y, v.z + u.z);
	}

	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public Vector add(double scalar) {
		x += scalar;
		y += scalar;
		z += scalar;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @param u
	 * @return
	 */
	public static Vector sub(Vector v, Vector u) {
		return new Vector(v.x - u.x, v.y - u.y, v.z - u.z);
	}

	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public Vector sub(double scalar) {
		x -= scalar;
		y -= scalar;
		z -= scalar;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector sub(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector sub(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector mult(Vector v) {
		x *= v.x;
		y *= v.y;
		z *= v.z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @param u
	 * @return
	 */
	public static Vector mult(Vector v, Vector u) {
		return new Vector(v.x * u.x, v.y * u.y, v.z * u.z);
	}

	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public Vector mult(double scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector mult(double x, double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector mult(double x, double y, double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	/**
	 * @param v
	 * @return
	 */
	public Vector div(Vector v) {
		x /= v.x;
		y /= v.y;
		z /= v.z;
		return this;
	}

	/**
	 * 
	 * @param v
	 * @param u
	 * @return
	 */
	public static Vector div(Vector v, Vector u) {
		if (u.x == 0 || u.y == 0 || u.z == 0)
			return new Vector(0, 0, 0);

		return new Vector(v.x / u.x, v.y / u.y, v.z / u.z);
	}

	/**
	 * 
	 * @param scalar
	 * @return
	 */
	public Vector div(double scalar) {
		x /= scalar;
		y /= scalar;
		z /= scalar;
		return this;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector div(double x, double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Vector div(double x, double y, double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public Vector mid(Vector b) {
		return new Vector((x + b.x) / 2d, (y + b.y) / 2d, (z + b.z) / 2d);
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Vector mid(Vector a, Vector b) {
		return new Vector((a.x + b.x) / 2d, (a.y + b.y) / 2d, (a.z + b.z) / 2d);
	}

	/**
	 * 
	 * @return
	 */
	public double norm() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * 
	 * @return
	 */
	public double normSquared() {
		return x * x + y * y + z * z;
	}

	public static double norm(Vector v) {
		return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
	}

	/**
	 * 
	 * @return
	 */
	public Vector normalize() {
		double norm = norm();
		if (norm != 0 && norm != 1)
			div(norm);
		return this;
	}

	/**
	 * 
	 * @param magnitude
	 * @return
	 */
	public Vector setMagnitude(double magnitude) {
		normalize();
		mult(magnitude);
		return this;
	}

	/**
	 * 
	 * @param limit
	 * @return
	 */
	public Vector limit(double limit) {
		if (normSquared() > limit * limit) {
			normalize();
			mult(limit);
		}
		return this;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public double distance(Vector v) {
		double dx = x - v.x;
		double dy = y - v.y;
		double dz = z - v.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double distance(double x, double y) {
		double dx = this.x - x;
		double dy = this.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public double distance(double x, double y, double z) {
		double dx = this.x - x;
		double dy = this.y - y;
		double dz = this.z - z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public double dot(Vector v) {
		return x * v.x + y * v.y + z * v.z;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Vector cross(Vector v) {
		double crossX = y * v.z - z * v.y;
		double crossY = z * v.x - x * v.z;
		double crossZ = x * v.y - y * v.x;
		return new Vector(crossX, crossY, crossZ);
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public double angleBetween(Vector v) {

		if (x == 0 && y == 0 && z == 0)
			return 0;
		if (v.x == 0 && v.y == 0 && v.z == 0)
			return 0;

		return Math.acos(dot(v) / (norm() * norm(v)));
	}

	/**
	 * 
	 * @return
	 */
	public double heading() {
		return Math.atan2(y, x);
	}

	/**
	 * 
	 * @param theta
	 * @return
	 */
	public Vector rotate(float theta) {
		double aux = x;
		x = x * Math.cos(theta) - y * Math.sin(theta);
		y = aux * Math.sin(theta) + y * Math.cos(theta);
		return this;
	}

	/**
	 * 
	 * @param theta
	 * @return
	 */
	public Vector rotateX(float theta) {
		double aux = y;
		y = y * Math.cos(theta) - z * Math.sin(theta);
		z = aux * Math.sin(theta) + z * Math.cos(theta);
		return this;
	}

	/**
	 * 
	 * @param theta
	 * @return
	 */
	public Vector rotateY(float theta) {
		double aux = -x;
		x = x * Math.cos(theta) + z * Math.sin(theta);
		z = aux * Math.sin(theta) + z * Math.cos(theta);
		return this;
	}

	/**
	 * 
	 * @param theta
	 * @return
	 */
	public Vector rotateZ(float theta) {
		double aux = x;
		x = x * Math.cos(theta) - y * Math.sin(theta);
		y = aux * Math.sin(theta) + y * Math.cos(theta);
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public double[] toArray() {
		return new double[] { x, y, z };
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector v = (Vector) obj;
		return x == v.x && y == v.y && z == v.z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

}
