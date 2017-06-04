# geom - in development (~30kb jar)

![Build Status](https://travis-ci.org/nickscha/geom.svg?branch=master)
![codecov.io](https://codecov.io/github/nickscha/geom/coverage.svg?branch=master)
![Size](https://reposs.herokuapp.com/?path=nickscha/geom)
![License](https://img.shields.io/hexpm/l/plug.svg)

A lightweight but powerful geometry libary with productive readiness API and fluent usages.

## What is in for you ?

* Fluent, easy to use api with a fast learning curve
* Rich documentation, use cases, tutorials
* Consistent function structure
* Full set of geometry types, as you would see them in shader languages such as GLSL (OpenGL Shader Language)


## Supported Types

Class names are aligned with GLSL names.

| Type          | Components                 | Status                                                           |
| ------------- | -------------------------: | ---------------------------------------------------------------- |
| Vectors       | Vec1f, Vec2f, Vec3f, Vec4f | ![Progress](https://img.shields.io/badge/progress-80-green.svg)  |
| Matrices      |               Mat3f, Mat4f | ![Progress](https://img.shields.io/badge/progress-40-orange.svg) |
| Quaternions   |                      Quatf | ![Progress](https://img.shields.io/badge/progress-10-orange.svg) |

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
