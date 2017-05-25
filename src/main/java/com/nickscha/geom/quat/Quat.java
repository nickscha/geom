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
package com.nickscha.geom.quat;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public final class Quat {

	private Quat() {
	}

	public static Quatf of(float x, float y, float z, float w) {
		return new Quatf(x, y, z, w);
	}
}
