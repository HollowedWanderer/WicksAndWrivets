package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneContext

class WaitAction(val millis: Long) : Action {
    override fun execute() {
        Thread.sleep(millis)
    }

    override fun cleanup() {}

    override var callback: CutSceneContext.() -> Unit = {}
}