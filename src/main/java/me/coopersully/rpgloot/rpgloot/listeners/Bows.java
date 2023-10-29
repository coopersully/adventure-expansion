package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Bows implements Listener {

    @EventHandler
    public void onShootBow(@NotNull EntityShootBowEvent event) {

        LivingEntity entity = event.getEntity();

        ItemStack bow = event.getBow();
        if (bow == null) return;

        ItemMeta bowMeta = event.getBow().getItemMeta();
        if (bowMeta == null) return;

        if (bowMeta.getPersistentDataContainer().has(TreasureItemKeys.witherBow)) {
            CoreUtils.killProjectile(event.getProjectile());
            entity.launchProjectile(WitherSkull.class).setVelocity(event.getProjectile().getVelocity().multiply(0.45));
        }

        if (bowMeta.getPersistentDataContainer().has(TreasureItemKeys.evokerBow)) {
            CoreUtils.killProjectile(event.getProjectile());
            Snowball projectile = entity.launchProjectile(Snowball.class);
            projectile.setVelocity(event.getProjectile().getVelocity());
            projectile.setItem(new ItemStack(Material.GHAST_TEAR, 1));
            projectile.getPersistentDataContainer().set(TreasureItemKeys.evokerBow, PersistentDataType.STRING, "1");
        }

    }

}
