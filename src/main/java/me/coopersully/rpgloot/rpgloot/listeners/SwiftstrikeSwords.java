package me.coopersully.rpgloot.rpgloot.listeners;

import com.google.common.collect.Multimap;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class SwiftstrikeSwords implements Listener {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;
        if (!itemMeta.getPersistentDataContainer().has(TreasureItemKeys.swiftstrike)) return;

        if (isInCooldown(player)) {
            long timeLeft = ((cooldowns.get(player.getUniqueId()) + 5000 - System.currentTimeMillis()) / 1000) / 2;
            player.sendActionBar(Component.text("Ability on cooldown for " + timeLeft + "s"));
            return;
        }
        dashPlayer(player, item);
        setCooldown(player, 5);
    }

    private void setCooldown(@NotNull Player player, int seconds) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + seconds * 1000L);
    }

    private boolean isInCooldown(@NotNull Player player) {
        return cooldowns.containsKey(player.getUniqueId()) && cooldowns.get(player.getUniqueId()) > System.currentTimeMillis();
    }

    private void dashPlayer(@NotNull Player player, @NotNull ItemStack item) {
        Vector direction = player.getLocation().getDirection().multiply(2);
        player.setVelocity(direction);
        item.damage(1, player);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 4.0f, 1.75f);

        // Schedule the task to run every tick for the first 0.25 seconds (5 ticks) of the dash
        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                // Stop the task after the specified time approximately 0.25 seconds
                // If server is running at 20 ticks per second
                if (count >= 5) this.cancel();
                else {
                    Vector playerDirection = player.getLocation().getDirection();
                    double reach = 2.0;  // How far in front we are checking
                    Location startPoint = player.getLocation();
                    Location checkPoint = startPoint.clone().add(playerDirection.multiply(reach));

                    // Check entities in a small box in front of the player
                    Collection<Entity> entities = player.getWorld().getNearbyEntities(checkPoint, 1, 1, 1);
                    for (Entity entity : entities) {
                        if (entity instanceof LivingEntity target && entity != player) {
                            attackTarget(player, target, item);
                        }
                    }
                    count++;
                }
            }
        }.runTaskTimer(AdventureExpansion.getPlugin(), 0L, 1L);
    }

    private void attackTarget(Player player, LivingEntity target, @NotNull ItemStack sword) {
        double baseDamage = getWeaponDamage(sword); // Base damage for a wooden sword hit

        // Sharpness effect
        if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
            int level = sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
            baseDamage += (level * 1.25);
        }

        // Knock-back effect
        if (sword.containsEnchantment(Enchantment.KNOCKBACK)) {
            int level = sword.getEnchantmentLevel(Enchantment.KNOCKBACK);
            Vector knockback = player.getLocation().getDirection().multiply(level * 0.5);
            target.setVelocity(target.getVelocity().add(knockback));
        }

        // Fire Aspect effect
        if (sword.containsEnchantment(Enchantment.FIRE_ASPECT)) {
            int level = sword.getEnchantmentLevel(Enchantment.FIRE_ASPECT);
            target.setFireTicks(level * 40);
        }

        player.playSound(player.getLocation(), Sound.ITEM_AXE_SCRAPE, 2.0f, 2f);
        target.damage(baseDamage, player);
        sword.damage(1, target);
    }

    private static final double DEFAULT_DAMAGE = 4.0;

    public static double getWeaponDamage(ItemStack item) {
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            double baseDamage = getBaseDamage(item.getType()); // Get base damage based on item type

            Multimap<Attribute, AttributeModifier> modifiers = meta.getAttributeModifiers();
            if (modifiers != null && !modifiers.isEmpty() && modifiers.containsKey(Attribute.GENERIC_ATTACK_DAMAGE)) {
                // Accumulate all attack damage modifiers
                for (AttributeModifier mod : modifiers.get(Attribute.GENERIC_ATTACK_DAMAGE)) {
                    baseDamage += mod.getAmount();
                }
                return baseDamage;
            }
            return baseDamage;
        }
        return DEFAULT_DAMAGE;
    }

    private static double getBaseDamage(@NotNull Material material) {
        return switch (material) {
            case WOODEN_SWORD -> 4.0;
            case STONE_SWORD -> 5.0;
            case IRON_SWORD -> 6.0;
            case DIAMOND_SWORD -> 7.0;
            case NETHERITE_SWORD -> 8.0;
            case WOODEN_AXE, GOLDEN_AXE -> 7.0;
            case STONE_AXE, IRON_AXE, DIAMOND_AXE -> 9.0;
            case NETHERITE_AXE -> 10.0;
            default -> DEFAULT_DAMAGE;
        };
    }
}
