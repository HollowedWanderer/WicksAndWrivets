package de.xyndra.wikwriv.events

import de.xyndra.wikwriv.cutscenes.CutSceneStore
import net.minecraft.client.MinecraftClient

fun onTick(client: MinecraftClient) {
    // Do something
    CutSceneStore.runTodos(client)
}