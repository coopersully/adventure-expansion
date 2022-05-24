package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.RPGLoot;
import org.bukkit.ChatColor;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Cleanse {

    public static void command(CommandSender sender) {

        Player player = PlayerCommand.getPlayerFromSender(sender);
        if (player == null) return;

        if (!player.hasPermission(RPGLoot.permissionPrefix + "cleanse")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to cleanse items.");
            return;
        }

        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemItemMeta = mainItem.getItemMeta();

        if (mainItemItemMeta == null) {
            sender.sendMessage(ChatColor.RED + "You must hold an item in your hand.");
            return;
        }

        PersistentDataContainer persistentDataContainer = mainItemItemMeta.getPersistentDataContainer();
        var attributeModifiers = mainItem.getItemMeta().getAttributeModifiers();

        if (persistentDataContainer.has(ItemKeys.cleansed) || attributeModifiers == null) {
            sender.sendMessage(ChatColor.RED + "You cannot cleanse this item.");
            return;
        }

        mainItemItemMeta.getAttributeModifiers().forEach((attribute, attributeModifier) -> mainItemItemMeta.removeAttributeModifier(attribute));
        persistentDataContainer.set(ItemKeys.cleansed, PersistentDataType.STRING, "1");
        mainItem.setItemMeta(mainItemItemMeta);
        sender.sendMessage(ChatColor.GREEN + "You cleansed this item.");

    }

}
