package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.commands.CommandTreasure;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class TreasureView implements Listener {

    private static final Map<UUID, Integer> playerPages = new HashMap<>();
    private static final HashSet<UUID> openTreasureViews = new HashSet<>();

    public static boolean isTreasureViewOpen(UUID uuid) {
        return openTreasureViews.contains(uuid);
    }

    public static void setTreasureViewOpen(UUID uuid, boolean open) {
        if (open) openTreasureViews.add(uuid);
        else {
            openTreasureViews.remove(uuid);
            playerPages.remove(uuid); // Clean up page tracking when inventory is closed
        }
    }

    public static void openInventoryForPage(Player player, int page) {
        int itemsPerPage = 36; // Items fit in 4 rows (9 slots per row)
        int start = page * itemsPerPage;
        int end = start + itemsPerPage;
        Inventory inventory = Bukkit.createInventory(null, 54, Component.text("Unique Treasure Items"));

        List<ItemStack> paginatedItems = TreasureItem.TREASURE_ITEMS.subList(Math.min(start, TreasureItem.TREASURE_ITEMS.size()), Math.min(end, TreasureItem.TREASURE_ITEMS.size()));

        for (ItemStack item : paginatedItems) {
            inventory.addItem(item.clone()); // Clone items to prevent manipulation
        }

        initializeNavigationControls(inventory, page, TreasureItem.TREASURE_ITEMS.size() > end);
        player.openInventory(inventory);
        TreasureView.setTreasureViewOpen(player.getUniqueId(), true);
        TreasureView.setPage(player.getUniqueId(), page);
    }

    private static void initializeNavigationControls(Inventory inventory, int currentPage, boolean hasNextPage) {
        ItemStack previousPageItem = new ItemStack(Material.ARROW);
        ItemStack nextPageItem = new ItemStack(Material.ARROW);

        ItemMeta prevMeta = previousPageItem.getItemMeta();
        prevMeta.displayName(Component.text("Previous Page").color(NamedTextColor.GREEN));
        previousPageItem.setItemMeta(prevMeta);

        ItemMeta nextMeta = nextPageItem.getItemMeta();
        nextMeta.displayName(Component.text("Next Page").color(NamedTextColor.GREEN));
        nextPageItem.setItemMeta(nextMeta);

        if (currentPage > 0) {
            inventory.setItem(45, previousPageItem);
        }
        if (hasNextPage) {
            inventory.setItem(53, nextPageItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (!isTreasureViewOpen(player.getUniqueId())) return;
        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;

        int currentPage = getPage(player.getUniqueId());

        // Handle navigation items/buttons
        if (clickedItem.getType() == Material.ARROW) {
            switch (event.getSlot()) {
                case 45 -> openInventoryForPage(player, currentPage - 1);
                case 53 -> openInventoryForPage(player, currentPage + 1);
            }
            return;
        }

        // Handle treasure giving if player has the appropriate permission
        if (player.hasPermission(AdventureExpansion.permissionPrefix + "givetreasure")) {
            int previousSlots = playerPages.get(player.getUniqueId()) * 36;
            int slot = event.getSlot();
            String slotWeighted = String.valueOf(previousSlots + slot);
            CommandTreasure.handleGive(player, new String[]{"give", slotWeighted});
        }
    }

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        setTreasureViewOpen(player.getUniqueId(), false);
    }

    public static void setPage(UUID uuid, int page) {
        playerPages.put(uuid, page);
    }

    public static int getPage(UUID uuid) {
        return playerPages.getOrDefault(uuid, 0);
    }
}
