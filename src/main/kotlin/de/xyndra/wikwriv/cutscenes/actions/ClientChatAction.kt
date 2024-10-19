package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore
import net.minecraft.text.Text

class ClientChatAction(val message: Text) : Action() {
    override fun execute(side: Side) {
        if (side == Side.CLIENT) {
            CutSceneStore.addClientTodo { client ->
                client.player?.sendMessage(message)
            }
        }
    }

    override fun cleanup(side: Side) {}
}