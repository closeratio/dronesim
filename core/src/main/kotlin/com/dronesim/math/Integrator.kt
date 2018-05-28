package com.dronesim.math

abstract class Integrator {

    abstract fun integrate(base: Vec3d, modifier: Vec3d, delta: Number): Vec3d

}