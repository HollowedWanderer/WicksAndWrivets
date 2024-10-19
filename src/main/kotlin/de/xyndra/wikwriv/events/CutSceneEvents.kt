package de.xyndra.wikwriv.events

import de.xyndra.wikwriv.cutscenes.CutSceneStore
import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer

fun onTickClient(client: MinecraftClient) {
    // Do something
    CutSceneStore.runClientTodos(client)
}

fun onTickServer(server: MinecraftServer) {
    // Do something
    CutSceneStore.runServerTodos(server)
}