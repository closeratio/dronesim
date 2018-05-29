package com.dronesim.math

class Matrix4d(
        r1c1: Number, r1c2: Number, r1c3: Number, r1c4: Number,
        r2c1: Number, r2c2: Number, r2c3: Number, r2c4: Number,
        r3c1: Number, r3c2: Number, r3c3: Number, r3c4: Number,
        r4c1: Number, r4c2: Number, r4c3: Number, r4c4: Number) {

    val row1 = Vec4d(r1c1, r1c2, r1c3, r1c4)
    val row2 = Vec4d(r2c1, r2c2, r2c3, r2c4)
    val row3 = Vec4d(r3c1, r3c2, r3c3, r3c4)
    val row4 = Vec4d(r4c1, r4c2, r4c3, r4c4)

    companion object {

        val IDENTITY = Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1)

    }

}