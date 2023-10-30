package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class WanderingTraderKilled implements Listener {

    @EventHandler
    public void onDeath(@NotNull EntityDeathEvent event) {

        if (event.getEntity().getType() != EntityType.WANDERING_TRADER) return;
        if (!CoreUtils.rollChances(20)) return;

        Location location = event.getEntity().getLocation();
        location.getWorld().dropItemNaturally(location, TreasureItem.TRADER_HAT);
    }

}
