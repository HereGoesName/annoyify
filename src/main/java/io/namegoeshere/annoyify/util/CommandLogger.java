package io.namegoeshere.annoyify.util;

import static org.bukkit.Bukkit.getLogger;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLogger {

    /**
     * Write a message to the player who issued the command or log the command's message output.
     * This is used only for logging command results, which is why there's no usage of Bukkit's
     * Logger where log levels can be manually set.
     * @param sender The source of the command to be sent
     * @param logMessage The message to write to the player or console
     */
    public static void logCommandEvent(CommandSender sender, String logMessage) {
        if(sender instanceof Player) {
            sender.sendMessage(logMessage);
        } else {
            getLogger().info(logMessage);
        }
    }
}
