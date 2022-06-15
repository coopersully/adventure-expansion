package me.coopersully.rpgloot.rpgloot.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.HalaraRPG;
import me.coopersully.rpgloot.rpgloot.items.EternalArmor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Armor implements Listener {

    @EventHandler
    public void onArmorEquip(@NotNull PlayerArmorChangeEvent event) {

        Bukkit.getScheduler().runTaskLaterAsynchronously(HalaraRPG.getPlugin(), () -> updateArmorTags(event), 20L);

        Player player = event.getPlayer();
        ItemStack oldItem = event.getOldItem();
        ItemStack item = event.getNewItem();

        if (item == null) return;

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;
        PersistentDataContainer itemPDC = itemMeta.getPersistentDataContainer();

        // Play eternal equip effect
        if (itemPDC.has(ItemKeys.eternal)) {

            /* Don't play equip effect on every damage, update,
            etc. only play it on equip from non-Eternal armor. */
            if (oldItem != null) {
                ItemMeta oldItemMeta = oldItem.getItemMeta();
                if (oldItemMeta != null) {

                    PersistentDataContainer oldItemPDC = oldItemMeta.getPersistentDataContainer();
                    if (oldItemPDC.has(ItemKeys.eternal)) return;
                }
            }

            EternalArmor.playEquipEffect(player);
        }

    }

    public void updateArmorTags(@NotNull PlayerArmorChangeEvent event) {

        Player player = event.getPlayer();

        ItemStack[] armor = player.getInventory().getArmorContents();

        boolean hasGrimsteel = false;
        for (ItemStack item : armor) {

            if (item == null) continue;

            ItemMeta itemMeta = item.getItemMeta();

            if (itemMeta == null) continue;

            if (itemMeta.getPersistentDataContainer().has(ItemKeys.grimsteel)) {
                hasGrimsteel = true;
                break;
            }
        }

        if (hasGrimsteel) {
            player.getPersistentDataContainer().set(ItemKeys.grimsteel, PersistentDataType.STRING, "1");
        } else {
            player.getPersistentDataContainer().remove(ItemKeys.grimsteel);
        }

        giveIfEquippedArmor(player, ItemKeys.sculkBoots, 0);
        giveIfEquippedArmor(player, ItemKeys.minersHat, 3);

    }

    private void giveIfEquippedArmor(@NotNull Player player, NamespacedKey key, int slot) {

        var playerPDC = player.getPersistentDataContainer();
        var playerArmor = player.getInventory().getArmorContents();
        var item = playerArmor[slot];

        // Remove the given NamespacedKey from the player
        playerPDC.remove(ItemKeys.minersHat);

        if (item == null) return;

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;

        // If the player's worn item contains the NamespacedKey specified
        if (itemMeta.getPersistentDataContainer().has(key)) {
            // Give the player the corresponding NamespacedKey
            playerPDC.set(key, PersistentDataType.STRING, "1");
        }

    }

}
