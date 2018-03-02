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
package com.nickscha.geom.v002.quat;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.quat.Quatd;
import com.nickscha.geom.vec.Vec3d;
import com.nickscha.geom.vec.Vec4d;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class QuatdTest {

    @Test
    public void testInit() {
        Assert.assertEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1));
        Assert.assertEquals(Quatd.of(1), Quatd.of(1, 1, 1, 1));
        Quatd.of(Vec3d.of(1, 0, 0), 90);
    }

    @Test
    public void testNormalize() {
        Assert.assertEquals(Quatd.of(0.5f, 0.5f, 0.5f, 0.5f), Quatd.of(2, 2, 2, 2).normalize());
    }

    @Test
    public void testInitRotation() {
        Quatd res = Quatd.of(Mat4d.rotationMatrix(Vec3d.of(0, 0, 1), Vec3d.of(0, 1, 0)));
        Assert.assertEquals(Quatd.of(0, 0, 0, 1), res);
    }

    @Test
    public void testRotationMatrix() {
        Mat4d res = Quatd.of(1, 1, -2, 1).rotationMatrix();

        double[][] expArr = new double[4][4];
        expArr[0][0] = -9;
        expArr[0][1] = 6;
        expArr[0][2] = -2;
        expArr[0][3] = 0;
        expArr[1][0] = -2;
        expArr[1][1] = -9;
        expArr[1][2] = -6;
        expArr[1][3] = 0;
        expArr[2][0] = -6;
        expArr[2][1] = -2;
        expArr[2][2] = -3;
        expArr[2][3] = 0;
        expArr[3][0] = 0;
        expArr[3][1] = 0;
        expArr[3][2] = 0;
        expArr[3][3] = 1;

        Assert.assertEquals(Mat4d.of(expArr), res);
    }

    @Test
    public void testLengthSquared() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).lengthSquared() == 4);
    }

    @Test
    public void testGetForward() {
        System.out.println(Quatd.of(1, 1, 1, 0).getForward());
    }

    @Test
    public void testGetBack() {
        System.out.println(Quatd.of(1, 1, 1, 0).getBack());
    }

    @Test
    public void testGetUp() {
        System.out.println(Quatd.of(1, 1, 1, 0).getUp());
    }

    @Test
    public void testGetDown() {
        System.out.println(Quatd.of(1, 1, 1, 0).getDown());
    }

    @Test
    public void testGetLeft() {
        System.out.println(Quatd.of(1, 1, 1, 0).getLeft());
    }

    @Test
    public void testGetRight() {
        System.out.println(Quatd.of(1, 1, 1, 0).getRight());
    }

    @Test
    public void testLength() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).length() == 2);
    }

    @Test
    public void testIsZero() {
        Assert.assertTrue(Quatd.of(0, 0, 0, 0).isZero());
        Assert.assertFalse(Quatd.of(1, 1, 1, 1).isZero());
    }

    @Test
    public void testIsIdentity() {
        Assert.assertTrue(Quatd.of(0, 0, 0, 1).isIdentity());
        Assert.assertFalse(Quatd.of(1, 1, 1, 0).isIdentity());
    }

    @Test
    public void testNegate() {
        Assert.assertEquals(Quatd.of(-1, -1, -1, -1), Quatd.of(1, 1, 1, 1).negate());
    }

    @Test
    public void testConjugate() {
        Assert.assertEquals(Quatd.of(-1, -1, -1, 1), Quatd.of(1, 1, 1, 1).conjugate());
    }

    @Test
    public void testDot() {
        double result = Quatd.of(1, 2, 3, 4).dot(Quatd.of(2));
        Assert.assertTrue(result == 20f);
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(Quatd.of(2, 2, 2, 2), Quatd.of(1, 1, 1, 1).add(Quatd.of(1, 1, 1, 1)));
    }

    @Test
    public void testAddAmt() {
        Assert.assertEquals(Quatd.of(2, 2, 2, 2), Quatd.of(1, 1, 1, 1).add(1));
    }

    @Test
    public void testSub() {
        Assert.assertEquals(Quatd.of(0, 0, 0, 0), Quatd.of(1, 1, 1, 1).sub(Quatd.of(1, 1, 1, 1)));
    }

    @Test
    public void testSubAmt() {
        Assert.assertEquals(Quatd.of(0, 0, 0, 0), Quatd.of(1, 1, 1, 1).sub(1));
    }

    @Test
    public void testMul() {
        Assert.assertEquals(Quatd.of(2, 2, 2, -2), Quatd.of(1, 1, 1, 1).mul(Quatd.of(1, 1, 1, 1)));
    }

    @Test
    public void testMulAmt() {
        Assert.assertEquals(Quatd.of(2, 2, 2, -2), Quatd.of(1, 1, 1, 1).mul(1));
    }

    @Test
    public void testMulVec3d() {
        Assert.assertEquals(Quatd.of(1, 1, 1, -3), Quatd.of(1, 1, 1, 1).mul(Vec3d.of(1, 1, 1)));
    }

    @Test
    public void testDiv() {
        Assert.assertEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 1, 1, 1).div(Quatd.of(1, 1, 1, 1)));
    }

    @Test
    public void testDivAmt() {
        Assert.assertEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 1, 1, 1).div(1));
    }

    @Test
    public void testGetX() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).getX() == 1f);
    }

    @Test
    public void testGetY() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).getY() == 1f);
    }

    @Test
    public void testGetZ() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).getZ() == 1f);
    }

    @Test
    public void testGetW() {
        Assert.assertTrue(Quatd.of(1, 1, 1, 1).getW() == 1f);
    }

    @Test
    public void testVec4() {
        Vec4d result = Quatd.of(1, 2, 3, 4).vec4();
        Assert.assertEquals(Vec4d.of(1, 2, 3, 4), result);
    }

    @Test
    public void testHashcode() {
        int result = Quatd.of(1, 1, 1, 1).hashCode();
    }

    @Test
    public void testToBytes() {
        Quatd first = Quatd.of(1, 2, 3, 4);
        byte[] bytes = first.toBytes();
        Assert.assertTrue(bytes.length == Quatd.BYTES);
    }

    @Test
    public void testToBytesData() {
        Quatd first = Quatd.of(1, 2, 3, 4);
        byte[] data = new byte[Quatd.BYTES];
        byte[] bytes = first.toBytes(data);
        Assert.assertTrue(bytes.length == Quatd.BYTES);
    }

    @Test
    public void testFromBytes() {
        Quatd first = Quatd.of(1, 2, 3, 4);
        byte[] bytes = first.toBytes();
        Quatd fromBytes = Quatd.fromBytes(bytes);

        Assert.assertNotNull(fromBytes);
        Assert.assertEquals(first, fromBytes);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 1, 1, 1));
        Assert.assertNotEquals(Quatd.of(1, 1, 1, 1), Quatd.of(2, 1, 1, 1));
        Assert.assertNotEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 2, 1, 1));
        Assert.assertNotEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 1, 2, 1));
        Assert.assertNotEquals(Quatd.of(1, 1, 1, 1), Quatd.of(1, 1, 1, 2));
    }

    @Test
    public void testToString() {
        String result = Quatd.of(1, 1, 1, 1).toString();
        Assert.assertEquals("quatd[x=1.0, y=1.0, z=1.0, w=1.0]", result);
    }
}
