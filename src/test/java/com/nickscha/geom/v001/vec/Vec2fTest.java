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

import com.nickscha.geom.vec.Vec;
import com.nickscha.geom.vec.Vec1f;
import com.nickscha.geom.vec.Vec2f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Vec2fTest {

	Vec2f first = Vec.of(6, 4);
	Vec2f second = Vec.of(4, 2);

	@Test
	public void testInit() {
		Vec2f result = new Vec2f(2);
		Vec2f expected = Vec2f.of(2, 2);

		Assert.assertEquals(expected, result);

		result = Vec2f.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.add(two);
		Vec2f expected = Vec2f.of(3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec2f one = Vec2f.of(1, 1);

		Vec2f result = one.add(2f);
		Vec2f expected = Vec2f.of(3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.sub(two);
		Vec2f expected = Vec2f.of(-1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec2f one = Vec2f.of(1, 1);

		Vec2f result = one.sub(2f);
		Vec2f expected = Vec2f.of(-1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.mul(two);
		Vec2f expected = Vec2f.of(2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec2f one = Vec2f.of(1, 1);

		Vec2f result = one.mul(2f);
		Vec2f expected = Vec2f.of(2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.div(two);
		Vec2f expected = Vec2f.of(0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec2f one = Vec2f.of(1, 1);

		Vec2f result = one.div(2);
		Vec2f expected = Vec2f.of(0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMin() {
		Vec2f one = Vec2f.of(1, 2);

		float result = one.min();

		Assert.assertTrue(1f == result);
	}

	@Test
	public void testMinVec() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMax() {
		Vec2f one = Vec2f.of(1, 2);

		float result = one.max();

		Assert.assertTrue(2f == result);
	}

	@Test
	public void testMaxVec() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec2f one = Vec2f.of(1, 1);

		Vec2f result = one.negate();

		Assert.assertEquals(Vec2f.of(-1, -1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec2f.of(1, 0).isUnit());
		Assert.assertTrue(Vec2f.of(0, 1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec2f.of(Float.NaN, 0).isNAN());
		Assert.assertTrue(Vec2f.of(0, Float.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec2f.of(Float.POSITIVE_INFINITY, 0).isInfinite());
		Assert.assertTrue(Vec2f.of(0, Float.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec2f.of(1, 1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec2f.of(1, 0).isZero());
		Assert.assertTrue(Vec2f.of(0, 0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(3, 3);
		Vec2f three = Vec2f.of(-1, 1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(-1, -1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec2f result = Vec2f.of(1.5f, 1.5f).ceil();

		Assert.assertEquals(Vec2f.of(2, 2), result);
	}

	@Test
	public void testFloor() {
		Vec2f result = Vec2f.of(1.5f, 1.5f).floor();

		Assert.assertEquals(Vec2f.of(1, 1), result);
	}

	@Test
	public void testAbsolute() {
		Vec2f result = Vec2f.of(-1, 1).absolute();

		Assert.assertEquals(Vec2f.of(1, 1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec2f.of(1, 1), Vec2f.of(2, 2).clamp(1));
		Assert.assertEquals(Vec2f.of(2, 1), Vec2f.of(2, 2).clamp(Vec2f.of(2, 1)));
	}

	@Test
	public void testProject() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.project(two);

		Assert.assertEquals(Vec2f.of(0.35355338f, 0.35355338f), result);
	}

	@Test
	public void testReflect() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2).normalize();

		Vec2f result = one.reflect(two);

		Assert.assertEquals(Vec2f.of(-0.9999999f, -0.9999999f), result);
	}

	@Test
	public void testHalf() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(2, 2);

		Vec2f result = one.half(two);

		Assert.assertEquals(Vec2f.of(0.7071068f, 0.7071068f), result);
	}

	@Test
	public void testLength() {
		Vec2f one = Vec2f.of(1, 1);

		Assert.assertTrue(1.4142135f == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec2f one = Vec2f.of(1, 1);
		Assert.assertTrue(2f == one.lengthSquared());

		Vec2f two = Vec2f.of(2, 2);
		Assert.assertTrue(8f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec2f start = Vec2f.of(0, 0);
		Vec2f end = Vec2f.of(2, 2);

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
		Vec2f one = Vec2f.of(0, 0);
		Vec2f two = Vec2f.of(2, 1);

		float result = one.lengthEuclidean(two);

		Assert.assertTrue(2.236068f == result);

	}

	@Test
	public void testDot() {
		Vec2f start = Vec2f.of(1, 0);
		Vec2f opposite = Vec2f.of(-1, 0);
		Vec2f same = Vec2f.of(1, 0);
		Vec2f degree90 = Vec2f.of(0, 1);
		Vec2f degree90minus = Vec2f.of(0, -1);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testLerp() {
		Vec2f start = Vec2f.of(0, 0);
		Vec2f end = Vec2f.of(2, 2);

		Assert.assertEquals(Vec2f.of(0.0f, 0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec2f.of(0.5f, 0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec2f.of(1.0f, 1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec2f.of(1.5f, 1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec2f.of(2.0f, 2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testSwizzleX() {
		Vec1f result = first.x();
		Assert.assertEquals(Vec1f.of(6), result);
	}

	@Test
	public void testSwizzleY() {
		Vec1f result = first.y();
		Assert.assertEquals(Vec1f.of(4), result);
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
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec2f.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec2f.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec2f.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec2f fromBytes = Vec2f.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(-1, -2);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec2f one = Vec2f.of(1, 1);
		Vec2f two = Vec2f.of(1, 1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec2f one = Vec2f.of(1, 1);
		Assert.assertEquals("vec2f[x=1.0, y=1.0]", one.toString());
	}

}
