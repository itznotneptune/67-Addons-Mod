package addons67.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.Commands
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import java.util.function.Supplier

abstract class Command(
    val name: String,
    val aliases: List<String> = listOf(),
    val usage: String = ""
) {
    open val permission: Int = 0

    abstract fun onCommand(ctx: CommandContext<CommandSourceStack>, args: Array<String>): Int

    private fun makeBuilder(cmdName: String): LiteralArgumentBuilder<CommandSourceStack> {
        return Commands.literal(cmdName).executes { ctx ->
            if (!ctx.source.hasPermission(permission)) {
                ctx.source.sendSuccess(Supplier { Component.literal("You do not have permission to use this command") }, false)
                return@executes 0
            }
            val args = extractArgs(ctx)
            try {
                return@executes onCommand(ctx, args)
            } catch (t: Throwable) {
                ctx.source.sendSuccess(Supplier { Component.literal("Command error: ${t.message ?: t::class.simpleName}") }, false)
                return@executes 0
            }
        }
    }

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        // Register main name
        dispatcher.register(makeBuilder(name))

        // Register aliases as separate literals with same behavior
        for (alias in aliases) {
            dispatcher.register(makeBuilder(alias))
        }
    }

    private fun extractArgs(ctx: CommandContext<CommandSourceStack>): Array<String> {
        val input = ctx.input
        val idx = input.indexOf(' ')
        if (idx < 0) return emptyArray()
        val rest = input.substring(idx + 1).trim()
        if (rest.isEmpty()) return emptyArray()
        return rest.split("\\s+".toRegex()).toTypedArray()
    }

    protected fun reply(source: CommandSourceStack, msg: String) {
        source.sendSuccess(Supplier { Component.literal(msg) }, false)
    }
}