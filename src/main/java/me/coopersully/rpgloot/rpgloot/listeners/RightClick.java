package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.items.EnderSword;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class RightClick implements Listener {

    @EventHandler
    public void onRightClick(@NotNull PlayerInteractEvent event) {

        if (!event.getAction().isRightClick()) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (persistentDataContainer.isEmpty()) return;

        if (!itemMeta.getPersistentDataContainer().has(ItemKeys.enderSword)) return;
        EnderSword.attemptTeleport(player);

    }

}
