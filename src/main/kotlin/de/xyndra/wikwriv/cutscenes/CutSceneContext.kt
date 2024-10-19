package de.xyndra.wikwriv.cutscenes

import de.xyndra.wikwriv.Side

class RootContext(val callback: CutSceneContext.() -> Unit) {
    fun play(side: Side, forceConcurrent: Boolean = false) {
        Thread {
            val context = CutSceneContext()
            callback(context)
            context.start(side)
            if (!forceConcurrent && side == Side.CLIENT) {
                CutSceneStore.unblockInput()
            }
        }.start()
    }
}

class CutSceneContext {
    val actions = mutableListOf<Action>()
    var subContext: CutSceneContext? = null
    fun start(side: Side) {
        actions.forEach {
            it.start(this, side)
            subContext?.start(side)
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
        override fun execute(side: Side) {
            subContext.start(side)
        }

        override fun cleanup(side: Side) {
        }
    }
}