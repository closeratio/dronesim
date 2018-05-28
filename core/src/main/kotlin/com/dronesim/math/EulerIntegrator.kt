package com.dronesim.math

class EulerIntegrator: Integrator() {

    override fun integrate(base: Vec3d, modifier: Vec3d, delta: Number): Vec3d {
        return base + modifier * delta.toDouble()
    }


}