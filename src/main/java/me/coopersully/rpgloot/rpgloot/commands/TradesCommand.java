package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.RPGLoot;
import me.coopersully.rpgloot.rpgloot.config.Trades;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class TradesCommand {

    public static void command(@NotNull CommandSender sender, String[] args) {

        if (!sender.hasPermission(RPGLoot.permissionPrefix + "trades")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to configure trades.");
            return;
        }

        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /trades <reload|spawn>");
            return;
        }

        String operation = args[0].toLowerCase().strip();
        if (operation.contains("reload")) {
            sender.sendMessage(ChatColor.YELLOW + "All trades reloaded from config file(s).");
            Trades.load();
            return;
        }
        if (operation.contains("spawn")) {

            Player player = PlayerCommand.getPlayerFromSender(sender);
            if (player == null) return;

            World world = player.getWorld();
            Location location = player.getLocation();

            sender.sendMessage(ChatColor.YELLOW + "Spawned a cave trader at your current position.");
            WanderingTrader wanderingTrader = world.spawn(location, WanderingTrader.class);
            Trades.giveTrades(wanderingTrader);
            return;
        }

    }

}
