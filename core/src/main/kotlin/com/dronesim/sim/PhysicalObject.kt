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

import org.apache.commons.math3.geometry.euclidean.threed.Rotation
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D
import org.apache.commons.math3.linear.RealMatrix

abstract class PhysicalObject(
        var mass: Double,
        var pos: Vector3D,
        var vel: Vector3D,
        var acc: Vector3D,
        var force: Vector3D,
        var inertiaTensor: RealMatrix,
        var ori: Rotation,
        var angVel: Vector3D,
        var angAcc: Vector3D,
        var torque: Vector3D,
        var fixed: Boolean): Entity() {

    override fun iterate(params: IterationParameters) {
        if (fixed) return

        acc = force / mass
        vel += acc * params.deltaTime
        pos += vel * params.deltaTime

        // TODO: Rotation
    }
}


