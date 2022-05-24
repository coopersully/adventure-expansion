package me.coopersully.rpgloot.rpgloot.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.RPGLoot;
import me.coopersully.rpgloot.rpgloot.items.EternalArmor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class EquipArmor implements Listener {

    @EventHandler
    public void onArmorEquip(@NotNull PlayerArmorChangeEvent event) {

        Bukkit.getScheduler().runTaskLaterAsynchronously(RPGLoot.getPlugin(), () -> updateArmorTags(event), 20L);

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

        player.getPersistentDataContainer().remove(ItemKeys.skulkBoots);

        ItemStack boots = armor[0];
        if (boots != null) {
            ItemMeta bootsMeta = boots.getItemMeta();
            if (bootsMeta != null) {
                if (bootsMeta.getPersistentDataContainer().has(ItemKeys.skulkBoots)) {
                    player.getPersistentDataContainer().set(ItemKeys.skulkBoots, PersistentDataType.STRING, "1");
                }
            }
        }

    }

}
