package io.namegoeshere.annoyify.listeners;

import io.namegoeshere.annoyify.config.DefaultConfig;
import io.namegoeshere.annoyify.models.Annoyify;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class DoorInteractListener implements Listener {

    public static final Set<Material> doorTypes = new HashSet<>(Arrays.asList(
        Material.ACACIA_DOOR, Material.ACACIA_TRAPDOOR, Material.ACACIA_FENCE_GATE,
        Material.BIRCH_DOOR, Material.BIRCH_TRAPDOOR, Material.BIRCH_FENCE_GATE,
        Material.CRIMSON_DOOR, Material.CRIMSON_TRAPDOOR, Material.CRIMSON_FENCE_GATE,
        Material.DARK_OAK_DOOR, Material.DARK_OAK_TRAPDOOR, Material.DARK_OAK_FENCE_GATE,
        Material.IRON_DOOR, Material.IRON_TRAPDOOR,
        Material.JUNGLE_DOOR, Material.JUNGLE_TRAPDOOR, Material.JUNGLE_FENCE_GATE,
        Material.MANGROVE_DOOR, Material.MANGROVE_TRAPDOOR, Material.MANGROVE_FENCE_GATE,
        Material.OAK_DOOR, Material.OAK_TRAPDOOR, Material.OAK_FENCE_GATE,
        Material.SPRUCE_DOOR, Material.SPRUCE_TRAPDOOR, Material.SPRUCE_FENCE_GATE,
        Material.WARPED_DOOR, Material.WARPED_TRAPDOOR, Material.WARPED_FENCE_GATE
    ));

    /**
     *
     * @param block The {@link Material block}
     * @return If the material matches {@link #doorTypes}
     */
    private Boolean isDoor(Material block) {
        return doorTypes.contains(block);
    }

    /**
     * Cancels a door opening event if the event's player is on the
     * {@link io.namegoeshere.annoyify.models.EnabledPlayers EnabledPlayers} list
     * @param event The {@link PlayerInteractEvent}
     */
    @EventHandler
    public void doorInteractListener(PlayerInteractEvent event) {
        if(DefaultConfig.getIgnoreDoorInteractEnabled()) {
            if(Annoyify.getInstance().getEnabledPlayers().isPlayerEnabled(
                event.getPlayer())
            ) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (isDoor(event.getClickedBlock().getType())) {
                        Random randomValue = new Random();
                        if (randomValue.nextInt(100)
                            >= DefaultConfig.getIgnoreDoorInteractRate()
                        ) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}