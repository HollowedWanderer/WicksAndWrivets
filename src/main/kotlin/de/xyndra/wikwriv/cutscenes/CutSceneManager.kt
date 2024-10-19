package de.xyndra.wikwriv.cutscenes

import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.actions.BlockInputAction
import de.xyndra.wikwriv.cutscenes.actions.ClientChatAction
import de.xyndra.wikwriv.cutscenes.actions.CloseWindowsAction
import de.xyndra.wikwriv.cutscenes.actions.ConsoleAction
import de.xyndra.wikwriv.cutscenes.actions.FixedCameraAction
import de.xyndra.wikwriv.cutscenes.actions.ServerChatAction
import de.xyndra.wikwriv.cutscenes.actions.UnblockInputAction
import de.xyndra.wikwriv.cutscenes.actions.WaitAction
import net.minecraft.text.Text

typealias CutScene = RootContext

object CutSceneManager {
    val cutsceneRegistry = mutableMapOf<String, CutScene>()

    init {
        register()
    }

    fun play(name: String, side: Side): Boolean {
        val cutscene = cutsceneRegistry[name] ?: return false
        cutscene.play(side)
        return true
    }

    fun register() {
        cutsceneRegistry.put("test", CutScene {
            - ConsoleAction("Hello, world!").afterExecution {
                - ConsoleAction("This is a test callback!")
                - WaitAction(1000)
                - ClientChatAction(Text.of("This is a test chat message!"))
                println("This is also a test callback!")
            }
            - ConsoleAction("This is a test cutscene!")
        })
        cutsceneRegistry.put("block", CutScene {
            - BlockInputAction()
            - ClientChatAction(Text.of("Started Blocking!"))
            - WaitAction(1000)
            - ClientChatAction(Text.of("Blocked for 1 second!"))
            - WaitAction(1000)
            - ClientChatAction(Text.of("Blocked for 2 seconds!"))
            - WaitAction(1000)
            - ClientChatAction(Text.of("Stopped Blocking!"))
            - UnblockInputAction()
        })
        cutsceneRegistry.put("losingControl", CutScene {
            - ClientChatAction(Text.of("You are about to lose control!"))
            - WaitAction(3000)
            - CloseWindowsAction()
            - ClientChatAction(Text.of("You have lost your window!"))
            - WaitAction(3000)
            - BlockInputAction()
            - ClientChatAction(Text.of("You have lost your input!"))
            - WaitAction(3000)
            - FixedCameraAction()
            - ClientChatAction(Text.of("You have lost control over camera!"))
            - WaitAction(3000)
            - UnblockInputAction()
            - ClientChatAction(Text.of("You have regained control!"))
        })
        cutsceneRegistry.put("serverChat", CutScene {
            - ServerChatAction(Text.of("This is a server chat message!"))
            - WaitAction(10000)
            - ServerChatAction(Text.of("This is another server chat message!"))
        })
    }
}