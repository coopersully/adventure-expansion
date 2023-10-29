package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Magnets implements Listener {

    private static List<Player> expMagnetWielders = new ArrayList<>();
    private static List<Player> itemMagnetWielders = new ArrayList<>();

    static {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(AdventureExpansion.getPlugin(), Magnets::performAbilities, 0, 20);
    }

    @EventHandler
    public void onHoldItem(@NotNull PlayerItemHeldEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(AdventureExpansion.getPlugin(), () -> updatePlayerWithItemTags(event.getPlayer()), 20L);
    }

    public void updatePlayerWithItemTags(@NotNull Player player) {

        ItemStack mainhand = player.getInventory().getItemInMainHand();
        ItemMeta mainhandMeta =  mainhand.getItemMeta();

        ItemStack offhand = player.getInventory().getItemInOffHand();
        ItemMeta offhandMeta =  offhand.getItemMeta();

        PersistentDataContainer itemPDC;
        PersistentDataContainer playerPDC = player.getPersistentDataContainer();

        // Remove the tag from the player
        itemMagnetWielders.remove(player);
        player.getPersistentDataContainer().remove(TreasureItemKeys.itemMagnet);

        // If the player has an item in their mainhand
        if (mainhandMeta != null) {
            itemPDC = mainhandMeta.getPersistentDataContainer();
            // If the item has the specified ItemKey tag
            if (itemPDC.has(TreasureItemKeys.itemMagnet)) equipMagnetItem(player, playerPDC);
        }

        // If the player has an item in their offhand
        if (offhandMeta != null) {
            itemPDC = offhandMeta.getPersistentDataContainer();
            // If the item has the specified ItemKey tag
            if (itemPDC.has(TreasureItemKeys.itemMagnet)) equipMagnetItem(player, playerPDC);
        }

        // Remove the tag from the player
        expMagnetWielders.remove(player);
        player.getPersistentDataContainer().remove(TreasureItemKeys.expMagnet);

        // If the player has an item in their mainhand
        if (mainhandMeta != null) {
            itemPDC = mainhandMeta.getPersistentDataContainer();
            // If the item has the specified ItemKey tag
            if (itemPDC.has(TreasureItemKeys.expMagnet)) equipMagnetExp(player, playerPDC);
        }

        // If the player has an item in their offhand
        if (offhandMeta != null) {
            itemPDC = offhandMeta.getPersistentDataContainer();
            // If the item has the specified ItemKey tag
            if (itemPDC.has(TreasureItemKeys.expMagnet)) equipMagnetExp(player, playerPDC);
        }

    }

    private void equipMagnetItem(Player player, @NotNull PersistentDataContainer playerPDC) {
        itemMagnetWielders.add(player);
        playerPDC.set(TreasureItemKeys.itemMagnet, PersistentDataType.STRING, "1");
    }

    private void equipMagnetExp(Player player, @NotNull PersistentDataContainer playerPDC) {
        expMagnetWielders.add(player);
        playerPDC.set(TreasureItemKeys.expMagnet, PersistentDataType.STRING, "1");
    }

    private static void performAbilities() {
        performAbility(itemMagnetWielders, EntityType.DROPPED_ITEM.getEntityClass());
        performAbility(expMagnetWielders, EntityType.EXPERIENCE_ORB.getEntityClass());
    }

    private static void performAbility(@NotNull List<Player> wielders, Class<? extends Entity> entityType) {

        if (wielders.isEmpty()) return;
        // Iterate over every wielding player in the list
        for (Player player : wielders) {

            Location location = player.getLocation();

            var entities = location.getNearbyEntitiesByType(entityType, 5);
            if (entities.isEmpty()) continue;

            // Iterate over all nearby entities
            for (var entity : entities) {
                // Added safeguard for item display plugins
                if (entity instanceof Item item) if (!item.canPlayerPickup()) return;
                // Teleport targeted entities towards the wielding player
                entity.teleportAsync(location, PlayerTeleportEvent.TeleportCause.COMMAND);
            }
        }

    }

}
