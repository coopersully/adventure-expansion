package me.coopersully.rpgloot.rpgloot.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.EternalArmor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static me.coopersully.rpgloot.rpgloot.AdventureExpansion.getPlugin;

public class Armor implements Listener {

    @EventHandler
    public void onArmorEquip(@NotNull PlayerArmorChangeEvent event) {
        Player player = event.getPlayer();
        refreshPlayerKeysConcerningArmor(player);
    }

    public void refreshPlayerKeysConcerningArmor(@NotNull Player player) {
        killPlayerKeys(player);

        PlayerInventory inventory = player.getInventory();

        ItemStack helmet = inventory.getHelmet();
        if (helmet != null) addPlayerKeysConcerningItem(player, helmet);

        ItemStack chestplate = inventory.getChestplate();
        if (chestplate != null) addPlayerKeysConcerningItem(player, chestplate);

        ItemStack leggings = inventory.getLeggings();
        if (leggings != null) addPlayerKeysConcerningItem(player, leggings);

        ItemStack boots = inventory.getBoots();
        if (boots != null) addPlayerKeysConcerningItem(player, boots);
    }

    public void killPlayerKeys(@NotNull Player player) {
        for (NamespacedKey key : player.getPersistentDataContainer().getKeys()) {
            // Ensure key is from this plugin
            if (!key.getNamespace().equals(getPlugin().getName().toLowerCase(Locale.ROOT))) return;

            // Remove key from player
            player.getPersistentDataContainer().remove(key);
        }
    }

    public void addPlayerKeysConcerningItem(Player player, ItemStack itemStack) {
        if (itemStack == null) return;

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) return;

        for (NamespacedKey key : itemMeta.getPersistentDataContainer().getKeys()) {
            // Ensure key is from this plugin
            if (!key.getNamespace().equals(getPlugin().getName().toLowerCase(Locale.ROOT))) return;

            // Add key to player
            player.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, Boolean.TRUE);
        }
    }

}
