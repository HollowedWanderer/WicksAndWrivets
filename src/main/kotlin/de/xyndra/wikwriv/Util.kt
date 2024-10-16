package de.xyndra.wikwriv

import net.minecraft.util.math.random.Random

fun Double.randomPosNeg(random: Random): Double {
    return if (random.nextBoolean()) {
        this
    } else {
        -this
    }
}

operator fun String.minus(other: String): String {
    return this.replace(other, "")
}