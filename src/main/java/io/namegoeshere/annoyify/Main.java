package io.namegoeshere.annoyify;

import io.namegoeshere.annoyify.config.DefaultConfig;
import io.namegoeshere.annoyify.managers.CommandManager;
import io.namegoeshere.annoyify.managers.ListenerManager;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    /**
     * Calls DefaultConfig's initialiseConfig to create and set up the config file if it doesn't
     * exist and populate variables from the config file
     * @throws IOException Thrown in the event a file couldn't be created or read
     */
    public static void initialiseConfigs() throws IOException {
        DefaultConfig.initialiseConfig();
    }

    /**
     * Method executed when the plugin enables in the bukkit server
     */
    public void onEnable () {
        try {
            initialiseConfigs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new ListenerManager(this);
        CommandManager.initialiseCommands(this);
    }

    /**
     * Method executed when the plugin disables in the bukkit server
     */
    public void onDisable () {}

}

