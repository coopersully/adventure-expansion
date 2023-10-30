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

import java.util.List;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.*;

public class CommandList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "listtreasure")) {
            noteError(player, "You don't have permission to list treasure items.");
            return false;
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
}
