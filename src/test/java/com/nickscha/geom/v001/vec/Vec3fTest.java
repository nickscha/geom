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
package com.nickscha.geom.v001.vec;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.mat.Mat4f;
import com.nickscha.geom.vec.Vec;
import com.nickscha.geom.vec.Vec2f;
import com.nickscha.geom.vec.Vec3f;

public class Vec3fTest {

	Vec3f first = Vec.of(6, 4, 2);
	Vec3f second = Vec.of(4, 2, 1);

	@Test
	public void testInit() {
		Vec3f result = new Vec3f(2);
		Vec3f expected = Vec3f.of(2, 2, 2);

		Assert.assertEquals(expected, result);

		result = Vec3f.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.add(two);
		Vec3f expected = Vec3f.of(3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Vec3f result = one.add(2f);
		Vec3f expected = Vec3f.of(3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.sub(two);
		Vec3f expected = Vec3f.of(-1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Vec3f result = one.sub(2f);
		Vec3f expected = Vec3f.of(-1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.mul(two);
		Vec3f expected = Vec3f.of(2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Vec3f result = one.mul(2f);
		Vec3f expected = Vec3f.of(2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.div(two);
		Vec3f expected = Vec3f.of(0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Vec3f result = one.div(2);
		Vec3f expected = Vec3f.of(0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMin() {
		Vec3f one = Vec3f.of(1, 2, 3);

		float result = one.min();

		Assert.assertTrue(1f == result);
	}

	@Test
	public void testMinVec() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMax() {
		Vec3f one = Vec3f.of(1, 2, 3);

		float result = one.max();

		Assert.assertTrue(3f == result);
	}

	@Test
	public void testMaxVec() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Vec3f result = one.negate();

		Assert.assertEquals(Vec3f.of(-1, -1, -1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec3f.of(1, 0, 0).isUnit());
		Assert.assertTrue(Vec3f.of(0, 1, 0).isUnit());
		Assert.assertTrue(Vec3f.of(0, 0, 1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec3f.of(Float.NaN, 0, 0).isNAN());
		Assert.assertTrue(Vec3f.of(0, Float.NaN, 0).isNAN());
		Assert.assertTrue(Vec3f.of(0, 0, Float.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec3f.of(Float.POSITIVE_INFINITY, 0, 0).isInfinite());
		Assert.assertTrue(Vec3f.of(0, Float.POSITIVE_INFINITY, 0).isInfinite());
		Assert.assertTrue(Vec3f.of(0, 0, Float.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec3f.of(1, 1, 1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec3f.of(1, 0, 0).isZero());
		Assert.assertTrue(Vec3f.of(0, 0, 0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(3, 3, 3);
		Vec3f three = Vec3f.of(-1, 1, -1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(1, -1, -1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec3f result = Vec3f.of(1.5f, 1.5f, 1.5f).ceil();

		Assert.assertEquals(Vec3f.of(2, 2, 2), result);
	}

	@Test
	public void testFloor() {
		Vec3f result = Vec3f.of(1.5f, 1.5f, 1.5f).floor();

		Assert.assertEquals(Vec3f.of(1, 1, 1), result);
	}

	@Test
	public void testAbsolute() {
		Vec3f result = Vec3f.of(-1, 1, -1).absolute();

		Assert.assertEquals(Vec3f.of(1, 1, 1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec3f.of(1, 1, 1), Vec3f.of(2, 2, 2).clamp(1));
		Assert.assertEquals(Vec3f.of(2, 1, 2), Vec3f.of(2, 2, 2).clamp(Vec3f.of(2, 1, 3)));
	}

	@Test
	public void testProject() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.project(two);

		Assert.assertEquals(Vec3f.of(0.28867513f, 0.28867513f, 0.28867513f), result);
	}

	@Test
	public void testReflect() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2).normalize();

		Vec3f result = one.reflect(two);

		Assert.assertEquals(Vec3f.of(-0.9999999f, -0.9999999f, -0.9999999f), result);
	}

	@Test
	public void testHalf() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(2, 2, 2);

		Vec3f result = one.half(two);

		Assert.assertEquals(Vec3f.of(0.5773503f, 0.5773503f, 0.5773503f), result);
	}

	@Test
	public void testOrthogonal() {
		Vec3f one = Vec3f.of(2, 2, 0);
		Vec3f two = Vec3f.of(4, 4, 0);

		Vec3f result = one.orthogonal(two);
		Assert.assertEquals(Vec3f.of(0, 0, -0.99999994f), result);
	}

	@Test
	public void testLength() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Assert.assertTrue(1.7320508f == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Assert.assertTrue(3f == one.lengthSquared());

		Vec3f two = Vec3f.of(2, 2, 2);
		Assert.assertTrue(12f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec3f start = Vec3f.of(0, 0, 0);
		Vec3f end = Vec3f.of(2, 2, 2);

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
		Vec3f one = Vec3f.of(0, 0, 0);
		Vec3f two = Vec3f.of(2, 1, 0);

		float result = one.lengthEuclidean(two);

		Assert.assertTrue(2.236068f == result);

	}

	@Test
	public void testDelta() {
		Vec3f one = Vec3f.of(1, 1, 1);

		Assert.assertTrue(0.6154797f == one.delta());
	}

	@Test
	public void testDot() {
		Vec3f start = Vec3f.of(1, 0, 0);
		Vec3f opposite = Vec3f.of(-1, 0, 0);
		Vec3f same = Vec3f.of(1, 0, 0);
		Vec3f degree90 = Vec3f.of(0, 1, 0);
		Vec3f degree90minus = Vec3f.of(0, -1, 0);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testCross() {
		Vec3f one = Vec3f.of(1, 1, 0);
		Vec3f two = Vec3f.of(2, -2, 0);

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
		Assert.assertEquals(Vec3f.of(0, 0, -4), one.cross(two));
	}

	@Test
	public void testNormalize() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(-1, -1, -1);

		Vec3f normalized = one.cross(two).normalize();

		/**
		 * <pre>
		 *            ^
		 * two(-1,-1) | one(1,1)
		 *          \ | /
		 *           \|/
		 *        normalized
		 * </pre>
		 */
		Vec3f expected = Vec3f.of(0, 0, 0);

		Assert.assertEquals(expected, normalized);
	}

	@Test
	public void testLerp() {
		Vec3f start = Vec3f.of(0, 0, 0);
		Vec3f end = Vec3f.of(2, 2, 2);

		Assert.assertEquals(Vec3f.of(0.0f, 0.0f, 0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec3f.of(0.5f, 0.5f, 0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec3f.of(1.0f, 1.0f, 1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec3f.of(1.5f, 1.5f, 1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec3f.of(2.0f, 2.0f, 2.0f), start.lerp(end, 1.00f));
	}
	
	@Test
	public void testScreenSpace(){
		Mat4f viewMatrix = new Mat4f();
		Mat4f projectionMatrix = new Mat4f();
		
		Vec3f anObjectInScene = Vec3f.of(5, 3, -5);
		Vec2f screenSpace = anObjectInScene.screenSpace(viewMatrix, projectionMatrix);
	}

	@Test
	public void testSwizzleXy() {
		Vec2f result = first.xy();
		Assert.assertEquals(Vec2f.of(6, 4), result);
	}

	@Test
	public void testSwizzleXz() {
		Vec2f result = first.xz();
		Assert.assertEquals(Vec2f.of(6, 2), result);
	}

	@Test
	public void testSwizzleYx() {
		Vec2f result = first.yx();
		Assert.assertEquals(Vec2f.of(4, 6), result);
	}

	@Test
	public void testSwizzleYz() {
		Vec2f result = first.yz();
		Assert.assertEquals(Vec2f.of(4, 2), result);
	}

	@Test
	public void testSwizzleZx() {
		Vec2f result = first.zx();
		Assert.assertEquals(Vec2f.of(2, 6), result);
	}

	@Test
	public void testSwizzleZy() {
		Vec2f result = first.zy();
		Assert.assertEquals(Vec2f.of(2, 4), result);
	}

	@Test
	public void testGetX() {
		float result = first.getX();
		Assert.assertEquals(6, result, 0);
	}

	@Test
	public void testGetY() {
		float result = first.getY();
		Assert.assertEquals(4, result, 0);
	}

	@Test
	public void testGetZ() {
		float result = first.getZ();
		Assert.assertEquals(2, result, 0);
	}

	@Test
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec3f.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec3f.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec3f.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec3f fromBytes = Vec3f.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(-1, -1, -1);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(1, 1, 1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Assert.assertEquals("vec3f[x=1.0, y=1.0, z=1.0]", one.toString());
	}

}
