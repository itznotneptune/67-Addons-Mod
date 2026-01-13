package addons67.commands

import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandSourceStack

object CommandManager {
    // Instantiate your command objects here (use your concrete Command subclasses)
    val commands: List<Command> = listOf(
        // Example: HelloCommand(),
        // Replace with your own command instances: Addons67Commands, DungeonHub(), etc.
    )

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        commands.forEach { it.register(dispatcher) }
    }
}