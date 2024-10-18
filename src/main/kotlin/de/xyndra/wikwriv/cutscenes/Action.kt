package de.xyndra.wikwriv.cutscenes

interface Action {
    fun execute()
    fun start(context: CutSceneContext) {
        execute()
        cleanup()
        val subContext = CutSceneContext()
        subContext.callback()
        context.subContext = subContext
    }
    fun cleanup()
    var callback: CutSceneContext.() -> Unit
    fun afterExecution(callback: CutSceneContext.() -> Unit): Action {
        this.callback = callback
        return this
    }
}