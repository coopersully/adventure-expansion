package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.ItemKeys;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CommandCleanse implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "cleanse")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to cleanse items.");
            return false;
        }

        ItemStack mainItem = player.getInventory().getItemInMainHand();
        ItemMeta mainItemItemMeta = mainItem.getItemMeta();

        if (mainItemItemMeta == null) {
            sender.sendMessage(ChatColor.RED + "You must hold an item in your hand.");
            return false;
        }

        PersistentDataContainer persistentDataContainer = mainItemItemMeta.getPersistentDataContainer();
        var attributeModifiers = mainItem.getItemMeta().getAttributeModifiers();

        if (persistentDataContainer.has(ItemKeys.cleansed) || attributeModifiers == null) {
            sender.sendMessage(ChatColor.RED + "You cannot cleanse this item.");
            return false;
        }

        mainItemItemMeta.getAttributeModifiers().forEach((attribute, attributeModifier) -> mainItemItemMeta.removeAttributeModifier(attribute));
        persistentDataContainer.set(ItemKeys.cleansed, PersistentDataType.STRING, "1");
        mainItem.setItemMeta(mainItemItemMeta);
        sender.sendMessage(ChatColor.GREEN + "You cleansed this item.");
        return true;
    }

}
