package io.namegoeshere.annoyify.models;

import static org.bukkit.Bukkit.getLogger;

import io.namegoeshere.annoyify.models.helpers.EnabledPlayerListFile;
import java.io.IOException;
import lombok.Getter;
import org.bukkit.Bukkit;

public final class Annoyify {
    private static Annoyify INSTANCE;

    private EnabledPlayers enabledPlayers;

    @Getter
    private EnabledPlayerListFile enabledPlayerListFile;

    /**
     * Singleton for Annoyify runtime classes
     */
    private Annoyify() {
        enabledPlayers = EnabledPlayers.getInstance();
        enabledPlayerListFile = new EnabledPlayerListFile();
        try {
            enabledPlayers.addPlayers(enabledPlayerListFile.loadFile());
        } catch(IOException exception) {
            getLogger().warning(
                "[Annoyify] Player file was unsuccessfully loaded.");
        }
    }

    /**
     *
     * @return Instance of {@link Annoyify}
     */
    public static Annoyify getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Annoyify();
        }

        return INSTANCE;
    }

    /**
     *
     * @return The instance of {@link EnabledPlayers}
     */
    public EnabledPlayers getEnabledPlayers() {
        return EnabledPlayers.getInstance();
    }
}
