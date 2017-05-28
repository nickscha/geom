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

import org.junit.Test;

import com.nickscha.geom.mat.Mat4f;
import com.nickscha.geom.vec.Vec3f;

/**
 * @author nickscha
 * @since 0.0.1
 * @version 0.0.1
 *
 */
public class ModelViewProjectionMatrix {

	@Test
	// Nice explanation from source:
	// http://www.learnopengles.com/tag/perspective-divide/
	public void createMVPMatrix() {
		Vec3f myObjectPosInScene = Vec3f.of(3, 5, -2);

		// Rotate around the same point as the model position
		Mat4f rotationMatrix = myObjectPosInScene.rotationMatrix();

		// 1,0,0,3 | 0,1,0,5 | 0,0,1,-2 | 0,0,0,1
		// This matrix is used to move a model somewhere in the world. For
		// example, let’s say we have a car model, and it’s defined such that it
		// is centered around (0, 0, 0). We can place one car at (5, 5, 5) by
		// setting up a model matrix that translates everything by (5, 5, 5),
		// and drawing the car model with this matrix. We can then easily add a
		// second car to the scene by just adjusting the translation. The model
		// matrix helps us to push stuff out into the world.
		float scale = 1.0f;
		Mat4f modelMatrix = Mat4f.modelMatrix(myObjectPosInScene, scale);

		// The view matrix is functionally equivalent to a camera. It does the
		// same thing as a model matrix, but it applies the same transformations
		// equally to every object in the scene. Moving the whole world 5 units
		// towards us is the same as if we had walked 5 units forwards.
		Mat4f viewMatrix = Mat4f.viewMatrix(myObjectPosInScene, rotationMatrix);

		// We shrink the model secene to the specified ratios and define that
		// objects nearer than one and farther than ten gets clipped out.
		Mat4f projectionMatrix = Mat4f.projectionMatrix(90, 1024 / 860, 1, 10);

		// The object to render has been moved from model to view (aka world)
		// space to the projection matrix (rectangle as the screen)
		Mat4f mvpMatrix = Mat4f.mvpMatrix(modelMatrix, viewMatrix, projectionMatrix);

	}

}
