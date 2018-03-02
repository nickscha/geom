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

import com.nickscha.geom.vec.Vec1d;
import com.nickscha.geom.vec.Vec2d;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Vec2dTest {

	Vec2d first = Vec2d.of(6, 4);
	Vec2d second = Vec2d.of(4, 2);

	@Test
	public void testInit() {
		Vec2d result = new Vec2d(2);
		Vec2d expected = Vec2d.of(2, 2);

		Assert.assertEquals(expected, result);

		result = Vec2d.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.add(two);
		Vec2d expected = Vec2d.of(3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec2d one = Vec2d.of(1, 1);

		Vec2d result = one.add(2f);
		Vec2d expected = Vec2d.of(3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.sub(two);
		Vec2d expected = Vec2d.of(-1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec2d one = Vec2d.of(1, 1);

		Vec2d result = one.sub(2f);
		Vec2d expected = Vec2d.of(-1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.mul(two);
		Vec2d expected = Vec2d.of(2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec2d one = Vec2d.of(1, 1);

		Vec2d result = one.mul(2f);
		Vec2d expected = Vec2d.of(2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.div(two);
		Vec2d expected = Vec2d.of(0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec2d one = Vec2d.of(1, 1);

		Vec2d result = one.div(2);
		Vec2d expected = Vec2d.of(0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMin() {
		Vec2d one = Vec2d.of(1, 2);

		double result = one.min();

		Assert.assertTrue(1f == result);
	}

	@Test
	public void testMinVec() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMax() {
		Vec2d one = Vec2d.of(1, 2);

		double result = one.max();

		Assert.assertTrue(2f == result);
	}

	@Test
	public void testMaxVec() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec2d one = Vec2d.of(1, 1);

		Vec2d result = one.negate();

		Assert.assertEquals(Vec2d.of(-1, -1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec2d.of(1, 0).isUnit());
		Assert.assertTrue(Vec2d.of(0, 1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec2d.of(Double.NaN, 0).isNAN());
		Assert.assertTrue(Vec2d.of(0, Double.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec2d.of(Double.POSITIVE_INFINITY, 0).isInfinite());
		Assert.assertTrue(Vec2d.of(0, Double.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec2d.of(1, 1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec2d.of(1, 0).isZero());
		Assert.assertTrue(Vec2d.of(0, 0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(3, 3);
		Vec2d three = Vec2d.of(-1, 1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(-1, -1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec2d result = Vec2d.of(1.5f, 1.5f).ceil();

		Assert.assertEquals(Vec2d.of(2, 2), result);
	}

	@Test
	public void testFloor() {
		Vec2d result = Vec2d.of(1.5f, 1.5f).floor();

		Assert.assertEquals(Vec2d.of(1, 1), result);
	}

	@Test
	public void testAbsolute() {
		Vec2d result = Vec2d.of(-1, 1).absolute();

		Assert.assertEquals(Vec2d.of(1, 1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec2d.of(1, 1), Vec2d.of(2, 2).clamp(1));
		Assert.assertEquals(Vec2d.of(2, 1), Vec2d.of(2, 2).clamp(Vec2d.of(2, 1)));
	}

	@Test
	public void testProject() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.project(two);

		Assert.assertEquals(Vec2d.of(0.35355339059327373d, 0.35355339059327373d), result);
	}

	@Test
	public void testReflect() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2).normalize();

		Vec2d result = one.reflect(two);

		Assert.assertEquals(Vec2d.of(-0.9999999999999996d, -0.9999999999999996d), result);
	}

	@Test
	public void testHalf() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(2, 2);

		Vec2d result = one.half(two);

		Assert.assertEquals(Vec2d.of(0.7071067811865476d, 0.7071067811865476d), result);
	}

	@Test
	public void testLength() {
		Vec2d one = Vec2d.of(1, 1);
		Assert.assertTrue(1.4142135623730951d == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec2d one = Vec2d.of(1, 1);
		Assert.assertTrue(2f == one.lengthSquared());

		Vec2d two = Vec2d.of(2, 2);
		Assert.assertTrue(8f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec2d start = Vec2d.of(0, 0);
		Vec2d end = Vec2d.of(2, 2);

		Assert.assertTrue(4f == start.lengthManhattan(end));
		Assert.assertTrue(4f == end.lengthManhattan(start));
		Assert.assertTrue(4f == start.lengthManhattan(end.negate()));
		Assert.assertTrue(4f == end.lengthManhattan(start.negate()));
		Assert.assertTrue(8f == start.lengthManhattan(end, 0.5f));
		Assert.assertTrue(8f == end.lengthManhattan(start, 0.5f));
		Assert.assertTrue(8f == start.lengthManhattan(end.negate(), 0.5f));
		Assert.assertTrue(8f == end.lengthManhattan(start.negate(), 0.5f));
	}

	@Test
	public void testLengthEuclidean() {
		Vec2d one = Vec2d.of(0, 0);
		Vec2d two = Vec2d.of(2, 1);

		double result = one.lengthEuclidean(two);
		Assert.assertTrue(2.23606797749979d == result);

	}

	@Test
	public void testDot() {
		Vec2d start = Vec2d.of(1, 0);
		Vec2d opposite = Vec2d.of(-1, 0);
		Vec2d same = Vec2d.of(1, 0);
		Vec2d degree90 = Vec2d.of(0, 1);
		Vec2d degree90minus = Vec2d.of(0, -1);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testLerp() {
		Vec2d start = Vec2d.of(0, 0);
		Vec2d end = Vec2d.of(2, 2);

		Assert.assertEquals(Vec2d.of(0.0f, 0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec2d.of(0.5f, 0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec2d.of(1.0f, 1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec2d.of(1.5f, 1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec2d.of(2.0f, 2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testSwizzleX() {
		Vec1d result = first.x();
		Assert.assertEquals(Vec1d.of(6), result);
	}

	@Test
	public void testSwizzleY() {
		Vec1d result = first.y();
		Assert.assertEquals(Vec1d.of(4), result);
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
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec2d.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec2d.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec2d.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec2d fromBytes = Vec2d.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(-1, -2);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec2d one = Vec2d.of(1, 1);
		Vec2d two = Vec2d.of(1, 1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec2d one = Vec2d.of(1, 1);
		Assert.assertEquals("vec2d[x=1.0, y=1.0]", one.toString());
	}

}
