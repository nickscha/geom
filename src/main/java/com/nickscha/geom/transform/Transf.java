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
package com.nickscha.geom.transform;

import com.nickscha.geom.mat.Mat4f;
import com.nickscha.geom.quat.Quatf;
import com.nickscha.geom.vec.Vec3f;

/**
 * The Transform class allows cascading translation, rotation and scaling based
 * on the parent matrices, quaternions.
 * 
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.1
 */
public final class Transf {

    private Transf parent;
    private Mat4f parentMatrix;

    private Vec3f pos;
    private Quatf rot;
    private Vec3f scale;

    private Vec3f oldPos;
    private Quatf oldRot;
    private Vec3f oldScale;

    public Transf() {
        pos = Vec3f.of(0);
        rot = Quatf.of(0, 0, 0, 1);
        scale = Vec3f.of(1);

        parentMatrix = Mat4f.IDENTITY;
    }

    public void update() {
        if (oldPos != null) {
            oldPos = Vec3f.of(pos);
            oldRot = Quatf.of(rot);
            oldScale = Vec3f.of(scale);
        } else {
            oldPos = Vec3f.of(pos).add(1.0f);
            oldRot = Quatf.of(rot).mul(0.5f);
            oldScale = Vec3f.of(scale).add(1.0f);
        }
    }

    public void rotate(Vec3f axis, float angle) {
        rot = Quatf.of(axis, angle).mul(rot).normalize();
    }

    public boolean hasChanged() {
        if (parent != null && parent.hasChanged()) {
            return true;
        } else if (!pos.equals(oldPos) || !rot.equals(oldRot) || !scale.equals(oldScale)) {
            return true;
        }
        return false;
    }

    public Mat4f getTransformation() {
        Mat4f translationMatrix = pos.translationMatrix();
        Mat4f rotationMatrix = rot.rotationMatrix();
        Mat4f scaleMatrix = scale.scaleMatrix();

        return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
    }

    private Mat4f getParentMatrix() {
        if (parent != null && parent.hasChanged()) {
            parentMatrix = parent.getTransformation();
        }
        return parentMatrix;
    }

    public void setParent(Transf parent) {
        this.parent = parent;
    }

    public Vec3f getTransformedPos() {
        return getParentMatrix().transform(pos);
    }

    public Quatf getTransformedRot() {
        Quatf parentRotation = Quatf.of(0, 0, 0, 1);

        if (parent != null) {
            parentRotation = parent.getTransformedRot();
        }

        return parentRotation.mul(rot);
    }

    public Vec3f getPos() {
        return pos;
    }

    public void setPos(Vec3f position) {
        this.pos = position;
    }

    public Quatf getRot() {
        return rot;
    }

    public void setRot(Quatf rotation) {
        this.rot = rotation;
    }

    public Vec3f getScale() {
        return scale;
    }

    public void setScale(Vec3f scale) {
        this.scale = scale;
    }
}
