package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class EventHorizonHarness implements Listener {
    @EventHandler
    public void onPrePlayerAttackEntity(@NotNull EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player) || event.getCause() != EntityDamageEvent.DamageCause.VOID) {
            return;
        }
        if (!player.getPersistentDataContainer().has(TreasureItemKeys.eventHorizonHarness)) return;

        event.setCancelled(true);
        handleVoidFall(player);
    }

    private void handleVoidFall(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                Location originalLocation = player.getLocation();
                Location skyLocation = new Location(player.getWorld(), originalLocation.getX(), 420, originalLocation.getZ());

                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1, false, false, false));
                player.teleport(skyLocation);

                createExplosion(originalLocation);
                createExplosion(skyLocation);

                // Playing sounds at the sky location after teleportation
                player.playSound(skyLocation, Sound.BLOCK_BEACON_DEACTIVATE, 1.0f, 0.0f);
                player.playSound(skyLocation, Sound.BLOCK_CONDUIT_ATTACK_TARGET, 1.0f, 0.0f);
                player.playSound(skyLocation, Sound.ENTITY_ENDER_DRAGON_HURT, 0.5f, 0.0f);
            }
        };
        task.runTask(AdventureExpansion.getPlugin());
    }

    private void createExplosion(@NotNull Location location) {
        location.getWorld().createExplosion(location, 0F, false, false);
    }
}
