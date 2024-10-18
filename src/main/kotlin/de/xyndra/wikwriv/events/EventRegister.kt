package de.xyndra.wikwriv.events

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

fun registerEvents() {
    // Register events here
    ClientTickEvents.START_CLIENT_TICK.register(ClientTickEvents.StartTick(::onTick))
    ClientCommandRegistrationCallback.EVENT.register(::registerCommands)
}