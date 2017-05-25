![Build Status](https://travis-ci.org/nickscha/geom.svg?branch=master)
![codecov.io](https://codecov.io/github/nickscha/geom/coverage.svg?branch=master)
![Size](https://reposs.herokuapp.com/?path=nickscha/geom)

# geom - in development
A lightweight but powerful geometry libary with productive readiness API and fluent usages.

## What is in for you ?

* Fluent, easy to use api with a fast learning curve
* Rich documentation, use cases, tutorials
* Consistent function structure
* Full set of geometry types, as you would see them in shader languages such as GLSL (OpenGL Shader Language)


## Supported Types

Class names are aligned with GLSL names. Delegate classes providing factory methods to components.

| Type          | Delegate | Components                 |
| ------------- | :------- | -------------------------: |
| Vectors       | Vec      | Vec1f, Vec2f, Vec3f, Vec4f |
| Matrices      | Mat      |               Mat3f, Mat4f |
| Quaternions   | Quat     |                      Quatf |

## Requirements

* Java 8 or later

## Example

For more examples have a look at the src/test/resources/demo directory.

### Calculating Normals based on two vectors
In this demo we will calculate the normal vector based on two supplied vectors.
```java
Vec3f one = Vec3f.of(1, 1, 1);
Vec3f two = Vec3f.of(-1, -1, -1);
Vec3f normalized = one.cross(two).normalize(); // Result is 0,0,0
```

## Motivation

There are other geom libaries already available but they have either
* a small set of supported types
* inconsistent structures (In some you'll find immutable methods next to mutable ones with the same function. Now that ain't pretty and helpful)

That why I've created this project with the aim of tackling both issues by having a comprehensive set of functions with a nice consistent api.