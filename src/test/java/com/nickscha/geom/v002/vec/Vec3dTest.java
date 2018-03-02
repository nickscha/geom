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
package com.nickscha.geom.v002.vec;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.quat.Quatd;
import com.nickscha.geom.vec.Vec2d;
import com.nickscha.geom.vec.Vec3d;
import com.nickscha.geom.vec.Vec4d;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Vec3dTest {

    Vec3d first = Vec3d.of(6, 4, 2);
    Vec3d second = Vec3d.of(4, 2, 1);

    @Test
    public void testInit() {
        Vec3d result = new Vec3d(2);
        Vec3d expected = Vec3d.of(2, 2, 2);

        Assert.assertEquals(expected, result);

        result = Vec3d.of(2);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testOfOther() {
        Vec3d other = Vec3d.of(6, 2, 1);
        Assert.assertEquals(Vec3d.of(6, 2, 1), Vec3d.of(other));
    }

    @Test
    public void testTranslationMatrix() {
        Mat4d res = Vec3d.of(6, 2, 1).translationMatrix();

        double[][] expArr = new double[4][4];
        expArr[0][0] = 1;
        expArr[0][1] = 0;
        expArr[0][2] = 0;
        expArr[0][3] = 6;
        expArr[1][0] = 0;
        expArr[1][1] = 1;
        expArr[1][2] = 0;
        expArr[1][3] = 2;
        expArr[2][0] = 0;
        expArr[2][1] = 0;
        expArr[2][2] = 1;
        expArr[2][3] = 1;
        expArr[3][0] = 0;
        expArr[3][1] = 0;
        expArr[3][2] = 0;
        expArr[3][3] = 1;

        Assert.assertEquals(Mat4d.of(expArr), res);
    }

    @Test
    public void testScaleMatrix() {
        Mat4d res = Vec3d.of(6, 2, 1).scaleMatrix();

        double[][] expArr = new double[4][4];
        expArr[0][0] = 6;
        expArr[0][1] = 0;
        expArr[0][2] = 0;
        expArr[0][3] = 0;
        expArr[1][0] = 0;
        expArr[1][1] = 2;
        expArr[1][2] = 0;
        expArr[1][3] = 0;
        expArr[2][0] = 0;
        expArr[2][1] = 0;
        expArr[2][2] = 1;
        expArr[2][3] = 0;
        expArr[3][0] = 0;
        expArr[3][1] = 0;
        expArr[3][2] = 0;
        expArr[3][3] = 1;

        Assert.assertEquals(Mat4d.of(expArr), res);
    }

    @Test
    public void testRotationMatrix() {
        Mat4d res = Vec3d.of(6, 2, 1).rotationMatrix();

        double[][] expArr = new double[4][4];
        expArr[0][0] = 0.9992386149554826d;
        expArr[0][1] = -0.02100423548120956d;
        expArr[0][2] = -0.03287875413848234d;
        expArr[0][3] = 0;
        expArr[1][0] = 0.017441774902830158d;
        expArr[1][1] = 0.9943067586491261d;
        expArr[1][2] = -0.10511828667224181d;
        expArr[1][3] = 0;
        expArr[2][0] = 0.03489949670250097d;
        expArr[2][1] = 0.10446478735209536d;
        expArr[2][2] = 0.9939160595006973d;
        expArr[2][3] = 0;
        expArr[3][0] = 0;
        expArr[3][1] = 0;
        expArr[3][2] = 0;
        expArr[3][3] = 1;

        Assert.assertEquals(Mat4d.of(expArr), res);
    }

    @Test
    public void testAdd() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.add(two);
        Vec3d expected = Vec3d.of(3, 3, 3);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddAmt() {
        Vec3d one = Vec3d.of(1, 1, 1);

        Vec3d result = one.add(2f);
        Vec3d expected = Vec3d.of(3, 3, 3);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSub() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.sub(two);
        Vec3d expected = Vec3d.of(-1, -1, -1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSubAmt() {
        Vec3d one = Vec3d.of(1, 1, 1);

        Vec3d result = one.sub(2f);
        Vec3d expected = Vec3d.of(-1, -1, -1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMul() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.mul(two);
        Vec3d expected = Vec3d.of(2, 2, 2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMulAmt() {
        Vec3d one = Vec3d.of(1, 1, 1);

        Vec3d result = one.mul(2f);
        Vec3d expected = Vec3d.of(2, 2, 2);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testDiv() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.div(two);
        Vec3d expected = Vec3d.of(0.5f, 0.5f, 0.5f);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testDivAmt() {
        Vec3d one = Vec3d.of(1, 1, 1);

        Vec3d result = one.div(2);
        Vec3d expected = Vec3d.of(0.5f, 0.5f, 0.5f);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMin() {
        Vec3d one = Vec3d.of(1, 2, 3);

        double result = one.min();

        Assert.assertTrue(1f == result);
    }

    @Test
    public void testMinVec() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.min(two);

        Assert.assertEquals(one, result);
    }

    @Test
    public void testMax() {
        Vec3d one = Vec3d.of(1, 2, 3);

        double result = one.max();

        Assert.assertTrue(3f == result);
    }

    @Test
    public void testMaxVec() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.max(two);

        Assert.assertEquals(two, result);
    }

    @Test
    public void testNegate() {
        Vec3d one = Vec3d.of(1, 1, 1);

        Vec3d result = one.negate();

        Assert.assertEquals(Vec3d.of(-1, -1, -1), result);
    }

    @Test
    public void testIsUnit() {
        Assert.assertTrue(Vec3d.of(1, 0, 0).isUnit());
        Assert.assertTrue(Vec3d.of(0, 1, 0).isUnit());
        Assert.assertTrue(Vec3d.of(0, 0, 1).isUnit());
    }

    @Test
    public void testIsNaN() {
        Assert.assertTrue(Vec3d.of(Double.NaN, 0, 0).isNAN());
        Assert.assertTrue(Vec3d.of(0, Double.NaN, 0).isNAN());
        Assert.assertTrue(Vec3d.of(0, 0, Double.NaN).isNAN());
    }

    @Test
    public void testIsInfinite() {
        Assert.assertTrue(Vec3d.of(Double.POSITIVE_INFINITY, 0, 0).isInfinite());
        Assert.assertTrue(Vec3d.of(0, Double.POSITIVE_INFINITY, 0).isInfinite());
        Assert.assertTrue(Vec3d.of(0, 0, Double.POSITIVE_INFINITY).isInfinite());
    }

    @Test
    public void testIsValid() {
        Assert.assertTrue(Vec3d.of(1, 1, 1).isValid());
    }

    @Test
    public void testIsZero() {
        Assert.assertFalse(Vec3d.of(1, 0, 0).isZero());
        Assert.assertTrue(Vec3d.of(0, 0, 0).isZero());
    }

    @Test
    public void testIsSameDirection() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(3, 3, 3);
        Vec3d three = Vec3d.of(-1, 1, -1);

        Assert.assertTrue(one.isSameDirection(two));
        Assert.assertTrue(two.isSameDirection(one));
        Assert.assertFalse(one.isSameDirection(three));
    }

    @Test
    public void testIsOppositeDirection() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(1, -1, -1);

        Assert.assertTrue(one.isOppositeDirection(two));
    }

    @Test
    public void testCeil() {
        Vec3d result = Vec3d.of(1.5f, 1.5f, 1.5f).ceil();

        Assert.assertEquals(Vec3d.of(2, 2, 2), result);
    }

    @Test
    public void testFloor() {
        Vec3d result = Vec3d.of(1.5f, 1.5f, 1.5f).floor();

        Assert.assertEquals(Vec3d.of(1, 1, 1), result);
    }

    @Test
    public void testAbsolute() {
        Vec3d result = Vec3d.of(-1, 1, -1).absolute();

        Assert.assertEquals(Vec3d.of(1, 1, 1), result);
    }

    @Test
    public void testClamp() {
        Assert.assertEquals(Vec3d.of(1, 1, 1), Vec3d.of(2, 2, 2).clamp(1));
        Assert.assertEquals(Vec3d.of(2, 1, 2), Vec3d.of(2, 2, 2).clamp(Vec3d.of(2, 1, 3)));
    }

    @Test
    public void testProject() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.project(two);

        Assert.assertEquals(Vec3d.of(0.2886751345948129d, 0.2886751345948129d, 0.2886751345948129d), result);
    }

    @Test
    public void testReflect() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2).normalize();

        Vec3d result = one.reflect(two);

        Assert.assertEquals(Vec3d.of(-1.0000000000000004d, -1.0000000000000004d, -1.0000000000000004d), result);
    }

    @Test
    public void testHalf() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(2, 2, 2);

        Vec3d result = one.half(two);

        Assert.assertEquals(Vec3d.of(0.5773502691896257d, 0.5773502691896257d, 0.5773502691896257d), result);
    }

    @Test
    public void testOrthogonal() {
        Vec3d one = Vec3d.of(2, 2, 0);
        Vec3d two = Vec3d.of(4, 4, 0);

        Vec3d result = one.orthogonal(two);
        Assert.assertEquals(Vec3d.of(0, 0, -1), result);
    }

    @Test
    public void testLength() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Assert.assertTrue(1.7320508075688772d == one.length());
    }

    @Test
    public void testLengthSquared() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Assert.assertTrue(3f == one.lengthSquared());

        Vec3d two = Vec3d.of(2, 2, 2);
        Assert.assertTrue(12f == two.lengthSquared());
    }

    @Test
    public void testLengthManhattan() {
        Vec3d start = Vec3d.of(0, 0, 0);
        Vec3d end = Vec3d.of(2, 2, 2);

        Assert.assertTrue(6f == start.lengthManhattan(end));
        Assert.assertTrue(6f == end.lengthManhattan(start));
        Assert.assertTrue(6f == start.lengthManhattan(end.negate()));
        Assert.assertTrue(6f == end.lengthManhattan(start.negate()));
        Assert.assertTrue(12f == start.lengthManhattan(end, 0.5f));
        Assert.assertTrue(12f == end.lengthManhattan(start, 0.5f));
        Assert.assertTrue(12f == start.lengthManhattan(end.negate(), 0.5f));
        Assert.assertTrue(12f == end.lengthManhattan(start.negate(), 0.5f));
    }

    @Test
    public void testLengthEuclidean() {
        Vec3d one = Vec3d.of(0, 0, 0);
        Vec3d two = Vec3d.of(2, 1, 0);

        double result = one.lengthEuclidean(two);
        Assert.assertTrue(2.23606797749979d == result);
    }

    @Test
    public void testDelta() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Assert.assertTrue(0.6154797086703874d == one.delta());
    }

    @Test
    public void testDot() {
        Vec3d start = Vec3d.of(1, 0, 0);
        Vec3d opposite = Vec3d.of(-1, 0, 0);
        Vec3d same = Vec3d.of(1, 0, 0);
        Vec3d degree90 = Vec3d.of(0, 1, 0);
        Vec3d degree90minus = Vec3d.of(0, -1, 0);

        Assert.assertTrue(-1f == start.dot(opposite));
        Assert.assertTrue(1f == start.dot(same));
        Assert.assertTrue(0f == start.dot(degree90));
        Assert.assertTrue(0f == start.dot(degree90minus));
    }

    @Test
    public void testCross() {
        Vec3d one = Vec3d.of(1, 1, 0);
        Vec3d two = Vec3d.of(2, -2, 0);

        /**
         * <pre>
         *  
         *             |   * cross
         *             |  /
         *             | /  
         *             |/ * one
         * +--+--+--+--+--+--+--+--+
         *             |
         *             |     * two
         * </pre>
         */
        Assert.assertEquals(Vec3d.of(0, 0, -4), one.cross(two));
    }

    @Test
    public void testNormalize() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(-1, -1, -1);

        Vec3d normalized = one.cross(two).normalize();

        /**
         * <pre>
         *            ^
         * two(-1,-1) | one(1,1)
         *          \ | /
         *           \|/
         *        normalized
         * </pre>
         */
        Vec3d expected = Vec3d.of(0, 0, 0);

        Assert.assertEquals(expected, normalized);
    }

    @Test
    public void testLerp() {
        Vec3d start = Vec3d.of(0, 0, 0);
        Vec3d end = Vec3d.of(2, 2, 2);

        Assert.assertEquals(Vec3d.of(0.0f, 0.0f, 0.0f), start.lerp(end, 0.00f));
        Assert.assertEquals(Vec3d.of(0.5f, 0.5f, 0.5f), start.lerp(end, 0.25f));
        Assert.assertEquals(Vec3d.of(1.0f, 1.0f, 1.0f), start.lerp(end, 0.50f));
        Assert.assertEquals(Vec3d.of(1.5f, 1.5f, 1.5f), start.lerp(end, 0.75f));
        Assert.assertEquals(Vec3d.of(2.0f, 2.0f, 2.0f), start.lerp(end, 1.00f));
    }

    @Test
    public void testScreenSpace() {
        Mat4d viewMatrix = new Mat4d();
        Mat4d projectionMatrix = new Mat4d();

        Vec3d anObjectInScene = Vec3d.of(5, 3, -5);
        Vec2d screenSpace = anObjectInScene.screenSpace(viewMatrix, projectionMatrix);
    }

    @Test
    public void testSwizzleXy() {
        Vec2d result = first.xy();
        Assert.assertEquals(Vec2d.of(6, 4), result);
    }

    @Test
    public void testSwizzleXz() {
        Vec2d result = first.xz();
        Assert.assertEquals(Vec2d.of(6, 2), result);
    }

    @Test
    public void testSwizzleYx() {
        Vec2d result = first.yx();
        Assert.assertEquals(Vec2d.of(4, 6), result);
    }

    @Test
    public void testSwizzleYz() {
        Vec2d result = first.yz();
        Assert.assertEquals(Vec2d.of(4, 2), result);
    }

    @Test
    public void testSwizzleZx() {
        Vec2d result = first.zx();
        Assert.assertEquals(Vec2d.of(2, 6), result);
    }

    @Test
    public void testSwizzleZy() {
        Vec2d result = first.zy();
        Assert.assertEquals(Vec2d.of(2, 4), result);
    }

    @Test
    public void testVec2() {
        Vec3d one = Vec3d.of(1, 2, 3);
        Vec2d result = one.vec2();

        Assert.assertEquals(Vec2d.of(1, 2), result);
    }

    @Test
    public void testVec4() {
        Vec3d one = Vec3d.of(1, 2, 3);

        Vec4d result = one.vec4();
        Assert.assertEquals(Vec4d.of(1, 2, 3, 0), result);

        result = one.vec4(1);
        Assert.assertEquals(Vec4d.of(1, 2, 3, 1), result);
    }

    @Test
    public void testGetX() {
        double result = first.getX();
        Assert.assertEquals(6, result, 0);
    }

    @Test
    public void testGetY() {
        double result = first.getY();
        Assert.assertEquals(4, result, 0);
    }

    @Test
    public void testGetZ() {
        double result = first.getZ();
        Assert.assertEquals(2, result, 0);
    }

    @Test
    public void testQuat() {
        Quatd result = Vec3d.of(1, 2, 3).quat();
        Assert.assertEquals(Quatd.of(1, 2, 3, 0), result);

        result = Vec3d.of(1, 2, 3).quat(1);
        Assert.assertEquals(Quatd.of(1, 2, 3, 1), result);
    }

    @Test
    public void testToBytes() {
        byte[] bytes = first.toBytes();
        Assert.assertTrue(bytes.length == Vec3d.BYTES);
    }

    @Test
    public void testToBytesData() {
        byte[] data = new byte[Vec3d.BYTES];
        byte[] bytes = first.toBytes(data);
        Assert.assertTrue(bytes.length == Vec3d.BYTES);
    }

    @Test
    public void testFromBytes() {
        byte[] bytes = first.toBytes();
        Vec3d fromBytes = Vec3d.fromBytes(bytes);

        Assert.assertNotNull(fromBytes);
        Assert.assertEquals(first, fromBytes);
    }

    @Test
    public void testHashcode() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(-1, -1, -1);

        Assert.assertTrue(one.hashCode() == one.hashCode());
        Assert.assertFalse(one.hashCode() == two.hashCode());
    }

    @Test
    public void testEquals() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Vec3d two = Vec3d.of(1, 1, 1);

        Assert.assertEquals(one, two);
        Assert.assertEquals(two, one);
        Assert.assertEquals(one, one);
    }

    @Test
    public void testToString() {
        Vec3d one = Vec3d.of(1, 1, 1);
        Assert.assertEquals("vec3d[x=1.0, y=1.0, z=1.0]", one.toString());
    }

}
