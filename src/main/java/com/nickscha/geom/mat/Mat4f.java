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

public final class Mat4f {

    /**
     * Defines how much bytes will be needed to store this type as binary
     */
    public static final byte BYTES = 64;

    private float[][] m;

    public Mat4f() {
        this.m = new float[4][4];
    }

    public Mat4f initIdentity() {
        m[0][0] = 1;
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = 0;
        m[1][0] = 0;
        m[1][1] = 1;
        m[1][2] = 0;
        m[1][3] = 0;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = 1;
        m[2][3] = 0;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    public Mat4f initTranslation(float x, float y, float z) {
        m[0][0] = 1;
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = x;
        m[1][0] = 0;
        m[1][1] = 1;
        m[1][2] = 0;
        m[1][3] = y;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = 1;
        m[2][3] = z;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    public Mat4f initRotation(float x, float y, float z) {
        Mat4f rx = new Mat4f();
        Mat4f ry = new Mat4f();
        Mat4f rz = new Mat4f();

        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);

        rz.m[0][0] = (float) Math.cos(z);
        rz.m[0][1] = -(float) Math.sin(z);
        rz.m[0][2] = 0;
        rz.m[0][3] = 0;
        rz.m[1][0] = (float) Math.sin(z);
        rz.m[1][1] = (float) Math.cos(z);
        rz.m[1][2] = 0;
        rz.m[1][3] = 0;
        rz.m[2][0] = 0;
        rz.m[2][1] = 0;
        rz.m[2][2] = 1;
        rz.m[2][3] = 0;
        rz.m[3][0] = 0;
        rz.m[3][1] = 0;
        rz.m[3][2] = 0;
        rz.m[3][3] = 1;

        rx.m[0][0] = 1;
        rx.m[0][1] = 0;
        rx.m[0][2] = 0;
        rx.m[0][3] = 0;
        rx.m[1][0] = 0;
        rx.m[1][1] = (float) Math.cos(x);
        rx.m[1][2] = -(float) Math.sin(x);
        rx.m[1][3] = 0;
        rx.m[2][0] = 0;
        rx.m[2][1] = (float) Math.sin(x);
        rx.m[2][2] = (float) Math.cos(x);
        rx.m[2][3] = 0;
        rx.m[3][0] = 0;
        rx.m[3][1] = 0;
        rx.m[3][2] = 0;
        rx.m[3][3] = 1;

        ry.m[0][0] = (float) Math.cos(y);
        ry.m[0][1] = 0;
        ry.m[0][2] = -(float) Math.sin(y);
        ry.m[0][3] = 0;
        ry.m[1][0] = 0;
        ry.m[1][1] = 1;
        ry.m[1][2] = 0;
        ry.m[1][3] = 0;
        ry.m[2][0] = (float) Math.sin(y);
        ry.m[2][1] = 0;
        ry.m[2][2] = (float) Math.cos(y);
        ry.m[2][3] = 0;
        ry.m[3][0] = 0;
        ry.m[3][1] = 0;
        ry.m[3][2] = 0;
        ry.m[3][3] = 1;

        m = rz.mul(ry.mul(rx)).getM();

        return this;
    }

    public Mat4f initScale(float x, float y, float z) {
        m[0][0] = x;
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = 0;
        m[1][0] = 0;
        m[1][1] = y;
        m[1][2] = 0;
        m[1][3] = 0;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = z;
        m[2][3] = 0;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    public Mat4f initPerspective(float fov, float aspectRatio, float zNear, float zFar) {
        float tanHalfFOV = (float) Math.tan(fov / 2);
        float zRange = zNear - zFar;

        m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = 0;
        m[1][0] = 0;
        m[1][1] = 1.0f / tanHalfFOV;
        m[1][2] = 0;
        m[1][3] = 0;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = (-zNear - zFar) / zRange;
        m[2][3] = 2 * zFar * zNear / zRange;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 1;
        m[3][3] = 0;

        return this;
    }

    public Mat4f initOrthographic(float left, float right, float bottom, float top, float near, float far) {
        float width = right - left;
        float height = top - bottom;
        float depth = far - near;

        m[0][0] = 2 / width;
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = -(right + left) / width;
        m[1][0] = 0;
        m[1][1] = 2 / height;
        m[1][2] = 0;
        m[1][3] = -(top + bottom) / height;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = -2 / depth;
        m[2][3] = -(far + near) / depth;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    public Mat4f initRotation(Vec3f forward, Vec3f up) {
        Vec3f f = forward.normalize();

        Vec3f r = up.normalize();
        r = r.cross(f);

        Vec3f u = f.cross(r);

        return initRotation(f, u, r);
    }

    public Mat4f initRotation(Vec3f forward, Vec3f up, Vec3f right) {
        Vec3f f = forward;
        Vec3f r = right;
        Vec3f u = up;

        m[0][0] = r.getX();
        m[0][1] = r.getY();
        m[0][2] = r.getZ();
        m[0][3] = 0;
        m[1][0] = u.getX();
        m[1][1] = u.getY();
        m[1][2] = u.getZ();
        m[1][3] = 0;
        m[2][0] = f.getX();
        m[2][1] = f.getY();
        m[2][2] = f.getZ();
        m[2][3] = 0;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    /**
     * Return the determinant of this matrix.
     *
     * @return determinant of matrix
     */
    public float determinant() {
        return (m[0][0] * m[1][1] - m[0][1] * m[1][0])
                * (m[2][2] * m[3][3] - m[2][3] * m[3][2])
                - (m[0][0] * m[1][2] - m[0][2] * m[1][0])
                * (m[2][1] * m[3][3] - m[2][3] * m[3][1])
                + (m[0][0] * m[1][3] - m[0][3] * m[1][0])
                * (m[2][1] * m[3][2] - m[2][2] * m[3][1])
                + (m[0][1] * m[1][2] - m[0][2] * m[1][1])
                * (m[2][0] * m[3][3] - m[2][3] * m[3][0])
                - (m[0][1] * m[1][3] - m[0][3] * m[1][1])
                * (m[2][0] * m[3][2] - m[2][2] * m[3][0])
                + (m[0][2] * m[1][3] - m[0][3] * m[1][2])
                * (m[2][0] * m[3][1] - m[2][1] * m[3][0]);
    }

    /**
     * Return the determinant of the top-left 3x3 submatrix of this matrix.
     *
     * @return determinant top left 3x3
     */
    public float determinant3x3() {
        return m[0][0]
                * m[1][1]
                * m[2][2]
                + m[1][0]
                * m[2][1]
                * m[0][2]
                + m[2][0]
                * m[0][1]
                * m[1][2]
                - m[2][0]
                * m[1][1]
                * m[0][2]
                - m[0][0]
                * m[2][1]
                * m[1][2]
                - m[1][0]
                * m[0][1]
                * m[2][2];
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

        res.set(0, 0, 2.0f * zNear / (right - left));
        res.set(0, 1, 0.0f);
        res.set(0, 2, 0.0f);
        res.set(0, 3, 0.0f);
        res.set(1, 0, 0.0f);
        res.set(1, 1, 2.0f * zNear / (top - bottom));
        res.set(1, 2, 0.0f);
        res.set(1, 3, 0.0f);
        res.set(2, 0, (right + left) / (right - left));
        res.set(2, 1, (top + bottom) / (top - bottom));
        res.set(2, 2, -(zFar + zNear) / (zFar - zNear));
        res.set(2, 3, -1.0f);
        res.set(3, 0, 0.0f);
        res.set(3, 1, 0.0f);
        res.set(3, 2, -2.0f * zFar * zNear / (zFar - zNear));
        res.set(3, 3, 0.0f);

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
     * within [0..1]
     * @param y the interpolation factor along the bottom-to-top frustum planes,
     * within [0..1]
     * @param dir will hold the normalized ray direction in the local frame of
     * the coordinate system before transforming to homogeneous clipping space
     * using this matrix
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
        float a = m[1][0] * m[2][3], b = m[1][3] * m[2][1], c = m[1][0] * m[2][1], d = m[1][1] * m[2][3], e = m[1][3] * m[2][0], f = m[1][1] * m[2][0];
        float g = m[0][3] * m[2][0], h = m[0][1] * m[2][3], i = m[0][1] * m[2][0], j = m[0][3] * m[2][1], k = m[0][0] * m[2][3], l = m[0][0] * m[2][1];
        float m_ = m[0][0] * m[1][3], n = m[0][3] * m[1][1], o = m[0][0] * m[1][1], p = m[0][1] * m[1][3], q = m[0][3] * m[1][0], r = m[0][1] * m[1][0];
        float m1x, m1y, m1z;
        m1x = (d + e + f - a - b - c) * (1.0f - y) + (a - b - c + d - e + f) * y;
        m1y = (j + k + l - g - h - i) * (1.0f - y) + (g - h - i + j - k + l) * y;
        m1z = (p + q + r - m_ - n - o) * (1.0f - y) + (m_ - n - o + p - q + r) * y;
        float m2x, m2y, m2z;
        m2x = (b - c - d + e + f - a) * (1.0f - y) + (a + b - c - d - e + f) * y;
        m2y = (h - i - j + k + l - g) * (1.0f - y) + (g + h - i - j - k + l) * y;
        m2z = (n - o - p + q + r - m_) * (1.0f - y) + (m_ + n - o - p - q + r) * y;

        float x_ = m1x * (1.0f - x) + m2x * x;
        float y_ = m1y * (1.0f - x) + m2y * x;
        float z_ = m1z * (1.0f - x) + m2z * x;

        return Vec3f.of(x_, y_, z_).normalize();
    }

    public Vec3f transform(Vec3f r) {
        return new Vec3f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3],
                m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3], m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3]);
    }

    public Mat4f add(Mat4f r) {
        Mat4f res = new Mat4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, m[i][0] + r.get(0, j) + m[i][1] + r.get(1, j) + m[i][2] + r.get(2, j) + m[i][3] + r.get(3, j));
            }
        }

        return res;
    }

    public Mat4f sub(Mat4f r) {
        Mat4f res = new Mat4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, m[i][0] - r.get(0, j) + m[i][1] - r.get(1, j) + m[i][2] - r.get(2, j) + m[i][3] - r.get(3, j));
            }
        }

        return res;
    }

    public Mat4f mul(Mat4f r) {
        Mat4f res = new Mat4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, m[i][0] * r.get(0, j) + m[i][1] * r.get(1, j) + m[i][2] * r.get(2, j) + m[i][3] * r.get(3, j));
            }
        }

        return res;
    }

    public Mat4f div(Mat4f r) {
        Mat4f res = new Mat4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, m[i][0] / r.get(0, j) + m[i][1] / r.get(1, j) + m[i][2] / r.get(2, j) + m[i][3] / r.get(3, j));
            }
        }

        return res;
    }

    public float[][] getM() {
        float[][] res = new float[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[i][j] = m[i][j];
            }
        }

        return res;
    }

    public float get(int x, int y) {
        return m[x][y];
    }

    private void set(int x, int y, float value) {
        m[x][y] = value;
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
        return "mat4f[" + "(" + get(0, 0) + "/" + get(0, 1) + "/" + get(0, 2) + "/" + get(0, 3) + ")," + "(" + get(1, 0) + "/" + get(1, 1) + "/" + get(1, 2)
                + "/" + get(1, 3) + ")," + "(" + get(2, 0) + "/" + get(2, 1) + "/" + get(2, 2) + "/" + get(2, 3) + ")," + "(" + get(3, 0) + "/" + get(3, 1)
                + "/" + get(3, 2) + "/" + get(3, 3) + ")," + "]";
    }

}
