package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore
import net.minecraft.text.Text

class ServerChatAction(val message: Text) : Action() {
    override fun execute(side: Side) {
        if (side == Side.SERVER) {
            CutSceneStore.addServerTodo { server ->
                server.playerManager.broadcast(message, false)
            }
        }
    }

    override fun cleanup(side: Side) {}
}