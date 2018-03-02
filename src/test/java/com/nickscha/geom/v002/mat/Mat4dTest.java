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
package com.nickscha.geom.v002.mat;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.vec.Vec3d;

/**
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.2
 *
 */
public class Mat4dTest {

	@Test
	public void testIdentity() {
		Mat4d result = Mat4d.IDENTITY;
		Assert.assertTrue(result.get(0, 0) == 1f);
		Assert.assertTrue(result.get(1, 1) == 1f);
		Assert.assertTrue(result.get(2, 2) == 1f);
		Assert.assertTrue(result.get(3, 3) == 1f);
	}

	@Test
	public void testInit() {
		Mat4d result = Mat4d.of(2);
		for (int i = 0; i < Mat4d.GROUPS; i++) {
			for (int j = 0; j < Mat4d.FIELDS; j++) {
				Assert.assertTrue(result.get(i, j) == 2f);
			}
		}
	}

	@Test
	public void testMat4dIdentity() {
		Mat4d mat4d = Mat4d.identity();

		Assert.assertTrue(mat4d.get(0, 0) == 1f);
		Assert.assertTrue(mat4d.get(1, 1) == 1f);
		Assert.assertTrue(mat4d.get(2, 2) == 1f);
		Assert.assertTrue(mat4d.get(3, 3) == 1f);
	}

	@Test
	public void testMat4dTranslation() {
		Mat4d mat4d = Mat4d.translationMatrix(2, 2, 2);

		Assert.assertTrue(mat4d.get(0, 0) == 1f);
		Assert.assertTrue(mat4d.get(0, 3) == 2f);
		Assert.assertTrue(mat4d.get(1, 1) == 1f);
		Assert.assertTrue(mat4d.get(1, 3) == 2f);
		Assert.assertTrue(mat4d.get(2, 2) == 1f);
		Assert.assertTrue(mat4d.get(2, 3) == 2f);
		Assert.assertTrue(mat4d.get(3, 3) == 1f);
	}

	@Test
	public void testMat4dScale() {
		Mat4d mat4d = Mat4d.scaleMatrix(2, 2, 2);

		Assert.assertTrue(mat4d.get(0, 0) == 2f);
		Assert.assertTrue(mat4d.get(1, 1) == 2f);
		Assert.assertTrue(mat4d.get(2, 2) == 2f);
		Assert.assertTrue(mat4d.get(3, 3) == 1f);

		mat4d = Mat4d.scaleMatrix(Vec3d.of(2, 2, 2));

		Assert.assertTrue(mat4d.get(0, 0) == 2f);
		Assert.assertTrue(mat4d.get(1, 1) == 2f);
		Assert.assertTrue(mat4d.get(2, 2) == 2f);
		Assert.assertTrue(mat4d.get(3, 3) == 1f);
	}
	
	@Test
	public void testScale() {
	    Mat4d res = Mat4d.identity().scale(Vec3d.of(2, 2, 2));
	    
        double[][] expArr = new double[4][4];
        expArr[0][0] = 2;
        expArr[0][1] = 0;
        expArr[0][2] = 0;
        expArr[0][3] = 0;
        expArr[1][0] = 0;
        expArr[1][1] = 2;
        expArr[1][2] = 0;
        expArr[1][3] = 0;
        expArr[2][0] = 0;
        expArr[2][1] = 0;
        expArr[2][2] = 2;
        expArr[2][3] = 0;
        expArr[3][0] = 0;
        expArr[3][1] = 0;
        expArr[3][2] = 0;
        expArr[3][3] = 0;

        Assert.assertEquals(Mat4d.of(expArr), res);
	}

	@Test
	public void testMat4dRotation() {
		Mat4d mat4d = Mat4d.rotationMatrix(2, 2, 2);
	}

	@Test
	public void testMat4dPerspective() {
		Mat4d mat4d = Mat4d.perspectiveMatrix(90, 1, 5, 100);
	}

	@Test
	public void testMat4dFrustum() {
		Mat4d mat4d = Mat4d.orthographicMatrix(1, 1, 1, 1, 5, 100);
		new Mat4d().frustum(1, 1, 1, 1, 5, 100);
		new Mat4d().frustumNonPost(1, 1, 1, 1, 5, 100);
		new Mat4d().frustumRayDir(1, 2);
	}

	@Test
	public void testMat4dDeterminant() {
		double result = new Mat4d().determinant();
	}

	@Test
	public void testMat4dDeterminant3x3() {
		double result = new Mat4d().determinant3x3();
	}

	@Test
	public void testToBytes() {
		Mat4d first = Mat4d.IDENTITY;
		byte[] bytes = first.toBytes();
		Assert.assertTrue(bytes.length == Mat4d.BYTES);
	}

	@Test
	public void testToBytesData() {
		Mat4d first = Mat4d.IDENTITY;
		byte[] data = new byte[Mat4d.BYTES];
		byte[] bytes = first.toBytes(data);
		Assert.assertTrue(bytes.length == Mat4d.BYTES);
	}

	@Test
	public void testFromBytes() {
		Mat4d first = Mat4d.IDENTITY;
		byte[] bytes = first.toBytes();
		System.out.println(Arrays.toString(bytes));
		Mat4d fromBytes = Mat4d.fromBytes(bytes);

		Assert.assertNotNull(fromBytes);
		Assert.assertEquals(first, fromBytes);
	}
	
	@Test
	public void testHashCode(){
		Mat4d.IDENTITY.hashCode();
	}

	@Test
	public void testEquals() {
		Assert.assertTrue(Mat4d.IDENTITY.equals(Mat4d.identity()));
	}

	@Test
	public void testToString() {
		String result = Mat4d.identity().toString();

		Assert.assertEquals("mat4d[(1.0/0.0/0.0/0.0),(0.0/1.0/0.0/0.0),(0.0/0.0/1.0/0.0),(0.0/0.0/0.0/1.0)]", result);
	}

}
