package com.dronesim

import com.dronesim.sim.WorldFactory

class DroneSim(val params: InitialisationParameters) {

    val world = WorldFactory.create(params)

    fun step(delta: Double) {

    }

}