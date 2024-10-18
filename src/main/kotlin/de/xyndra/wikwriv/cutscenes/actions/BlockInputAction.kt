package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class BlockInputAction() : Action() {
    override fun execute() {
        CutSceneStore.blockInput()
    }

    override fun cleanup() {
    }
}