package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

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

        Component metaKeys = Component.empty();
        for (Iterator<NamespacedKey> iterator = PDC.getKeys().iterator(); iterator.hasNext(); ) {
            NamespacedKey key = iterator.next();
            if (key == null) {
                sender.sendMessage(Component.text("? = ", NamedTextColor.GRAY)
                        .append(Component.text("?", NamedTextColor.GREEN)));
                continue;
            }
            Component hoverInfo = Component.text("Namespace: ", NamedTextColor.GRAY)
                    .append(Component.text(key.getNamespace(), NamedTextColor.WHITE))
                    .append(Component.text("\nKey: ", NamedTextColor.GRAY))
                    .append(Component.text(key.getKey(), NamedTextColor.WHITE ))
                    .append(Component.text("\nHashcode: ", NamedTextColor.GRAY))
                    .append(Component.text(String.valueOf(key.hashCode()), NamedTextColor.WHITE));

            Component keyComponent = Component.text(key.value(), NamedTextColor.GREEN)
                    .hoverEvent(HoverEvent.showText(hoverInfo));

            metaKeys = metaKeys.append(keyComponent);
            if (iterator.hasNext()) {
                metaKeys = metaKeys.append(Component.text(", ", NamedTextColor.GRAY));
            }
        }

        Component message = Component.text("The current ", NamedTextColor.GRAY)
                .append(Component.text(object, NamedTextColor.GREEN))
                .append(Component.text(" has the following meta: ", NamedTextColor.GRAY))
                .append(metaKeys);

        sender.sendMessage(message);
        return true;
    }

}
