package de.xyndra.wikwriv.cutscenes

import net.minecraft.client.MinecraftClient

object CutSceneStore {
    val lock = Object()
    val todos = mutableListOf<(MinecraftClient) -> Unit>()

    fun runTodos(client: MinecraftClient) = synchronized(lock) {
        todos.forEach { it(client) }
        todos.clear()
    }

    fun addTodo(todo: (MinecraftClient) -> Unit) = synchronized(lock) {
        todos.add(todo)
    }
}