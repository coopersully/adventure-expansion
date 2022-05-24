package me.coopersully.rpgloot.rpgloot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand {

    public static Player getPlayerFromSender(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "This command must be executed via a player.");
            return null;
        }
        return player;
    }
}
