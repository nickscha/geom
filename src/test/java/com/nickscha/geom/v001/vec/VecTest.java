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
import com.nickscha.geom.vec.Vec3f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class VecTest {

	@Test
	public void testVec1f() {
		Vec1f result = Vec.of(1);
		Assert.assertEquals(Vec1f.of(1), result);
	}

	@Test
	public void testVec2f() {
		Vec2f result = Vec.of(1, 2);
		Assert.assertEquals(Vec2f.of(1, 2), result);
	}

	@Test
	public void testVec3f() {
		Vec3f result = Vec.of(1, 2, 3);
		Assert.assertEquals(Vec3f.of(1, 2, 3), result);
	}
}
