package de.xyndra.wikwriv

import net.minecraft.util.math.random.Random

fun Double.randomPosNeg(random: Random): Double {
    return if (random.nextBoolean()) {
        this
    } else {
        -this
    }
}

fun Float.randomPosNeg(): Float {
    return if (kotlin.random.Random.nextBoolean()) {
        this
    } else {
        -this
    }
}