/*
 * This file is part of DroneSim.
 *
 * DroneSim is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DroneSim is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DroneSim.  If not, see http://www.gnu.org/licenses.
 */

package com.dronesim.math

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.DoubleComparator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Math.*

class TestVec3d {

    @Test
    fun testLength() {
        assertThat(Vec3d(1, 0, 0).length()).isEqualTo(1.0)
        assertThat(Vec3d(0, 1, 0).length()).isEqualTo(1.0)
        assertThat(Vec3d(0, 0, 1).length()).isEqualTo(1.0)

        assertThat(Vec3d(1, 1, 0).length()).isEqualTo(pow(2.0, 0.5))
        assertThat(Vec3d(1, 1, 1).length()).isEqualTo(pow(3.0, 0.5))
        assertThat(Vec3d(1, -1, -1).length()).isEqualTo(pow(3.0, 0.5))

        assertThat(Vec3d(2, 3, -4).length()).isEqualTo(pow(
                pow(2.0, 2.0) + pow(3.0, 2.0) + pow(-4.0, 2.0),
                0.5))
    }

    @Test
    fun testTimes() {
        assertThat(Vec3d(1, 0, 0) * 2).isEqualTo(Vec3d(2, 0, 0))
        assertThat(Vec3d(0, -2, 0) * 3).isEqualTo(Vec3d(0, -6, 0))
        assertThat(Vec3d(0, 0, 4) * -3).isEqualTo(Vec3d(0, 0, -12))
    }

    @Test
    fun testDivide() {
        assertThat(Vec3d(1, 0, 0) / 10).isEqualTo(Vec3d(0.1, 0, 0))
        assertThat(Vec3d(0, -6, 0) / 3).isEqualTo(Vec3d(0, -2, 0))
        assertThat(Vec3d(0, 0, 16) / -8).isEqualTo(Vec3d(0, 0, -2))
    }

    @Test
    fun testNormalize() {
        assertThat(Vec3d(1, 0, 0).normalized()).isEqualTo(Vec3d(1, 0, 0))
        assertThat(Vec3d(-1, 0, 0).normalized()).isEqualTo(Vec3d(-1, 0, 0))
        assertThat(Vec3d(2, 0, 0).normalized()).isEqualTo(Vec3d(1, 0, 0))
        assertThat(Vec3d(-2, 0, 0).normalized()).isEqualTo(Vec3d(-1, 0, 0))

        val testVec = Vec3d(1, 2, 3)
        val len = pow(pow(testVec.x, 2.0) + pow(testVec.y, 2.0) + pow(testVec.z, 2.0), 0.5)
        val expected = testVec / len

        assertThat(testVec.normalized()).isEqualTo(expected)
    }

    @Test
    fun testDotProduct() {
        assertThat(Vec3d(1, 0, 0).dot(Vec3d(1, 0, 0))).isEqualTo(1.0)
        assertThat(Vec3d(1, 0, 0).dot(Vec3d(0, 1, 0))).isEqualTo(0.0)
        assertThat(Vec3d(1, 0, 0).dot(Vec3d(0, -1, 0))).isEqualTo(0.0)

        assertThat(Vec3d(1, 0, 0).dot(Vec3d(-1, 0, 0))).isEqualTo(-1.0)

        assertThat(Vec3d(1, 0, 0).dot(Vec3d(1, 1, 0)))
                .usingComparator(DoubleComparator(0.000001))
                .isEqualTo(cos(PI / 4))
    }

    @Test
    fun testCrossProduct() {
        assertThat(Vec3d(1, 0, 0).cross(Vec3d(0, 1, 0))).isEqualTo(Vec3d(0, 0, 1))
        assertThat(Vec3d(1, 0, 0).cross(Vec3d(0, -1, 0))).isEqualTo(Vec3d(0, 0, -1))

        assertThat(Vec3d(2, 0, 0).cross(Vec3d(0, 0, 3))).isEqualTo(Vec3d(0, -6, 0))

        assertThrows<IllegalArgumentException>({
            Vec3d(1, 0, 0).cross(Vec3d(1, 0, 0))
        })
    }

}