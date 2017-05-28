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

import com.nickscha.geom.mat.Mat4f;
import com.nickscha.geom.quat.Quatf;

/**
 * Vector of 3 element floats (final::immutable)
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
 * @since 0.0.1
 * @version 0.0.1
 */
public final class Vec3f {

	/**
	 * Defines how much fields are stored in this class which will be used for
	 * optimal binary serialization
	 */
	public static final int FIELDS = 3;

	/**
	 * Defines how much bytes will be needed to store this type as binary
	 */
	public static final byte BYTES = 12;

	/**
	 * Represents the X axis vector
	 */
	public static final Vec3f XAXIS = new Vec3f(1.0f, 0.0f, 0.0f);

	/**
	 * Represents the Y axis vector
	 */
	public static final Vec3f YAXIS = new Vec3f(0.0f, 1.0f, 0.0f);

	/**
	 * Represents the Z axis vector
	 */
	public static final Vec3f ZAXIS = new Vec3f(0.0f, 0.0f, 1.0f);

	/**
	 * Represents the zero vector
	 */
	public static final Vec3f ZERO = new Vec3f(0.0f, 0.0f, 0.0f);

	/**
	 * Represents the NAN vector
	 */
	public static final Vec3f NAN = new Vec3f(Float.NaN, Float.NaN, Float.NaN);

	/**
	 * A vector with all coordinates set to positive infinity.
	 */
	public static final Vec3f POSITIVE_INFINITY = new Vec3f(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
			Float.POSITIVE_INFINITY);

	/**
	 * A vector with all coordinates set to negative infinity.
	 */
	public static final Vec3f NEGATIVE_INFINITY = new Vec3f(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY,
			Float.NEGATIVE_INFINITY);

	/**
	 * A vector with all coordinates set to the minimal value.
	 */
	public static final Vec3f MIN = new Vec3f(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);

	/**
	 * A vector with all coordinates set to the maximal value.
	 */
	public static final Vec3f MAX = new Vec3f(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);

	/**
	 * x, y and z component of this vector
	 */
	private final float x, y, z;

	/**
	 * Creates a new vector and sets all properties to the specified amount.
	 *
	 * @param amt as the value for x, y and z
	 */
	public Vec3f(float amt) {
		x = y = z = amt;
	}

	/**
	 * Creates a new vector for the specified properties
	 *
	 * @param x the x component
	 * @param y the y component
	 * @param z the z component
	 */
	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Creates an new Vec3f instance where each component has the specified
	 * amount.
	 *
	 * @param amt to apply to all components
	 * @return the new vector
	 */
	public static Vec3f of(float amt) {
		return new Vec3f(amt);
	}

	/**
	 * Creates an new Vec3f instance where each component has the specified
	 * float.
	 *
	 * @param x the x component
	 * @param y the y component
	 * @param z the z component
	 * @return the new vector
	 */
	public static Vec3f of(float x, float y, float z) {
		return new Vec3f(x, y, z);
	}

	/**
	 * Calculates the regular length or magnitude of this vector.
	 *
	 * @return the regular magnitude/length
	 * 
	 */
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}

	/**
	 * Calculates the squared length or magnitude of this vector.
	 *
	 * @return the squared magnitude/length
	 */
	public float lengthSquared() {
		return x * x + y * y + z * z;
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
	 *            Normally 1.0f is set. 0.5f will double the Manhattan length
	 *            result.
	 * @return the distance between both vectors in a fixed cartesian coordinate
	 *         system
	 * @see Vec3f#lengthManhattan(Vec3f) Default Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec3f#lengthEuclidean(Vec3f)
	 */
	public float lengthManhattan(Vec3f r, float unit) {
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
	 * @see Vec3f#lengthManhattan(Vec3f, float) Passing a unit value to
	 *      Manhattan length calculation
	 * @throws NullPointerException if passed vector r is null
	 * @see Vec3f#lengthEuclidean(Vec3f)
	 */
	public float lengthManhattan(Vec3f r) {
		return Math.abs(x - r.getX()) + Math.abs(y - r.getY()) + Math.abs(z - r.getZ());
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
	 * @see #lengthManhattan(Vec3f)
	 */
	public float lengthEuclidean(Vec3f r) {
		return (float) Math.sqrt(
				(x - r.getX()) * (x - r.getX()) + (y - r.getY()) * (y - r.getY()) + (z - r.getZ()) * (z - r.getZ()));
	}

	/**
	 * Get the elevation of the vector in z.
	 *
	 * @return the elevation of the vector in z
	 */
	public float delta() {
		return (float) Math.asin(z / length());
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
	 * Vec3f k = u.normalize();<br>
	 * Vec3f i = k.orthogonal();<br>
	 * Vec3f j = Vec3f.crossProduct(k, i);
	 * </code>
	 * </p>
	 *
	 * @return a new normalized vector orthogonal to the instance
	 * @throws ArithmeticException if the normal is zero
	 */
	public Vec3f orthogonal() {
		float threshold = 0.6f * length();
		if (threshold == 0) {
			throw new ArithmeticException("The Normal is zero");
		}
		if (Math.abs(x) <= threshold) {
			float inverse = (float) (1 / Math.sqrt(y * y + z * z));
			return new Vec3f(0, inverse * z, -inverse * y);
		} else if (Math.abs(y) <= threshold) {
			float inverse = (float) (1 / Math.sqrt(x * x + z * z));
			return new Vec3f(-inverse * z, 0, inverse * x);
		}
		float inverse = (float) (1 / Math.sqrt(x * x + y * y));
		return new Vec3f(inverse * y, -inverse * x, 0);
	}

	/**
	 * Get a vector orthogonal to the instance for the specified vector.
	 *
	 * @param r the other vector for the orthogonal instance
	 * @return the new vector orthogonal to the instance
	 * @see Vec3f#orthogonal()
	 * @throws NullPointerException if passed vector is null
	 */
	public Vec3f orthogonal(Vec3f r) {
		Vec3f k = r.normalize();
		Vec3f i = k.orthogonal();
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
	public float dot(Vec3f other) {
		return x * other.getX() + y * other.getY() + z * other.getZ();
	}

	/**
	 * Returns the minimal value of the vector components
	 *
	 * @return the minimal value of the vector
	 */
	public float min() {
		return Math.min(x, Math.min(y, z));
	}

	/**
	 * Returns the minimal vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the minimized vector between this and the specified one
	 */
	public Vec3f min(Vec3f other) {
		return new Vec3f(Math.min(x, other.getX()), Math.min(y, other.getY()), Math.min(z, other.getZ()));
	}

	/**
	 * Returns the max value of the vector components
	 *
	 * @return the max value of the vector
	 */
	public float max() {
		return Math.max(x, Math.max(y, z));
	}

	/**
	 * Returns the max vector between this and the specified one.
	 *
	 * @param other the other vector
	 * @return the maximized vector between this and the specified one
	 */
	public Vec3f max(Vec3f other) {
		return new Vec3f(Math.max(x, other.getX()), Math.max(y, other.getY()), Math.max(z, other.getZ()));
	}

	/**
	 * Ceils all values of the vector.
	 *
	 * @return the ceiled vector
	 */
	public Vec3f ceil() {
		return new Vec3f((float) Math.ceil(x), (float) Math.ceil(y), (float) Math.ceil(z));
	}

	/**
	 * Floors all values of the vector.
	 *
	 * @return the floored vector
	 */
	public Vec3f floor() {
		return new Vec3f((float) Math.floor(x), (float) Math.floor(y), (float) Math.floor(z));
	}

	/**
	 * Computes the absolute number of each vector component and returns a new
	 * vector.
	 *
	 * @return the new vector where each component is an absolute number
	 */
	public Vec3f absolute() {
		return new Vec3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	/**
	 * Negates all components of this vector.
	 *
	 * @return the new negated vector
	 */
	public Vec3f negate() {
		return new Vec3f(-x, -y, -z);
	}

	/**
	 * Clamp all components to the constant c. Just checks: COMPONENT > c. -4
	 * will stay the same if clamped to 3!
	 *
	 * @param c the clamp value to compare
	 * @return the new clamped vector
	 */
	public Vec3f clamp(float c) {
		return new Vec3f((x > c) ? c : x, (y > c) ? c : y, (z > c) ? c : z);
	}

	/**
	 * Clamp all components to the ones from the specified vector. Just checks:
	 * COMPONENT > c. -4 will stay the same if clamped to 3!
	 *
	 * @param other the clamp vector to compare
	 * @return the new clamped vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec3f clamp(Vec3f other) {
		return new Vec3f((x > other.getX()) ? other.getX() : x, (y > other.getY()) ? other.getY() : y,
				(z > other.getZ()) ? other.getZ() : z);
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
		return this.length() == 1.0f;
	}

	/**
	 * Checks if this vector bellows to a zero one (all components are zero).
	 *
	 * @return weather this vector is a zero vector
	 */
	public boolean isZero() {
		return x == 0 && y == 0 && z == 0;
	}

	/**
	 * Checks if any component comprises the value NAN.
	 *
	 * @return true if any of the components is NAN.
	 */
	public boolean isNAN() {
		return Float.isNaN(x) || Float.isNaN(y) || Float.isNaN(z);
	}

	/**
	 * Checks if any component has an infinite value.
	 *
	 * @return true if any value is infinite
	 */
	public boolean isInfinite() {
		return !isNAN() && (Float.isInfinite(x) || Float.isInfinite(y) || Float.isInfinite(z));
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
	public boolean isSameDirection(Vec3f vector) {
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
	public boolean isOppositeDirection(Vec3f vector) {
		return dot(vector) < 0;
	}

	/**
	 * Checks this vector if it is null or its floats are NaN or infinite,
	 * return false. Else return true.
	 * 
	 * @return true if float is not NAN and not mapped with INFINITE values,
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
	public float angleCos(Vec3f v) {
		return (float) (dot(v) / (length() * v.length()));
	}

	/**
	 * Return the angle between this vector and the supplied vector.
	 *
	 * @param v
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public float angle(Vec3f v) {
		float cos = angleCos(v);
		cos = Math.min(cos, 1);
		cos = Math.max(cos, -1);
		return (float) Math.acos(cos);
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
	 * will usually result in a "divide-by-zero" error. Usually we resolve
	 * this by performing the normalization in multiple steps:
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
	public Vec3f normalize() {
		final float length = length();
		return (length == 0.0f) ? this : div(length);
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
	public Vec3f cross(Vec3f r) {
		float x_ = y * r.getZ() - z * r.getY();
		float y_ = z * r.getX() - x * r.getZ();
		float z_ = x * r.getY() - y * r.getX();

		return new Vec3f(x_, y_, z_);
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
	public Vec3f project(Vec3f other) {
		float n = this.dot(other);
		float d = other.lengthSquared();
		return other.normalize().mul(n / d);
	}

	/**
	 * Reflect this vector about the given normal vector.
	 *
	 * @param normal the vector to reflect about
	 * @return the new reflected vector
	 * @throws NullPointerException if the passed normal vector is null
	 */
	public Vec3f reflect(Vec3f normal) {
		float dot = dot(normal);

		float x_ = this.x - 2.0f * dot * normal.x;
		float y_ = this.y - 2.0f * dot * normal.y;
		float z_ = this.z - 2.0f * dot * normal.z;

		return new Vec3f(x_, y_, z_);
	}

	/**
	 * Compute the half vector between this and the other vector.
	 *
	 * @param other
	 * @return
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec3f half(Vec3f other) {
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
	public Vec3f rotate(Vec3f axis, float degree) {
		float sinAngle = (float) Math.sin(-degree);
		float cosAngle = (float) Math.cos(-degree);

		return this.cross(axis.mul(sinAngle)).add( // Rotation X
				(this.mul(cosAngle)).add( // Rotation Z
						axis.mul(this.dot(axis.mul(1 - cosAngle))))); // Rotation
																		// Y
	}

	public Vec3f rotate(Quatf rotation) {
		Quatf conjugate = rotation.conjugate();
		Quatf w = rotation.mul(this).mul(conjugate);

		return new Vec3f(w.getX(), w.getY(), w.getZ());
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
	public Vec3f lerp(Vec3f dest, float amt) {
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
	//TODO adjust matrix class
	public Vec2f screenSpace(Mat4f viewMatrix, Mat4f projectionMatrix) {
		Vec4f coords = vec4(1f);	
		// Mat4f.transform(viewMatrix, coords, coords);
		// Mat4f.transform(projectionMatrix, coords, coords);
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
	public Vec3f add(Vec3f other) {
		return new Vec3f(x + other.getX(), y + other.getY(), z + other.getZ());
	}

	/**
	 * Adds the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the addition
	 * @return a new vector
	 */
	public Vec3f add(float amt) {
		return new Vec3f(x + amt, y + amt, z + amt);
	}

	/**
	 * Subtracts the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the subtraction
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec3f sub(Vec3f other) {
		return new Vec3f(x - other.getX(), y - other.getY(), z - other.getZ());
	}

	/**
	 * Subtracts the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the subtraction
	 * @return a new vector
	 */
	public Vec3f sub(float amt) {
		return new Vec3f(x - amt, y - amt, z - amt);
	}

	/**
	 * Multiplies the current vector by the specified vector and returns a new
	 * one.
	 *
	 * @param other vector amount of the multiplication
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec3f mul(Vec3f other) {
		return new Vec3f(x * other.getX(), y * other.getY(), z * other.getZ());
	}

	/**
	 * Multiplies the current vector by the specified amount and returns a new
	 * one.
	 *
	 * @param amt amount of the multiplication
	 * @return a new vector
	 */
	public Vec3f mul(float amt) {
		return new Vec3f(x * amt, y * amt, z * amt);
	}

	/**
	 * Divides the current vector by the specified vector and returns a new one.
	 *
	 * @param other vector amount of the division
	 * @return a new vector
	 * @throws NullPointerException if the passed vector is null
	 */
	public Vec3f div(Vec3f other) {
		return new Vec3f(x / other.getX(), y / other.getY(), z / other.getZ());
	}

	/**
	 * Divides the current vector by the specified amount and returns a new one.
	 *
	 * @param amt amount of the division
	 * @return a new vector
	 */
	public Vec3f div(float amt) {
		return new Vec3f(x / amt, y / amt, z / amt);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * <p>
	 * Vec3f.of(1,2,3).orthogonal()
	 *
	 * </p>
	 *
	 * @return the new Vector
	 */
	public Vec2f xy() {
		return new Vec2f(x, y);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec2f xz() {
		return new Vec2f(x, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec2f yx() {
		return new Vec2f(y, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec2f yz() {
		return new Vec2f(y, z);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec2f zx() {
		return new Vec2f(z, x);
	}

	/**
	 * Swizzling this Vector down to another.
	 *
	 * @return the new Vector
	 */
	public Vec2f zy() {
		return new Vec2f(z, y);
	}

	/**
	 * Swizzling this Vector down to a Quaternion where w component is zero.
	 *
	 * @return the new quaternion
	 */
	public Quatf quat() {
		return new Quatf(x, y, z, 0.0f);
	}

	/**
	 * Swizzling this Vector to a Quaternion where w component is the specified
	 * one.
	 *
	 * @param w as w component of the quaternion
	 * @return the new quaternion
	 */
	public Quatf quat(float w) {
		return new Quatf(x, y, z, w);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the translation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4f translationMatrix() {
		return Mat4f.translationMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the scale view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4f scaleMatrix() {
		return Mat4f.scaleMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 4x4 matrix where the coordinates are stored in
	 * the rotation view of the matrix.
	 *
	 * @return the new matrix
	 */
	public Mat4f rotationMatrix() {
		return Mat4f.rotationMatrix(x, y, z);
	}

	/**
	 * Swizzling this Vector to a 2 dimensional one where the x and y components
	 * are passed.
	 *
	 * @return the new vector
	 */
	public Vec2f vec2() {
		return new Vec2f(x, y);
	}

	/**
	 * Swizzling this Vector to a 4 dimensional one where the w component is
	 * zero.
	 *
	 * @return the new vector
	 */
	public Vec4f vec4() {
		return new Vec4f(x, y, z, 0.0f);
	}

	/**
	 * Swizzling this Vector to a 4 dimensional one where the w component is the
	 * specfied one.
	 *
	 * @param w the component of w for the new vector
	 * @return the new vector
	 */
	public Vec4f vec4(float w) {
		return new Vec4f(x, y, z, w);
	}

	/**
	 * @return the x component of this vector
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y component of this vector
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the z component of this vector
	 */
	public float getZ() {
		return z;
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
		int[] values = new int[] { Float.floatToIntBits(x), Float.floatToIntBits(y), Float.floatToIntBits(z) };
		for (int i = 0; i < FIELDS; i++) {
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
	public static Vec3f fromBytes(byte[] data) {
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
	public static Vec3f fromBytes(byte[] data, int offset) {
		float[] values = new float[FIELDS];
		for (int i = 0; i < FIELDS; i++) {
			values[i] = Float.intBitsToFloat((data[offset++] & 0xFF) << 24 | (data[offset++] & 0xFF) << 16
					| (data[offset++] & 0xFF) << 8 | (data[offset++] & 0xFF));
		}
		return new Vec3f(values[0], values[1], values[2]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
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
		} else if (obj instanceof Vec3f) {
			Vec3f v = (Vec3f) obj;
			return (x == v.x) && (y == v.y) && (z == v.z);
		}
		return false;
	}

	@Override
	public String toString() {
		return "vec3f[x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
