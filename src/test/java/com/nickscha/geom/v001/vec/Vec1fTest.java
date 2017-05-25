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

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Vec1fTest {

	Vec1f first = Vec.of(6);
	Vec1f second = Vec.of(4);

	@Test
	public void testInit() {
		Vec1f result = new Vec1f(2);
		Vec1f expected = Vec1f.of(2);

		Assert.assertEquals(expected, result);

		result = Vec1f.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.add(two);
		Vec1f expected = Vec1f.of(3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec1f one = Vec1f.of(1);

		Vec1f result = one.add(2f);
		Vec1f expected = Vec1f.of(3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.sub(two);
		Vec1f expected = Vec1f.of(-1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec1f one = Vec1f.of(1);

		Vec1f result = one.sub(2f);
		Vec1f expected = Vec1f.of(-1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.mul(two);
		Vec1f expected = Vec1f.of(2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec1f one = Vec1f.of(1);

		Vec1f result = one.mul(2f);
		Vec1f expected = Vec1f.of(2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.div(two);
		Vec1f expected = Vec1f.of(0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec1f one = Vec1f.of(1);

		Vec1f result = one.div(2);
		Vec1f expected = Vec1f.of(0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMinVec() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMaxVec() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec1f one = Vec1f.of(1);

		Vec1f result = one.negate();

		Assert.assertEquals(Vec1f.of(-1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec1f.of(1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec1f.of(Float.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec1f.of(Float.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec1f.of(1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec1f.of(1).isZero());
		Assert.assertTrue(Vec1f.of(0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(3);
		Vec1f three = Vec1f.of(-1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(-1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec1f result = Vec1f.of(1.5f).ceil();

		Assert.assertEquals(Vec1f.of(2), result);
	}

	@Test
	public void testFloor() {
		Vec1f result = Vec1f.of(1.5f).floor();

		Assert.assertEquals(Vec1f.of(1), result);
	}

	@Test
	public void testAbsolute() {
		Vec1f result = Vec1f.of(-1).absolute();

		Assert.assertEquals(Vec1f.of(1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec1f.of(1), Vec1f.of(2).clamp(1));
		Assert.assertEquals(Vec1f.of(2), Vec1f.of(2).clamp(Vec1f.of(2)));
	}

	@Test
	public void testProject() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.project(two);

		Assert.assertEquals(Vec1f.of(0.5f), result);
	}

	@Test
	public void testReflect() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2).normalize();

		Vec1f result = one.reflect(two);

		Assert.assertEquals(Vec1f.of(-1f), result);
	}

	@Test
	public void testHalf() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(2);

		Vec1f result = one.half(two);

		Assert.assertEquals(Vec1f.of(1f), result);
	}

	@Test
	public void testLength() {
		Vec1f one = Vec1f.of(1);

		Assert.assertTrue(1f == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec1f one = Vec1f.of(1);
		Assert.assertTrue(1f == one.lengthSquared());

		Vec1f two = Vec1f.of(2);
		Assert.assertTrue(4f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec1f start = Vec1f.of(0);
		Vec1f end = Vec1f.of(2);

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
		Vec1f one = Vec1f.of(0);
		Vec1f two = Vec1f.of(2);

		float result = one.lengthEuclidean(two);

		Assert.assertTrue(2f == result);

	}

	@Test
	public void testDot() {
		Vec1f start = Vec1f.of(1);
		Vec1f opposite = Vec1f.of(-1);
		Vec1f same = Vec1f.of(1);
		Vec1f degree90 = Vec1f.of(0);
		Vec1f degree90minus = Vec1f.of(0);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testLerp() {
		Vec1f start = Vec1f.of(0);
		Vec1f end = Vec1f.of(2);

		Assert.assertEquals(Vec1f.of(0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec1f.of(0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec1f.of(1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec1f.of(1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec1f.of(2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testGetX() {
		float result = first.getX();
		Assert.assertEquals(6, result, 0);
	}

	@Test
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec1f.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec1f.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec1f.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec1f fromBytes = Vec1f.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(-1);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec1f one = Vec1f.of(1);
		Vec1f two = Vec1f.of(1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec1f one = Vec1f.of(1);
		Assert.assertEquals("vec1f[x=1.0]", one.toString());
	}

}
