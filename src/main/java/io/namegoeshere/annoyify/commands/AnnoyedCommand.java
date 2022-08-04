package io.namegoeshere.annoyify.commands;

import io.namegoeshere.annoyify.models.Annoyify;
import io.namegoeshere.annoyify.util.CommandLogger;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnoyedCommand implements CommandExecutor {
    /**
     * List players on the annoy list
     * @param sender Source of the command
     * @param command Command object being requested by the sender
     * @param label String for using command
     * @param args Arguments
     * @return If the command is sent correctly
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandLogger.logCommandEvent(sender,
            "Annoyify targeted players: "
                + Annoyify.getInstance()
                .getEnabledPlayers()
                .getEnabledPlayers()
                .stream()
                .filter(Objects::nonNull)
                .map(player -> player.getName())
                .collect(Collectors.toSet()));
        return true;
    }
}
