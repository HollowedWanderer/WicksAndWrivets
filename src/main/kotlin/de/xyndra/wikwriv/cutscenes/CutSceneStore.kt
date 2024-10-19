package de.xyndra.wikwriv.cutscenes

import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer

object CutSceneStore {
    private val todoLock = Object()
    private val clientTodos = mutableListOf<(MinecraftClient) -> Unit>()
    private val serverTodos = mutableListOf<(MinecraftServer) -> Unit>()
    private val inputBlockerLock = Object()
    private var inputBlocker = false
    private var cameraBlocker = false

    fun runClientTodos(client: MinecraftClient) = synchronized(todoLock) {
        clientTodos.forEach { it(client) }
        clientTodos.clear()
    }

    fun addClientTodo(todo: (MinecraftClient) -> Unit) = synchronized(todoLock) {
        clientTodos.add(todo)
    }

    fun runServerTodos(server: MinecraftServer) = synchronized(todoLock) {
        serverTodos.forEach { it(server) }
        serverTodos.clear()
    }

    fun addServerTodo(todo: (MinecraftServer) -> Unit) = synchronized(todoLock) {
        serverTodos.add(todo)
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