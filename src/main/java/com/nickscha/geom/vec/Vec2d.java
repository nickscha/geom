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
 * Vector of 2 element doubles (final::immutable)
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
public final class Vec2d {

	/**
	 * Defines how much fields are stored in this class which will be used for
	 * optimal binary serialization
	 */
	public static final int FIELDS = 2;

	/**
	 * Defines how much bytes will be needed to store this type as binary
	 */
	public static final byte BYTES = 16;

	/**
	 * Represents the X axis vector
	 */
	public static final Vec2d XAXIS = new Vec2d(1.0d, 0.0d);

	/**
	 * Represents the Y axis vector
	 */
	public static final Vec2d YAXIS = new Vec2d(0.0d, 1.0d);

	/**
	 * Represents the zero vector
	 */
	public static final Vec2d ZERO = new Vec2d(0.0d, 0.0d);

	/**
	 * Represents the NAN vector
	 */
	public static final Vec2d NAN = new Vec2d(Double.NaN, Double.NaN);

	/**
	 * A vector with all coordinates set to positive infinity.
	 */
	public static final Vec2d POSITIVE_INFINITY = new Vec2d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	/**
	 * A vector with all coordinates set to negative infinity.
	 */
	public static final Vec2d NEGATIVE_INFINITY = new Vec2d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

	/**
	 * A vector with all coordinates set to the minimal value.
	 */
	public static final Vec2d MIN = new Vec2d(Double.MIN_VALUE, Double.MIN_VALUE);

	/**
	 * A vector with all coordinates set to the maximal value.
	 */
	public static final Vec2d MAX = new Vec2d(Double.MAX_VALUE, Double.MAX_VALUE);

	/**
	 * x, y and z component of this vector
	 */
	private final double x, y;

	/**
	 * Creates a new vector and sets all properties to the specified amount.
	 *
	 * @param amt as the value for x and y
	 */
	public Vec2d(double amt) {
		x = y = amt;
	}

	/**
	 * Creates a new vector for the specified properties
	 *
	 * @param x the x component
	 * @param y the y component
	 */
	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates an new Vec3f instance where each component has the specified
	 * amount.
	 *
	 * @param amt to apply to all components
	 * @return the new vector
	 */
	public static Vec2d of(double amt) {
		return new Vec2d(amt);
	}

	/**
	 * Creates an new Vec3f instance where each component has the specified
	 * double.
	 *
	 * @param x the x component
	 * @param y the y component
	 * @return the new vector
	 */
	public static Vec2d of(double x, double y) {
		return new Vec2d(x, y);
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
		return x * x + y * y;
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
	 * @see Vec2d#lengthManhattan(Vec2d) Default Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec2d#lengthEuclidean(Vec2d)
	 */
	public double lengthManhattan(Vec2d r, double unit) {
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
	 * @see Vec2d#lengthManhattan(Vec2d, double) Passing a unit value to
	 *      Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec2d#lengthEuclidean(Vec2d)
	 */
	public double lengthManhattan(Vec2d r) {
		return Math.abs(x - r.getX()) + Math.abs(y - r.getY());
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
	 * @see #lengthManhattan(Vec2d)
	 */
	public double lengthEuclidean(Vec2d r) {
		return (double) Math.sqrt((x - r.getX()) * (x - r.getX()) + (y - r.getY()) * (y - r.getY()));
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
	public double dot(Vec2d other) {
		return x * other.getX() + y * other.getY();
	}

	/**
	 * Returns the minimal value of the vector components
	 *
	 * @return the minimal value of the vector
	 */
	public double min() {
		return Math.min(x, y);
	}

	/**
	 * Returns the minimal vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the minimized vector between this and the specified one
	 */
	public Vec2d min(Vec2d other) {
		return new Vec2d(Math.min(x, other.getX()), Math.min(y, other.getY()));
	}

	/**
	 * Returns the max value of the vector components
	 *
	 * @return the max value of the vector
	 */
	public double max() {
		return Math.max(x, y);
	}

	/**
	 * Returns the max vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the maximized vector between this and the specified one
	 */
	public Vec2d max(Vec2d other) {
		return new Vec2d(Math.max(x, other.getX()), Math.max(y, other.getY()));
	}

	/**
	 * Ceils all values of the vector.
	 *
	 * @return the ceiled vector
	 */
	public Vec2d ceil() {
		return new Vec2d((double) Math.ceil(x), (double) Math.ceil(y));
	}

	/**
	 * Floors all values of the vector.
	 *
	 * @return the floored vector
	 */
	public Vec2d floor() {
		return new Vec2d((double) Math.floor(x), (double) Math.floor(y));
	}

	/**
	 * Computes the absolute number of each vector component and returns a new
	 * vector.
	 *
	 * @return the new vector where each component is an absolute number
	 */
	public Vec2d absolute() {
		return new Vec2d(Math.abs(x), Math.abs(y));
	}

	/**
	 * Negates all components of this vector.
	 *
	 * @return the new negated vector
	 */
	public Vec2d negate() {
		return new Vec2d(-x, -y);
	}

	/**
	 * Clamp all components to the constant c. Just checks: COMPONENT > c. -4
	 * will stay the same if clamped to 3!
	 *
	 * @param c the clamp value to compare
	 * @return the new clamped vector
	 */
	public Vec2d clamp(double c) {
		return new Vec2d((x > c) ? c : x, (y > c) ? c : y);
	}

	/**
	 * Clamp all components to the ones from the specified vector. Just checks:
	 * COMPONENT > c. -4 will stay the same if clamped to 3!
	 *
	 * @param other the clamp vector to compare
	 * @return the new clamped vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d clamp(Vec2d other) {
		return new Vec2d((x > other.getX()) ? other.getX() : x, (y > other.getY()) ? other.getY() : y);
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
		return x == 0 && y == 0;
	}

	/**
	 * Checks if any component comprises the value NAN.
	 *
	 * @return true if any of the components is NAN.
	 */
	public boolean isNAN() {
		return Double.isNaN(x) || Double.isNaN(y);
	}

	/**
	 * Checks if any component has an infinite value.
	 *
	 * @return true if any value is infinite
	 */
	public boolean isInfinite() {
		return !isNAN() && (Double.isInfinite(x) || Double.isInfinite(y));
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
	public boolean isSameDirection(Vec2d vector) {
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
	public boolean isOppositeDirection(Vec2d vector) {
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
	public double angleCos(Vec2d v) {
		return (double) (dot(v) / (length() * v.length()));
	}

	/**
	 * Return the angle between this vector and the supplied vector.
	 *
	 * @param v
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public double angle(Vec2d v) {
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
	 * surface with a “upward facing plane”. Vectors of unit length are also
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
	 * will usually result in a “divide-by-zero” error. Usually we resolve this
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
	 * Vec3f vectorOne = ...<br/>
	 * Vec3f vectorTwo = ...<br/>
	 * Vec3f normalVector = vectorOne.cross(vectorTwo).normalize();<br/>
	 * </code>
	 * </p>
	 *
	 * @return the new normalized vector;
	 */
	public Vec2d normalize() {
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
	public double cross(Vec2d r) {
		return x * r.getY() - y * r.getX();
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
	public Vec2d project(Vec2d other) {
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
	public Vec2d reflect(Vec2d normal) {
		double dot = dot(normal);

		double x_ = this.x - 2.0d * dot * normal.x;
		double y_ = this.y - 2.0d * dot * normal.y;

		return new Vec2d(x_, y_);
	}

	/**
	 * Compute the half vector between this and the other vector.
	 *
	 * @param other
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d half(Vec2d other) {
		return add(other).normalize();
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
	public Vec2d lerp(Vec2d dest, double amt) {
		return dest.sub(this).mul(amt).add(this);
	}

	/**
	 * Adds the current vector by the specified vector and returns a new one.
	 *
	 * @param other vector amount of the addition
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d add(Vec2d other) {
		return new Vec2d(x + other.getX(), y + other.getY());
	}

	/**
	 * Adds the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the addition
	 * @return a new vector
	 */
	public Vec2d add(double amt) {
		return new Vec2d(x + amt, y + amt);
	}

	/**
	 * Subtracts the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the subtraction
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d sub(Vec2d other) {
		return new Vec2d(x - other.getX(), y - other.getY());
	}

	/**
	 * Subtracts the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the subtraction
	 * @return a new vector
	 */
	public Vec2d sub(double amt) {
		return new Vec2d(x - amt, y - amt);
	}

	/**
	 * Multiplies the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the multiplication
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d mul(Vec2d other) {
		return new Vec2d(x * other.getX(), y * other.getY());
	}

	/**
	 * Multiplies the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the multiplication
	 * @return a new vector
	 */
	public Vec2d mul(double amt) {
		return new Vec2d(x * amt, y * amt);
	}

	/**
	 * Divides the current vector by the specified vector and returns a new one.
	 *
	 * @param other vector amount of the division
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec2d div(Vec2d other) {
		return new Vec2d(x / other.getX(), y / other.getY());
	}

	/**
	 * Divides the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the division
	 * @return a new vector
	 */
	public Vec2d div(double amt) {
		return new Vec2d(x / amt, y / amt);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 *
	 * @return the new Vector
	 */
	public Vec1d x() {
		return new Vec1d(x);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec1d y() {
		return new Vec1d(y);
	}

	/**
	 * Swizzling this Vector down to a Quaternion where w component is zero.
	 *
	 * @return the new quaternion
	 */
	public Quatd quat() {
		return new Quatd(x, y, 0.0d, 0.0d);
	}

	/**
	 * Swizzling this Vector to a Quaternion where w component is the specified
	 * one.
	 *
	 * @param w as w component of the quaternion
	 * @return the new quaternion
	 */
	public Quatd quat(double w) {
		return new Quatd(x, y, 0.0d, w);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the translation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Translation() {
		return Mat4d.translationMatrix(x, y, 0);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the scale view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Scale() {
		return Mat4d.scaleMatrix(x, y, 0);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the rotation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4d mat4Rotation() {
		return Mat4d.rotationMatrix(x, y, 0);
	}

	/**
	 * Swizzling this Vector to a 4 dimensional one where the z and w component
	 * is zero.
	 *
	 * @return the new vector
	 */
	public Vec4d vec4() {
		return new Vec4d(x, y, 0.0d, 0.0d);
	}

	/**
	 * Swizzling this Vector to a 4 dimensional one where the w component is the
	 * specfied one.
	 *
	 * @param w the component of w for the new vector
	 * @return the new vector
	 */
	public Vec4d vec4(double w) {
		return new Vec4d(x, y, 0.0d, w);
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
        long[] values = new long[] { Double.doubleToRawLongBits(x), Double.doubleToRawLongBits(y)};
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
	public static Vec2d fromBytes(byte[] data) {
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
	public static Vec2d fromBytes(byte[] data, int offset) {
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
		return new Vec2d(values[0], values[1]);
	}

	@Override
	public int hashCode() {
        final long prime = 31L;       
        long bits = 7L;
        bits = prime * bits + Double.doubleToLongBits(x);
        bits = prime * bits + Double.doubleToLongBits(y);
        return (int) (bits ^ (bits >> 32));
	}

	/**
	 * Determines whether or not two Vec3f points or vectors are equal. Two
	 * instances of <code>Vec3f</code> are equal if the values of their
	 * <code>x</code>, <code>y</code> and <code>z</code> member fields,
	 * representing their position in the coordinate space, are the same.
	 *
	 * @param obj an object to be compared with this <code>Vec3f</code>
	 * @return <code>true</code> if the object to be compared is an instance of
	 *         <code>Vec3f</code> and has the same values, <code>false</code>
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof Vec2d) {
			Vec2d v = (Vec2d) obj;
			return (x == v.x) && (y == v.y);
		}
		return false;
	}

	@Override
	public String toString() {
		return "vec2d[x=" + x + ", y=" + y + "]";
	}

}
