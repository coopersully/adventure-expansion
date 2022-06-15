package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.HalaraRPG;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class CommandMeta implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (!player.hasPermission(HalaraRPG.permissionPrefix + "meta")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to check item meta.");
            return false;
        }

        StringBuilder arg = new StringBuilder();
        for (var line : args) arg.append(line);

        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemMeta = mainItem.getItemMeta();

        PersistentDataContainer PDC;
        String object;
        if (mainItemMeta == null || arg.toString().contains("p")) {
            object = "player";
            PDC = player.getPersistentDataContainer();
        } else {
            object = "item";
            PDC = mainItemMeta.getPersistentDataContainer();
        }

        if (PDC.getKeys().isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No data is attached to this " + object + ".");
            return false;
        }

        StringBuilder metaKeys = new StringBuilder();
        for (NamespacedKey key : PDC.getKeys()) {
            if (key == null) {
                sender.sendMessage(ChatColor.YELLOW + "? = " + ChatColor.AQUA + "?");
                continue;
            }
            metaKeys.append(ChatColor.AQUA);
            metaKeys.append(key.value());
            metaKeys.append(ChatColor.YELLOW);
            metaKeys.append(", ");
        }
        metaKeys.deleteCharAt(metaKeys.length() - 1);
        metaKeys.deleteCharAt(metaKeys.length() - 1);
        sender.sendMessage(ChatColor.YELLOW + "The current " + ChatColor.AQUA + object + ChatColor.YELLOW + " has the following meta: " + metaKeys);
        return true;
    }

}
