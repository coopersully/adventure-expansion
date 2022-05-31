package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.ItemKeys;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class MinersHat implements Listener {

    @EventHandler
    public void onWalk(@NotNull PlayerMoveEvent event) {

        if (!event.hasChangedBlock()) return;

        Location previous = event.getFrom().toBlockLocation();
        Location current = event.getTo().toBlockLocation();

        if (previous.getBlock().getType() == Material.LIGHT) {
            previous.getBlock().setType(Material.AIR);
        }

        if (event.getPlayer().getPersistentDataContainer().has(ItemKeys.minersHat)) {
            if (current.getBlock().getType() == Material.AIR || current.getBlock().getType() == Material.CAVE_AIR) {
                current.getBlock().setType(Material.LIGHT);
            }
        }

    }

}
