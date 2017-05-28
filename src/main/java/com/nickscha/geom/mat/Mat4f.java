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
package com.nickscha.geom.mat;

import java.util.Arrays;

import com.nickscha.geom.vec.Vec3f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public final class Mat4f {

	/**
	 * Defines how much many groups are set for the amount of fields.
	 */
	public static final int GROUPS = 4;

	/**
	 * Defines how much fields are stored per group in this class which will be
	 * used for optimal binary serialization
	 */
	public static final int FIELDS = 4;

	/**
	 * Defines how much bytes will be needed to store this type as binary
	 */
	public static final byte BYTES = 64;

	/**
	 * Represents an empty matrix
	 */
	public static final Mat4f ZERO = new Mat4f();

	/**
	 * Represents an identity matrix
	 */
	public static final Mat4f IDENTITY = Mat4f.identity();

	private final float[][] m;

	/**
	 * Initializes the matrix where all fields are set to zero.
	 */
	public Mat4f() {
		this.m = new float[GROUPS][FIELDS];
	}

	public Mat4f(float amt) {
		this();
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < FIELDS; j++) {
				this.m[i][j] = amt;
			}
		}
	}

	public Mat4f(float[][] m) {
		this.m = m;
	}

	public static Mat4f of(float amt) {
		return new Mat4f(amt);
	}

	public static Mat4f of(float[][] m) {
		return new Mat4f(m);
	}

	public static Mat4f identity() {
		final float[][] m = new float[GROUPS][FIELDS];

		m[0][0] = 1;
		m[1][1] = 1;
		m[2][2] = 1;
		m[3][3] = 1;

		return new Mat4f(m);
	}

	public static Mat4f translationMatrix(float x, float y, float z) {
		final float[][] m = new float[GROUPS][FIELDS];

		m[0][0] = 1;
		m[0][3] = x;
		m[1][1] = 1;
		m[1][3] = y;
		m[2][2] = 1;
		m[2][3] = z;
		m[3][3] = 1;

		return new Mat4f(m);
	}

	public static Mat4f scaleMatrix(Vec3f scale) {
		return scaleMatrix(scale.getX(), scale.getY(), scale.getZ());
	}

	public static Mat4f scaleMatrix(float x, float y, float z) {
		final float[][] m = new float[GROUPS][FIELDS];

		m[0][0] = x;
		m[1][1] = y;
		m[2][2] = z;
		m[3][3] = 1;

		return new Mat4f(m);
	}

	public static Mat4f modelMatrix(Vec3f position, float scale) {
		Mat4f modelMatrix = Mat4f.translationMatrix(position.getX(), position.getY(), position.getZ());
		modelMatrix.scale(Vec3f.of(scale));
		return modelMatrix;
	}

	public static Mat4f viewMatrix(Vec3f position, Mat4f rotationMatrix) {
		Mat4f translation = Mat4f.translationMatrix(position.getX(), position.getY(), position.getZ());
		return rotationMatrix.mul(translation);
	}

	/**
	 * 
	 * @param fov the field of view
	 * @param aspectRatio the aspect ratio (for example:
	 *            screen.width/screen.height)
	 * @param zNear first distance to consider (clip object to near)
	 * @param zFar last distance to ignore (clip object to far)
	 * @return the projection (perspective) matrix for supplied parameters
	 * @see #initPerspective(float, float, float, float)
	 */
	public static Mat4f projectionMatrix(float fov, float aspectRatio, float zNear, float zFar) {
		return perspectiveMatrix(fov, aspectRatio, zNear, zFar);
	}

	public static Mat4f mvpMatrix(Mat4f modelMatrix, Mat4f viewMatrix, Mat4f projectionMatrix) {
		return projectionMatrix.mul(viewMatrix).mul(modelMatrix);
	}

	/**
	 * 
	 * 
	 * @param fov the field of view
	 * @param aspectRatio the aspect ratio (for example:
	 *            screen.width/screen.height)
	 * @param zNear first distance to consider (clip object to near)
	 * @param zFar last distance to ignore (clip object to far)
	 * @return the perspective (projection) matrix for supplied parameters
	 * @see #projectionMatrix(float, float, float, float)
	 */
	public static Mat4f perspectiveMatrix(float fov, float aspectRatio, float zNear, float zFar) {
		float tanHalfFOV = (float) Math.tan(fov / 2);
		float zRange = zNear - zFar;

		final float[][] m = new float[GROUPS][FIELDS];
		m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
		m[1][1] = 1.0f / tanHalfFOV;
		m[2][2] = (-zNear - zFar) / zRange;
		m[2][3] = 2 * zFar * zNear / zRange;
		m[3][2] = 1;

		return new Mat4f(m);
	}

	public static Mat4f orthographicMatrix(float left, float right, float bottom, float top, float near, float far) {
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		final float[][] m = new float[GROUPS][FIELDS];
		m[0][0] = 2 / width;
		m[0][3] = -(right + left) / width;
		m[1][1] = 2 / height;
		m[1][3] = -(top + bottom) / height;
		m[2][2] = -2 / depth;
		m[2][3] = -(far + near) / depth;
		m[3][3] = 1;

		return new Mat4f(m);
	}

	public static Mat4f rotationMatrix(Vec3f forward, Vec3f up) {
		Vec3f f = forward.normalize();

		Vec3f r = up.normalize();
		r = r.cross(f);

		Vec3f u = f.cross(r);

		return rotationMatrix(f, u, r);
	}

	public static Mat4f rotationMatrix(Vec3f forward, Vec3f up, Vec3f right) {
		Vec3f f = forward;
		Vec3f r = right;
		Vec3f u = up;

		final float[][] m = new float[GROUPS][FIELDS];
		m[0][0] = r.getX();
		m[0][1] = r.getY();
		m[0][2] = r.getZ();
		m[1][0] = u.getX();
		m[1][1] = u.getY();
		m[1][2] = u.getZ();
		m[2][0] = f.getX();
		m[2][1] = f.getY();
		m[2][2] = f.getZ();
		m[3][3] = 1;

		return new Mat4f(m);
	}

	public static Mat4f rotationMatrix(float x, float y, float z) {
		Mat4f rx = new Mat4f();
		Mat4f ry = new Mat4f();
		Mat4f rz = new Mat4f();

		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);

		final float cosZ = (float) Math.cos(z);
		final float sinZ = (float) Math.sin(z);
		final float cosX = (float) Math.cos(x);
		final float sinX = (float) Math.sin(x);
		final float cosY = (float) Math.cos(y);
		final float sinY = (float) Math.sin(y);

		rz.m[0][0] = cosZ;
		rz.m[0][1] = -sinZ;
		rz.m[1][0] = sinZ;
		rz.m[1][1] = cosZ;
		rz.m[2][2] = 1;
		rz.m[3][3] = 1;

		rx.m[0][0] = 1;
		rx.m[1][1] = cosX;
		rx.m[1][2] = -sinX;
		rx.m[2][1] = sinX;
		rx.m[2][2] = cosX;
		rx.m[3][3] = 1;

		ry.m[0][0] = cosY;
		ry.m[0][2] = -sinY;
		ry.m[1][1] = 1;
		ry.m[2][0] = sinY;
		ry.m[2][2] = cosY;
		ry.m[3][3] = 1;

		return new Mat4f(rz.mul(ry.mul(rx)).getM());
	}

	public static Mat4f lookAtMatrix(Vec3f eye, Vec3f target, Vec3f up) {
		Vec3f f = target.sub(eye).normalize();
		Vec3f s = f.cross(up).normalize();
		Vec3f u = s.cross(f);

		Mat4f res = new Mat4f();
		res.m[0][0] = s.getX();
		res.m[0][1] = s.getY();
		res.m[0][2] = s.getZ();
		res.m[1][0] = u.getX();
		res.m[1][1] = u.getY();
		res.m[1][2] = u.getZ();
		res.m[2][0] = -f.getX();
		res.m[2][1] = -f.getY();
		res.m[2][2] = -f.getZ();
		res.m[0][3] = -s.dot(eye);
		res.m[1][3] = -u.dot(eye);
		res.m[2][3] = f.dot(eye);
		res.m[3][3] = 1.0f;

		return res;
	}

	/**
	 * Return the determinant of this matrix.
	 *
	 * @return determinant of matrix
	 */
	public float determinant() {
		return (m[0][0] * m[1][1] - m[0][1] * m[1][0]) * (m[2][2] * m[3][3] - m[2][3] * m[3][2])
				- (m[0][0] * m[1][2] - m[0][2] * m[1][0]) * (m[2][1] * m[3][3] - m[2][3] * m[3][1])
				+ (m[0][0] * m[1][3] - m[0][3] * m[1][0]) * (m[2][1] * m[3][2] - m[2][2] * m[3][1])
				+ (m[0][1] * m[1][2] - m[0][2] * m[1][1]) * (m[2][0] * m[3][3] - m[2][3] * m[3][0])
				- (m[0][1] * m[1][3] - m[0][3] * m[1][1]) * (m[2][0] * m[3][2] - m[2][2] * m[3][0])
				+ (m[0][2] * m[1][3] - m[0][3] * m[1][2]) * (m[2][0] * m[3][1] - m[2][1] * m[3][0]);
	}

	/**
	 * Return the determinant of the top-left 3x3 submatrix of this matrix.
	 *
	 * @return determinant top left 3x3
	 */
	public float determinant3x3() {
		return m[0][0] * m[1][1] * m[2][2] + m[1][0] * m[2][1] * m[0][2] + m[2][0] * m[0][1] * m[1][2]
				- m[2][0] * m[1][1] * m[0][2] - m[0][0] * m[2][1] * m[1][2] - m[1][0] * m[0][1] * m[2][2];
	}

	/**
	 * Apply an arbitrary perspective projection frustum transformation to this
	 * matrix and store the result in dest.
	 *
	 * If M is this matrix and F the frustum matrix, then the new matrix will be
	 * M * F. So when transforming a vector v with the new matrix by using M * F
	 * * v, the frustum transformation will be applied first! In order to set
	 * the matrix to a perspective frustum transformation without
	 * post-multiplying, use frustumNonPost().
	 *
	 * @param left the distance along the x-axis to the left frustum edge
	 * @param right the distance along the x-axis to the right frustum edge
	 * @param bottom the distance along the y-axis to the bottom frustum edge
	 * @param top the distance along the y-axis to the top frustum edge
	 * @param zNear the distance along the z-axis to the near clipping plane
	 * @param zFar the distance along the z-axis to the far clipping plane
	 * @return the frustum culled matrix
	 * @see Mat4f#frustumNonPost(float, float, float, float, float, float)
	 */
	public Mat4f frustum(float left, float right, float bottom, float top, float zNear, float zFar) {
		Mat4f res = new Mat4f();

		// calculate right matrix elements
		float rm00 = 2.0f * zNear / (right - left);
		float rm11 = 2.0f * zNear / (top - bottom);
		float rm20 = (right + left) / (right - left);
		float rm21 = (top + bottom) / (top - bottom);
		float rm22 = -(zFar + zNear) / (zFar - zNear);
		float rm32 = -2.0f * zFar * zNear / (zFar - zNear);
		// perform optimized matrix multiplication
		float nm20 = m[0][0] * rm20 + m[1][0] * rm21 + m[2][0] * rm22 - m[3][0];
		float nm21 = m[0][1] * rm20 + m[1][1] * rm21 + m[2][1] * rm22 - m[3][1];
		float nm22 = m[0][2] * rm20 + m[1][2] * rm21 + m[2][2] * rm22 - m[3][2];
		float nm23 = m[0][3] * rm20 + m[1][3] * rm21 + m[2][3] * rm22 - m[3][3];
		res.m[0][0] = m[0][0] * rm00;
		res.m[0][1] = m[0][1] * rm00;
		res.m[0][2] = m[0][2] * rm00;
		res.m[0][3] = m[0][3] * rm00;
		res.m[1][0] = m[1][0] * rm11;
		res.m[1][1] = m[1][1] * rm11;
		res.m[1][2] = m[1][2] * rm11;
		res.m[1][3] = m[1][3] * rm11;
		res.m[3][0] = m[2][0] * rm32;
		res.m[3][1] = m[2][1] * rm32;
		res.m[3][2] = m[2][2] * rm32;
		res.m[3][3] = m[2][3] * rm32;
		res.m[2][0] = nm20;
		res.m[2][1] = nm21;
		res.m[2][2] = nm22;
		res.m[2][3] = nm23;
		res.m[3][0] = m[3][0];
		res.m[3][1] = m[3][1];
		res.m[3][2] = m[3][2];
		res.m[3][3] = m[3][3];
		return res;
	}

	/**
	 * Set this matrix to be an arbitrary perspective projection frustum
	 * transformation.
	 *
	 * In order to apply the perspective frustum transformation to an existing
	 * transformation, use frustum().
	 *
	 * @param left the distance along the x-axis to the left frustum edge
	 * @param right the distance along the x-axis to the right frustum edge
	 * @param bottom the distance along the y-axis to the bottom frustum edge
	 * @param top the distance along the y-axis to the top frustum edge
	 * @param zNear the distance along the z-axis to the near clipping plane
	 * @param zFar the distance along the z-axis to the far clipping plane
	 * @return the frustum culled matrix
	 * @see Mat4f#frustum(float, float, float, float, float, float)
	 */
	public Mat4f frustumNonPost(float left, float right, float bottom, float top, float zNear, float zFar) {
		Mat4f res = new Mat4f();

		res.m[0][0] = 2.0f * zNear / (right - left);
		res.m[1][1] = 2.0f * zNear / (top - bottom);
		res.m[2][0] = (right + left) / (right - left);
		res.m[2][1] = (top + bottom) / (top - bottom);
		res.m[2][2] = -(zFar + zNear) / (zFar - zNear);
		res.m[2][3] = -1.0f;
		res.m[3][2] = -2.0f * zFar * zNear / (zFar - zNear);

		return res;
	}

	/**
	 * Obtain the direction of a ray starting at the center of the coordinate
	 * system and going through the near frustum plane.
	 *
	 * This method computes the dir vector in the local frame of any coordinate
	 * system that existed before this transformation was applied to it in order
	 * to yield homogeneous clipping space.
	 *
	 * The parameters x and y are used to interpolate the generated ray
	 * direction from the bottom-left to the top-right frustum corners.
	 *
	 * For optimal efficiency when building many ray directions over the whole
	 * frustum, it is recommended to use this method only in order to compute
	 * the four corner rays at (0, 0), (1, 0), (0, 1) and (1, 1) and then
	 * bilinearly interpolating between them
	 *
	 * @param x the interpolation factor along the left-to-right frustum planes,
	 *            within [0..1]
	 * @param y the interpolation factor along the bottom-to-top frustum planes,
	 *            within [0..1]
	 * @param dir will hold the normalized ray direction in the local frame of
	 *            the coordinate system before transforming to homogeneous
	 *            clipping space using this matrix
	 * @return the direction
	 */
	public Vec3f frustumRayDir(float x, float y) {
		/*
		 * This method works by first obtaining the frustum plane normals, then
		 * building the cross product to obtain the corner rays, and finall
		 * bilinearly interpolating to obtain the desired direction. The code
		 * below uses a condense form of doing all this making use of some
		 * mathematical identities to simplify the overall expression.
		 */
		float a = m[1][0] * m[2][3], b = m[1][3] * m[2][1], c = m[1][0] * m[2][1], d = m[1][1] * m[2][3];
		float e = m[1][3] * m[2][0], f = m[1][1] * m[2][0];
		float g = m[0][3] * m[2][0], h = m[0][1] * m[2][3], i = m[0][1] * m[2][0], j = m[0][3] * m[2][1];
		float k = m[0][0] * m[2][3], l = m[0][0] * m[2][1];
		float v = m[0][0] * m[1][3], n = m[0][3] * m[1][1], o = m[0][0] * m[1][1], p = m[0][1] * m[1][3];
		float q = m[0][3] * m[1][0], r = m[0][1] * m[1][0];

		float m1x = (d + e + f - a - b - c) * (1.0f - y) + (a - b - c + d - e + f) * y;
		float m1y = (j + k + l - g - h - i) * (1.0f - y) + (g - h - i + j - k + l) * y;
		float m1z = (p + q + r - v - n - o) * (1.0f - y) + (v - n - o + p - q + r) * y;
		float m2x = (b - c - d + e + f - a) * (1.0f - y) + (a + b - c - d - e + f) * y;
		float m2y = (h - i - j + k + l - g) * (1.0f - y) + (g + h - i - j - k + l) * y;
		float m2z = (n - o - p + q + r - v) * (1.0f - y) + (v + n - o - p - q + r) * y;

		float x_ = m1x * (1.0f - x) + m2x * x;
		float y_ = m1y * (1.0f - x) + m2y * x;
		float z_ = m1z * (1.0f - x) + m2z * x;

		return Vec3f.of(x_, y_, z_).normalize();
	}

	public Vec3f transform(Vec3f r) {
		return new Vec3f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3],
				m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3],
				m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3]);
	}

	public Mat4f add(Mat4f r) {
		final Mat4f res = new Mat4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.m[i][j] = m[i][0] + r.get(0, j) + m[i][1] + r.get(1, j) + m[i][2] + r.get(2, j) + m[i][3]
						+ r.get(3, j);
			}
		}

		return res;
	}

	public Mat4f sub(Mat4f r) {
		final Mat4f res = new Mat4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.m[i][j] = m[i][0] - r.get(0, j) + m[i][1] - r.get(1, j) + m[i][2] - r.get(2, j) + m[i][3]
						- r.get(3, j);
			}
		}

		return res;
	}

	public Mat4f mul(Mat4f r) {
		final Mat4f res = new Mat4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.m[i][j] = m[i][0] * r.get(0, j) + m[i][1] * r.get(1, j) + m[i][2] * r.get(2, j)
						+ m[i][3] * r.get(3, j);
			}
		}

		return res;
	}

	public Mat4f div(Mat4f r) {
		final Mat4f res = new Mat4f();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.m[i][j] = m[i][0] / r.get(0, j) + m[i][1] / r.get(1, j) + m[i][2] / r.get(2, j)
						+ m[i][3] / r.get(3, j);
			}
		}

		return res;
	}

	public Mat4f scale(Vec3f vec) {
		final Mat4f res = new Mat4f();
		res.m[0][0] = m[0][0] * vec.getX();
		res.m[0][1] = m[0][1] * vec.getX();
		res.m[0][2] = m[0][2] * vec.getX();
		res.m[0][3] = m[0][3] * vec.getX();
		res.m[1][0] = m[1][0] * vec.getY();
		res.m[1][1] = m[1][1] * vec.getY();
		res.m[1][2] = m[1][2] * vec.getY();
		res.m[1][3] = m[1][3] * vec.getY();
		res.m[2][0] = m[2][0] * vec.getZ();
		res.m[2][1] = m[2][1] * vec.getZ();
		res.m[2][2] = m[2][2] * vec.getZ();
		res.m[2][3] = m[2][3] * vec.getZ();
		return res;
	}

	public float[][] getM() {
		return m;
	}

	public float get(int x, int y) {
		return m[x][y];
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
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < FIELDS; j++) {
				data[offset++] = (byte) (Float.floatToIntBits(m[i][j]) >> 24);
				data[offset++] = (byte) (Float.floatToIntBits(m[i][j]) >> 16);
				data[offset++] = (byte) (Float.floatToIntBits(m[i][j]) >> 8);
				data[offset++] = (byte) (Float.floatToIntBits(m[i][j]));
			}
		}
		return data;
	}

	/**
	 * Converts the specified byte array (length >= 3) to a new vector
	 *
	 * @param data the byte data (0=X,1=Y,2=Z)
	 * @return the new vector from the specified byte array
	 */
	public static Mat4f fromBytes(byte[] data) {
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
	public static Mat4f fromBytes(byte[] data, int offset) {
		float[][] values = new float[GROUPS][FIELDS];
		for (int i = 0; i < GROUPS; i++) {
			for (int j = 0; j < FIELDS; j++) {
				values[i][j] = Float.intBitsToFloat((data[offset++] & 0xFF) << 24 | (data[offset++] & 0xFF) << 16
						| (data[offset++] & 0xFF) << 8 | (data[offset++] & 0xFF));
			}
		}
		return new Mat4f(values);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(m);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Mat4f other = (Mat4f) obj;
		if (!Arrays.deepEquals(m, other.m)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "mat4f[" + "(" + get(0, 0) + "/" + get(0, 1) + "/" + get(0, 2) + "/" + get(0, 3) + ")," + "(" + get(1, 0)
				+ "/" + get(1, 1) + "/" + get(1, 2) + "/" + get(1, 3) + ")," + "(" + get(2, 0) + "/" + get(2, 1) + "/"
				+ get(2, 2) + "/" + get(2, 3) + ")," + "(" + get(3, 0) + "/" + get(3, 1) + "/" + get(3, 2) + "/"
				+ get(3, 3) + ")" + "]";
	}

}
