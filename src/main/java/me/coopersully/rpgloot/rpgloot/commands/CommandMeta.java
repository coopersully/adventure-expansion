package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteError;

public class CommandMeta implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = CoreUtils.getPlayerFromSender(sender);
        if (player == null) return false;

        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "meta")) {
            noteError(player, "You don't have permission to check item meta.");
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
            noteError(player, "No data is attached to this " + object + ".");
            return false;
        }

        StringBuilder metaKeys = new StringBuilder();
        for (NamespacedKey key : PDC.getKeys()) {
            if (key == null) {
                sender.sendMessage(Component.text("? = ", NamedTextColor.YELLOW)
                        .append(Component.text("?", NamedTextColor.AQUA)));
                continue;
            }
            metaKeys.append(key.value()).append(", ");
        }
        if (metaKeys.length() > 2) {
            metaKeys.deleteCharAt(metaKeys.length() - 1);
            metaKeys.deleteCharAt(metaKeys.length() - 1);
        }

        Component message = Component.text("The current ", NamedTextColor.YELLOW)
                .append(Component.text(object, NamedTextColor.AQUA))
                .append(Component.text(" has the following meta: ", NamedTextColor.YELLOW))
                .append(Component.text(metaKeys.toString(), NamedTextColor.AQUA));

        sender.sendMessage(message);
        return true;
    }

}
