package me.coopersully.rpgloot.rpgloot.listeners;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class UseCalamitySword implements Listener {
    @EventHandler
    public void onPrePlayerAttackEntity(@NotNull PrePlayerAttackEntityEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) return;
        if (!itemMeta.getPersistentDataContainer().has(TreasureItemKeys.calamitySword)) return;
        if (!CoreUtils.rollChances(1)) return;

        event.getPlayer().getInventory().getItemInMainHand().setAmount(itemStack.getAmount() - 1);
        event.getPlayer().getLocation().createExplosion(3.0f, false, false);
    }
}
