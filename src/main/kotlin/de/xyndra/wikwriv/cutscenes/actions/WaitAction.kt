package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action

class WaitAction(val millis: Long) : Action() {
    override fun execute(side: Side) {
        Thread.sleep(millis)
    }

    override fun cleanup(side: Side) {}
}