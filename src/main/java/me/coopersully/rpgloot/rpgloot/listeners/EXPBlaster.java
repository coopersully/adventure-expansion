package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EXPBlaster implements Listener {

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

        // Ensure the item is an EXP Blaster
        if (!persistentDataContainer.has(TreasureItemKeys.expBlaster)) return;

        // Check the player's experience level
        int playerExp = player.calculateTotalExperiencePoints();
        if (playerExp <= 0) {
            // Play sound and alert action bar if the player is out of experience
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f);
            player.sendActionBar(Component.text("Need more experience"));
            return;
        }

        // Take 1 experience point from the player
        player.setExperienceLevelAndProgress(playerExp - 1);
        player.setExpCooldown(40);
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 1.0f, 2.0f);

        // Raytrace the bullet
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection();
        for (int i = 0; i < 15; i++) {
            start.add(direction);

            // Spawn particle trail
            player.getWorld().spawnParticle(Particle.REDSTONE, start, 1, new Particle.DustOptions(org.bukkit.Color.GREEN, 1));

            // Check if the raytrace hit a block or entity
            if (!player.getWorld().getNearbyEntities(start, 0.5, 0.5, 0.5).isEmpty() || !player.getWorld().getBlockAt(start).isPassable()) {
                dropExperienceOrb(start);
                return;
            }
        }

        // Drop experience orb at max range
        dropExperienceOrb(start);
    }

    private void dropExperienceOrb(@NotNull Location location) {
        location.getWorld().spawn(location, ExperienceOrb.class, expOrb -> expOrb.setExperience(1));
    }
}
