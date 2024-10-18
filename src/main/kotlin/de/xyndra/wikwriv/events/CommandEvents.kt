package de.xyndra.wikwriv.events

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import de.xyndra.wikwriv.cutscenes.CutSceneManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.text.Text

fun registerCommands(dispatcher: CommandDispatcher<FabricClientCommandSource>, registryAccess: CommandRegistryAccess) {
    dispatcher.register(ClientCommandManager.literal("testCutscene").then(ClientCommandManager.argument("cutscene", StringArgumentType.string()).executes { context ->
        val cutscene = StringArgumentType.getString(context, "cutscene")
        return@executes if (CutSceneManager.play(cutscene)) 1 else {
            context.source.sendFeedback(Text.of("Cutscene $cutscene not found!"))
            -1
        }
    }))
}