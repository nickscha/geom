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

import com.nickscha.geom.mat.Mat4f;
import com.nickscha.geom.vec.Vec3f;
import com.nickscha.geom.vec.Vec4f;

/**
 * Quaternions are used to represent an orientation and rotation in 3D space.
 * They extend a rotation in three dimensions to a four dimensional one.
 * 
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public final class Quatf {

    /**
     * Defines how much fields are stored in this class which will be used for
     * optimal binary serialization
     */
    public static final int FIELDS = 4;

    /**
     * Defines how much bytes will be needed to store this type as binary
     */
    public static final byte BYTES = 16;

    /**
     * Quaternion as an identity Quaternion
     */
    public static final Quatf IDENTITY = new Quatf(0, 0, 0, 1);

    private final float x, y, z, w;

    /**
     * 
     * @param x the x value of the quaternion.
     * @param y the y value of the quaternion.
     * @param z the z value of the quaternion.
     * @param w the w value of the quaternion.
     */
    public Quatf(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quatf(Vec3f axis, float angle) {
        float sinHalfAngle = (float) Math.sin(angle / 2);
        float cosHalfAngle = (float) Math.cos(angle / 2);

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }

    public Quatf(Mat4f rot) {
        float trace = rot.get(0, 0) + rot.get(1, 1) + rot.get(2, 2);
        float x_, y_, z_, w_;

        if (trace > 0) {
            float s = 0.5f / (float) Math.sqrt(trace + 1.0f);
            w_ = 0.25f / s;
            x_ = (rot.get(1, 2) - rot.get(2, 1)) * s;
            y_ = (rot.get(2, 0) - rot.get(0, 2)) * s;
            z_ = (rot.get(0, 1) - rot.get(1, 0)) * s;
        } else {
            if (rot.get(0, 0) > rot.get(1, 1) && rot.get(0, 0) > rot.get(2, 2)) {
                float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(0, 0) - rot.get(1, 1) - rot.get(2, 2));
                w_ = (rot.get(1, 2) - rot.get(2, 1)) / s;
                x_ = 0.25f * s;
                y_ = (rot.get(1, 0) + rot.get(0, 1)) / s;
                z_ = (rot.get(2, 0) + rot.get(0, 2)) / s;
            } else if (rot.get(1, 1) > rot.get(2, 2)) {
                float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(1, 1) - rot.get(0, 0) - rot.get(2, 2));
                w_ = (rot.get(2, 0) - rot.get(0, 2)) / s;
                x_ = (rot.get(1, 0) + rot.get(0, 1)) / s;
                y_ = 0.25f * s;
                z_ = (rot.get(2, 1) + rot.get(1, 2)) / s;
            } else {
                float s = 2.0f * (float) Math.sqrt(1.0f + rot.get(2, 2) - rot.get(0, 0) - rot.get(1, 1));
                w_ = (rot.get(0, 1) - rot.get(1, 0)) / s;
                x_ = (rot.get(2, 0) + rot.get(0, 2)) / s;
                y_ = (rot.get(1, 2) + rot.get(2, 1)) / s;
                z_ = 0.25f * s;
            }
        }

        float length = (float) Math.sqrt(x_ * x_ + y_ * y_ + z_ * z_ + w_ * w_);
        this.x = x_ / length;
        this.y = y_ / length;
        this.z = z_ / length;
        this.w = w_ / length;
    }

    public static Quatf of(float amt) {
        return new Quatf(amt, amt, amt, amt);
    }

    public static Quatf of(Mat4f rotation) {
        return new Quatf(rotation);
    }

    public static Quatf of(float x, float y, float z, float w) {
        return new Quatf(x, y, z, w);
    }

    public static Quatf of(Vec3f axis, float angle) {
        return new Quatf(axis, angle);
    }

    public float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
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

    public Quatf normalize() {
        float length = length();

        return new Quatf(x / length, y / length, z / length, w / length);
    }

    public Quatf conjugate() {
        return new Quatf(-x, -y, -z, w);
    }

    public float dot(Quatf r) {
        return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
    }

    public Quatf negate() {
        return new Quatf(-x, -y, -z, -w);
    }

    // public Quatf lerp(Quatf dest, float lerpFactor, boolean shortest) {
    // Quatf correctedDest = dest;
    //
    // if (shortest && this.dot(dest) < 0) {
    // correctedDest = new Quatf(-dest.getX(), -dest.getY(), -dest.getZ(),
    // -dest.getW());
    // }
    //
    // return correctedDest.sub(this).mul(lerpFactor).add(this);
    // }
    //
    // public Quatf nlerp(Quatf dest, float lerpFactor, boolean shortest) {
    // return lerp(dest, lerpFactor, shortest).normalize();
    // }
    //
    // public Quatf slerp(Quatf dest, float lerpFactor, boolean shortest) {
    // final float EPSILON = 1e3f;
    //
    // float cos = this.dot(dest);
    // Quatf correctedDest = dest;
    //
    // if (shortest && cos < 0) {
    // cos = -cos;
    // correctedDest = dest.negate();
    // }
    //
    // if (Math.abs(cos) >= 1 - EPSILON) {
    // return nlerp(correctedDest, lerpFactor, false);
    // }
    //
    // float sin = (float) Math.sqrt(1.0f - cos * cos);
    // float angle = (float) Math.atan2(sin, cos);
    // float invSin = 1.0f / sin;
    //
    // float srcFactor = (float) Math.sin((1.0f - lerpFactor) * angle) * invSin;
    // float destFactor = (float) Math.sin((lerpFactor) * angle) * invSin;
    //
    // return this.mul(srcFactor).add(correctedDest.mul(destFactor));
    // }
    //
    // /**
    // * Calculates (this quaternion)^alpha where alpha is a real number and
    // * stores the result in this quaternion. See
    // *
    // http://en.wikipedia.org/wiki/Quaternion#Exponential.2C_logarithm.2C_and_power
    // *
    // * @param alpha Exponent
    // * @return new quaternion for chaining
    // */
    // public Quatf exp(float alpha) {
    // // Calculate |q|^alpha
    // float norm = length();
    // float normExp = (float) Math.pow(norm, alpha);
    // // Calculate theta
    // float theta = (float) Math.acos(w / norm);
    // // Calculate coefficient of basis elements
    // float coeff;
    // if (Math.abs(theta) < 0.001) // If theta is small enough, use the limit
    // // of sin(alpha*theta) / sin(theta)
    // // instead of actual value
    // {
    // coeff = normExp * alpha / norm;
    // } else {
    // coeff = (float) (normExp * Math.sin(alpha * theta) / (norm *
    // Math.sin(theta)));
    // }
    // // Write results
    // float w_ = (float) (normExp * Math.cos(alpha * theta));
    // float x_ = x * coeff;
    // float y_ = y * coeff;
    // float z_ = z * coeff;
    // // Fix any possible discrepancies
    // return new Quatf(x_, y_, z_, w_).normalize();
    // }

    public Quatf add(float r) {
        return new Quatf(x + r, y + r, z + r, w + r);
    }

    public Quatf add(Quatf r) {
        return new Quatf(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
    }

    public Quatf sub(float r) {
        return new Quatf(x - r, y - r, z - r, w - r);
    }

    public Quatf sub(Quatf r) {
        return new Quatf(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
    }

    public Quatf mul(float r) {
        return mul(Quatf.of(r));
    }

    public Quatf mul(Quatf r) {
        float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quatf(x_, y_, z_, w_);
    }

    public Quatf mul(Vec3f r) {
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = w * r.getZ() + x * r.getY() - y * r.getX();

        return new Quatf(x_, y_, z_, w_);
    }

    public Quatf div(float r) {
        return new Quatf(x / r, y / r, z / r, w / r);
    }

    public Quatf div(Quatf r) {
        return new Quatf(x / r.getX(), y / r.getY(), z / r.getZ(), w / r.getW());
    }

    public Vec3f getForward() {
        return new Vec3f(0, 0, 1).rotate(this);
    }

    public Vec3f getBack() {
        return new Vec3f(0, 0, -1).rotate(this);
    }

    public Vec3f getUp() {
        return new Vec3f(0, 1, 0).rotate(this);
    }

    public Vec3f getDown() {
        return new Vec3f(0, -1, 0).rotate(this);
    }

    public Vec3f getRight() {
        return new Vec3f(1, 0, 0).rotate(this);
    }

    public Vec3f getLeft() {
        return new Vec3f(-1, 0, 0).rotate(this);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public Vec4f vec4() {
        return new Vec4f(x, y, z, w);
    }

    /**
     * Transforms this quaternion representation into a rotation matrix.
     * @return the rotation matrix from this quaternion.
     */
    public Mat4f rotationMatrix() {
        Vec3f forward = new Vec3f(2.0f * (x * z - w * y), 2.0f * (y * z + w * x), 1.0f - 2.0f * (x * x + y * y));
        Vec3f up = new Vec3f(2.0f * (x * y + w * z), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * x));
        Vec3f right = new Vec3f(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - w * z), 2.0f * (x * z + w * y));

        return Mat4f.rotationMatrix(forward, up, right);
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
        int[] values = new int[] { Float.floatToIntBits(x), Float.floatToIntBits(y), Float.floatToIntBits(z), Float.floatToIntBits(w) };
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
    public static Quatf fromBytes(byte[] data) {
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
    public static Quatf fromBytes(byte[] data, int offset) {
        float[] values = new float[FIELDS];
        for (int i = 0; i < FIELDS; i++) {
            values[i] = Float.intBitsToFloat((data[offset++] & 0xFF) << 24 | (data[offset++] & 0xFF) << 16 | (data[offset++] & 0xFF) << 8 | (data[offset++] & 0xFF));
        }
        return new Quatf(values[0], values[1], values[2], values[3]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(w);
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        result = prime * result + Float.floatToIntBits(z);
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
        Quatf other = (Quatf) obj;
        if (Float.floatToIntBits(w) != Float.floatToIntBits(other.w)) {
            return false;
        }
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x)) {
            return false;
        }
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y)) {
            return false;
        }
        return Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
    }

    @Override
    public String toString() {
        return "quatf[x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + "]";
    }

}
