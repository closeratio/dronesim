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

class IterationParameters(
        val deltaTime: Double
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IterationParameters

        if (deltaTime != other.deltaTime) return false

        return true
    }

    override fun hashCode(): Int {
        return deltaTime.hashCode()
    }
}