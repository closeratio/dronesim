package com.dronesim.sim

import com.dronesim.InitialisationParameters

object WorldFactory {

    fun create(params: InitialisationParameters): World {
        return World(listOf())
    }

}
