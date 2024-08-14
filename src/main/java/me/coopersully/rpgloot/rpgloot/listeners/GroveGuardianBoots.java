package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class GroveGuardianBoots implements Listener {

    private final HashMap<UUID, BukkitTask> sprintTasks = new HashMap<>();

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();

        // If sprinting is toggled on
        if (event.isSprinting()) {
            startSprintTask(player);
        } else {
            stopSprintTask(player);
        }
    }

    private void startSprintTask(Player player) {
        // Cancel any existing tasks for this player
        stopSprintTask(player);

        // Schedule a new task that checks and applies speed every 5 seconds
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(AdventureExpansion.getPlugin(), () -> {
            if (isPlayerInForest(player) && player.getPersistentDataContainer().has(TreasureItemKeys.groveGuardianBoots)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1, false, false, false));
            } else {
                stopSprintTask(player);
            }
        }, 0L, 100L);

        sprintTasks.put(player.getUniqueId(), task);
    }

    private void stopSprintTask(Player player) {
        BukkitTask task = sprintTasks.remove(player.getUniqueId());
        if (task != null) {
            task.cancel();
        }
        player.removePotionEffect(PotionEffectType.SPEED);
    }

    private boolean isPlayerInForest(Player player) {
        return switch (player.getLocation().getBlock().getBiome()) {
            case FOREST, FLOWER_FOREST, BIRCH_FOREST, DARK_FOREST -> true;
            default -> false;
        };
    }
}
