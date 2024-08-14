package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class Flamethrower implements Listener {

    @EventHandler
    public void onRightClick(@NotNull PlayerInteractEvent event) {
        // Ensure the action is a right-click action
        if (!event.getAction().isRightClick()) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        // Ensure the item has meta data
        if (itemMeta == null) return;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        // Ensure the item has persistent data
        if (persistentDataContainer.isEmpty()) return;

        // Ensure the item is a Flamethrower
        if (!persistentDataContainer.has(TreasureItemKeys.flamethrower)) return;

        // Check if the player has blaze powder
        if (!player.getInventory().containsAtLeast(new ItemStack(Material.BLAZE_POWDER), 1)) {
            // Play sound and alert action bar if the player is out of blaze powder
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f);
            player.sendActionBar(Component.text("Need more Blaze Powder"));
            return;
        }

        // Take 1 blaze powder from the player
        ItemStack blazePowder = new ItemStack(Material.BLAZE_POWDER, 1);
        player.getInventory().removeItem(blazePowder);
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_BURN, 1.0f, 2.0f);

        // Raytrace the bullet
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection().normalize();
        for (int i = 0; i < 15; i++) {
            start.add(direction);

            // Spawn particle trail
            player.getWorld().spawnParticle(Particle.FLAME, start, 5, 0, 0, 0, 0.01);

            // Check if the raytrace hit a block or entity
            Collection<Entity> entities = player.getWorld().getNearbyEntities(start, 0.5, 0.5, 0.5);
            if (!entities.isEmpty()) {
                for (Entity entity : entities) {
                    entity.setFireTicks(100);
                    if (entity instanceof LivingEntity livingEntity) livingEntity.damage(2.0, player);
                }
            }

            // Stop when it hits something
            if (!player.getWorld().getBlockAt(start).isPassable()) return;
        }
    }

}
