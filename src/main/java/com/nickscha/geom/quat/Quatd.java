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
package com.nickscha.geom.quat;

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.vec.Vec3d;
import com.nickscha.geom.vec.Vec4d;

/**
 * Quaternions are used to represent an orientation and rotation in 3D space.
 * They extend a rotation in three dimensions to a four dimensional one.
 * 
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.2
 *
 */
public final class Quatd {

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
     * Quaternion as an identity Quaternion
     */
    public static final Quatd IDENTITY = new Quatd(0, 0, 0, 1);

    private final double x, y, z, w;

    /**
     * 
     * @param x the x value of the quaternion.
     * @param y the y value of the quaternion.
     * @param z the z value of the quaternion.
     * @param w the w value of the quaternion.
     */
    public Quatd(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quatd(Vec3d axis, double angle) {
        double sinHalfAngle = (double) Math.sin(angle / 2);
        double cosHalfAngle = (double) Math.cos(angle / 2);

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }

    public Quatd(Mat4d rot) {
        double trace = rot.get(0, 0) + rot.get(1, 1) + rot.get(2, 2);
        double x_, y_, z_, w_;

        if (trace > 0) {
            double s = 0.5f / (double) Math.sqrt(trace + 1.0d);
            w_ = 0.25f / s;
            x_ = (rot.get(1, 2) - rot.get(2, 1)) * s;
            y_ = (rot.get(2, 0) - rot.get(0, 2)) * s;
            z_ = (rot.get(0, 1) - rot.get(1, 0)) * s;
        } else {
            if (rot.get(0, 0) > rot.get(1, 1) && rot.get(0, 0) > rot.get(2, 2)) {
                double s = 2.0f * (double) Math.sqrt(1.0d + rot.get(0, 0) - rot.get(1, 1) - rot.get(2, 2));
                w_ = (rot.get(1, 2) - rot.get(2, 1)) / s;
                x_ = 0.25f * s;
                y_ = (rot.get(1, 0) + rot.get(0, 1)) / s;
                z_ = (rot.get(2, 0) + rot.get(0, 2)) / s;
            } else if (rot.get(1, 1) > rot.get(2, 2)) {
                double s = 2.0f * (double) Math.sqrt(1.0d + rot.get(1, 1) - rot.get(0, 0) - rot.get(2, 2));
                w_ = (rot.get(2, 0) - rot.get(0, 2)) / s;
                x_ = (rot.get(1, 0) + rot.get(0, 1)) / s;
                y_ = 0.25f * s;
                z_ = (rot.get(2, 1) + rot.get(1, 2)) / s;
            } else {
                double s = 2.0f * (double) Math.sqrt(1.0d + rot.get(2, 2) - rot.get(0, 0) - rot.get(1, 1));
                w_ = (rot.get(0, 1) - rot.get(1, 0)) / s;
                x_ = (rot.get(2, 0) + rot.get(0, 2)) / s;
                y_ = (rot.get(1, 2) + rot.get(2, 1)) / s;
                z_ = 0.25f * s;
            }
        }

        double length = (double) Math.sqrt(x_ * x_ + y_ * y_ + z_ * z_ + w_ * w_);
        this.x = x_ / length;
        this.y = y_ / length;
        this.z = z_ / length;
        this.w = w_ / length;
    }

    public static Quatd of(double amt) {
        return new Quatd(amt, amt, amt, amt);
    }

    public static Quatd of(Mat4d rotation) {
        return new Quatd(rotation);
    }
    
    public static Quatd of(Quatd other) {
        return new Quatd(other.getX(), other.getY(), other.getZ(), other.getW());
    }


    public static Quatd of(double x, double y, double z, double w) {
        return new Quatd(x, y, z, w);
    }

    public static Quatd of(Vec3d axis, double angle) {
        return new Quatd(axis, angle);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    public double length() {
        return (double) Math.sqrt(lengthSquared());
    }

    /**
     * @return weather this vector is a zero vector
     */
    public boolean isZero() {
        return x == 0 && y == 0 && z == 0 && w == 0;
    }

    /**
     * @return true if this quaternion is an identity instance
     */
    public boolean isIdentity() {
        return (x == 0 && y == 0 && z == 0 && w == 1);
    }

    public Quatd normalize() {
        double length = length();

        return new Quatd(x / length, y / length, z / length, w / length);
    }

    public Quatd conjugate() {
        return new Quatd(-x, -y, -z, w);
    }

    public double dot(Quatd r) {
        return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
    }

    public Quatd negate() {
        return new Quatd(-x, -y, -z, -w);
    }

    public Quatd add(double r) {
        return new Quatd(x + r, y + r, z + r, w + r);
    }

    public Quatd add(Quatd r) {
        return new Quatd(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
    }

    public Quatd sub(double r) {
        return new Quatd(x - r, y - r, z - r, w - r);
    }

    public Quatd sub(Quatd r) {
        return new Quatd(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
    }

    public Quatd mul(double r) {
        return mul(Quatd.of(r));
    }

    public Quatd mul(Quatd r) {
        double w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        double x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        double y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        double z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quatd(x_, y_, z_, w_);
    }

    public Quatd mul(Vec3d r) {
        double w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        double x_ = w * r.getX() + y * r.getZ() - z * r.getY();
        double y_ = w * r.getY() + z * r.getX() - x * r.getZ();
        double z_ = w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quatd(x_, y_, z_, w_);
    }

    public Quatd div(double r) {
        return new Quatd(x / r, y / r, z / r, w / r);
    }

    public Quatd div(Quatd r) {
        return new Quatd(x / r.getX(), y / r.getY(), z / r.getZ(), w / r.getW());
    }

    public Vec3d getForward() {
        return new Vec3d(0, 0, 1).rotate(this);
    }

    public Vec3d getBack() {
        return new Vec3d(0, 0, -1).rotate(this);
    }

    public Vec3d getUp() {
        return new Vec3d(0, 1, 0).rotate(this);
    }

    public Vec3d getDown() {
        return new Vec3d(0, -1, 0).rotate(this);
    }

    public Vec3d getRight() {
        return new Vec3d(1, 0, 0).rotate(this);
    }

    public Vec3d getLeft() {
        return new Vec3d(-1, 0, 0).rotate(this);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public Vec4d vec4() {
        return new Vec4d(x, y, z, w);
    }

    /**
     * Transforms this quaternion representation into a rotation matrix.
     * @return the rotation matrix from this quaternion.
     */
    public Mat4d rotationMatrix() {
        Vec3d forward = new Vec3d(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0d - 2.0f * (x * x + y * y));
        Vec3d up = new Vec3d(2.0f * (x * y + w * z), 1.0d - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
        Vec3d right = new Vec3d(1.0d - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));

        return Mat4d.rotationMatrix(forward, up, right);
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
    public static Quatd fromBytes(byte[] data) {
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
    public static Quatd fromBytes(byte[] data, int offset) {
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
        return new Quatd(values[0], values[1], values[2], values[3]);
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
        Quatd other = (Quatd) obj;
        if (Double.doubleToLongBits(w) != Double.doubleToLongBits(other.w)) {
            return false;
        }
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return Double.doubleToLongBits(z) == Double.doubleToLongBits(other.z);
    }

    @Override
    public String toString() {
        return "quatd[x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
    }

}
