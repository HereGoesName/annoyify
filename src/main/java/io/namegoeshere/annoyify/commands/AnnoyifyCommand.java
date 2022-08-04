package io.namegoeshere.annoyify.commands;

import io.namegoeshere.annoyify.models.Annoyify;
import io.namegoeshere.annoyify.util.CommandLogger;
import java.util.Arrays;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnoyifyCommand implements CommandExecutor {
    /**
     * Add or remove a player to/from the annoy list
     * @param sender Source of the command
     * @param command Command object being requested by the sender
     * @param label String for using command
     * @param args Arguments
     * @return If the command is sent correctly
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Server server = Bukkit.getServer();

        for(String playerName : new HashSet<>(Arrays.asList(args))) {
            Player player = server.getPlayer(playerName);
            if (player != null) {
                Annoyify.getInstance().getEnabledPlayers().togglePlayer(player, sender);
            } else {
                CommandLogger.logCommandEvent(sender,
                    "Player ["
                        + playerName
                        + "] must be online to add or remove from the annoy list");
            }
        }
        return true;
    }
}
