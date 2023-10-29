package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Death implements Listener {

    public void onDeath(@NotNull PlayerDeathEvent event) {

        Player player = event.getPlayer();
        if (!player.getPersistentDataContainer().has(TreasureItemKeys.shadowCrown)) return;

        Bukkit.getScheduler().runTaskLaterAsynchronously(AdventureExpansion.getPlugin(), () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (60 * (20 * 120)), 9));
        }, 20L);

    }

}
