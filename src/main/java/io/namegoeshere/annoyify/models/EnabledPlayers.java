package io.namegoeshere.annoyify.models;

import static org.bukkit.Bukkit.getLogger;

import io.namegoeshere.annoyify.util.CommandLogger;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class EnabledPlayers {
    private static EnabledPlayers INSTANCE;

    @Getter
    private Set<OfflinePlayer> enabledPlayers;

    /**
     * A set containing {@link OfflinePlayer} to "annoy"
     */
    private EnabledPlayers() {
        enabledPlayers = new HashSet<>();
    }

    /**
     *
     * @return Instance of {@link EnabledPlayers}
     */
    public static EnabledPlayers getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EnabledPlayers();
        }

        return INSTANCE;
    }

    /**
     * Adds or removes a {@link Player} to the {@link #enabledPlayers Annoyify list}
     * @param player The instance of a player to add or remove
     * @param sender The origin of the toggle request
     */
    public void togglePlayer(Player player, CommandSender sender) {
        if(!enabledPlayers.contains(player)) {
            addPlayer(player);
            try {
                Annoyify.getInstance().getEnabledPlayerListFile().addPlayerToFile(
                    player.getUniqueId().toString());
                CommandLogger.logCommandEvent(sender,
                    "Player [" + player.getName() + "] added to annoy list");
            } catch(IOException exception) {
                getLogger().warning(
                    "[Annoyify] Player ["
                        + player.getName()
                        + "] was unsuccessfully saved to the enabled player list file.");
            }
        } else {
            removePlayer(player);
            try {
                Annoyify.getInstance().getEnabledPlayerListFile().removePlayerFromFile(
                    player.getUniqueId().toString());
                CommandLogger.logCommandEvent(sender,
                    "Player [" + player.getName() + "] removed from annoy list");
            } catch(IOException exception) {
                getLogger().warning(
                    "[Annoyify] Player ["
                        + player.getName()
                        + "] was unsuccessfully removed from the enabled player list file.");
            }
        }
    }

    /**
     * Add a player to the {@link #enabledPlayers} list
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        enabledPlayers.add(player);
    }

    /**
     * Adds players to the {@link #enabledPlayers} list
     * @param players The players to add
     */
    public void addPlayers(Set<String> players) {
        players.forEach(
            player -> {
                try {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(player));
                    if(!Objects.equals(offlinePlayer.getName(), null)) {
                        enabledPlayers.add(offlinePlayer);
                    }
                } catch (IllegalArgumentException exception) {
                    getLogger().info(
                        "[Annoyify] Player [UUID: "
                            + player
                            + "] was unsuccessfully added to the enabled player list file.");
                }
            });
    }

    /**
     * Remove a player from the {@link #enabledPlayers} list
     * @param player The player to remove
     */
    public void removePlayer(Player player) {
        enabledPlayers.remove(player);
    }

    /**
     *
     * @param player The player to query
     * @return If the player is in the {@link #enabledPlayers} list
     */
    public boolean isPlayerEnabled(Player player) {
        return enabledPlayers.contains(player);
    }
}
