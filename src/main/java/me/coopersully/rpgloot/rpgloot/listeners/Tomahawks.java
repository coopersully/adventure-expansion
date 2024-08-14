package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Tomahawks implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;
        if (!itemMeta.getPersistentDataContainer().has(TreasureItemKeys.tomahawk)) return;


        throwTomahawk(player, item);
        item.setAmount(item.getAmount() - 1);
    }

    @EventHandler
    public void onProjectileHit(@NotNull ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        if (!entity.getPersistentDataContainer().has(TreasureItemKeys.tomahawk)) return;
        entity.remove();
    }

    private void throwTomahawk(@NotNull Player player, @NotNull ItemStack tomahawk) {
        Arrow thrownTomahawk = player.launchProjectile(Arrow.class);
        thrownTomahawk.setVelocity(player.getLocation().getDirection().multiply(1.5));
        thrownTomahawk.setCritical(true);
        thrownTomahawk.setItem(new ItemStack(Material.AIR, 1));
        thrownTomahawk.getPersistentDataContainer().set(TreasureItemKeys.tomahawk, PersistentDataType.BOOLEAN, true);

        // Manage durability
        tomahawk.damage(5, player);

        Item item = thrownTomahawk.getWorld().dropItem(thrownTomahawk.getLocation(), tomahawk.clone());
        thrownTomahawk.addPassenger(item);

        // Play sound when the tomahawk is thrown
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0f, 1.5f);
    }

}
