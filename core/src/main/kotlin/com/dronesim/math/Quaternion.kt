package com.dronesim.math

class Quaternion(
        w: Number = 0.0,
        x: Number = 0.0,
        y: Number = 0.0,
        z: Number = 0.0) {

    val w = w.toDouble()
    val x = x.toDouble()
    val y = y.toDouble()
    val z = z.toDouble()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Quaternion

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
        return "Quaternion(w=$w, x=$x, y=$y, z=$z)"
    }


}