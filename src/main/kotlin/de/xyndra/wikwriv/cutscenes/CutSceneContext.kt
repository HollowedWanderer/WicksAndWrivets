package de.xyndra.wikwriv.cutscenes

class RootContext(val callback: CutSceneContext.() -> Unit) {
    fun play(forceConcurrent: Boolean = false) {
        Thread {
            val context = CutSceneContext()
            callback(context)
            context.start()
            if (!forceConcurrent) {
                CutSceneStore.unblockInput()
            }
        }.start()
    }
}

class CutSceneContext {
    val actions = mutableListOf<Action>()
    var subContext: CutSceneContext? = null
    fun start() {
        actions.forEach {
            it.start(this)
            subContext?.start()
            subContext = null
        }
    }

    operator fun Action.unaryMinus() {
        actions.add(this)
    }
}

fun group(callback: CutSceneContext.() -> Unit): Action {
    val subContext = CutSceneContext()
    subContext.callback()
    return object : Action() {
        override fun execute() {
            subContext.start()
        }

        override fun cleanup() {
        }
    }
}