package io.namegoeshere.annoyify.managers;

import io.namegoeshere.annoyify.Main;
import io.namegoeshere.annoyify.listeners.DoorInteractListener;
import io.namegoeshere.annoyify.listeners.PlayerChangedWorldListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenerManager implements Listener {

    /**
     * Initialises command listeners
     * @param main The instance of {@link Main}
     */
    public ListenerManager(Main main) {
        Bukkit.getPluginManager().registerEvents(new PlayerChangedWorldListener(), main);
        Bukkit.getPluginManager().registerEvents(new DoorInteractListener(), main);
    }
}
