package com.dronesim.math

class Vec3d(
        x: Number = 0.0,
        y: Number = 0.0,
        z: Number = 0.0) {

    val x = x.toDouble()
    val y = y.toDouble()
    val z = z.toDouble()

    fun length2(): Double {
        return Math.pow(x, 2.0) + Math.pow(y, 2.0) + Math.pow(z, 2.0)
    }

    fun length(): Double {
        return Math.sqrt(length2())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec3d

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }

    override fun toString(): String {
        return "Vec3d(x=$x, y=$y, z=$z)"
    }

    operator fun plus(vec: Vec3d): Vec3d {
        return Vec3d(x + vec.x, y + vec.y, z + vec.z)
    }

    operator fun plus(doub: Double): Vec3d {
        return plus(Vec3d(doub, doub, doub))
    }

    operator fun times(vec: Vec3d): Vec3d {
        return Vec3d(x * vec.x, y * vec.y, z * vec.z)
    }

    operator fun times(doub: Double): Vec3d {
        return times(Vec3d(doub, doub, doub))
    }

    companion object {
        val ZERO = Vec3d(0.0, 0.0, 0.0)
        val X = Vec3d(1.0, 0.0, 0.0)
        val Y = Vec3d(0.0, 1.0, 0.0)
        val z = Vec3d(0.0, 0.0, 1.0)
    }


}