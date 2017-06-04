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
import com.nickscha.geom.quat.Quatf;
import com.nickscha.geom.vec.Vec2f;
import com.nickscha.geom.vec.Vec3f;
import com.nickscha.geom.vec.Vec4f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 */
public class Vec4fTest {

	Vec4f first = Vec4f.of(6, 4, 2, 2);
	Vec4f second = Vec4f.of(4, 2, 1, 1);

	@Test
	public void testInit() {
		Vec4f result = new Vec4f(2);
		Vec4f expected = Vec4f.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);

		result = Vec4f.of(2);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAdd() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.add(two);
		Vec4f expected = Vec4f.of(3, 3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testAddAmt() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Vec4f result = one.add(2f);
		Vec4f expected = Vec4f.of(3, 3, 3, 3);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSub() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.sub(two);
		Vec4f expected = Vec4f.of(-1, -1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSubAmt() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Vec4f result = one.sub(2f);
		Vec4f expected = Vec4f.of(-1, -1, -1, -1);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMul() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.mul(two);
		Vec4f expected = Vec4f.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMulAmt() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Vec4f result = one.mul(2f);
		Vec4f expected = Vec4f.of(2, 2, 2, 2);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDiv() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.div(two);
		Vec4f expected = Vec4f.of(0.5f, 0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDivAmt() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Vec4f result = one.div(2);
		Vec4f expected = Vec4f.of(0.5f, 0.5f, 0.5f, 0.5f);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void testMin() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);

		float result = one.min();

		Assert.assertTrue(1f == result);
	}

	@Test
	public void testMinVec() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.min(two);

		Assert.assertEquals(one, result);
	}

	@Test
	public void testMax() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);

		float result = one.max();

		Assert.assertTrue(4f == result);
	}

	@Test
	public void testMaxVec() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.max(two);

		Assert.assertEquals(two, result);
	}

	@Test
	public void testNegate() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Vec4f result = one.negate();

		Assert.assertEquals(Vec4f.of(-1, -1, -1, -1), result);
	}

	@Test
	public void testIsUnit() {
		Assert.assertTrue(Vec4f.of(1, 0, 0, 0).isUnit());
		Assert.assertTrue(Vec4f.of(0, 1, 0, 0).isUnit());
		Assert.assertTrue(Vec4f.of(0, 0, 1, 0).isUnit());
		Assert.assertTrue(Vec4f.of(0, 0, 0, 1).isUnit());
	}

	@Test
	public void testIsNaN() {
		Assert.assertTrue(Vec4f.of(Float.NaN, 0, 0, 0).isNAN());
		Assert.assertTrue(Vec4f.of(0, Float.NaN, 0, 0).isNAN());
		Assert.assertTrue(Vec4f.of(0, 0, Float.NaN, 0).isNAN());
		Assert.assertTrue(Vec4f.of(0, 0, 0, Float.NaN).isNAN());
	}

	@Test
	public void testIsInfinite() {
		Assert.assertTrue(Vec4f.of(Float.POSITIVE_INFINITY, 0, 0, 0).isInfinite());
		Assert.assertTrue(Vec4f.of(0, Float.POSITIVE_INFINITY, 0, 0).isInfinite());
		Assert.assertTrue(Vec4f.of(0, 0, Float.POSITIVE_INFINITY, 0).isInfinite());
		Assert.assertTrue(Vec4f.of(0, 0, 0, Float.POSITIVE_INFINITY).isInfinite());
	}

	@Test
	public void testIsValid() {
		Assert.assertTrue(Vec4f.of(1, 1, 1, 1).isValid());
	}

	@Test
	public void testIsZero() {
		Assert.assertFalse(Vec4f.of(1, 0, 0, 0).isZero());
		Assert.assertTrue(Vec4f.of(0, 0, 0, 0).isZero());
	}

	@Test
	public void testIsSameDirection() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(3, 3, 3, 3);
		Vec4f three = Vec4f.of(-1, 1, -1, -1);

		Assert.assertTrue(one.isSameDirection(two));
		Assert.assertTrue(two.isSameDirection(one));
		Assert.assertFalse(one.isSameDirection(three));
	}

	@Test
	public void testIsOppositeDirection() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(1, -1, -1, -1);

		Assert.assertTrue(one.isOppositeDirection(two));
	}

	@Test
	public void testCeil() {
		Vec4f result = Vec4f.of(1.5f, 1.5f, 1.5f, 1.5f).ceil();

		Assert.assertEquals(Vec4f.of(2, 2, 2, 2), result);
	}

	@Test
	public void testFloor() {
		Vec4f result = Vec4f.of(1.5f, 1.5f, 1.5f, 1.5f).floor();

		Assert.assertEquals(Vec4f.of(1, 1, 1, 1), result);
	}

	@Test
	public void testAbsolute() {
		Vec4f result = Vec4f.of(-1, 1, -1, -1).absolute();

		Assert.assertEquals(Vec4f.of(1, 1, 1, 1), result);
	}

	@Test
	public void testClamp() {
		Assert.assertEquals(Vec4f.of(1, 1, 1, 1), Vec4f.of(2, 2, 2, 2).clamp(1));
		Assert.assertEquals(Vec4f.of(2, 1, 2, 2), Vec4f.of(2, 2, 2, 2).clamp(Vec4f.of(2, 1, 3, 3)));
	}

	@Test
	public void testProject() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 1);

		Vec4f result = one.project(two);

		Assert.assertEquals(Vec4f.of(0.29868475f, 0.29868475f, 0.29868475f, 0.14934237f), result);
	}

	@Test
	public void testReflect() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2).normalize();

		Vec4f result = one.reflect(two);

		Assert.assertEquals(Vec4f.of(-1, -1, -1, -1), result);
	}

	@Test
	public void testHalf() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(2, 2, 2, 2);

		Vec4f result = one.half(two);

		Assert.assertEquals(Vec4f.of(0.5f, 0.5f, 0.5f, 0.5f), result);
	}

	@Test
	public void testOrthogonal() {
		Vec4f one = Vec4f.of(2, 2, 0, 0);
		Vec4f two = Vec4f.of(4, 4, 0, 0);

		Vec4f result = one.orthogonal(two);
		Assert.assertEquals(Vec4f.of(0, 0, -0.99999994f, 0), result);
	}

	@Test
	public void testLength() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Assert.assertTrue(2 == one.length());
	}

	@Test
	public void testLengthSquared() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Assert.assertTrue(4f == one.lengthSquared());

		Vec4f two = Vec4f.of(2, 2, 2, 2);
		Assert.assertTrue(16f == two.lengthSquared());
	}

	@Test
	public void testLengthManhattan() {
		Vec4f start = Vec4f.of(0, 0, 0, 0);
		Vec4f end = Vec4f.of(2, 2, 2, 2);

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
		Vec4f one = Vec4f.of(0, 0, 0, 0);
		Vec4f two = Vec4f.of(2, 1, 0, 0);

		float result = one.lengthEuclidean(two);

		Assert.assertTrue(2.236068f == result);

	}

	@Test
	public void testDelta() {
		Vec4f two = Vec4f.of(0, 0, 4, 0);
		float result = two.delta();
		Assert.assertTrue(result == 1.5707964f);
	}

	@Test
	public void testDot() {
		Vec4f start = Vec4f.of(1, 0, 0, 0);
		Vec4f opposite = Vec4f.of(-1, 0, 0, 0);
		Vec4f same = Vec4f.of(1, 0, 0, 0);
		Vec4f degree90 = Vec4f.of(0, 1, 0, 0);
		Vec4f degree90minus = Vec4f.of(0, -1, 0, -1);

		Assert.assertTrue(-1f == start.dot(opposite));
		Assert.assertTrue(1f == start.dot(same));
		Assert.assertTrue(0f == start.dot(degree90));
		Assert.assertTrue(0f == start.dot(degree90minus));
	}

	@Test
	public void testNormalize() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(-1, -1, -1, -1);

		Vec4f normalized = one.cross(two).normalize();

		/**
		 * <pre>
		 *            ^
		 * two(-1,-1) | one(1,1)
		 *          \ | /
		 *           \|/
		 *        normalized
		 * </pre>
		 */
		Vec4f expected = Vec4f.of(0, 0, 0, 1);

		Assert.assertEquals(expected, normalized);
	}

	@Test
	public void testLerp() {
		Vec4f start = Vec4f.of(0, 0, 0, 0);
		Vec4f end = Vec4f.of(2, 2, 2, 2);

		Assert.assertEquals(Vec4f.of(0.0f, 0.0f, 0.0f, 0.0f), start.lerp(end, 0.00f));
		Assert.assertEquals(Vec4f.of(0.5f, 0.5f, 0.5f, 0.5f), start.lerp(end, 0.25f));
		Assert.assertEquals(Vec4f.of(1.0f, 1.0f, 1.0f, 1.0f), start.lerp(end, 0.50f));
		Assert.assertEquals(Vec4f.of(1.5f, 1.5f, 1.5f, 1.5f), start.lerp(end, 0.75f));
		Assert.assertEquals(Vec4f.of(2.0f, 2.0f, 2.0f, 2.0f), start.lerp(end, 1.00f));
	}

	@Test
	public void testScreenSpace() {
		Mat4f viewMatrix = new Mat4f();
		Mat4f projectionMatrix = new Mat4f();

		Vec4f anObjectInScene = Vec4f.of(5, 3, -5, 1f);
		Vec2f screenSpace = anObjectInScene.screenSpace(viewMatrix, projectionMatrix);
	}

	@Test
	public void testVec2() {
		Vec4f one = Vec4f.of(1, 2, 3, 0);
		Vec2f result = one.vec2();

		Assert.assertEquals(Vec2f.of(1, 2), result);
	}

	@Test
	public void testVec3() {
		Vec4f one = Vec4f.of(1, 2, 3, 0);

		Vec3f result = one.vec3();
		Assert.assertEquals(Vec3f.of(1, 2, 3), result);
	}

	@Test
	public void testxxx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xxx();

		Assert.assertEquals(Vec3f.of(1, 1, 1), result);
	}

	@Test
	public void testxxy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xxy();

		Assert.assertEquals(Vec3f.of(1, 1, 2), result);
	}

	@Test
	public void testxxz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xxz();

		Assert.assertEquals(Vec3f.of(1, 1, 3), result);
	}

	@Test
	public void testxxw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xxw();

		Assert.assertEquals(Vec3f.of(1, 1, 4), result);
	}

	@Test
	public void testxyx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xyx();

		Assert.assertEquals(Vec3f.of(1, 2, 1), result);
	}

	@Test
	public void testxyy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xyy();

		Assert.assertEquals(Vec3f.of(1, 2, 2), result);
	}

	@Test
	public void testxyz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xyz();

		Assert.assertEquals(Vec3f.of(1, 2, 3), result);
	}

	@Test
	public void testxyw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xyw();

		Assert.assertEquals(Vec3f.of(1, 2, 4), result);
	}

	@Test
	public void testxzx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xzx();

		Assert.assertEquals(Vec3f.of(1, 3, 1), result);
	}

	@Test
	public void testxzy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xzy();

		Assert.assertEquals(Vec3f.of(1, 3, 2), result);
	}

	@Test
	public void testxzz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xzz();

		Assert.assertEquals(Vec3f.of(1, 3, 3), result);
	}

	@Test
	public void testxzw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xzw();

		Assert.assertEquals(Vec3f.of(1, 3, 4), result);
	}

	@Test
	public void testxwx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xwx();

		Assert.assertEquals(Vec3f.of(1, 4, 1), result);
	}

	@Test
	public void testxwy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xwy();

		Assert.assertEquals(Vec3f.of(1, 4, 2), result);
	}

	@Test
	public void testxwz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xwz();

		Assert.assertEquals(Vec3f.of(1, 4, 3), result);
	}

	@Test
	public void testxww() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.xww();

		Assert.assertEquals(Vec3f.of(1, 4, 4), result);
	}

	@Test
	public void testyxx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yxx();

		Assert.assertEquals(Vec3f.of(2, 1, 1), result);
	}

	@Test
	public void testyxy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yxy();

		Assert.assertEquals(Vec3f.of(2, 1, 2), result);
	}

	@Test
	public void testyxz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yxz();

		Assert.assertEquals(Vec3f.of(2, 1, 3), result);
	}

	@Test
	public void testyxw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yxw();

		Assert.assertEquals(Vec3f.of(2, 1, 4), result);
	}

	@Test
	public void testyyx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yyx();

		Assert.assertEquals(Vec3f.of(2, 2, 1), result);
	}

	@Test
	public void testyyy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yyy();

		Assert.assertEquals(Vec3f.of(2, 2, 2), result);
	}

	@Test
	public void testyyz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yyz();

		Assert.assertEquals(Vec3f.of(2, 2, 3), result);
	}

	@Test
	public void testyyw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yyw();

		Assert.assertEquals(Vec3f.of(2, 2, 4), result);
	}

	@Test
	public void testyzx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yzx();

		Assert.assertEquals(Vec3f.of(2, 3, 1), result);
	}

	@Test
	public void testyzy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yzy();

		Assert.assertEquals(Vec3f.of(2, 3, 2), result);
	}

	@Test
	public void testyzz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yzz();

		Assert.assertEquals(Vec3f.of(2, 3, 3), result);
	}

	@Test
	public void testyzw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yzw();

		Assert.assertEquals(Vec3f.of(2, 3, 4), result);
	}

	@Test
	public void testywx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.ywx();

		Assert.assertEquals(Vec3f.of(2, 4, 1), result);
	}

	@Test
	public void testywy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.ywy();

		Assert.assertEquals(Vec3f.of(2, 4, 2), result);
	}

	@Test
	public void testywz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.ywz();

		Assert.assertEquals(Vec3f.of(2, 4, 3), result);
	}

	@Test
	public void testyww() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.yww();

		Assert.assertEquals(Vec3f.of(2, 4, 4), result);
	}

	@Test
	public void testzxx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zxx();

		Assert.assertEquals(Vec3f.of(3, 1, 1), result);
	}

	@Test
	public void testzxy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zxy();

		Assert.assertEquals(Vec3f.of(3, 1, 2), result);
	}

	@Test
	public void testzxz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zxz();

		Assert.assertEquals(Vec3f.of(3, 1, 3), result);
	}

	@Test
	public void testzxw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zxw();

		Assert.assertEquals(Vec3f.of(3, 1, 4), result);
	}

	@Test
	public void testzyx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zyx();

		Assert.assertEquals(Vec3f.of(3, 2, 1), result);
	}

	@Test
	public void testzyy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zyy();

		Assert.assertEquals(Vec3f.of(3, 2, 2), result);
	}

	@Test
	public void testzyz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zyz();

		Assert.assertEquals(Vec3f.of(3, 2, 3), result);
	}

	@Test
	public void testzyw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zyw();

		Assert.assertEquals(Vec3f.of(3, 2, 4), result);
	}

	@Test
	public void testzzx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zzx();

		Assert.assertEquals(Vec3f.of(3, 3, 1), result);
	}

	@Test
	public void testzzy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zzy();

		Assert.assertEquals(Vec3f.of(3, 3, 2), result);
	}

	@Test
	public void testzzz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zzz();

		Assert.assertEquals(Vec3f.of(3, 3, 3), result);
	}

	@Test
	public void testzzw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zzw();

		Assert.assertEquals(Vec3f.of(3, 3, 4), result);
	}

	@Test
	public void testzwx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zwx();

		Assert.assertEquals(Vec3f.of(3, 4, 1), result);
	}

	@Test
	public void testzwy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zwy();

		Assert.assertEquals(Vec3f.of(3, 4, 2), result);
	}

	@Test
	public void testzwz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zwz();

		Assert.assertEquals(Vec3f.of(3, 4, 3), result);
	}

	@Test
	public void testzww() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.zww();

		Assert.assertEquals(Vec3f.of(3, 4, 4), result);
	}

	@Test
	public void testwxx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wxx();

		Assert.assertEquals(Vec3f.of(4, 1, 1), result);
	}

	@Test
	public void testwxy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wxy();

		Assert.assertEquals(Vec3f.of(4, 1, 2), result);
	}

	@Test
	public void testwxz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wxz();

		Assert.assertEquals(Vec3f.of(4, 1, 3), result);
	}

	@Test
	public void testwxw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wxw();

		Assert.assertEquals(Vec3f.of(4, 1, 4), result);
	}

	@Test
	public void testwyx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wyx();

		Assert.assertEquals(Vec3f.of(4, 2, 1), result);
	}

	@Test
	public void testwyy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wyy();

		Assert.assertEquals(Vec3f.of(4, 2, 2), result);
	}

	@Test
	public void testwyz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wyz();

		Assert.assertEquals(Vec3f.of(4, 2, 3), result);
	}

	@Test
	public void testwyw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wyw();

		Assert.assertEquals(Vec3f.of(4, 2, 4), result);
	}

	@Test
	public void testwzx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wzx();

		Assert.assertEquals(Vec3f.of(4, 3, 1), result);
	}

	@Test
	public void testwzy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wzy();

		Assert.assertEquals(Vec3f.of(4, 3, 2), result);
	}

	@Test
	public void testwzz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wzz();

		Assert.assertEquals(Vec3f.of(4, 3, 3), result);
	}

	@Test
	public void testwzw() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wzw();

		Assert.assertEquals(Vec3f.of(4, 3, 4), result);
	}

	@Test
	public void testwwx() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wwx();

		Assert.assertEquals(Vec3f.of(4, 4, 1), result);
	}

	@Test
	public void testwwy() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wwy();

		Assert.assertEquals(Vec3f.of(4, 4, 2), result);
	}

	@Test
	public void testwwz() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.wwz();

		Assert.assertEquals(Vec3f.of(4, 4, 3), result);
	}

	@Test
	public void testwww() {
		Vec4f one = Vec4f.of(1, 2, 3, 4);
		Vec3f result = one.www();

		Assert.assertEquals(Vec3f.of(4, 4, 4), result);
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
	public void testGetW() {
		float result = first.getW();
		Assert.assertEquals(2, result, 0);
	}

	@Test
	public void testQuat() {
		Quatf result = Vec4f.of(1, 2, 3, 4).quat();
		Assert.assertEquals(Quatf.of(1, 2, 3, 4), result);

		result = Vec4f.of(1, 2, 3, 5).quat(1);
		Assert.assertEquals(Quatf.of(1, 2, 3, 1), result);
	}

	@Test
	public void testToBytes() {
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Vec4f.BYTES);
	}

	@Test
	public void testToBytesData() {
		byte[] data = new byte[Vec4f.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Vec4f.BYTES);
	}

	@Test
	public void testFromBytes() {
		byte[] bytes = first.toBytes();
		Vec4f fromBytes = Vec4f.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testHashcode() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);

		Assert.assertTrue(one.hashCode() == one.hashCode());
		// TODO
		// Assert.assertFalse(one.hashCode() == two.hashCode());
	}

	@Test
	public void testEquals() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Vec4f two = Vec4f.of(1, 1, 1, 1);

		Assert.assertEquals(one, two);
		Assert.assertEquals(two, one);
		Assert.assertEquals(one, one);
	}

	@Test
	public void testToString() {
		Vec4f one = Vec4f.of(1, 1, 1, 1);
		Assert.assertEquals("vec4f[x=1.0, y=1.0, z=1.0, w=1.0]", one.toString());
	}

}
