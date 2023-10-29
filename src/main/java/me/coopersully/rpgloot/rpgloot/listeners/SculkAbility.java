package me.coopersully.rpgloot.rpgloot.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class SculkAbility implements Listener {

    @EventHandler
    public void onJump(@NotNull PlayerJumpEvent event) {

        Player player = event.getPlayer();
        if (!player.getPersistentDataContainer().has(TreasureItemKeys.sculkBoots)) return;

        if (!player.isSneaking()) return;

        event.setCancelled(true);
        attemptAbility(player);
    }

    private static HashMap<UUID, Long> cooldown = new HashMap<>();

    public static void attemptAbility(@NotNull Player player) {

        UUID playerUUID = player.getUniqueId();
        if (cooldown.containsKey(playerUUID) && cooldown.get(playerUUID) > System.currentTimeMillis()) {

            long cooldownTime = (cooldown.get(playerUUID) - System.currentTimeMillis()) / 1000;
            player.sendActionBar(Component.text("Ability ready in " + cooldownTime + " seconds."));

        } else {
            Location location = player.getLocation();

            Collection<LivingEntity> nearbyEntities = location.getNearbyLivingEntities(15.0);
            for (LivingEntity entity : nearbyEntities) {

                World world = entity.getWorld();
                Location entityLocation = entity.getLocation();

                if (entity != player) {
                    // Only perform these effects on entities that are not the player
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 0));
                }

                // Perform these effects on every entity in the radius
                entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 0));
                world.playSound(entityLocation, Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.PLAYERS, 1, 1);
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
            player.sendActionBar(Component.text("Revealed " + (nearbyEntities.size() - 1) + " nearby entities."));

            cooldown.put(playerUUID, System.currentTimeMillis() + (45 * 1000));

        }

    }

}
