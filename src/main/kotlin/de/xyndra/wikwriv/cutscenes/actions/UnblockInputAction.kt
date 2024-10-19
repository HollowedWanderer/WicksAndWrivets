package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class UnblockInputAction() : Action() {
    override fun execute(side: Side) {
        if (side == Side.CLIENT) {
            CutSceneStore.unblockInput()
        }
    }

    override fun cleanup(side: Side) {
    }
}