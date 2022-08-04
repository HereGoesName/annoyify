package io.namegoeshere.annoyify.listeners;

import io.namegoeshere.annoyify.config.TeleportationType;
import io.namegoeshere.annoyify.models.Annoyify;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import static org.bukkit.World.Environment.NETHER;
import static org.bukkit.World.Environment.NORMAL;

import io.namegoeshere.annoyify.config.DefaultConfig;

public class PlayerChangedWorldListener implements Listener {

    /**
     * Teleports an event's {@link Player} when they're returning to the overworld from the nether
     * @param event The {@link PlayerChangedWorldEvent}
     */
    @EventHandler
    public void netherToWorld(PlayerChangedWorldEvent event) {
        if (DefaultConfig.getPortalTravelFromNetherType() != TeleportationType.OFF) {
            Player player = event.getPlayer();
            if(Annoyify.getInstance().getEnabledPlayers().isPlayerEnabled(player)) {
                if( event.getFrom().getEnvironment() == NETHER
                    && player.getWorld().getEnvironment() == NORMAL
                ) {
                    Random randomValue = new Random();
                    if(randomValue.nextInt(100) >= DefaultConfig.getPortalTravelFromNetherRate()) {
                        Location location = null;
                        if(DefaultConfig.getPortalTravelFromNetherType().equals(
                            TeleportationType.RELATIVE)
                        ) {
                            location = player.getLocation();
                        } else {
                            location = new Location(player.getWorld(), 0.0, 0.0, 0.0);
                        }

                        location.setX(
                            location.getX() + DefaultConfig.getPortalTravelFromNetherXPosition());
                        location.setY(
                            location.getY() + DefaultConfig.getPortalTravelFromNetherYPosition());
                        location.setZ(
                            location.getZ() + DefaultConfig.getPortalTravelFromNetherZPosition());
                        player.teleport(location);
                    }
                }
            }
        }
    }
}
