package me.coopersully.rpgloot.rpgloot.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAdventureExpansion implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Available commands:").color(NamedTextColor.GRAY));
            sender.sendMessage(Component.text("/ae - ").color(NamedTextColor.GREEN)
                    .append(Component.text("Shows all commands and their descriptions.").color(NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("/ae github - ").color(NamedTextColor.GREEN)
                    .append(Component.text("Click to open the GitHub repository.").color(NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("/ae wiki - ").color(NamedTextColor.GREEN)
                    .append(Component.text("Click to open the wiki page.").color(NamedTextColor.GRAY)));
            sender.sendMessage(Component.text("/treasure <view|list|give> [item-id] - ").color(NamedTextColor.GREEN)
                    .append(Component.text("View & grant all existing treasure items.").color(NamedTextColor.GRAY)));
            return true;
        }

        if (args[0].equalsIgnoreCase("github")) {
            if (sender instanceof Player player) {
                player.sendMessage(createClickableMessage("Click here to open the GitHub repository.", "https://github.com/coopersully/adventure-expansion"));
            } else {
                sender.sendMessage(Component.text("GitHub command can only be used by players.").color(NamedTextColor.GRAY));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("wiki")) {
            if (sender instanceof Player player) {
                player.sendMessage(createClickableMessage("Click here to open the wiki page.", "https://coopersully.notion.site/Adventure-Expansion-1c1ddd0a38b441d991830e7cbcda7aee"));
            } else {
                sender.sendMessage(Component.text("Wiki command can only be used by players.").color(NamedTextColor.GRAY));
            }
            return true;
        }

        sender.sendMessage(Component.text("Invalid command. Use /adventureexpansion for help.").color(NamedTextColor.GRAY));
        return false;
    }

    private Component createClickableMessage(String message, String url) {
        return Component.text(message)
                .color(NamedTextColor.GREEN)
                .decorate(TextDecoration.UNDERLINED)
                .clickEvent(ClickEvent.openUrl(url))
                .hoverEvent(HoverEvent.showText(Component.text("Click to visit").color(NamedTextColor.GREEN)));
    }
}
