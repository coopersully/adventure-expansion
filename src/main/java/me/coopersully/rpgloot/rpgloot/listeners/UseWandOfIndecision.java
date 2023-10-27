package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.items.EnderSword;
import me.coopersully.rpgloot.rpgloot.items.WandOfIndecision;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteError;

public class UseWandOfIndecision implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(@NotNull PlayerInteractEntityEvent event) {

        // Check if the entity the player is interacting with is a Villager
        if (event.getRightClicked().getType() != EntityType.VILLAGER) return;

        // Cast the entity to a Villager
        Villager villager = (Villager) event.getRightClicked();

        // Get the player who interacted with the Villager
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (persistentDataContainer.isEmpty()) return;

        if (!itemMeta.getPersistentDataContainer().has(ItemKeys.enderSword)) return;
        WandOfIndecision.refreshTrades(villager);
    }
}
