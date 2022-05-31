package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.RPGLoot;
import me.coopersully.rpgloot.rpgloot.entities.CaveTraders;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnTrader {

    public static void command(CommandSender sender) {

        Player player = PlayerCommand.getPlayerFromSender(sender);
        if (player == null) return;

        if (!player.hasPermission(RPGLoot.permissionPrefix + "spawntrader")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to spawn traders.");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW + "Successfully spawned " + ChatColor.AQUA + "1x Cave Trader");
        CaveTraders.spawnVillager(player.getLocation());
    }

}
