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
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class EquipArmor implements Listener {

    @EventHandler
    public void onArmorEquip(@NotNull PlayerArmorChangeEvent event) {

        event.get

        Bukkit.getScheduler().runTaskLaterAsynchronously(RPGLoot.getPlugin(), new Runnable() {
            @Override
            public void run() {
                updateArmorTags(event);
            }
        }, 20L);

        Player player = event.getPlayer();
        ItemStack oldItem = event.getOldItem();
        ItemStack item = event.getNewItem();

        if (item == null) return;

        if (oldItem != null) {
            if (oldItem.isSimilar(item)) return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;

        if (itemMeta.getPersistentDataContainer().has(ItemKeys.eternal)) EternalArmor.playEquipEffect(player);

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

    }

}
