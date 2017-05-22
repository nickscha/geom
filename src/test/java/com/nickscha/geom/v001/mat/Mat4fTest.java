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

public class Mat4fTest {

	@Test
	public void testMat4fIdentity() {
		Mat4f mat4f = new Mat4f().initIdentity();

		Assert.assertTrue(mat4f.get(0, 0) == 1f);
		Assert.assertTrue(mat4f.get(1, 1) == 1f);
		Assert.assertTrue(mat4f.get(2, 2) == 1f);
		Assert.assertTrue(mat4f.get(3, 3) == 1f);
	}
	
	@Test
	public void testMat4fTranslation() {
		Mat4f mat4f = new Mat4f().initTranslation(2, 2, 2);

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
		Mat4f mat4f = new Mat4f().initScale(2, 2, 2);

		Assert.assertTrue(mat4f.get(0, 0) == 2f);
		Assert.assertTrue(mat4f.get(1, 1) == 2f);
		Assert.assertTrue(mat4f.get(2, 2) == 2f);
		Assert.assertTrue(mat4f.get(3, 3) == 1f);
	}
	
	@Test
	public void testMat4fRotation() {
		Mat4f mat4f = new Mat4f().initRotation(2, 2, 2);
	}
	
	@Test
	public void testMat4fPerspective() {
		Mat4f mat4f = new Mat4f().initPerspective(90, 1, 5, 100);
	}
	
	
	@Test
	public void testMat4fFrustum() {
		Mat4f mat4f = new Mat4f().initOrthographic(1, 1, 1, 1, 5, 100);
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
	public void testEquals(){
		Assert.assertTrue(new Mat4f().initIdentity().equals(new Mat4f().initIdentity()));
	}
	
	@Test
	public void testToString(){
		String result = new Mat4f().initIdentity().toString();
		
		Assert.assertEquals("mat4f[(1.0/0.0/0.0/0.0),(0.0/1.0/0.0/0.0),(0.0/0.0/1.0/0.0),(0.0/0.0/0.0/1.0)]", result);
	}

}
