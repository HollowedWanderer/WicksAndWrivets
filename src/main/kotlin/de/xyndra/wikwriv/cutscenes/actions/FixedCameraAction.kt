package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneStore

class FixedCameraAction : Action() {
    override fun execute() {
        CutSceneStore.blockCamera()
    }

    override fun cleanup() {}
}