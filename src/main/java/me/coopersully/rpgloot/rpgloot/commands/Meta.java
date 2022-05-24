package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.RPGLoot;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class Meta {

    public static void command(CommandSender sender) {

        Player player = PlayerCommand.getPlayerFromSender(sender);
        if (player == null) return;

        if (!player.hasPermission(RPGLoot.permissionPrefix + "meta")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to check item meta.");
            return;
        }

        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemMeta = mainItem.getItemMeta();

        if (mainItemMeta == null) {
            alertEmpty(sender, "You must hold an item to scan it's meta.");
            return;
        }

        PersistentDataContainer persistentDataContainer = mainItemMeta.getPersistentDataContainer();

        if (persistentDataContainer.getKeys().isEmpty()) {
            alertEmpty(sender, "No data is attached to this item.");
            return;
        }

        StringBuilder metaKeys = new StringBuilder();
        for (NamespacedKey key : persistentDataContainer.getKeys()) {
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
        sender.sendMessage(ChatColor.YELLOW + "The current item has the following meta: " + metaKeys);
    }

    public static void alertEmpty(CommandSender sender, String text) {

        sender.sendMessage(ChatColor.RED + text);
    }

}
