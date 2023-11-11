package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class UseGonk implements Listener {
    @EventHandler
    public void onPrePlayerAttackEntity(@NotNull EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player)) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) return;
        if (!itemMeta.getPersistentDataContainer().has(TreasureItemKeys.gonk)) return;

        // Damage the item in the player's hand
        player.getInventory().getItemInMainHand().damage(80, player);

        // Play a sound at the player's location
        Location loc = player.getLocation();
        loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_FALL, SoundCategory.PLAYERS,4.0f, 1.0f);

        // Calculate the direction for the knockback
        Vector direction = loc.getDirection();
        double upwardForce = 0.5; // Adjust this value for upward force
        double backwardForce = -0.5; // Adjust this value for backward force

        // Apply the knockback
        Vector knockback = direction.multiply(backwardForce).setY(upwardForce);
        player.setVelocity(player.getVelocity().add(knockback));
    }
}