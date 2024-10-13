package de.xyndra.wikwriv.common

import net.minecraft.util.math.random.Random

fun Double.randomPosNeg(random: Random): Double {
    return if (random.nextBoolean()) {
        this
    } else {
        -this
    }
}