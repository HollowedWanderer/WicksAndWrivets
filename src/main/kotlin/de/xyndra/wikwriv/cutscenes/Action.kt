package de.xyndra.wikwriv.cutscenes

import de.xyndra.wikwriv.Side

abstract class Action {
    abstract fun execute(side: Side)
    fun start(context: CutSceneContext, side: Side) {
        execute(side)
        cleanup(side)
        val subContext = CutSceneContext()
        subContext.callback()
        context.subContext = subContext
    }
    abstract fun cleanup(side: Side)
    var callback: CutSceneContext.() -> Unit = {}
    fun afterExecution(callback: CutSceneContext.() -> Unit): Action {
        this.callback = callback
        return this
    }
}