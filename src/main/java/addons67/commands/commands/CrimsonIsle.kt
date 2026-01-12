package addons67.commands.commands

import addons67.commands.Command
import net.minecraft.command.ICommandSender
//import addons67.commands.Command
//import addons67.utils.ChatUtils.sendChatMessage

object CrimsonIsle : Command(name = "nether") {
    override fun processCommand(sender: ICommandSender, args: Array<out String>) {
        sendChatMessage("/warp nether")
    }
}