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

import java.util.*


/**
 * A 4x4 matrix of doubles. Values are stores in column-major order
 */
class Matrix4d(
        r1c1: Number, r1c2: Number, r1c3: Number, r1c4: Number,
        r2c1: Number, r2c2: Number, r2c3: Number, r2c4: Number,
        r3c1: Number, r3c2: Number, r3c3: Number, r3c4: Number,
        r4c1: Number, r4c2: Number, r4c3: Number, r4c4: Number) {

    // The actual matrix values. These are never modified once initialised.
    private val vals = doubleArrayOf(
            r1c1, r2c1, r3c1, r4c1,
            r1c2, r2c2, r3c2, r4c2,
            r1c3, r2c3, r3c3, r4c3,
            r1c4, r2c4, r3c4, r4c4)

    constructor(vals: List<Number>): this(
            vals[0], vals[1], vals[2], vals[3],
            vals[4], vals[5], vals[6], vals[7],
            vals[8], vals[9], vals[10], vals[11],
            vals[12], vals[13], vals[14], vals[15])

    fun col(index: Int): Vec4d {
        if (index < 0 || index > 3) {
            throw IllegalArgumentException("Bad index: $index")
        }

        return Vec4d(
                vals[index * 4],
                vals[index * 4 + 1],
                vals[index * 4 + 2],
                vals[index * 4 + 3]
        )
    }

    fun row(index: Int): Vec4d {
        if (index < 0 || index > 3) {
            throw IllegalArgumentException("Bad index: $index")
        }

        return Vec4d(
                vals[index],
                vals[4 + index],
                vals[8 + index],
                vals[12 + index])
    }

    fun value(rowIndex: Int, colIndex: Int): Double {
        if (rowIndex < 0 || rowIndex > 3) {
            throw IllegalArgumentException("Bad row index: $rowIndex")
        }

        if (colIndex < 0 || colIndex > 3) {
            throw IllegalArgumentException("Bad col index: $colIndex")
        }

        return vals[colIndex * 4 + rowIndex]
    }

    operator fun times(m: Matrix4d): Matrix4d {
        return Matrix4d(
                (row(0) * m.col(0)).man(), (row(0) * m.col(1)).man(), (row(0) * m.col(2)).man(), (row(0) * m.col(3)).man(),
                (row(1) * m.col(0)).man(), (row(1) * m.col(1)).man(), (row(1) * m.col(2)).man(), (row(1) * m.col(3)).man(),
                (row(2) * m.col(0)).man(), (row(2) * m.col(1)).man(), (row(2) * m.col(2)).man(), (row(2) * m.col(3)).man(),
                (row(3) * m.col(0)).man(), (row(3) * m.col(1)).man(), (row(3) * m.col(2)).man(), (row(3) * m.col(3)).man()
        )
    }

    private fun determinant(): Double {
        return vals[M30] * vals[M21] * vals[M12] * vals[M03] -
                vals[M20] * vals[M31] * vals[M12] * vals[M03] -
                vals[M30] * vals[M11] * vals[M22] * vals[M03] +
                vals[M10] * vals[M31] * vals[M22] * vals[M03] +
                vals[M20] * vals[M11] * vals[M32] * vals[M03] -
                vals[M10] * vals[M21] * vals[M32] * vals[M03] -
                vals[M30] * vals[M21] * vals[M02] * vals[M13] +
                vals[M20] * vals[M31] * vals[M02] * vals[M13] +
                vals[M30] * vals[M01] * vals[M22] * vals[M13] -
                vals[M00] * vals[M31] * vals[M22] * vals[M13] -
                vals[M20] * vals[M01] * vals[M32] * vals[M13] +
                vals[M00] * vals[M21] * vals[M32] * vals[M13] +
                vals[M30] * vals[M11] * vals[M02] * vals[M23] -
                vals[M10] * vals[M31] * vals[M02] * vals[M23] -
                vals[M30] * vals[M01] * vals[M12] * vals[M23] +
                vals[M00] * vals[M31] * vals[M12] * vals[M23] +
                vals[M10] * vals[M01] * vals[M32] * vals[M23] -
                vals[M00] * vals[M11] * vals[M32] * vals[M23] -
                vals[M20] * vals[M11] * vals[M02] * vals[M33] +
                vals[M10] * vals[M21] * vals[M02] * vals[M33] +
                vals[M20] * vals[M01] * vals[M12] * vals[M33] -
                vals[M00] * vals[M21] * vals[M12] * vals[M33] -
                vals[M10] * vals[M01] * vals[M22] * vals[M33] +
                vals[M00] * vals[M11] * vals[M22] * vals[M33]
    }

    fun inv(): Matrix4d? {
        val det = determinant()

        if (det == 0.0) return null

        val detInv = 1.0 / det

        val m00 = vals[M12] * vals[M23] * vals[M31] -
                vals[M13] * vals[M22] * vals[M31] +
                vals[M13] * vals[M21] * vals[M32] -
                vals[M11] * vals[M23] * vals[M32] -
                vals[M12] * vals[M21] * vals[M33] +
                vals[M11] * vals[M22] * vals[M33]
        val m01 = vals[M03] * vals[M22] * vals[M31] -
                vals[M02] * vals[M23] * vals[M31] -
                vals[M03] * vals[M21] * vals[M32] +
                vals[M01] * vals[M23] * vals[M32] +
                vals[M02] * vals[M21] * vals[M33] -
                vals[M01] * vals[M22] * vals[M33]
        val m02 = vals[M02] * vals[M13] * vals[M31] -
                vals[M03] * vals[M12] * vals[M31] +
                vals[M03] * vals[M11] * vals[M32] -
                vals[M01] * vals[M13] * vals[M32] -
                vals[M02] * vals[M11] * vals[M33] +
                vals[M01] * vals[M12] * vals[M33]
        val m03 = vals[M03] * vals[M12] * vals[M21] -
                vals[M02] * vals[M13] * vals[M21] -
                vals[M03] * vals[M11] * vals[M22] +
                vals[M01] * vals[M13] * vals[M22] +
                vals[M02] * vals[M11] * vals[M23] -
                vals[M01] * vals[M12] * vals[M23]
        val m10 = vals[M13] * vals[M22] * vals[M30] -
                vals[M12] * vals[M23] * vals[M30] -
                vals[M13] * vals[M20] * vals[M32] +
                vals[M10] * vals[M23] * vals[M32] +
                vals[M12] * vals[M20] * vals[M33] -
                vals[M10] * vals[M22] * vals[M33]
        val m11 = vals[M02] * vals[M23] * vals[M30] -
                vals[M03] * vals[M22] * vals[M30] +
                vals[M03] * vals[M20] * vals[M32] -
                vals[M00] * vals[M23] * vals[M32] -
                vals[M02] * vals[M20] * vals[M33] +
                vals[M00] * vals[M22] * vals[M33]
        val m12 = vals[M03] * vals[M12] * vals[M30] -
                vals[M02] * vals[M13] * vals[M30] -
                vals[M03] * vals[M10] * vals[M32] +
                vals[M00] * vals[M13] * vals[M32] +
                vals[M02] * vals[M10] * vals[M33] -
                vals[M00] * vals[M12] * vals[M33]
        val m13 = vals[M02] * vals[M13] * vals[M20] -
                vals[M03] * vals[M12] * vals[M20] +
                vals[M03] * vals[M10] * vals[M22] -
                vals[M00] * vals[M13] * vals[M22] -
                vals[M02] * vals[M10] * vals[M23] +
                vals[M00] * vals[M12] * vals[M23]
        val m20 = vals[M11] * vals[M23] * vals[M30] -
                vals[M13] * vals[M21] * vals[M30] +
                vals[M13] * vals[M20] * vals[M31] -
                vals[M10] * vals[M23] * vals[M31] -
                vals[M11] * vals[M20] * vals[M33] +
                vals[M10] * vals[M21] * vals[M33]
        val m21 = vals[M03] * vals[M21] * vals[M30] -
                vals[M01] * vals[M23] * vals[M30] -
                vals[M03] * vals[M20] * vals[M31] +
                vals[M00] * vals[M23] * vals[M31] +
                vals[M01] * vals[M20] * vals[M33] -
                vals[M00] * vals[M21] * vals[M33]
        val m22 = vals[M01] * vals[M13] * vals[M30] -
                vals[M03] * vals[M11] * vals[M30] +
                vals[M03] * vals[M10] * vals[M31] -
                vals[M00] * vals[M13] * vals[M31] -
                vals[M01] * vals[M10] * vals[M33] +
                vals[M00] * vals[M11] * vals[M33]
        val m23 = vals[M03] * vals[M11] * vals[M20] -
                vals[M01] * vals[M13] * vals[M20] -
                vals[M03] * vals[M10] * vals[M21] +
                vals[M00] * vals[M13] * vals[M21] +
                vals[M01] * vals[M10] * vals[M23] -
                vals[M00] * vals[M11] * vals[M23]
        val m30 = vals[M12] * vals[M21] * vals[M30] -
                vals[M11] * vals[M22] * vals[M30] -
                vals[M12] * vals[M20] * vals[M31] +
                vals[M10] * vals[M22] * vals[M31] +
                vals[M11] * vals[M20] * vals[M32] -
                vals[M10] * vals[M21] * vals[M32]
        val m31 = vals[M01] * vals[M22] * vals[M30] -
                vals[M02] * vals[M21] * vals[M30] +
                vals[M02] * vals[M20] * vals[M31] -
                vals[M00] * vals[M22] * vals[M31] -
                vals[M01] * vals[M20] * vals[M32] +
                vals[M00] * vals[M21] * vals[M32]
        val m32 = vals[M02] * vals[M11] * vals[M30] -
                vals[M01] * vals[M12] * vals[M30] -
                vals[M02] * vals[M10] * vals[M31] +
                vals[M00] * vals[M12] * vals[M31] +
                vals[M01] * vals[M10] * vals[M32] -
                vals[M00] * vals[M11] * vals[M32]
        val m33 = vals[M01] * vals[M12] * vals[M20] -
                vals[M02] * vals[M11] * vals[M20] +
                vals[M02] * vals[M10] * vals[M21] -
                vals[M00] * vals[M12] * vals[M21] -
                vals[M01] * vals[M10] * vals[M22] +
                vals[M00] * vals[M11] * vals[M22]

        return Matrix4d(arrayOf(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33)
                .map { it * detInv })
    }

    companion object {

        // Index constants
        private const val M00 = 0
        private const val M01 = 4
        private const val M02 = 8
        private const val M03 = 12

        private const val M10 = 1
        private const val M11 = 5
        private const val M12 = 9
        private const val M13 = 13

        private const val M20 = 2
        private const val M21 = 6
        private const val M22 = 10
        private const val M23 = 14

        private const val M30 = 3
        private const val M31 = 7
        private const val M32 = 11
        private const val M33 = 15

        val IDENTITY = Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1)

        val ASCENDING = Matrix4d(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16)

    }

    private fun doubleArrayOf(vararg elements: Number): DoubleArray {
        return elements.map { it.toDouble() }.toDoubleArray()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix4d

        vals.forEachIndexed { index, value ->
            if (other.vals[index] != value) {
                return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(vals)
    }

    override fun toString(): String {
        val matString = IntRange(0, 3).map { rowInd ->
            IntRange(0, 3).map { colInd -> "${vals[colInd * 4 + rowInd]}" }.joinToString(", ")
        }.joinToString("\n")

        return "Matrix4d(values=\n$matString)"
    }


}