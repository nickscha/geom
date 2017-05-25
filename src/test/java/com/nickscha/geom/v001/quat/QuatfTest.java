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
package com.nickscha.geom.v001.quat;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.quat.Quat;
import com.nickscha.geom.quat.Quatf;
import com.nickscha.geom.vec.Vec3f;
import com.nickscha.geom.vec.Vec4f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class QuatfTest {

	@Test
	public void testInit() {
		Assert.assertEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1));
		Assert.assertEquals(Quatf.of(1), Quatf.of(1, 1, 1, 1));
		Quatf.of(Vec3f.of(1, 0, 0), 90);
	}

	@Test
	public void testLengthSquared() {
		Assert.assertTrue(Quatf.of(1, 1, 1, 1).lengthSquared() == 4);
	}

	@Test
	public void testGetForward() {
		System.out.println(Quatf.of(1, 1, 1, 0).getForward());
	}

	@Test
	public void testGetBack() {
		System.out.println(Quatf.of(1, 1, 1, 0).getBack());
	}

	@Test
	public void testGetUp() {
		System.out.println(Quatf.of(1, 1, 1, 0).getUp());
	}

	@Test
	public void testGetDown() {
		System.out.println(Quatf.of(1, 1, 1, 0).getDown());
	}

	@Test
	public void testGetLeft() {
		System.out.println(Quatf.of(1, 1, 1, 0).getLeft());
	}

	@Test
	public void testGetRight() {
		System.out.println(Quatf.of(1, 1, 1, 0).getRight());
	}

	@Test
	public void testLength() {
		Assert.assertTrue(Quatf.of(1, 1, 1, 1).length() == 2);
	}

	@Test
	public void testIsZero() {
		Assert.assertTrue(Quatf.of(0, 0, 0, 0).isZero());
	}

	@Test
	public void testNegate() {
		Assert.assertEquals(Quatf.of(-1, -1, -1, -1), Quatf.of(1, 1, 1, 1).negate());
	}

	@Test
	public void testConjugate() {
		Assert.assertEquals(Quatf.of(-1, -1, -1, 1), Quatf.of(1, 1, 1, 1).conjugate());
	}

	@Test
	public void testAdd() {
		Assert.assertEquals(Quatf.of(2, 2, 2, 2), Quatf.of(1, 1, 1, 1).add(Quat.of(1, 1, 1, 1)));
	}

	@Test
	public void testAddAmt() {
		Assert.assertEquals(Quatf.of(2, 2, 2, 2), Quatf.of(1, 1, 1, 1).add(1));
	}

	@Test
	public void testSub() {
		Assert.assertEquals(Quatf.of(0, 0, 0, 0), Quatf.of(1, 1, 1, 1).sub(Quat.of(1, 1, 1, 1)));
	}

	@Test
	public void testSubAmt() {
		Assert.assertEquals(Quatf.of(0, 0, 0, 0), Quatf.of(1, 1, 1, 1).sub(1));
	}

	@Test
	public void testMul() {
		Assert.assertEquals(Quatf.of(2, 2, 2, -2), Quatf.of(1, 1, 1, 1).mul(Quat.of(1, 1, 1, 1)));
	}

	@Test
	public void testMulAmt() {
		Assert.assertEquals(Quatf.of(2, 2, 2, -2), Quatf.of(1, 1, 1, 1).mul(1));
	}

	@Test
	public void testMulVec3f() {
		Assert.assertEquals(Quatf.of(1, 1, 1, -3), Quatf.of(1, 1, 1, 1).mul(Vec3f.of(1, 1, 1)));
	}

	@Test
	public void testDiv() {
		Assert.assertEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 1, 1, 1).div(Quat.of(1, 1, 1, 1)));
	}

	@Test
	public void testDivAmt() {
		Assert.assertEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 1, 1, 1).div(1));
	}

	@Test
	public void testGetX() {
		Assert.assertTrue(Quat.of(1, 1, 1, 1).getX() == 1f);
	}

	@Test
	public void testGetY() {
		Assert.assertTrue(Quat.of(1, 1, 1, 1).getY() == 1f);
	}

	@Test
	public void testGetZ() {
		Assert.assertTrue(Quat.of(1, 1, 1, 1).getZ() == 1f);
	}

	@Test
	public void testGetW() {
		Assert.assertTrue(Quat.of(1, 1, 1, 1).getW() == 1f);
	}
	
	@Test
	public void testHashcode(){
		int result = Quatf.of(1, 1, 1, 1).hashCode();
	}
	
	@Test
	public void testToBytes() {
		Quatf first = Quatf.of(1, 2, 3, 4);
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Quatf.BYTES);
	}

	@Test
	public void testToBytesData() {
		Quatf first = Quatf.of(1, 2, 3, 4);
		byte[] data = new byte[Quatf.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Quatf.BYTES);
	}

	@Test
	public void testFromBytes() {
		Quatf first = Quatf.of(1, 2, 3, 4);
		byte[] bytes = first.toBytes();
		Quatf fromBytes = Quatf.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testEquals() {
		Assert.assertEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 1, 1, 1));
		Assert.assertNotEquals(Quatf.of(1, 1, 1, 1), Quatf.of(2, 1, 1, 1));
		Assert.assertNotEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 2, 1, 1));
		Assert.assertNotEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 1, 2, 1));
		Assert.assertNotEquals(Quatf.of(1, 1, 1, 1), Quatf.of(1, 1, 1, 2));
	}

	@Test
	public void testToString() {
		String result = Quatf.of(1, 1, 1, 1).toString();
		Assert.assertEquals("quatf[x=1.0, y=1.0, z=1.0, w=1.0]", result);
	}
}
