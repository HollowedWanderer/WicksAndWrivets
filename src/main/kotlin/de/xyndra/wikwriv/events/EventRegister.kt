package de.xyndra.wikwriv.events

import de.xyndra.wikwriv.Side
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents

fun registerEvents(side: Side) {
    // Register events here
    if (side == Side.SERVER) {
        ServerTickEvents.END_SERVER_TICK.register(::onTickServer)
        CommandRegistrationCallback.EVENT.register(::registerCommands)
    } else {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick(::onTickClient))
        ClientCommandRegistrationCallback.EVENT.register(::registerClientCommands)
    }
}