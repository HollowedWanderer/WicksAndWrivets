package de.xyndra.wikwriv.cutscenes.actions

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.Action
import net.hollowed.wikwriv.WicksWrivets

class ConsoleAction(val text: String) : Action() {
    override fun execute(side: Side) {
        WicksWrivets.LOGGER.info("Cutscene ConsoleAction: $text")
    }

    override fun cleanup(side: Side) {}
}