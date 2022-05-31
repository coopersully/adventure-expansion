package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.ItemKeys;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class Bows implements Listener {

    @EventHandler
    public void onShootBow(@NotNull EntityShootBowEvent event) {

        LivingEntity entity = event.getEntity();

        ItemStack bow = event.getBow();
        if (bow == null) return;

        ItemMeta bowMeta = event.getBow().getItemMeta();
        if (bowMeta == null) return;

        if (bowMeta.getPersistentDataContainer().has(ItemKeys.witherBow)) {
            Entity oldProjectile = event.getProjectile();
            Location killArea = oldProjectile.getLocation().clone();
            killArea.setY(-500);
            oldProjectile.teleportAsync(killArea);
            entity.launchProjectile(WitherSkull.class).setVelocity(event.getProjectile().getVelocity().multiply(0.45));
        }
    }

}
