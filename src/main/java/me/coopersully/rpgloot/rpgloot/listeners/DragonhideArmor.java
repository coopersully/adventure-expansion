package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class DragonhideArmor implements Listener {
    @EventHandler
    public void onPrePlayerAttackEntity(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player player))  return;

        EntityDamageEvent.DamageCause cause = event.getCause();
        if (cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && cause != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) return;

        if (!player.getPersistentDataContainer().has(TreasureItemKeys.dragonhide)) return;

        event.setCancelled(true);
    }
}
