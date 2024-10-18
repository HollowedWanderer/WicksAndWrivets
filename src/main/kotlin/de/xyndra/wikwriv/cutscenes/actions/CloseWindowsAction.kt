package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class CloseWindowsAction() : Action() {
    override fun execute() {
        CutSceneStore.addTodo { client ->
            client.player?.closeHandledScreen()
        }
    }

    override fun cleanup() {}
}