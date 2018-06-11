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

package com.dronesim.sim

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

operator fun Vector3D.plus(v: Vector3D): Vector3D {
    return this.add(v)
}

operator fun Vector3D.times(c: Double): Vector3D {
    return this.scalarMultiply(c)
}

operator fun Vector3D.times(v: Vector3D): Vector3D {
    return Vector3D(x * v.x, y * v.y, z * v.z)
}

operator fun Vector3D.div(c: Double): Vector3D {
    return this.scalarMultiply(1.0 / c)
}

operator fun Vector3D.div(v: Vector3D): Vector3D {
    return Vector3D(x / v.x, y / v.y, z / v.z)
}