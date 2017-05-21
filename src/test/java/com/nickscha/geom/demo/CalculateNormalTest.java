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
package com.nickscha.geom.demo;

import org.junit.Assert;
import org.junit.Test;

import com.nickscha.geom.vec.Vec3f;

public class CalculateNormalTest {

	/**
	 * In this demo we will calculate the normal vector based on two supplied vectors.
	 * 
	 * <pre>
	 *            ^
	 * two(-1,-1) | one(1,1)
	 *          \ | /
	 *           \|/
	 *            0
	 * </pre>
	 */
	@Test
	public void testNormalize() {
		Vec3f one = Vec3f.of(1, 1, 1);
		Vec3f two = Vec3f.of(-1, -1, -1);

		Vec3f normalized = one.cross(two).normalize();
		Vec3f expected = Vec3f.of(0, 0, 0);

		Assert.assertEquals(expected, normalized);
	}

}
