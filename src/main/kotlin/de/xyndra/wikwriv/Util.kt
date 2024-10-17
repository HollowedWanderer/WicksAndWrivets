package de.xyndra.wikwriv

import net.minecraft.util.math.random.Random
import kotlin.math.abs

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

operator fun String.minus(other: Int): String {
    return if (other < 0) {
        this.substring(abs(other), this.length)
    } else {
        this.substring(0, this.length - other)
    }
}