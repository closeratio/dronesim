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
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D.ZERO
import org.apache.commons.math3.linear.MatrixUtils

class Aircraft(
        mass: Number = 1,
        pos: Vector3D = ZERO,
        vel: Vector3D = ZERO,
        acc: Vector3D = ZERO,
        force: Vector3D = ZERO,
        ori: Rotation = Rotation.IDENTITY,
        angVel: Vector3D = ZERO,
        angAcc: Vector3D = ZERO,
        torque: Vector3D = ZERO
): PhysicalObject(
        mass.toDouble(),
        pos,
        vel,
        acc,
        force,
        MatrixUtils.createRealIdentityMatrix(3),
        ori,
        angVel,
        angAcc,
        torque,
        false) {

}