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
    private val values = doubleArrayOf(
            r1c1, r2c1, r3c1, r4c1,
            r1c2, r2c2, r3c2, r4c2,
            r1c3, r2c3, r3c3, r4c3,
            r1c4, r2c4, r3c4, r4c4)

    fun col(index: Int): Vec4d {
        if (index < 0 || index > 3) {
            throw IllegalArgumentException("Bad index: $index")
        }

        return Vec4d(
                values[index * 4],
                values[index * 4 + 1],
                values[index * 4 + 2],
                values[index * 4 + 3]
        )
    }

    fun row(index: Int): Vec4d {
        if (index < 0 || index > 3) {
            throw IllegalArgumentException("Bad index: $index")
        }

        return Vec4d(
                values[index],
                values[4 + index],
                values[8 + index],
                values[12 + index])
    }

    fun value(rowIndex: Int, colIndex: Int): Double {
        if (rowIndex < 0 || rowIndex > 3) {
            throw IllegalArgumentException("Bad row index: $rowIndex")
        }

        if (colIndex < 0 || colIndex > 3) {
            throw IllegalArgumentException("Bad col index: $colIndex")
        }

        return values[colIndex * 4 + rowIndex]
    }

    operator fun times(m: Matrix4d): Matrix4d {
        return Matrix4d(
                (row(0) * m.col(0)).man(), (row(0) * m.col(1)).man(), (row(0) * m.col(2)).man(), (row(0) * m.col(3)).man(),
                (row(1) * m.col(0)).man(), (row(1) * m.col(1)).man(), (row(1) * m.col(2)).man(), (row(1) * m.col(3)).man(),
                (row(2) * m.col(0)).man(), (row(2) * m.col(1)).man(), (row(2) * m.col(2)).man(), (row(2) * m.col(3)).man(),
                (row(3) * m.col(0)).man(), (row(3) * m.col(1)).man(), (row(3) * m.col(2)).man(), (row(3) * m.col(3)).man()
        )
    }

    fun inv(): Matrix4d? {
        return IDENTITY
    }

    companion object {

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

        values.forEachIndexed { index, value ->
            if (other.values[index] != value) {
                return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(values)
    }

    override fun toString(): String {
        val matString = IntRange(0, 3).map { rowInd ->
            IntRange(0, 3).map { colInd -> "${values[colInd * 4 + rowInd]}" }.joinToString(", ")
        }.joinToString("\n")

        return "Matrix4d(values=\n$matString)"
    }


}