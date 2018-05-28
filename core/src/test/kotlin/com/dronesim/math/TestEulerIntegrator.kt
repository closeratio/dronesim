package com.dronesim.math

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestEulerIntegrator {

    val integrator = EulerIntegrator()

    @Test
    fun testIntegrateZeroVec() {
        val result = integrator.integrate(Vec3d(1, 2, 3), Vec3d.ZERO, 1)
        assertThat(result).isEqualTo(Vec3d(1, 2, 3))
    }

    @Test
    fun testIntegrateNonZeroVec() {
        val result = integrator.integrate(Vec3d(4, 5, 6), Vec3d(1, -2, 4), 2)
        assertThat(result).isEqualTo(Vec3d(6, 1, 14))
    }

}