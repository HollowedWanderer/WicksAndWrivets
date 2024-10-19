package de.xyndra.wikwriv.events

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import de.xyndra.wikwriv.Side
import de.xyndra.wikwriv.cutscenes.CutSceneManager
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.util.function.Supplier

fun registerCommands(dispatcher: CommandDispatcher<ServerCommandSource>, registryAccess: CommandRegistryAccess, environment: CommandManager.RegistrationEnvironment) {
    dispatcher.register(CommandManager.literal("testCutscene").then(CommandManager.argument("cutscene", StringArgumentType.string()).executes { context ->
        val cutscene = StringArgumentType.getString(context, "cutscene")
        return@executes if (CutSceneManager.play(cutscene, Side.SERVER)) 1 else {
            context.source.sendFeedback(Supplier {Text.literal("Cutscene $cutscene not found!")}, false)
            -1
        }
    }))
}
