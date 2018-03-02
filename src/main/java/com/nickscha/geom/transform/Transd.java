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

import com.nickscha.geom.mat.Mat4d;
import com.nickscha.geom.quat.Quatd;
import com.nickscha.geom.vec.Vec3d;

/**
 * The Transform class allows cascading translation, rotation and scaling based
 * on the parent matrices, quaternions. <br/>
 * <b>Example:</b> <br/>
 * Imagine two wheel objects in your 3d scene which should be relative to the
 * position of the other wheel and the car chasis.
 * 
 * <pre>
 *  Wheel 1    Wheel 2
 *  +--+       +--+
 *  |  |-------|  |
 *  +--+       +--+
 * </pre>
 * 
 * When the driver steers both wheels should change their rotation based on the
 * amount of steering.
 * 
 * <pre>
 *  Wheel 1    Wheel 2
 *  +--+        +--+
 *   \  \------- \  \
 *    +--+        +--+
 * </pre>
 * 
 * In addition image that both wheels are relative to the car chasis
 * position/rotation. <br/>
 * <p>
 * This is where the Transform class comes in place because it is cascading
 * rotation, translations, ... based on the parent "object".
 * </p>
 * <b>Pseudo Code</b>
 * 
 * <pre>
 *   Model wheel1    = new Model("wheel1");
 *   Model wheel2    = new Model("wheel2");
 *   Model carChasis = new Model("carChasis");
 *   
 *   Tranformation carTrans = carChasis.getTransformation();
 *   wheel1.getTransformation().setParent(carTrans); // bind wheel to car chasis
 *   wheel2.getTransformation().setParent(carTrans); // bind wheel to car chasis
 *   
 *   carTrans.setRotation(...); // Apply your rotation matrix here
 * </pre>
 * 
 *
 * 
 * @author nickscha
 * @since 0.0.2
 * @version 0.0.2
 */
public final class Transd {

    private Transd parent;
    private Mat4d parentMatrix;

    private Vec3d pos;
    private Quatd rot;
    private Vec3d scale;

    private Vec3d oldPos;
    private Quatd oldRot;
    private Vec3d oldScale;

    public Transd() {
        pos = Vec3d.of(0);
        rot = Quatd.of(0, 0, 0, 1);
        scale = Vec3d.of(1);

        parentMatrix = Mat4d.identity();
    }

    public Transd update() {
        if (oldPos != null) {
            oldPos = Vec3d.of(pos);
            oldRot = Quatd.of(rot);
            oldScale = Vec3d.of(scale);
        } else {
            oldPos = Vec3d.of(pos).add(1.0d);
            oldRot = Quatd.of(rot).mul(0.5f);
            oldScale = Vec3d.of(scale).add(1.0d);
        }
        return this;
    }

    public Transd rotate(Vec3d axis, double angle) {
        rot = Quatd.of(axis, angle).mul(rot).normalize();
        return this;
    }

    public boolean hasChanged() {
        if (parent != null && parent.hasChanged()) {
            return true;
        } else if (!pos.equals(oldPos) || !rot.equals(oldRot) || !scale.equals(oldScale)) {
            return true;
        }
        return false;
    }

    public Mat4d getTransformation() {
        Mat4d translationMatrix = pos.translationMatrix();
        Mat4d rotationMatrix = rot.rotationMatrix();
        Mat4d scaleMatrix = scale.scaleMatrix();

        return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
    }

    private Mat4d getParentMatrix() {
        if (parent != null && parent.hasChanged()) {
            parentMatrix = parent.getTransformation();
        }
        return parentMatrix;
    }

    public Transd setParent(Transd parent) {
        this.parent = parent;
        return this;
    }

    public Vec3d getTransformedPos() {
        return getParentMatrix().transform(pos);
    }

    public Quatd getTransformedRot() {
        Quatd parentRotation = Quatd.of(0, 0, 0, 1);

        if (parent != null) {
            parentRotation = parent.getTransformedRot();
        }

        return parentRotation.mul(rot);
    }

    public Vec3d getPos() {
        return pos;
    }

    public Transd setPos(Vec3d position) {
        this.pos = position;
        return this;
    }

    public Quatd getRot() {
        return rot;
    }

    public Transd setRot(Quatd rotation) {
        this.rot = rotation;
        return this;
    }

    public Vec3d getScale() {
        return scale;
    }

    public Transd setScale(Vec3d scale) {
        this.scale = scale;
        return this;
    }
}
