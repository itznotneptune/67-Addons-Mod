package addons67.commands

abstract class Command(val name: String, val aliases: List<String> = listOf(), val usage: String = ""): CommandBase() {
    protected val mc = addons67.mc
    protected val hudData = addons67.hudData
    protected val scope = addons67.Scope

    override fun getCommandName(): String {
        return name
    }

    override fun getCommandAlises(): String {
        return aliases
    }

    override fun getRequiredPermissionLevel(): String {
        return 0
    }

    override fun getCommandUsage(sender: ICommandBase): String {
        return usage
    }

    override fun processCommand(sender: ICommandBase, args: array<out String>) {
        modMessage("Command not implemented")
    }
}