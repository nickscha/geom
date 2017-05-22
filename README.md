![Build Status](https://travis-ci.org/nickscha/geom.svg?branch=master) 
![Size](https://reposs.herokuapp.com/?path=nickscha/geom)

# geom - in development
A lightweight but powerful geometry libary with productive readiness API and fluent usages.


# Example

For more examples have a look at the src/test/resources/demo directory.

## Calculating Normals based on two vectors
In this demo we will calculate the normal vector based on two supplied vectors.
```java
Vec3f one = Vec3f.of(1, 1, 1);
Vec3f two = Vec3f.of(-1, -1, -1);
Vec3f normalized = one.cross(two).normalize(); // Result is 0,0,0
```
