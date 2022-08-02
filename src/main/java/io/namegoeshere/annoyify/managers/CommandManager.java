package io.namegoeshere.annoyify.managers;

import io.namegoeshere.annoyify.Main;
import io.namegoeshere.annoyify.commands.AnnoyedCommand;
import io.namegoeshere.annoyify.commands.AnnoyifyCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;

public class CommandManager {

    /**
     * Initialises commands
     * @param main The instance of {@link Main}
     */
    public static void initialiseCommands(Main main) {
        PluginCommand annoyifyCommand = main.getCommand("annoyify");
        if (annoyifyCommand != null) {
            annoyifyCommand.setExecutor(new AnnoyifyCommand());
        } else {
            Bukkit.getConsoleSender().sendMessage("\"annoyify\" command failed to initialise");
        }

        PluginCommand annoyedCommand = main.getCommand("annoyed");
        if (annoyedCommand != null) {
            annoyedCommand.setExecutor(new AnnoyedCommand());
        } else {
            Bukkit.getConsoleSender().sendMessage("\"annoyed\" command failed to initialise");
        }
    }
}
