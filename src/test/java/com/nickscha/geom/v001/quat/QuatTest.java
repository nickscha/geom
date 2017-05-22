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


public class QuatTest {
	
	@Test
	public void testQuatf(){
		Quatf result = Quat.of(1, 0, 1, 0);
		Assert.assertEquals(Quatf.of(1, 0, 1, 0), result);
	}

}
