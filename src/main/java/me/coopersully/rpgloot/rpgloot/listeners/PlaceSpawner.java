package me.coopersully.rpgloot.rpgloot.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

public class PlaceSpawner implements Listener {

    @EventHandler
    public void onBlockPlace(@NotNull BlockPlaceEvent event) {
        Block block = event.getBlock();
        ItemStack item = event.getItemInHand();

        if (block.getType() != Material.SPAWNER || !item.hasItemMeta()) return;

        BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
        CreatureSpawner placedSpawner = (CreatureSpawner) block.getState();
        CreatureSpawner itemSpawner = (CreatureSpawner) meta.getBlockState();
        placedSpawner.setSpawnedType(itemSpawner.getSpawnedType());
        placedSpawner.update();
    }
}
