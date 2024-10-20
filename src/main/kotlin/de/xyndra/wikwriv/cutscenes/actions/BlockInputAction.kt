package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class BlockInputAction() : Action() {
    override fun execute(side: Side) {
        if (side == Side.CLIENT) {
            CutSceneStore.blockInput()
        }
    }

    override fun cleanup(side: Side) {
    }
}