package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteError;
import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteSuccess;

public class CommandGive implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "givetreasure")) {
            noteError(player, "You don't have permission to spawn treasure items.");
            return false;
        }

        if (args.length != 1) {
            noteError(player, "Usage: /givetreasure <item-name>");
            return true;
        }

        String itemName = args[0].toLowerCase();
        for (ItemStack item : TreasureItem.TREASURE_ITEMS) {
            if (item == null) continue;
            String displayName = ((TextComponent) item.getItemMeta().displayName()).content().toLowerCase();
            if (displayName.contains(itemName)) {
                player.getInventory().addItem(item);
                noteSuccess(player, "You received " + item.getAmount() + "x " + displayName + ".");
                return true;
            }
        }

        noteError(player, "Treasure item not found!");
        return true;
    }
}
