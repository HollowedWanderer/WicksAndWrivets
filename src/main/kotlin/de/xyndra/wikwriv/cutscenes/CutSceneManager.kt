package de.xyndra.wikwriv.cutscenes

import de.xyndra.wikwriv.cutscenes.actions.BlockInputAction
import de.xyndra.wikwriv.cutscenes.actions.ChatAction
import de.xyndra.wikwriv.cutscenes.actions.ConsoleAction
import de.xyndra.wikwriv.cutscenes.actions.UnblockInputAction
import de.xyndra.wikwriv.cutscenes.actions.WaitAction
import net.minecraft.text.Text

typealias CutScene = RootContext

object CutSceneManager {
    val cutsceneRegistry = mutableMapOf<String, CutScene>()

    init {
        register()
    }

    fun play(name: String): Boolean {
        val cutscene = cutsceneRegistry[name] ?: return false
        cutscene.play()
        return true
    }

    fun register() {
        cutsceneRegistry.put("test", CutScene {
            - ConsoleAction("Hello, world!").afterExecution {
                - ConsoleAction("This is a test callback!")
                - WaitAction(1000)
                - ChatAction(Text.of("This is a test chat message!"))
                println("This is also a test callback!")
            }
            - ConsoleAction("This is a test cutscene!")
        })
        cutsceneRegistry.put("block", CutScene {
            - BlockInputAction()
            - ChatAction(Text.of("Started Blocking!"))
            - WaitAction(1000)
            - ChatAction(Text.of("Blocked for 1 second!"))
            - WaitAction(1000)
            - ChatAction(Text.of("Blocked for 2 seconds!"))
            - WaitAction(1000)
            - ChatAction(Text.of("Stopped Blocking!"))
            - UnblockInputAction()
        })
    }
}