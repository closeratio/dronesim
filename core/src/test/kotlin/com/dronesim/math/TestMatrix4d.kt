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
import org.junit.jupiter.api.Test

class TestMatrix4d {

    @Test
    fun testRow() {
        val mat = Matrix4d.ASCENDING

        val row = mat.row(1)

        assertThat(row).isEqualTo(Vec4d(5, 6, 7, 8))
    }

    @Test
    fun testCol() {
        val mat = Matrix4d.ASCENDING

        val row = mat.col(2)

        assertThat(row).isEqualTo(Vec4d(3, 7, 11, 15))
    }

    @Test
    fun testMultiplyIdentity() {
        val mat1 = Matrix4d(
                1, 2, 3, 4,
                1, 2, 3, 4,
                1, 2, 3, 4,
                1, 2, 3, 4)
        val mat2 = Matrix4d.IDENTITY

        val result = mat1 * mat2

        assertThat(result).isEqualTo(mat1)
    }

    @Test
    fun testMultiply() {
        val mat1 = Matrix4d(
                1, 2, 3, 4,
                1, 2, 3, 4,
                1, 2, 3, 4,
                1, 2, 3, 4)
        val mat2 = Matrix4d(
                1, 2, 3, 4,
                5, 6, 7, 8,
                -1, 2, -3, 4,
                1, -2, 3, -4)

        val result = mat1 * mat2

        assertThat(result).isEqualTo(Matrix4d(
                12, 12, 20, 16,
                12, 12, 20, 16,
                12, 12, 20, 16,
                12, 12, 20, 16))
    }

    @Test
    fun testValue() {
        val mat = Matrix4d(
                1, -2, 3, -4,
                5, -6, 7, -8,
                9, -10, 11, -12,
                13, -14, 15, -16)

        assertThat(mat.value(1, 2)).isEqualTo(7.0)
        assertThat(mat.value(3, 0)).isEqualTo(13.0)
        assertThat(mat.value(0, 0)).isEqualTo(1.0)
        assertThat(mat.value(3, 3)).isEqualTo(-16.0)
    }

    @Test
    fun testInverse() {
        val mat = Matrix4d(
                1, 3, 0, 0,
                0, 0, 0, 2,
                0, 0, 5, 0,
                1, 0, 0, 6)

        val inv = mat.inv()!!

        assertThat(inv * mat).isEqualTo(Matrix4d.IDENTITY)
    }

}