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

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Vec1dTest {

	Vec1d first = Vec1d.of(6);
	Vec1d second = Vec1d.of(4);

	@Test
	public void testInit() {
		Vec1d result = new Vec1d(2);
		Vec1d expected = Vec1d.of(2);

		Assert.assertEquals(expected, result);

		result = Vec1d.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.add(two);
		Vec1d expected = Vec1d.of(3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec1d one = Vec1d.of(1);

		Vec1d result = one.add(2f);
		Vec1d expected = Vec1d.of(3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.sub(two);
		Vec1d expected = Vec1d.of(-1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec1d one = Vec1d.of(1);

		Vec1d result = one.sub(2f);
		Vec1d expected = Vec1d.of(-1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.mul(two);
		Vec1d expected = Vec1d.of(2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec1d one = Vec1d.of(1);

		Vec1d result = one.mul(2f);
		Vec1d expected = Vec1d.of(2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.div(two);
		Vec1d expected = Vec1d.of(0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec1d one = Vec1d.of(1);

		Vec1d result = one.div(2);
		Vec1d expected = Vec1d.of(0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMinVec() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMaxVec() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec1d one = Vec1d.of(1);

		Vec1d result = one.negate();

		Assert.assertEquals(Vec1d.of(-1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec1d.of(1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec1d.of(Double.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec1d.of(Double.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec1d.of(1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec1d.of(1).isZero());
		Assert.assertTrue(Vec1d.of(0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(3);
		Vec1d three = Vec1d.of(-1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(-1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec1d result = Vec1d.of(1.5f).ceil();

		Assert.assertEquals(Vec1d.of(2), result);
	}

	@Test
	public void testFloor() {
		Vec1d result = Vec1d.of(1.5f).floor();

		Assert.assertEquals(Vec1d.of(1), result);
	}

	@Test
	public void testAbsolute() {
		Vec1d result = Vec1d.of(-1).absolute();

		Assert.assertEquals(Vec1d.of(1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec1d.of(1), Vec1d.of(2).clamp(1));
		Assert.assertEquals(Vec1d.of(2), Vec1d.of(2).clamp(Vec1d.of(2)));
	}

	@Test
	public void testProject() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.project(two);

		Assert.assertEquals(Vec1d.of(0.5f), result);
	}

	@Test
	public void testReflect() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2).normalize();

		Vec1d result = one.reflect(two);

		Assert.assertEquals(Vec1d.of(-1f), result);
	}

	@Test
	public void testHalf() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(2);

		Vec1d result = one.half(two);

		Assert.assertEquals(Vec1d.of(1f), result);
	}

	@Test
	public void testLength() {
		Vec1d one = Vec1d.of(1);

		Assert.assertTrue(1f == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec1d one = Vec1d.of(1);
		Assert.assertTrue(1f == one.lengthSquared());

		Vec1d two = Vec1d.of(2);
		Assert.assertTrue(4f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec1d start = Vec1d.of(0);
		Vec1d end = Vec1d.of(2);

		Assert.assertTrue(2f == start.lengthManhattan(end));
		Assert.assertTrue(2f == end.lengthManhattan(start));
		Assert.assertTrue(2f == start.lengthManhattan(end.negate()));
		Assert.assertTrue(2f == end.lengthManhattan(start.negate()));
		Assert.assertTrue(4f == start.lengthManhattan(end, 0.5f));
		Assert.assertTrue(4f == end.lengthManhattan(start, 0.5f));
		Assert.assertTrue(4f == start.lengthManhattan(end.negate(), 0.5f));
		Assert.assertTrue(4f == end.lengthManhattan(start.negate(), 0.5f));
	}

	@Test
	public void testLengthEuclidean() {
		Vec1d one = Vec1d.of(0);
		Vec1d two = Vec1d.of(2);

		double result = one.lengthEuclidean(two);

		Assert.assertTrue(2f == result);

	}

	@Test
	public void testDot() {
		Vec1d start = Vec1d.of(1);
		Vec1d opposite = Vec1d.of(-1);
		Vec1d same = Vec1d.of(1);
		Vec1d degree90 = Vec1d.of(0);
		Vec1d degree90minus = Vec1d.of(0);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testLerp() {
		Vec1d start = Vec1d.of(0);
		Vec1d end = Vec1d.of(2);

		Assert.assertEquals(Vec1d.of(0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec1d.of(0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec1d.of(1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec1d.of(1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec1d.of(2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testGetX() {
		double result = first.getX();
		Assert.assertEquals(6, result, 0);
	}

	@Test
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec1d.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec1d.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec1d.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec1d fromBytes = Vec1d.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(-1);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec1d one = Vec1d.of(1);
		Vec1d two = Vec1d.of(1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec1d one = Vec1d.of(1);
		Assert.assertEquals("vec1d[x=1.0]", one.toString());
	}

}
