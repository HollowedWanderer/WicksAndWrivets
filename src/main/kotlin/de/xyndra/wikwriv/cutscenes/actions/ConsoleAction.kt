package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.cutscenes.Action
import de.xyndra.wikwriv.cutscenes.CutSceneContext
import net.hollowed.wikwriv.WicksWrivets

class ConsoleAction(val text: String) : Action {
    override fun execute() {
        WicksWrivets.LOGGER.info("Cutscene ConsoleAction: $text")
    }

    override fun cleanup() {}

    override var callback: CutSceneContext.() -> Unit = {}
}