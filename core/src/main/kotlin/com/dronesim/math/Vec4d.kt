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

class Vec4d(
        w: Number = 0.0,
        x: Number = 0.0,
        y: Number = 0.0,
        z: Number = 0.0) {

    val w = w.toDouble()
    val x = x.toDouble()
    val y = y.toDouble()
    val z = z.toDouble()

    constructor(v: DoubleArray) : this(v[0], v[1], v[2], v[3])

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec4d

        if (w != other.w) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = w.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }

    override fun toString(): String {
        return "Vec4d(w=$w, x=$x, y=$y, z=$z)"
    }

    operator fun times(v: Vec4d): Vec4d {
        return Vec4d(w * v.w, x * v.x, y * v.y, z * v.z)
    }

    /**
     * Calculates the manhattan distance of this vector.
     */
    fun man(): Double {
        return w + x + y + z
    }


}