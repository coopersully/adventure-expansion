package me.coopersully.rpgloot.rpgloot.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import me.coopersully.rpgloot.rpgloot.items.GrimsteelArmor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class PickupExp implements Listener {

    @EventHandler
    public void onPickupExp(@NotNull PlayerPickupExperienceEvent event) {

        Player player = event.getPlayer();
        if (player.getPersistentDataContainer().has(TreasureItemKeys.grimsteel)) GrimsteelArmor.gainedExperience(event);

    }

}
