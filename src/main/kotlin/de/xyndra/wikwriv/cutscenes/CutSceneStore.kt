package de.xyndra.wikwriv.cutscenes

import net.minecraft.client.MinecraftClient

object CutSceneStore {
    private val todoLock = Object()
    private val todos = mutableListOf<(MinecraftClient) -> Unit>()
    private val inputBlockerLock = Object()
    private var inputBlocker = false
    private var cameraBlocker = false

    fun runTodos(client: MinecraftClient) = synchronized(todoLock) {
        todos.forEach { it(client) }
        todos.clear()
    }

    fun addTodo(todo: (MinecraftClient) -> Unit) = synchronized(todoLock) {
        todos.add(todo)
    }

    fun blockInput() = synchronized(inputBlockerLock) {
        inputBlocker = true
    }

    fun blockCamera() = synchronized(inputBlockerLock) {
        cameraBlocker = true
    }

    fun unblockInput() = synchronized(inputBlockerLock) {
        inputBlocker = false
        cameraBlocker = false
    }

    fun isInputBlocked(): Boolean = synchronized(inputBlockerLock) {
        inputBlocker
    }

    fun isCameraBlocked(): Boolean = synchronized(inputBlockerLock) {
        cameraBlocker
    }
}