package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import me.coopersully.rpgloot.rpgloot.listeners.TreasureView;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.*;

public class CommandTreasure implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (args.length == 0) {
            noteError(player, "Usage: /treasure <view|list|give> [item-id]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "give":
                return handleGive(player, args);
            case "list":
                return handleList(player, args);
            case "view":
                return handleView(player, args);
            default:
                noteError(player, "Invalid subcommand. Usage: /treasure <view|list|give> [item-id]");
                return true;
        }
    }

    public static boolean handleGive(Player player, String[] args) {
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "givetreasure")) {
            noteError(player, "You don't have permission to spawn treasure items.");
            return true;
        }

        if (args.length != 2) {
            noteError(player, "Usage: /treasure give <item-id>");
            return true;
        }

        int itemId;
        try {
            itemId = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            noteError(player, "That's not a valid item id. Try /treasure list for all item ids.");
            return true;
        }

        ItemStack itemStack = TreasureItem.TREASURE_ITEMS.get(itemId);
        if (itemStack == null) {
            noteError(player, "That item id does not exist. Try /treasure list for all item ids.");
            return true;
        }

        player.getInventory().addItem(itemStack);
        noteSuccess(player, "You received " + itemStack.getAmount() + "x " + ((TextComponent) itemStack.getItemMeta().displayName()).content() + ".");
        return true;
    }

    private boolean handleList(Player player, String[] args) {
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "listtreasure")) {
            noteError(player, "You don't have permission to list treasure items.");
            return true;
        }

        List<ItemStack> treasureItems = TreasureItem.TREASURE_ITEMS;
        for (int i = 0; i < treasureItems.size(); i++) {
            ItemStack item = treasureItems.get(i);
            if (item == null) continue;
            String displayName = ((TextComponent) item.getItemMeta().displayName()).content();
            noteInfo(player, i + " - " + displayName);
        }

        return true;
    }

    private boolean handleView(Player player, String[] args) {
        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "viewtreasure")) {
            noteError(player, "You don't have permission to view treasure items.");
            return true;
        }

        TreasureView.openInventoryForPage(player, 0); // Open the first page
        return true;
    }

}
