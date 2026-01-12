package addons67.commands

import net.minecraftforge.client.ClientCommandHandler
import addons67.commands.commands.*
import addons67.mixins.accessor.AccessorCommandHandler

object CommandManager {
    val commands = setOf(
        Addons67Commands,
        CrimsonIsle, DungeonHub,
        End, Skyblock, hub,
        DungeonWaypointCommand,
    )

    fun registerCommands() {
        commands.forEach(::registerCommand)
        //  if (config.pvCommand) registerCommand(ProfileViewerCommand)
    }

    fun registerCommand(command: Command) = ClientCommandHandler.instance.registerCommand(command)

    fun unregisterCommand(command: Command) {
        val handler = ClientCommandHandler.instance as? AccessorCommandHandler ?: return
        val commandMap = handler.commandMap
        val key = commandMap.entries.find { it.value == command }?.key ?: return
        commandMap.remove(key)
    }
}