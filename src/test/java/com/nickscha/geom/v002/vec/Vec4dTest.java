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
 */
public class Vec4dTest {

	Vec4d first = Vec4d.of(6, 4, 2, 2);
	Vec4d second = Vec4d.of(4, 2, 1, 1);

	@Test
	public void testInit() {
		Vec4d result = new Vec4d(2);
		Vec4d expected = Vec4d.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);

		result = Vec4d.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.add(two);
		Vec4d expected = Vec4d.of(3, 3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Vec4d result = one.add(2f);
		Vec4d expected = Vec4d.of(3, 3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.sub(two);
		Vec4d expected = Vec4d.of(-1, -1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Vec4d result = one.sub(2f);
		Vec4d expected = Vec4d.of(-1, -1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.mul(two);
		Vec4d expected = Vec4d.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Vec4d result = one.mul(2f);
		Vec4d expected = Vec4d.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.div(two);
		Vec4d expected = Vec4d.of(0.5f, 0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Vec4d result = one.div(2);
		Vec4d expected = Vec4d.of(0.5f, 0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMin() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);

		double result = one.min();

		Assert.assertTrue(1f == result);
	}

	@Test
	public void testMinVec() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMax() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);

		double result = one.max();

		Assert.assertTrue(4f == result);
	}

	@Test
	public void testMaxVec() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Vec4d result = one.negate();

		Assert.assertEquals(Vec4d.of(-1, -1, -1, -1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec4d.of(1, 0, 0, 0).isUnit());
		Assert.assertTrue(Vec4d.of(0, 1, 0, 0).isUnit());
		Assert.assertTrue(Vec4d.of(0, 0, 1, 0).isUnit());
		Assert.assertTrue(Vec4d.of(0, 0, 0, 1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec4d.of(Double.NaN, 0, 0, 0).isNAN());
		Assert.assertTrue(Vec4d.of(0, Double.NaN, 0, 0).isNAN());
		Assert.assertTrue(Vec4d.of(0, 0, Double.NaN, 0).isNAN());
		Assert.assertTrue(Vec4d.of(0, 0, 0, Double.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec4d.of(Double.POSITIVE_INFINITY, 0, 0, 0).isInfinite());
		Assert.assertTrue(Vec4d.of(0, Double.POSITIVE_INFINITY, 0, 0).isInfinite());
		Assert.assertTrue(Vec4d.of(0, 0, Double.POSITIVE_INFINITY, 0).isInfinite());
		Assert.assertTrue(Vec4d.of(0, 0, 0, Double.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec4d.of(1, 1, 1, 1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec4d.of(1, 0, 0, 0).isZero());
		Assert.assertTrue(Vec4d.of(0, 0, 0, 0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(3, 3, 3, 3);
		Vec4d three = Vec4d.of(-1, 1, -1, -1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(1, -1, -1, -1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec4d result = Vec4d.of(1.5f, 1.5f, 1.5f, 1.5f).ceil();

		Assert.assertEquals(Vec4d.of(2, 2, 2, 2), result);
	}

	@Test
	public void testFloor() {
		Vec4d result = Vec4d.of(1.5f, 1.5f, 1.5f, 1.5f).floor();

		Assert.assertEquals(Vec4d.of(1, 1, 1, 1), result);
	}

	@Test
	public void testAbsolute() {
		Vec4d result = Vec4d.of(-1, 1, -1, -1).absolute();

		Assert.assertEquals(Vec4d.of(1, 1, 1, 1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec4d.of(1, 1, 1, 1), Vec4d.of(2, 2, 2, 2).clamp(1));
		Assert.assertEquals(Vec4d.of(2, 1, 2, 2), Vec4d.of(2, 2, 2, 2).clamp(Vec4d.of(2, 1, 3, 3)));
	}

	@Test
	public void testProject() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 1);

		Vec4d result = one.project(two);

		Assert.assertEquals(Vec4d.of(0.2986847210443541d, 0.2986847210443541d, 0.2986847210443541d, 0.14934236052217706d), result);
	}

	@Test
	public void testReflect() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2).normalize();

		Vec4d result = one.reflect(two);

		Assert.assertEquals(Vec4d.of(-1, -1, -1, -1), result);
	}

	@Test
	public void testHalf() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(2, 2, 2, 2);

		Vec4d result = one.half(two);

		Assert.assertEquals(Vec4d.of(0.5f, 0.5f, 0.5f, 0.5f), result);
	}

	@Test
	public void testOrthogonal() {
		Vec4d one = Vec4d.of(2, 2, 0, 0);
		Vec4d two = Vec4d.of(4, 4, 0, 0);

		Vec4d result = one.orthogonal(two);
		Assert.assertEquals(Vec4d.of(0, 0, -1, 0), result);
	}

	@Test
	public void testLength() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Assert.assertTrue(2 == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Assert.assertTrue(4f == one.lengthSquared());

		Vec4d two = Vec4d.of(2, 2, 2, 2);
		Assert.assertTrue(16f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec4d start = Vec4d.of(0, 0, 0, 0);
		Vec4d end = Vec4d.of(2, 2, 2, 2);

		Assert.assertTrue(8f == start.lengthManhattan(end));
		Assert.assertTrue(8f == end.lengthManhattan(start));
		Assert.assertTrue(8f == start.lengthManhattan(end.negate()));
		Assert.assertTrue(8f == end.lengthManhattan(start.negate()));
		Assert.assertTrue(16f == start.lengthManhattan(end, 0.5f));
		Assert.assertTrue(16f == end.lengthManhattan(start, 0.5f));
		Assert.assertTrue(16f == start.lengthManhattan(end.negate(), 0.5f));
		Assert.assertTrue(16f == end.lengthManhattan(start.negate(), 0.5f));
	}

	@Test
	public void testLengthEuclidean() {
		Vec4d one = Vec4d.of(0, 0, 0, 0);
		Vec4d two = Vec4d.of(2, 1, 0, 0);

		double result = one.lengthEuclidean(two);
		Assert.assertTrue(2.23606797749979d == result);

	}

	@Test
	public void testDelta() {
		Vec4d two = Vec4d.of(0, 0, 4, 0);
		double result = two.delta();
		Assert.assertTrue(result == 1.5707963267948966d);
	}

	@Test
	public void testDot() {
		Vec4d start = Vec4d.of(1, 0, 0, 0);
		Vec4d opposite = Vec4d.of(-1, 0, 0, 0);
		Vec4d same = Vec4d.of(1, 0, 0, 0);
		Vec4d degree90 = Vec4d.of(0, 1, 0, 0);
		Vec4d degree90minus = Vec4d.of(0, -1, 0, -1);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testNormalize() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(-1, -1, -1, -1);

		Vec4d normalized = one.cross(two).normalize();

		/**
		 * <pre>
		 *            ^
		 * two(-1,-1) | one(1,1)
		 *          \ | /
		 *           \|/
		 *        normalized
		 * </pre>
		 */
		Vec4d expected = Vec4d.of(0, 0, 0, 1);

		Assert.assertEquals(expected, normalized);
	}

	@Test
	public void testLerp() {
		Vec4d start = Vec4d.of(0, 0, 0, 0);
		Vec4d end = Vec4d.of(2, 2, 2, 2);

		Assert.assertEquals(Vec4d.of(0.0f, 0.0f, 0.0f, 0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec4d.of(0.5f, 0.5f, 0.5f, 0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec4d.of(1.0f, 1.0f, 1.0f, 1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec4d.of(1.5f, 1.5f, 1.5f, 1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec4d.of(2.0f, 2.0f, 2.0f, 2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testScreenSpace() {
		Mat4d viewMatrix = new Mat4d();
		Mat4d projectionMatrix = new Mat4d();

		Vec4d anObjectInScene = Vec4d.of(5, 3, -5, 1f);
		Vec2d screenSpace = anObjectInScene.screenSpace(viewMatrix, projectionMatrix);
	}

	@Test
	public void testVec2() {
		Vec4d one = Vec4d.of(1, 2, 3, 0);
		Vec2d result = one.vec2();

		Assert.assertEquals(Vec2d.of(1, 2), result);
	}

	@Test
	public void testVec3() {
		Vec4d one = Vec4d.of(1, 2, 3, 0);

		Vec3d result = one.vec3();
		Assert.assertEquals(Vec3d.of(1, 2, 3), result);
	}

	@Test
	public void testxxx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xxx();

		Assert.assertEquals(Vec3d.of(1, 1, 1), result);
	}

	@Test
	public void testxxy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xxy();

		Assert.assertEquals(Vec3d.of(1, 1, 2), result);
	}

	@Test
	public void testxxz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xxz();

		Assert.assertEquals(Vec3d.of(1, 1, 3), result);
	}

	@Test
	public void testxxw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xxw();

		Assert.assertEquals(Vec3d.of(1, 1, 4), result);
	}

	@Test
	public void testxyx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xyx();

		Assert.assertEquals(Vec3d.of(1, 2, 1), result);
	}

	@Test
	public void testxyy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xyy();

		Assert.assertEquals(Vec3d.of(1, 2, 2), result);
	}

	@Test
	public void testxyz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xyz();

		Assert.assertEquals(Vec3d.of(1, 2, 3), result);
	}

	@Test
	public void testxyw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xyw();

		Assert.assertEquals(Vec3d.of(1, 2, 4), result);
	}

	@Test
	public void testxzx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xzx();

		Assert.assertEquals(Vec3d.of(1, 3, 1), result);
	}

	@Test
	public void testxzy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xzy();

		Assert.assertEquals(Vec3d.of(1, 3, 2), result);
	}

	@Test
	public void testxzz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xzz();

		Assert.assertEquals(Vec3d.of(1, 3, 3), result);
	}

	@Test
	public void testxzw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xzw();

		Assert.assertEquals(Vec3d.of(1, 3, 4), result);
	}

	@Test
	public void testxwx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xwx();

		Assert.assertEquals(Vec3d.of(1, 4, 1), result);
	}

	@Test
	public void testxwy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xwy();

		Assert.assertEquals(Vec3d.of(1, 4, 2), result);
	}

	@Test
	public void testxwz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xwz();

		Assert.assertEquals(Vec3d.of(1, 4, 3), result);
	}

	@Test
	public void testxww() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.xww();

		Assert.assertEquals(Vec3d.of(1, 4, 4), result);
	}

	@Test
	public void testyxx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yxx();

		Assert.assertEquals(Vec3d.of(2, 1, 1), result);
	}

	@Test
	public void testyxy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yxy();

		Assert.assertEquals(Vec3d.of(2, 1, 2), result);
	}

	@Test
	public void testyxz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yxz();

		Assert.assertEquals(Vec3d.of(2, 1, 3), result);
	}

	@Test
	public void testyxw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yxw();

		Assert.assertEquals(Vec3d.of(2, 1, 4), result);
	}

	@Test
	public void testyyx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yyx();

		Assert.assertEquals(Vec3d.of(2, 2, 1), result);
	}

	@Test
	public void testyyy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yyy();

		Assert.assertEquals(Vec3d.of(2, 2, 2), result);
	}

	@Test
	public void testyyz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yyz();

		Assert.assertEquals(Vec3d.of(2, 2, 3), result);
	}

	@Test
	public void testyyw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yyw();

		Assert.assertEquals(Vec3d.of(2, 2, 4), result);
	}

	@Test
	public void testyzx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yzx();

		Assert.assertEquals(Vec3d.of(2, 3, 1), result);
	}

	@Test
	public void testyzy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yzy();

		Assert.assertEquals(Vec3d.of(2, 3, 2), result);
	}

	@Test
	public void testyzz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yzz();

		Assert.assertEquals(Vec3d.of(2, 3, 3), result);
	}

	@Test
	public void testyzw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yzw();

		Assert.assertEquals(Vec3d.of(2, 3, 4), result);
	}

	@Test
	public void testywx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.ywx();

		Assert.assertEquals(Vec3d.of(2, 4, 1), result);
	}

	@Test
	public void testywy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.ywy();

		Assert.assertEquals(Vec3d.of(2, 4, 2), result);
	}

	@Test
	public void testywz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.ywz();

		Assert.assertEquals(Vec3d.of(2, 4, 3), result);
	}

	@Test
	public void testyww() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.yww();

		Assert.assertEquals(Vec3d.of(2, 4, 4), result);
	}

	@Test
	public void testzxx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zxx();

		Assert.assertEquals(Vec3d.of(3, 1, 1), result);
	}

	@Test
	public void testzxy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zxy();

		Assert.assertEquals(Vec3d.of(3, 1, 2), result);
	}

	@Test
	public void testzxz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zxz();

		Assert.assertEquals(Vec3d.of(3, 1, 3), result);
	}

	@Test
	public void testzxw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zxw();

		Assert.assertEquals(Vec3d.of(3, 1, 4), result);
	}

	@Test
	public void testzyx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zyx();

		Assert.assertEquals(Vec3d.of(3, 2, 1), result);
	}

	@Test
	public void testzyy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zyy();

		Assert.assertEquals(Vec3d.of(3, 2, 2), result);
	}

	@Test
	public void testzyz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zyz();

		Assert.assertEquals(Vec3d.of(3, 2, 3), result);
	}

	@Test
	public void testzyw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zyw();

		Assert.assertEquals(Vec3d.of(3, 2, 4), result);
	}

	@Test
	public void testzzx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zzx();

		Assert.assertEquals(Vec3d.of(3, 3, 1), result);
	}

	@Test
	public void testzzy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zzy();

		Assert.assertEquals(Vec3d.of(3, 3, 2), result);
	}

	@Test
	public void testzzz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zzz();

		Assert.assertEquals(Vec3d.of(3, 3, 3), result);
	}

	@Test
	public void testzzw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zzw();

		Assert.assertEquals(Vec3d.of(3, 3, 4), result);
	}

	@Test
	public void testzwx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zwx();

		Assert.assertEquals(Vec3d.of(3, 4, 1), result);
	}

	@Test
	public void testzwy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zwy();

		Assert.assertEquals(Vec3d.of(3, 4, 2), result);
	}

	@Test
	public void testzwz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zwz();

		Assert.assertEquals(Vec3d.of(3, 4, 3), result);
	}

	@Test
	public void testzww() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.zww();

		Assert.assertEquals(Vec3d.of(3, 4, 4), result);
	}

	@Test
	public void testwxx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wxx();

		Assert.assertEquals(Vec3d.of(4, 1, 1), result);
	}

	@Test
	public void testwxy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wxy();

		Assert.assertEquals(Vec3d.of(4, 1, 2), result);
	}

	@Test
	public void testwxz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wxz();

		Assert.assertEquals(Vec3d.of(4, 1, 3), result);
	}

	@Test
	public void testwxw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wxw();

		Assert.assertEquals(Vec3d.of(4, 1, 4), result);
	}

	@Test
	public void testwyx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wyx();

		Assert.assertEquals(Vec3d.of(4, 2, 1), result);
	}

	@Test
	public void testwyy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wyy();

		Assert.assertEquals(Vec3d.of(4, 2, 2), result);
	}

	@Test
	public void testwyz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wyz();

		Assert.assertEquals(Vec3d.of(4, 2, 3), result);
	}

	@Test
	public void testwyw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wyw();

		Assert.assertEquals(Vec3d.of(4, 2, 4), result);
	}

	@Test
	public void testwzx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wzx();

		Assert.assertEquals(Vec3d.of(4, 3, 1), result);
	}

	@Test
	public void testwzy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wzy();

		Assert.assertEquals(Vec3d.of(4, 3, 2), result);
	}

	@Test
	public void testwzz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wzz();

		Assert.assertEquals(Vec3d.of(4, 3, 3), result);
	}

	@Test
	public void testwzw() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wzw();

		Assert.assertEquals(Vec3d.of(4, 3, 4), result);
	}

	@Test
	public void testwwx() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wwx();

		Assert.assertEquals(Vec3d.of(4, 4, 1), result);
	}

	@Test
	public void testwwy() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wwy();

		Assert.assertEquals(Vec3d.of(4, 4, 2), result);
	}

	@Test
	public void testwwz() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.wwz();

		Assert.assertEquals(Vec3d.of(4, 4, 3), result);
	}

	@Test
	public void testwww() {
		Vec4d one = Vec4d.of(1, 2, 3, 4);
		Vec3d result = one.www();

		Assert.assertEquals(Vec3d.of(4, 4, 4), result);
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
	public void testGetW() {
		double result = first.getW();
		Assert.assertEquals(2, result, 0);
	}

	@Test
	public void testQuat() {
		Quatd result = Vec4d.of(1, 2, 3, 4).quat();
		Assert.assertEquals(Quatd.of(1, 2, 3, 4), result);

		result = Vec4d.of(1, 2, 3, 5).quat(1);
		Assert.assertEquals(Quatd.of(1, 2, 3, 1), result);
	}

	@Test
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec4d.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec4d.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec4d.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec4d fromBytes = Vec4d.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		// TODO
		// Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Vec4d two = Vec4d.of(1, 1, 1, 1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec4d one = Vec4d.of(1, 1, 1, 1);
		Assert.assertEquals("vec4d[x=1.0, y=1.0, z=1.0, w=1.0]", one.toString());
	}

}
