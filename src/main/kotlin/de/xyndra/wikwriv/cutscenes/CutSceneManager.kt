package de.xyndra.wikwriv.cutscenes

import de.xyndra.wikwriv.cutscenes.actions.ChatAction
import de.xyndra.wikwriv.cutscenes.actions.ConsoleAction
import de.xyndra.wikwriv.cutscenes.actions.WaitAction
import net.minecraft.text.Text

data class CutScene(val context: RootContext)

object CutSceneManager {
    val cutsceneRegistry = mutableMapOf<String, CutScene>()

    init {
        register()
    }

    fun play(name: String): Boolean {
        val cutscene = cutsceneRegistry[name] ?: return false
        cutscene.context.play()
        return true
    }

    fun register() {
        cutsceneRegistry.put("test", CutScene(RootContext {
            - ConsoleAction("Hello, world!").afterExecution {
                - ConsoleAction("This is a test callback!")
                - WaitAction(1000)
                - ChatAction(Text.of("This is a test chat message!"))
                println("This is also a test callback!")
            }
            - ConsoleAction("This is a test cutscene!")
        }))
    }
}