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
package com.nickscha.geom.v001.mat;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.mat.Mat4f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class Mat4fTest {

	@Test
	public void testIdentity() {
		Mat4f result = Mat4f.IDENTITY;
		Assert.assertTrue(result.get(0, 0) == 1f);
		Assert.assertTrue(result.get(1, 1) == 1f);
		Assert.assertTrue(result.get(2, 2) == 1f);
		Assert.assertTrue(result.get(3, 3) == 1f);
	}

	@Test
	public void testInit() {
		Mat4f result = Mat4f.of(2);
		for (int i = 0; i < Mat4f.GROUPS; i++) {
			for (int j = 0; j < Mat4f.FIELDS; j++) {
				Assert.assertTrue(result.get(i, j) == 2f);
			}
		}
	}

	@Test
	public void testMat4fIdentity() {
		Mat4f mat4f = Mat4f.identity();

		Assert.assertTrue(mat4f.get(0, 0) == 1f);
		Assert.assertTrue(mat4f.get(1, 1) == 1f);
		Assert.assertTrue(mat4f.get(2, 2) == 1f);
		Assert.assertTrue(mat4f.get(3, 3) == 1f);
	}

	@Test
	public void testMat4fTranslation() {
		Mat4f mat4f = Mat4f.translationMatrix(2, 2, 2);

		Assert.assertTrue(mat4f.get(0, 0) == 1f);
		Assert.assertTrue(mat4f.get(0, 3) == 2f);
		Assert.assertTrue(mat4f.get(1, 1) == 1f);
		Assert.assertTrue(mat4f.get(1, 3) == 2f);
		Assert.assertTrue(mat4f.get(2, 2) == 1f);
		Assert.assertTrue(mat4f.get(2, 3) == 2f);
		Assert.assertTrue(mat4f.get(3, 3) == 1f);
	}

	@Test
	public void testMat4fScale() {
		Mat4f mat4f = Mat4f.scaleMatrix(2, 2, 2);

		Assert.assertTrue(mat4f.get(0, 0) == 2f);
		Assert.assertTrue(mat4f.get(1, 1) == 2f);
		Assert.assertTrue(mat4f.get(2, 2) == 2f);
		Assert.assertTrue(mat4f.get(3, 3) == 1f);
	}

	@Test
	public void testMat4fRotation() {
		Mat4f mat4f = Mat4f.rotationMatrix(2, 2, 2);
	}

	@Test
	public void testMat4fPerspective() {
		Mat4f mat4f = Mat4f.perspectiveMatrix(90, 1, 5, 100);
	}

	@Test
	public void testMat4fFrustum() {
		Mat4f mat4f = Mat4f.orthographicMatrix(1, 1, 1, 1, 5, 100);
		new Mat4f().frustum(1, 1, 1, 1, 5, 100);
		new Mat4f().frustumNonPost(1, 1, 1, 1, 5, 100);
		new Mat4f().frustumRayDir(1, 2);
	}

	@Test
	public void testMat4fDeterminant() {
		float result = new Mat4f().determinant();
	}

	@Test
	public void testMat4fDeterminant3x3() {
		float result = new Mat4f().determinant3x3();
	}

	@Test
	public void testToBytes() {
		Mat4f first = Mat4f.IDENTITY;
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Mat4f.BYTES);
	}

	@Test
	public void testToBytesData() {
		Mat4f first = Mat4f.IDENTITY;
		byte[] data = new byte[Mat4f.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Mat4f.BYTES);
	}

	@Test
	public void testFromBytes() {
		Mat4f first = Mat4f.IDENTITY;
		byte[] bytes = first.toBytes();
		Mat4f fromBytes = Mat4f.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(Mat4f.IDENTITY.equals(Mat4f.identity()));
	}

	@Test
	public void testToString() {
		String result = Mat4f.identity().toString();

		Assert.assertEquals("mat4f[(1.0/0.0/0.0/0.0),(0.0/1.0/0.0/0.0),(0.0/0.0/1.0/0.0),(0.0/0.0/0.0/1.0)]", result);
	}

}
