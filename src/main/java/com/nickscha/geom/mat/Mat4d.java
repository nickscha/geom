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

import com.nickscha.geom.vec.Vec3d;

/**
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.2
 *
 */
public final class Mat4d {

    /**
     * Defines how much many groups are set for the amount of fields.
     */
    public static final int GROUPS = 4;

    /**
     * Defines how much fields are stored per group in this class which will be used
     * for optimal binary serialization
     */
    public static final int FIELDS = 4;

    /**
     * Defines how much bytes will be needed to store this type as binary
     */
    public static final short BYTES = 128;

    /**
     * Represents an empty matrix
     */
    public static final Mat4d ZERO = new Mat4d();

    /**
     * Represents an identity matrix
     */
    public static final Mat4d IDENTITY = Mat4d.identity();

    private final double[][] m;

    /**
     * Initializes the matrix where all fields are set to zero.
     */
    public Mat4d() {
        this.m = new double[GROUPS][FIELDS];
    }

    public Mat4d(double amt) {
        this();
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                this.m[i][j] = amt;
            }
        }
    }

    public Mat4d(double[][] m) {
        this.m = m;
    }

    public static Mat4d of(double amt) {
        return new Mat4d(amt);
    }

    public static Mat4d of(double[][] m) {
        return new Mat4d(m);
    }

    public static Mat4d identity() {
        final double[][] m = new double[GROUPS][FIELDS];

        m[0][0] = 1;
        m[1][1] = 1;
        m[2][2] = 1;
        m[3][3] = 1;

        return new Mat4d(m);
    }
    
    public static Mat4d translationMatrix(double x, double y, double z) {
        final double[][] m = new double[GROUPS][FIELDS];

        m[0][0] = 1;
        m[0][3] = x;
        m[1][1] = 1;
        m[1][3] = y;
        m[2][2] = 1;
        m[2][3] = z;
        m[3][3] = 1;

        return new Mat4d(m);
    }

    public static Mat4d scaleMatrix(Vec3d scale) {
        return scaleMatrix(scale.getX(), scale.getY(), scale.getZ());
    }

    public static Mat4d scaleMatrix(double x) {
        return scaleMatrix(x, x, x);
    }

    public static Mat4d scaleMatrix(double x, double y, double z) {
        final double[][] m = new double[GROUPS][FIELDS];

        m[0][0] = x;
        m[1][1] = y;
        m[2][2] = z;
        m[3][3] = 1;

        return new Mat4d(m);
    }
    
    public static Mat4d modelMatrix(Vec3d position) {
        return modelMatrix(position, 1.0d);
    }

    public static Mat4d modelMatrix(Vec3d position, double scale) {
        Mat4d modelMatrix = Mat4d.translationMatrix(position.getX(), position.getY(), position.getZ());
        if(scale != 1.0d) {
            modelMatrix.scale(Vec3d.of(scale));
        }
        return modelMatrix;
    }

    public static Mat4d viewMatrix(Vec3d position, Mat4d rotationMatrix) {
        Mat4d translation = Mat4d.translationMatrix(position.getX(), position.getY(), position.getZ());
        return rotationMatrix.mul(translation);
    }

    /**
     * 
     * @param fov the field of view
     * @param aspectRatio the aspect ratio (for example: screen.width/screen.height)
     * @param zNear first distance to consider (clip object to near)
     * @param zFar last distance to ignore (clip object to far)
     * @return the projection (perspective) matrix for supplied parameters
     * @see #initPerspective(double, double, double, double)
     */
    public static Mat4d projectionMatrix(double fov, double aspectRatio, double zNear, double zFar) {
        return perspectiveMatrix(fov, aspectRatio, zNear, zFar);
    }

    public static Mat4d mvpMatrix(Mat4d modelMatrix, Mat4d viewMatrix, Mat4d projectionMatrix) {
        return projectionMatrix.mul(viewMatrix).mul(modelMatrix);
    }

    public static Mat4d perspectiveMatrix(double fov, double width, double height, double zNear, double zFar) {
        return perspectiveMatrix(fov, width / height, zNear, zFar);
    }

    /**
     * 
     * 
     * @param fov the field of view
     * @param aspectRatio the aspect ratio (for example: screen.width/screen.height)
     * @param zNear first distance to consider (clip object to near)
     * @param zFar last distance to ignore (clip object to far)
     * @return the perspective (projection) matrix for supplied parameters
     * @see #projectionMatrix(double, double, double, double)
     */
    public static Mat4d perspectiveMatrix(double fov, double aspectRatio, double zNear, double zFar) {
        double tanHalfFOV = (double) Math.tan(fov / 2);
        double zRange = zNear - zFar;

        final double[][] m = new double[GROUPS][FIELDS];
        m[0][0] = 1.0d / (tanHalfFOV * aspectRatio);
        m[1][1] = 1.0d / tanHalfFOV;
        m[2][2] = (-zNear - zFar) / zRange;
        m[2][3] = 2 * zFar * zNear / zRange;
        m[3][2] = 1;

        return new Mat4d(m);
    }

    public static Mat4d orthographicMatrix(double left, double right, double bottom, double top, double near, double far) {
        double width = right - left;
        double height = top - bottom;
        double depth = far - near;

        final double[][] m = new double[GROUPS][FIELDS];
        m[0][0] = 2 / width;
        m[0][3] = -(right + left) / width;
        m[1][1] = 2 / height;
        m[1][3] = -(top + bottom) / height;
        m[2][2] = -2 / depth;
        m[2][3] = -(far + near) / depth;
        m[3][3] = 1;

        return new Mat4d(m);
    }

    public static Mat4d rotationMatrix(Vec3d forward, Vec3d up) {
        Vec3d f = forward.normalize();

        Vec3d r = up.normalize();
        r = r.cross(f);

        Vec3d u = f.cross(r);

        return rotationMatrix(f, u, r);
    }

    public static Mat4d rotationMatrix(Vec3d forward, Vec3d up, Vec3d right) {
        Vec3d f = forward;
        Vec3d r = right;
        Vec3d u = up;

        final double[][] m = new double[GROUPS][FIELDS];
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

        return new Mat4d(m);
    }

    public static Mat4d rotationMatrix(double x, double y, double z) {
        Mat4d rx = new Mat4d();
        Mat4d ry = new Mat4d();
        Mat4d rz = new Mat4d();

        x = (double) Math.toRadians(x);
        y = (double) Math.toRadians(y);
        z = (double) Math.toRadians(z);

        final double cosZ = (double) Math.cos(z);
        final double sinZ = (double) Math.sin(z);
        final double cosX = (double) Math.cos(x);
        final double sinX = (double) Math.sin(x);
        final double cosY = (double) Math.cos(y);
        final double sinY = (double) Math.sin(y);

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

        return new Mat4d(rz.mul(ry.mul(rx)).getM());
    }

    public static Mat4d lookAtMatrix(Vec3d eye, Vec3d target, Vec3d up) {
        Vec3d f = target.sub(eye).normalize();
        Vec3d s = f.cross(up).normalize();
        Vec3d u = s.cross(f);

        Mat4d res = new Mat4d();
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
        res.m[3][3] = 1.0d;

        return res;
    }

    /**
     * Return the determinant of this matrix.
     *
     * @return determinant of matrix
     */
    public double determinant() {
        return (m[0][0] * m[1][1] - m[0][1] * m[1][0]) * (m[2][2] * m[3][3] - m[2][3] * m[3][2]) - (m[0][0] * m[1][2] - m[0][2] * m[1][0]) * (m[2][1] * m[3][3] - m[2][3] * m[3][1])
                + (m[0][0] * m[1][3] - m[0][3] * m[1][0]) * (m[2][1] * m[3][2] - m[2][2] * m[3][1]) + (m[0][1] * m[1][2] - m[0][2] * m[1][1]) * (m[2][0] * m[3][3] - m[2][3] * m[3][0])
                - (m[0][1] * m[1][3] - m[0][3] * m[1][1]) * (m[2][0] * m[3][2] - m[2][2] * m[3][0]) + (m[0][2] * m[1][3] - m[0][3] * m[1][2]) * (m[2][0] * m[3][1] - m[2][1] * m[3][0]);
    }

    /**
     * Return the determinant of the top-left 3x3 submatrix of this matrix.
     *
     * @return determinant top left 3x3
     */
    public double determinant3x3() {
        return m[0][0] * m[1][1] * m[2][2] + m[1][0] * m[2][1] * m[0][2] + m[2][0] * m[0][1] * m[1][2] - m[2][0] * m[1][1] * m[0][2] - m[0][0] * m[2][1] * m[1][2] - m[1][0] * m[0][1] * m[2][2];
    }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this
     * matrix and store the result in dest.
     *
     * If M is this matrix and F the frustum matrix, then the new matrix will be M *
     * F. So when transforming a vector v with the new matrix by using M * F * v,
     * the frustum transformation will be applied first! In order to set the matrix
     * to a perspective frustum transformation without post-multiplying, use
     * frustumNonPost().
     *
     * @param left the distance along the x-axis to the left frustum edge
     * @param right the distance along the x-axis to the right frustum edge
     * @param bottom the distance along the y-axis to the bottom frustum edge
     * @param top the distance along the y-axis to the top frustum edge
     * @param zNear the distance along the z-axis to the near clipping plane
     * @param zFar the distance along the z-axis to the far clipping plane
     * @return the frustum culled matrix
     * @see Mat4d#frustumNonPost(double, double, double, double, double, double)
     */
    public Mat4d frustum(double left, double right, double bottom, double top, double zNear, double zFar) {
        Mat4d res = new Mat4d();

        // calculate right matrix elements
        double rm00 = 2.0d * zNear / (right - left);
        double rm11 = 2.0d * zNear / (top - bottom);
        double rm20 = (right + left) / (right - left);
        double rm21 = (top + bottom) / (top - bottom);
        double rm22 = -(zFar + zNear) / (zFar - zNear);
        double rm32 = -2.0d * zFar * zNear / (zFar - zNear);
        // perform optimized matrix multiplication
        double nm20 = m[0][0] * rm20 + m[1][0] * rm21 + m[2][0] * rm22 - m[3][0];
        double nm21 = m[0][1] * rm20 + m[1][1] * rm21 + m[2][1] * rm22 - m[3][1];
        double nm22 = m[0][2] * rm20 + m[1][2] * rm21 + m[2][2] * rm22 - m[3][2];
        double nm23 = m[0][3] * rm20 + m[1][3] * rm21 + m[2][3] * rm22 - m[3][3];
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
     * @see Mat4d#frustum(double, double, double, double, double, double)
     */
    public Mat4d frustumNonPost(double left, double right, double bottom, double top, double zNear, double zFar) {
        Mat4d res = new Mat4d();

        res.m[0][0] = 2.0d * zNear / (right - left);
        res.m[1][1] = 2.0d * zNear / (top - bottom);
        res.m[2][0] = (right + left) / (right - left);
        res.m[2][1] = (top + bottom) / (top - bottom);
        res.m[2][2] = -(zFar + zNear) / (zFar - zNear);
        res.m[2][3] = -1.0d;
        res.m[3][2] = -2.0d * zFar * zNear / (zFar - zNear);

        return res;
    }

    /**
     * Obtain the direction of a ray starting at the center of the coordinate system
     * and going through the near frustum plane.
     *
     * This method computes the dir vector in the local frame of any coordinate
     * system that existed before this transformation was applied to it in order to
     * yield homogeneous clipping space.
     *
     * The parameters x and y are used to interpolate the generated ray direction
     * from the bottom-left to the top-right frustum corners.
     *
     * For optimal efficiency when building many ray directions over the whole
     * frustum, it is recommended to use this method only in order to compute the
     * four corner rays at (0, 0), (1, 0), (0, 1) and (1, 1) and then bilinearly
     * interpolating between them
     *
     * @param x the interpolation factor along the left-to-right frustum planes,
     *            within [0..1]
     * @param y the interpolation factor along the bottom-to-top frustum planes,
     *            within [0..1]
     * @param dir will hold the normalized ray direction in the local frame of the
     *            coordinate system before transforming to homogeneous clipping
     *            space using this matrix
     * @return the direction
     */
    public Vec3d frustumRayDir(double x, double y) {
        /*
         * This method works by first obtaining the frustum plane normals, then building
         * the cross product to obtain the corner rays, and finall bilinearly
         * interpolating to obtain the desired direction. The code below uses a condense
         * form of doing all this making use of some mathematical identities to simplify
         * the overall expression.
         */
        double a = m[1][0] * m[2][3], b = m[1][3] * m[2][1], c = m[1][0] * m[2][1], d = m[1][1] * m[2][3];
        double e = m[1][3] * m[2][0], f = m[1][1] * m[2][0];
        double g = m[0][3] * m[2][0], h = m[0][1] * m[2][3], i = m[0][1] * m[2][0], j = m[0][3] * m[2][1];
        double k = m[0][0] * m[2][3], l = m[0][0] * m[2][1];
        double v = m[0][0] * m[1][3], n = m[0][3] * m[1][1], o = m[0][0] * m[1][1], p = m[0][1] * m[1][3];
        double q = m[0][3] * m[1][0], r = m[0][1] * m[1][0];

        double m1x = (d + e + f - a - b - c) * (1.0d - y) + (a - b - c + d - e + f) * y;
        double m1y = (j + k + l - g - h - i) * (1.0d - y) + (g - h - i + j - k + l) * y;
        double m1z = (p + q + r - v - n - o) * (1.0d - y) + (v - n - o + p - q + r) * y;
        double m2x = (b - c - d + e + f - a) * (1.0d - y) + (a + b - c - d - e + f) * y;
        double m2y = (h - i - j + k + l - g) * (1.0d - y) + (g + h - i - j - k + l) * y;
        double m2z = (n - o - p + q + r - v) * (1.0d - y) + (v + n - o - p - q + r) * y;

        double x_ = m1x * (1.0d - x) + m2x * x;
        double y_ = m1y * (1.0d - x) + m2y * x;
        double z_ = m1z * (1.0d - x) + m2z * x;

        return Vec3d.of(x_, y_, z_).normalize();
    }

    public Vec3d transform(Vec3d r) {
        return new Vec3d(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3], m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3],
                m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3]);
    }

    public Mat4d add(Mat4d r) {
        final Mat4d res = new Mat4d();
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                res.m[i][j] = m[i][0] + r.get(0, j) + m[i][1] + r.get(1, j) + m[i][2] + r.get(2, j) + m[i][3] + r.get(3, j);
            }
        }

        return res;
    }

    public Mat4d sub(Mat4d r) {
        final Mat4d res = new Mat4d();
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                res.m[i][j] = m[i][0] - r.get(0, j) + m[i][1] - r.get(1, j) + m[i][2] - r.get(2, j) + m[i][3] - r.get(3, j);
            }
        }

        return res;
    }

    public Mat4d mul(Mat4d r) {
        final Mat4d res = new Mat4d();
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                res.m[i][j] = m[i][0] * r.get(0, j) + m[i][1] * r.get(1, j) + m[i][2] * r.get(2, j) + m[i][3] * r.get(3, j);
            }
        }

        return res;
    }

    public Mat4d div(Mat4d r) {
        final Mat4d res = new Mat4d();
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                res.m[i][j] = m[i][0] / r.get(0, j) + m[i][1] / r.get(1, j) + m[i][2] / r.get(2, j) + m[i][3] / r.get(3, j);
            }
        }

        return res;
    }

    public Mat4d scale(Vec3d vec) {
        final Mat4d res = new Mat4d();
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

    public double[][] getM() {
        return m;
    }

    public double get(int x, int y) {
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
                long value = Double.doubleToLongBits(m[i][j]);
                data[offset++] = (byte) (value >> 56);
                data[offset++] = (byte) (value >> 48);
                data[offset++] = (byte) (value >> 40);
                data[offset++] = (byte) (value >> 32);
                data[offset++] = (byte) (value >> 24);
                data[offset++] = (byte) (value >> 16);
                data[offset++] = (byte) (value >> 8);
                data[offset++] = (byte) (value >> 0);
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
    public static Mat4d fromBytes(byte[] data) {
        return fromBytes(data, 0);
    }

    /**
     * Converts the specified byte array (length >= 3) to a new vector by the given
     * offset
     *
     * @param data the byte data (0=X,1=Y,2=Z)
     * @param offset
     * @return the new vector from the specified byte array and offset
     */
    public static Mat4d fromBytes(byte[] data, int offset) {
        
        double[][] values = new double[GROUPS][FIELDS];
        for (int i = 0; i < GROUPS; i++) {
            for (int j = 0; j < FIELDS; j++) {
                values[i][j] = Double.longBitsToDouble(
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
        }
        return new Mat4d(values);
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
        Mat4d other = (Mat4d) obj;
        if (!Arrays.deepEquals(m, other.m)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mat4d[" + "(" + get(0, 0) + "/" + get(0, 1) + "/" + get(0, 2) + "/" + get(0, 3) + ")," + "(" + get(1, 0) + "/" + get(1, 1) + "/" + get(1, 2) + "/" + get(1, 3) + ")," + "(" + get(2, 0)
                + "/" + get(2, 1) + "/" + get(2, 2) + "/" + get(2, 3) + ")," + "(" + get(3, 0) + "/" + get(3, 1) + "/" + get(3, 2) + "/" + get(3, 3) + ")" + "]";
    }

}
