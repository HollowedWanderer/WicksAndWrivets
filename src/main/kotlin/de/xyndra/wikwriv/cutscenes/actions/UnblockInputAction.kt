package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class UnblockInputAction() : Action() {
    override fun execute() {
        CutSceneStore.unblockInput()
    }

    override fun cleanup() {
    }
}