package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class CloseWindowsAction() : Action() {
    override fun execute(side: Side) {
        if (side == Side.CLIENT) {
            CutSceneStore.addClientTodo { client ->
                client.player?.closeHandledScreen()
            }
        }
    }

    override fun cleanup(side: Side) {}
}