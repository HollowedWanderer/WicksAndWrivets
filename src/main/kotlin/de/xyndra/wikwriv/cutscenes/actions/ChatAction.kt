package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneContext
import de.xyndra.wikwriv.cutscenes.CutSceneStore
import net.minecraft.text.Text

class ChatAction(val message: Text) : Action {
    override fun execute() {
        CutSceneStore.addTodo { client ->
            client.player?.sendMessage(message)
        }
    }

    override fun cleanup() {}

    override var callback: CutSceneContext.() -> Unit = {}
}