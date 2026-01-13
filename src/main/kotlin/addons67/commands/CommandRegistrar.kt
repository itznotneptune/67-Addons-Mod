package addons67.commands

import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.eventbus.api.EventPriority

object CommandRegistrar {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    fun onRegisterCommands(e: RegisterCommandsEvent) {
        CommandManager.register(e.dispatcher)
    }
}