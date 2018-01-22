# geom

[![Maven](https://maven-badges.herokuapp.com/maven-central/com.github.nickscha/geom/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nickscha/geom)
[![javadoc](http://javadoc.io/badge/com.github.nickscha/geom.svg)](http://javadoc.io/doc/com.github.nickscha/geom)
![Build Status](https://travis-ci.org/nickscha/geom.svg?branch=master)
![codecov.io](https://codecov.io/github/nickscha/geom/coverage.svg?branch=master)
![License](https://img.shields.io/hexpm/l/plug.svg)

A lightweight geometry liberay with fluent API.

## Maven
```xml
<dependency>
  <groupId>com.github.nickscha</groupId>
  <artifactId>geom</artifactId>
  <version>0.0.1</version>
</dependency>
```

The artifact is published under https://oss.sonatype.org


## Supported Types

Class names are aligned with GLSL names.

| Type          | Components                 | Progress  |
| ------------- | -------------------------: | --------- |
| Vectors       | Vec1f, Vec2f, Vec3f, Vec4f | 80%       |
|               | Vec1d, Vec2d, Vec3d, Vec4d |  0%       |
| Matrices      |               Mat3f, Mat4f | 60%       |
|               |               Mat3d, Mat4d |  0%       |
| Quaternions   |                      Quatf | 40%       |
|               |                      Quatd |  0%       |
| Transform     |             Transf, Transd | 10%       |

## Roadmap version 0.0.2

* Add cascading Transform (Transf) class for translating, scaling and rotating matrices based on their parent matrices.
* Enhance Javadocs

## Requirements

* Java 8 or later

## Examples

For more examples have a look at the src/test/resources/demo directory.

### MVP (Model View Projection Matrix)

```java
Vec3f myObjectPosInScene = Vec3f.of(3, 5, -2);
Mat4f rotationMatrix = myObjectPosInScene.rotationMatrix();
Mat4f modelMatrix = Mat4f.modelMatrix(myObjectPosInScene);

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
```

### Look at
Imagine an object in your 3d scene which should look always to your camera.
Here we resolve the roatation matrix for the model...

```java
Vec3f camera = Vec3f.of(0, 5, 5);
Vec3f modelToLookAtCamera = Vec3f.of(7, 7, 7);
Mat4f.lookAtMatrix(camera, modelToLookAtCamera, Vec3f.of(0, 1, 0));
```

### Calculating Normals based on two vectors
In this demo we will calculate the normal vector based on two supplied vectors.
```java
Vec3f one = Vec3f.of(1, 1, 1);
Vec3f two = Vec3f.of(-1, -1, -1);
Vec3f normalized = one.cross(two).normalize(); // Result is 0,0,0
```

### Swizzling types
Geom provides swizzling for all types.
```java
Vec2f res = Vec3f.of(1, 2, 1).mul(2).xy();
Mat4f res = Vec3f.of(6, 2, 1).scaleMatrix();
```

### Serialization
Geom provides a general purpose serialization for all types.
```java
byte[] data = Vec3f.of(1,2,3).toBytes();
Vec3f res = Vec3f.fromBytes(data);
```
