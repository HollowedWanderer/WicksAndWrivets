package de.xyndra.wikwriv.cutscenes

abstract class Action {
    abstract fun execute()
    fun start(context: CutSceneContext) {
        execute()
        cleanup()
        val subContext = CutSceneContext()
        subContext.callback()
        context.subContext = subContext
    }
    abstract fun cleanup()
    var callback: CutSceneContext.() -> Unit = {}
    fun afterExecution(callback: CutSceneContext.() -> Unit): Action {
        this.callback = callback
        return this
    }
}