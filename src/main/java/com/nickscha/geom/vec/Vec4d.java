/*
 * Copyright (C) 2017 nickscha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nickscha.geom.vec;

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.quat.Quatd;

/**
 * Vector of 4 element doubles (final::immutable)
 * <p>
 * All methods return a new instance rather then modifying the object it self. A
 * Vector is a quantity which has both magnitude and direction, but no position.
 * </p>
 * <b>Null-Handling</b>
 * <p>
 * Passing null values will cause to throw a NullpointerException. Methods which
 * could throw a NullPointerException are marked in the javadoc.
 * </p>
 * <b>Method naming</b>
 * <table>
 * <tr>
 * <td>of</td>
 * <td>static builder methods. starts the fluent pipeline</td>
 * </tr>
 * <tr>
 * <td>is</td>
 * <td>performs a check, returns boolean and ends the fluent pipeline</td>
 * </tr>
 * <tr>
 * <td>get</td>
 * <td>returns a component of this class and ends the fluent pipeline</td>
 * </tr>
 * <tr>
 * <td>to</td>
 * <td>converts this object in another format and ends the fluent pipeline</td>
 * </tr>
 * <tr>
 * <td>from</td>
 * <td>creates a instance using the specified input. starts the fluent
 * pipeline</td>
 * </tr>
 * <tr>
 * <td>*</td>
 * <td>all other methods which processes the instance and returns it for the
 * fluent usage</td>
 * </tr>
 * </table>
 * <br/>
 * <b>Why immutable ?</b>
 * <p>
 * It is proposed to keep all vector classes immutable to prepare for Javas
 * <b>possible release</b> of <i>value types</i> within project <i>Valhalla</i>.
 * Vectors will be than value type based.
 *
 * </p>
 *
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.2
 */
public final class Vec4d {

	/**
	 * Defines how much fields are stored in this class which will be used for
	 * optimal binary serialization
	 */
	public static final int FIELDS = 4;

	/**
	 * Defines how much bytes will be needed to store this type as binary
	 */
	public static final byte BYTES = 32;

	/**
	 * Represents the X axis vector
	 */
	public static final Vec4d XAXIS = new Vec4d(1.0d, 0.0d, 0.0d, 0.0d);

	/**
	 * Represents the Y axis vector
	 */
	public static final Vec4d YAXIS = new Vec4d(0.0d, 1.0d, 0.0d, 0.0d);

	/**
	 * Represents the Z axis vector
	 */
	public static final Vec4d ZAXIS = new Vec4d(0.0d, 0.0d, 1.0d, 0.0d);

	/**
	 * Represents the W axis vector
	 */
	public static final Vec4d WAXIS = new Vec4d(0.0d, 0.0d, 0.0d, 1.0d);

	/**
	 * Represents the zero vector
	 */
	public static final Vec4d ZERO = new Vec4d(0.0d, 0.0d, 0.0d, 0.0d);

	/**
	 * Represents the NAN vector
	 */
	public static final Vec4d NAN = new Vec4d(Double.NaN, Double.NaN, Double.NaN, Double.NaN);

	/**
	 * A vector with all coordinates set to positive infinity.
	 */
	public static final Vec4d POSITIVE_INFINITY = new Vec4d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	/**
	 * A vector with all coordinates set to negative infinity.
	 */
	public static final Vec4d NEGATIVE_INFINITY = new Vec4d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

	/**
	 * A vector with all coordinates set to the minimal value.
	 */
	public static final Vec4d MIN = new Vec4d(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

	/**
	 * A vector with all coordinates set to the maximal value.
	 */
	public static final Vec4d MAX = new Vec4d(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

	/**
	 * x, y and z component of this vector
	 */
	private final double x, y, z, w;

	/**
	 * Creates a new vector and sets all properties to the specified amount.
	 *
	 * @param amt as the value for x, y and z
	 */
	public Vec4d(double amt) {
		x = y = z = w = amt;
	}

	/**
	 * Creates a new vector for the specified properties
	 *
	 * @param x the x component
	 * @param y the y component
	 * @param z the z component
	 */
	public Vec4d(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Creates an new Vec3d instance where each component has the specified
	 * amount.
	 *
	 * @param amt to apply to all components
	 * @return the new vector
	 */
	public static Vec4d of(double amt) {
		return new Vec4d(amt);
	}

	/**
	 * Creates an new Vec3d instance where each component has the specified
	 * double.
	 *
	 * @param x the x component
	 * @param y the y component
	 * @param z the z component
	 * @return the new vector
	 */
	public static Vec4d of(double x, double y, double z, double w) {
		return new Vec4d(x, y, z, w);
	}

	/**
	 * Calculates the regular length or magnitude of this vector.
	 *
	 * @return the regular magnitude/length
	 * 
	 */
	public double length() {
		return (double) Math.sqrt(lengthSquared());
	}

	/**
	 * Calculates the squared length or magnitude of this vector.
	 *
	 * @return the squared magnitude/length
	 */
	public double lengthSquared() {
		return x * x + y * y + z * z + w * w;
	}

	/**
	 * Calculates the Manhattan length between two vectors for a specified unit.
	 * <p>
	 * The Manhattan length (aka. Taxicab geometry) calculates the distance
	 * between two vectors in a real vector space with fixed cartesian
	 * coordinate systems and is the sum of the lengths of the projections of
	 * the line between the points onto the coordinate axis.
	 * </p>
	 * <p>
	 * In other words the distance between two vectors in the euclidean space
	 * would be a direct line from start point toward the end point while the
	 * Manhattan length is bound to the aligned axis (like x,y or z).
	 * </p>
	 *
	 *
	 * @param r the end point from the current start vector
	 * @param unit the calculation unit length for the fixed axis length.
	 *            Normally 1.0d is set. 0.5f will double the Manhattan length
	 *            result.
	 * @return the distance between both vectors in a fixed cartesian coordinate
	 *         system
	 * @see Vec4d#lengthManhattan(Vec4d) Default Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec4d#lengthEuclidean(Vec4d)
	 */
	public double lengthManhattan(Vec4d r, double unit) {
		return lengthManhattan(r) / unit;
	}

	/**
	 * Calculates the Manhattan length between two vectors.
	 * <p>
	 * The Manhattan length (aka. Taxicab geometry) calculates the distance
	 * between two vectors in a real vector space with fixed cartesian
	 * coordinate systems and is the sum of the lengths of the projections of
	 * the line between the points onto the coordinate axis.
	 * </p>
	 * <p>
	 * In other words the distance between two vectors in the euclidean space
	 * would be a direct line from start point toward the end point while the
	 * Manhattan length is bound to the aligned axis (like x,y or z).
	 * </p>
	 *
	 *
	 * @param r the end point from the current start vector
	 * @return the distance between both vectors in a fixed cartesian coordinate
	 *         system
	 * @see Vec4d#lengthManhattan(Vec4d, double) Passing a unit value to
	 *      Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec4d#lengthEuclidean(Vec4d)
	 */
	public double lengthManhattan(Vec4d r) {
		return Math.abs(x - r.getX()) + Math.abs(y - r.getY()) + Math.abs(z - r.getZ()) + Math.abs(w - r.getW());
	}

	/**
	 * Calculates the Euclidean (ordinalry) distance between this and the other
	 * vector.
	 * <p>
	 * Calculates the euclidean distance in the "ordinary" distance between two
	 * points in the euclidean space. With this distance the euclidean space is
	 * mapped to the matrix space (aka. euclidean norm).
	 * </p>
	 * 
	 * <p>
	 * In other words the distance between two vectors in the Manhattan length
	 * is bound to the aligned axis (like x,y or z) while the euclidean space
	 * would be a direct line from start point toward the end point.
	 * </p>
	 *
	 * @param r the other vector
	 * @return the euclidean distance between both vectors
	 * @see #lengthManhattan(Vec4d)
	 */
	public double lengthEuclidean(Vec4d r) {
		return (double) Math.sqrt((x - r.getX()) * (x - r.getX()) + (y - r.getY()) * (y - r.getY())
				+ (z - r.getZ()) * (z - r.getZ()) + (w - r.getW()) * (w - r.getW()));
	}

	/**
	 * Get the elevation of the vector in z.
	 *
	 * @return the elevation of the vector in z
	 */
	public double delta() {
		return (double) Math.asin(z / length());
	}

	/**
	 * <p>
	 * Get a vector orthogonal to the instance.
	 * </p>
	 * <p>
	 * There are an infinite number of normalized vectors orthogonal to the
	 * instance. This method picks up one of them almost arbitrarily. It is
	 * useful when one needs to compute a reference frame with one of the axes
	 * in a predefined direction. The following example shows how to build a
	 * frame having the k axis aligned with the known vector u :
	 * </p>
	 * <p>
	 * <code>
	 * Vec3d k = u.normalize();<br>
	 * Vec3d i = k.orthogonal();<br>
	 * Vec3d j = Vec3d.crossProduct(k, i);
	 * </code>
	 * </p>
	 *
	 * @return a new normalized vector orthogonal to the instance
	 * @throws ArithmeticException if the normal is zero
	 */
	public Vec4d orthogonal() {
		double threshold = 0.6f * length();
		if (threshold == 0) {
			throw new ArithmeticException("The Normal is zero");
		}
		if (Math.abs(x) <= threshold) {
			double inverse = (double) (1 / Math.sqrt(y * y + z * z));
			return new Vec4d(0, inverse * z, -inverse * y, w);
		} else if (Math.abs(y) <= threshold) {
			double inverse = (double) (1 / Math.sqrt(x * x + z * z));
			return new Vec4d(-inverse * z, 0, inverse * x, w);
		}
		double inverse = (double) (1 / Math.sqrt(x * x + y * y));
		return new Vec4d(inverse * y, -inverse * x, 0, w);
	}

	/**
	 * Get a vector orthogonal to the instance for the specified vector.
	 *
	 * @param r the other vector for the orthogonal instance
	 * @return the new vector orthogonal to the instance
	 * @see Vec4d#orthogonal()
	 * @throws NullPointerException if passed vector is null
	 */
	public Vec4d orthogonal(Vec4d r) {
		Vec4d k = r.normalize();
		Vec4d i = k.orthogonal();
		return k.cross(i);
	}

	/**
	 * <b>Calculates the dot product of this and the specified vector</b>
	 * <p>
	 * This is one of, if not the most important operator(s) in geometry. Its so
	 * important because it gets used for many tasks, such as projecting a
	 * vector along another, or to help in finding the magnitude of a vector. It
	 * works consistently in any number of dimensions (unlike the cross product,
	 * which is only defined in 3d).
	 * </p>
	 * <p>
	 * The dot product of two vectors is equal to the magnitude of each
	 * multiplied together all multiplied by the cosine of the angle between
	 * them
	 * </p>
	 * <p>
	 * So when two <b>unit</b> vectors point exactly the same way, the result of
	 * the dot product is 1 and when they are exactly opposite -1. The dot
	 * product returns 0 for any two vectors (unit or otherwise) at 90o to each
	 * other in some plane.
	 * </p>
	 * <p>
	 * The really useful part comes when we realize that this geometric
	 * description also allows us to tell how much of a vector is in the
	 * direction of another, because when we know that we can calculate this new
	 * projected vector which is useful in all sorts of geometric problems,
	 * rotation being one of the most fundamental.
	 * </p>
	 *
	 * @param other the other for dot product calculation
	 * @return the dot product of this and the other vector
	 */
	public double dot(Vec4d other) {
		return x * other.getX() + y * other.getY() + z * other.getZ() + w * other.getW();
	}

	/**
	 * Returns the minimal value of the vector components
	 *
	 * @return the minimal value of the vector
	 */
	public double min() {
		return Math.min(x, Math.min(y, Math.min(z, w)));
	}

	/**
	 * Returns the minimal vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the minimized vector between this and the specified one
	 */
	public Vec4d min(Vec4d other) {
		return new Vec4d(Math.min(x, other.getX()), Math.min(y, other.getY()), Math.min(z, other.getZ()),
				Math.min(w, other.getW()));
	}

	/**
	 * Returns the max value of the vector components
	 *
	 * @return the max value of the vector
	 */
	public double max() {
		return Math.max(x, Math.max(y, Math.max(z, w)));
	}

	/**
	 * Returns the max vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the maximized vector between this and the specified one
	 */
	public Vec4d max(Vec4d other) {
		return new Vec4d(Math.max(x, other.getX()), Math.max(y, other.getY()), Math.max(z, other.getZ()),
				Math.max(w, other.getW()));
	}

	/**
	 * Ceils all values of the vector.
	 *
	 * @return the ceiled vector
	 */
	public Vec4d ceil() {
		return new Vec4d((double) Math.ceil(x), (double) Math.ceil(y), (double) Math.ceil(z), (double) Math.ceil(w));
	}

	/**
	 * Floors all values of the vector.
	 *
	 * @return the floored vector
	 */
	public Vec4d floor() {
		return new Vec4d((double) Math.floor(x), (double) Math.floor(y), (double) Math.floor(z), (double) Math.floor(w));
	}

	/**
	 * Computes the absolute number of each vector component and returns a new
	 * vector.
	 *
	 * @return the new vector where each component is an absolute number
	 */
	public Vec4d absolute() {
		return new Vec4d(Math.abs(x), Math.abs(y), Math.abs(z), Math.abs(w));
	}

	/**
	 * Negates all components of this vector.
	 *
	 * @return the new negated vector
	 */
	public Vec4d negate() {
		return new Vec4d(-x, -y, -z, -w);
	}

	/**
	 * Clamp all components to the constant c. Just checks: COMPONENT > c. -4
	 * will stay the same if clamped to 3!
	 *
	 * @param c the clamp value to compare
	 * @return the new clamped vector
	 */
	public Vec4d clamp(double c) {
		return new Vec4d((x > c) ? c : x, (y > c) ? c : y, (z > c) ? c : z, (w > c) ? c : w);
	}

	/**
	 * Clamp all components to the ones from the specified vector. Just checks:
	 * COMPONENT > c. -4 will stay the same if clamped to 3!
	 *
	 * @param other the clamp vector to compare
	 * @return the new clamped vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d clamp(Vec4d other) {
		return new Vec4d((x > other.getX()) ? other.getX() : x, (y > other.getY()) ? other.getY() : y,
				(z > other.getZ()) ? other.getZ() : z, (w > other.getW()) ? other.getW() : w);
	}

	/**
	 * Checks if this vector is a unit one where the total length is 1.
	 * <p>
	 * A unit vector is simply a vector whose length (or magnitude) is 1. These
	 * are extremely important in geometry because they allow transformations
	 * which do not alter the scale of the things being transformed. Such a
	 * vector is said to be normalized - i.e. divided by its length:
	 * </p>
	 *
	 * @return weather this vector is a unit length vector
	 */
	public boolean isUnit() {
		return this.length() == 1.0d;
	}

	/**
	 * Checks if this vector bellows to a zero one (all components are zero).
	 *
	 * @return weather this vector is a zero vector
	 */
	public boolean isZero() {
		return x == 0 && y == 0 && z == 0 && w == 0;
	}

	/**
	 * Checks if any component comprises the value NAN.
	 *
	 * @return true if any of the components is NAN.
	 */
	public boolean isNAN() {
		return Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || Double.isNaN(w);
	}

	/**
	 * Checks if any component has an infinite value.
	 *
	 * @return true if any value is infinite
	 */
	public boolean isInfinite() {
		return !isNAN() && (Double.isInfinite(x) || Double.isInfinite(y) || Double.isInfinite(z) || Double.isInfinite(w));
	}

	/**
	 * Whether this vector has similar direction compared to the other vector.
	 * True if the normalized dot product is > 0.
	 * 
	 * @param vector the other vector to check
	 * @return true if this vector has similar direction compared to the other
	 *         vector, otherwise false
	 * @throws NullPointerException if the passed vector is null
	 */
	public boolean isSameDirection(Vec4d vector) {
		return dot(vector) > 0;
	}

	/**
	 * Whether this vector has opposite direction compared to the other vector.
	 * True if the normalized dot product is < 0.
	 * 
	 * @param vector the other vector to check
	 * @return true if this vector has opposite direction compared to the other
	 *         vector, otherwise false
	 * @throws NullPointerException if the passed vector is null
	 */
	public boolean isOppositeDirection(Vec4d vector) {
		return dot(vector) < 0;
	}

	/**
	 * Checks this vector if it is null or its doubles are NaN or infinite,
	 * return false. Else return true.
	 * 
	 * @return true if double is not NAN and not mapped with INFINITE values,
	 *         otherwise false
	 */
	public boolean isValid() {
		if (isNAN() || isInfinite()) {
			return false;
		}
		return true;

	}

	/**
	 * Return the cosine of the angle between this vector and the supplied
	 * vector. Use this instead of Math.cos(this.angle(v)).
	 *
	 * @param v the other vector
	 * @return cosine of the angle between both vectors
	 * @throws NullPointerException if the passed vector is null
	 */
	public double angleCos(Vec4d v) {
		return (double) (dot(v) / (length() * v.length()));
	}

	/**
	 * Return the angle between this vector and the supplied vector.
	 *
	 * @param v
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public double angle(Vec4d v) {
		double cos = angleCos(v);
		cos = Math.min(cos, 1);
		cos = Math.max(cos, -1);
		return (double) Math.acos(cos);
	}

	/**
	 * <b>Normalizes this vector</b>
	 * <p>
	 * It is sometimes useful to express vectors only by their direction and not
	 * by their length. This is necessary for example when we want to find out
	 * if another object, or point is in front of or behind our reference frame,
	 * or when we need to calculate a reflection vector that would occur from a
	 * surface with a "upward facing plane". Vectors of unit length are also
	 * called normals.
	 * </p>
	 * <b>Example for a normalized vector between one(1,1) and two(-1,-1):</b>
	 * 
	 * <pre>
	 *            ^
	 * two(-1,-1) | one(1,1)
	 *          \ | /
	 *           \|/
	 *            0 normalized
	 * </pre>
	 * <p>
	 * In words, the normalized vector is the vector divided by the magnitude of
	 * itself. This results in a vector of unit length.
	 *
	 * We must take care when calculating the normalized vector because zero
	 * length vectors cannot be normalized. Normalizing a zero-length vector
	 * will usually result in a "divide-by-zero" error. Usually we resolve this
	 * by performing the normalization in multiple steps:
	 *
	 * <ul>
	 * <li>Calculate the length squared of the vector (only calculate the
	 * squared length of the vector because we only need the square root if the
	 * squared length is not zero).</li>
	 * <li>If the squared length is greater than zero, then calculate the square
	 * root of that and multiply the vector components by the reciprocal of the
	 * length.</li>
	 * </ul>
	 * </p>
	 * <b>Example Usage</b>
	 * <p>
	 * This example shows you how to calculate the normal vector based on two
	 * "origin" vectors. This technique for example is used when a model from
	 * formats like "obj" are imported and we want to calculate the normal
	 * vectors. <code><br/><br/>
	 * Vec3d vectorOne = ...<br/>
	 * Vec3d vectorTwo = ...<br/>
	 * Vec3d normalVector = vectorOne.cross(vectorTwo).normalize();<br/>
	 * </code>
	 * </p>
	 *
	 * @return the new normalized vector;
	 */
	public Vec4d normalize() {
		final double length = length();
		return (length == 0.0d) ? this : div(length);
	}

	/**
	 * <b>Calculates the cross product between both specified vectors</b>
	 * <p>
	 * A cross product is also an operation on two vectors. The result is a
	 * third vector, which is perpendicular to the first two, and it's length is
	 * an average of the both lengths.
	 * </p>
	 * <p>
	 * Note that for cross product, the order of arguments matters. If you
	 * switch order, the result will be a vector of the same length, but facing
	 * the opposite direction.
	 * </p>
	 *
	 * @param r the other vector
	 * @return the new cross-product result vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d cross(Vec4d r) {
		double x_ = y * r.getZ() - z * r.getY();
		double y_ = z * r.getX() - x * r.getZ();
		double z_ = x * r.getY() - y * r.getX();

		return new Vec4d(x_, y_, z_, w);
	}

	/**
	 * Projects this vector onto another vector. The vector projection of a
	 * vector a on (or onto) a nonzero vector b (also known as the vector
	 * component or vector resolution of a in the direction of b) is the
	 * orthogonal projection of a onto a straight line parallel to b.
	 *
	 * <p>
	 * What is projection? It is the procedure of taking something in n
	 * dimensions and collapsing it down to the dimension below (n-1). So in 3d
	 * rendering we often want to project the 3d world onto the near plane of
	 * the camera. When we do a dot product we are projecting one vector onto
	 * another and the by product is a scalar.
	 * </p>
	 *
	 * @param other the other vector for projection
	 * @return the new vector where this is projected on the other specified one
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d project(Vec4d other) {
		double n = this.dot(other);
		double d = other.lengthSquared();
		return other.normalize().mul(n / d);
	}

	/**
	 * Reflect this vector about the given normal vector.
	 *
	 * @param normal the vector to reflect about
	 * @return the new reflected vector
	 * @throws NullPointerException if the passed normal vector is null
	 */
	public Vec4d reflect(Vec4d normal) {
		double dot = dot(normal);

		double x_ = this.x - 2.0f * dot * normal.x;
		double y_ = this.y - 2.0f * dot * normal.y;
		double z_ = this.z - 2.0f * dot * normal.z;
		double w_ = this.w - 2.0f * dot * normal.w;

		return new Vec4d(x_, y_, z_, w_);
	}

	/**
	 * Compute the half vector between this and the other vector.
	 *
	 * @param other
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d half(Vec4d other) {
		return add(other).normalize();
	}

	/**
	 * Rotates this vector around the specified axis for the given angle amount.
	 * 
	 * @param axis where this vector should be rotated around
	 * @param degree the angle as the power how much should be rotated
	 * @return the new vector rotated along the specified axis with the given
	 *         angle.
	 * @throws NullPointerException if the passed axis vector is null
	 */
	public Vec4d rotate(Vec4d axis, double degree) {
		double sinAngle = (double) Math.sin(-degree);
		double cosAngle = (double) Math.cos(-degree);

		return this.cross(axis.mul(sinAngle)).add( // Rotation X
				(this.mul(cosAngle)).add( // Rotation Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); // Rotation
																		// Y
	}

	/**
	 * Linearly interpolates between this vector and the target vector by alpha
	 * which is in the range [0,1]. The result is stored in a new vector.
	 *
	 * @param dest the target vector
	 * @param amt the interpolation coefficient in the range [0,1]
	 * @return a new vector interpolated through the specified destination with
	 *         the given alpha between zero and one.
	 * @throws NullPointerException if the passed destination vector is null
	 */
	public Vec4d lerp(Vec4d dest, double amt) {
		return dest.sub(this).mul(amt).add(this);
	}

	/**
	 * Transforms this three dimensional vector to its screen space (x,y)
	 * position based on supplied view and projection matrix.
	 * 
	 * @param viewMatrix
	 * @param projectionMatrix
	 * @return the screen space position of this vector
	 */
	// TODO adjust matrix class
	public Vec2d screenSpace(Mat4d viewMatrix, Mat4d projectionMatrix) {
		Vec4d coords = this;
		// Mat4d.transform(viewMatrix, coords, coords);
		// Mat4d.transform(projectionMatrix, coords, coords);
		if (coords.getW() <= 0) {
			return null;
		}
		// no need for conversion below
		return coords.vec2().div(coords.getW());
	}

	/**
	 * Adds the current vector by the specified vector and returns a new one.
	 *
	 * @param other vector amount of the addition
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d add(Vec4d other) {
		return new Vec4d(x + other.getX(), y + other.getY(), z + other.getZ(), w + other.getW());
	}

	/**
	 * Adds the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the addition
	 * @return a new vector
	 */
	public Vec4d add(double amt) {
		return new Vec4d(x + amt, y + amt, z + amt, w + amt);
	}

	/**
	 * Subtracts the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the subtraction
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d sub(Vec4d other) {
		return new Vec4d(x - other.getX(), y - other.getY(), z - other.getZ(), w - other.getW());
	}

	/**
	 * Subtracts the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the subtraction
	 * @return a new vector
	 */
	public Vec4d sub(double amt) {
		return new Vec4d(x - amt, y - amt, z - amt, w - amt);
	}

	/**
	 * Multiplies the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the multiplication
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d mul(Vec4d other) {
		return new Vec4d(x * other.getX(), y * other.getY(), z * other.getZ(), w * other.getW());
	}

	/**
	 * Multiplies the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the multiplication
	 * @return a new vector
	 */
	public Vec4d mul(double amt) {
		return new Vec4d(x * amt, y * amt, z * amt, w * amt);
	}

	/**
	 * Divides the current vector by the specified vector and returns a new one.
	 *
	 * @param other vector amount of the division
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec4d div(Vec4d other) {
		return new Vec4d(x / other.getX(), y / other.getY(), z / other.getZ(), w / other.getW());
	}

	/**
	 * Divides the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the division
	 * @return a new vector
	 */
	public Vec4d div(double amt) {
		return new Vec4d(x / amt, y / amt, z / amt, w / amt);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xxx() {
		return new Vec3d(x, x, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xxy() {
		return new Vec3d(x, x, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xxz() {
		return new Vec3d(x, x, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xxw() {
		return new Vec3d(x, x, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xyx() {
		return new Vec3d(x, y, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xyy() {
		return new Vec3d(x, y, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xyz() {
		return new Vec3d(x, y, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xyw() {
		return new Vec3d(x, y, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xzx() {
		return new Vec3d(x, z, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xzy() {
		return new Vec3d(x, z, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xzz() {
		return new Vec3d(x, z, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xzw() {
		return new Vec3d(x, z, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xwx() {
		return new Vec3d(x, w, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xwy() {
		return new Vec3d(x, w, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xwz() {
		return new Vec3d(x, w, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d xww() {
		return new Vec3d(x, w, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yxx() {
		return new Vec3d(y, x, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yxy() {
		return new Vec3d(y, x, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yxz() {
		return new Vec3d(y, x, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yxw() {
		return new Vec3d(y, x, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yyx() {
		return new Vec3d(y, y, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yyy() {
		return new Vec3d(y, y, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yyz() {
		return new Vec3d(y, y, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yyw() {
		return new Vec3d(y, y, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yzx() {
		return new Vec3d(y, z, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yzy() {
		return new Vec3d(y, z, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yzz() {
		return new Vec3d(y, z, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yzw() {
		return new Vec3d(y, z, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d ywx() {
		return new Vec3d(y, w, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d ywy() {
		return new Vec3d(y, w, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d ywz() {
		return new Vec3d(y, w, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d yww() {
		return new Vec3d(y, w, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zxx() {
		return new Vec3d(z, x, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zxy() {
		return new Vec3d(z, x, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zxz() {
		return new Vec3d(z, x, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zxw() {
		return new Vec3d(z, x, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zyx() {
		return new Vec3d(z, y, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zyy() {
		return new Vec3d(z, y, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zyz() {
		return new Vec3d(z, y, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zyw() {
		return new Vec3d(z, y, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zzx() {
		return new Vec3d(z, z, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zzy() {
		return new Vec3d(z, z, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zzz() {
		return new Vec3d(z, z, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zzw() {
		return new Vec3d(z, z, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zwx() {
		return new Vec3d(z, w, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zwy() {
		return new Vec3d(z, w, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zwz() {
		return new Vec3d(z, w, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d zww() {
		return new Vec3d(z, w, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wxx() {
		return new Vec3d(w, x, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wxy() {
		return new Vec3d(w, x, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wxz() {
		return new Vec3d(w, x, z);
	}

	/** * Swizzling this Vector down to another. * * @return the new Vector */
	public Vec3d wxw() {
		return new Vec3d(w, x, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wyx() {
		return new Vec3d(w, y, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wyy() {
		return new Vec3d(w, y, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wyz() {
		return new Vec3d(w, y, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wyw() {
		return new Vec3d(w, y, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wzx() {
		return new Vec3d(w, z, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wzy() {
		return new Vec3d(w, z, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wzz() {
		return new Vec3d(w, z, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wzw() {
		return new Vec3d(w, z, w);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wwx() {
		return new Vec3d(w, w, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wwy() {
		return new Vec3d(w, w, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d wwz() {
		return new Vec3d(w, w, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 * 
	 * @return the new Vector
	 */
	public Vec3d www() {
		return new Vec3d(w, w, w);
	}

	/**
	 * Swizzling this Vector down to a Quaternion.
	 *
	 * @return the new quaternion
	 */
	public Quatd quat() {
		return new Quatd(x, y, z, w);
	}

	/**
	 * Swizzling this Vector to a Quaternion where w component is the specified
	 * one.
	 *
	 * @param w as w component of the quaternion
	 * @return the new quaternion
	 */
	public Quatd quat(double w) {
		return new Quatd(x, y, z, w);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the translation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Translation() {
		return Mat4d.translationMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the scale view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Scale() {
		return Mat4d.scaleMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the rotation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Rotation() {
		return Mat4d.rotationMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 2 dimensional one where the x and y components
	 * are passed zero.
	 *
	 * @return the new vector
	 */
	public Vec2d vec2() {
		return new Vec2d(x, y);
	}

	/**
	 * Swizzling this Vector to a 3 dimensional one where the x, y and z
	 * components are passed zero.
	 *
	 * @return the new vector
	 */
	public Vec3d vec3() {
		return new Vec3d(x, y, z);
	}

	/**
	 * @return the x component of this vector
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y component of this vector
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the z component of this vector
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @return the z component of this vector
	 */
	public double getW() {
		return w;
	}

	/**
	 * Converts the vector to a byte array optimized for high performance
	 * serialization.
	 *
	 * @return this vector comprised in a byte array.
	 */
	public byte[] toBytes() {
		return toBytes(new byte[BYTES], 0);
	}

	/**
	 * Converts the vector to the specified byte array optimized for high
	 * performance serialization.
	 *
	 * @param data the array to store the data
	 * @return the byte array
	 */
	public byte[] toBytes(byte[] data) {
		return toBytes(data, 0);
	}

	/**
	 * Converts the vector to the specified byte array optimized for high
	 * performance serialization.
	 *
	 * @param data the array to store the data
	 * @param offset the offset to start from
	 * @return the byte array
	 */
	public byte[] toBytes(byte[] data, int offset) {
		long[] values = new long[] { Double.doubleToRawLongBits(x), Double.doubleToRawLongBits(y), Double.doubleToRawLongBits(z), Double.doubleToRawLongBits(w) };
		for (int i = 0; i < FIELDS; i++) {
            data[offset++] = (byte) (values[i] >> 56);
            data[offset++] = (byte) (values[i] >> 48);
            data[offset++] = (byte) (values[i] >> 40);
            data[offset++] = (byte) (values[i] >> 32);
			data[offset++] = (byte) (values[i] >> 24);
			data[offset++] = (byte) (values[i] >> 16);
			data[offset++] = (byte) (values[i] >> 8);
			data[offset++] = (byte) (values[i]);
		}
		return data;
	}

	/**
	 * Converts the specified byte array (length >= 3) to a new vector
	 *
	 * @param data the byte data (0=X,1=Y,2=Z)
	 * @return the new vector from the specified byte array
	 */
	public static Vec4d fromBytes(byte[] data) {
		return fromBytes(data, 0);
	}

	/**
	 * Converts the specified byte array (length >= 3) to a new vector by the
	 * given offset
	 *
	 * @param data the byte data (0=X,1=Y,2=Z)
	 * @param offset
	 * @return the new vector from the specified byte array and offset
	 */
	public static Vec4d fromBytes(byte[] data, int offset) {
		double[] values = new double[FIELDS];
		for (int i = 0; i < FIELDS; i++) {
			values[i] = Double.longBitsToDouble(
                (data[offset++] & 0xFFL) << 56 | 
                (data[offset++] & 0xFFL) << 48 | 
                (data[offset++] & 0xFFL) << 40 | 
                (data[offset++] & 0xFFL) << 32 | 
                (data[offset++] & 0xFFL) << 24 | 
                (data[offset++] & 0xFFL) << 16 | 
                (data[offset++] & 0xFFL) << 8  | 
                (data[offset++] & 0xFFL) << 0
			);
		}
		return new Vec4d(values[0], values[1], values[2], values[3]);
	}

	@Override
	public int hashCode() {
        final long prime = 31L;       
        long bits = 7L;
        bits = prime * bits + Double.doubleToLongBits(w);
        bits = prime * bits + Double.doubleToLongBits(x);
        bits = prime * bits + Double.doubleToLongBits(y);
        bits = prime * bits + Double.doubleToLongBits(z);
        return (int) (bits ^ (bits >> 32));
	}

	/**
	 * Determines whether or not two Vec3d points or vectors are equal. Two
	 * instances of <code>Vec3d</code> are equal if the values of their
	 * <code>x</code>, <code>y</code> and <code>z</code> member fields,
	 * representing their position in the coordinate space, are the same.
	 *
	 * @param obj an object to be compared with this <code>Vec3d</code>
	 * @return <code>true</code> if the object to be compared is an instance of
	 *         <code>Vec3d</code> and has the same values, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof Vec4d) {
			Vec4d v = (Vec4d) obj;
			return (x == v.x) && (y == v.y) && (z == v.z) && (w == v.w);
		}
		return false;
	}

	@Override
	public String toString() {
		return "vec4d[x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
	}

}
