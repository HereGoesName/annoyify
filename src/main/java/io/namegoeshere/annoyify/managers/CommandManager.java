package io.namegoeshere.annoyify.managers;

import static org.bukkit.Bukkit.getLogger;

import io.namegoeshere.annoyify.Main;
import io.namegoeshere.annoyify.commands.AnnoyedCommand;
import io.namegoeshere.annoyify.commands.AnnoyifyCommand;
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
            getLogger().warning("\"annoyify\" command failed to initialise");
        }

        PluginCommand annoyedCommand = main.getCommand("annoyed");
        if (annoyedCommand != null) {
            annoyedCommand.setExecutor(new AnnoyedCommand());
        } else {
            getLogger().warning("\"annoyed\" command failed to initialise");
        }
    }
}
