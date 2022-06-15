package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.HalaraRPG;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.NotNull;

public class CommandTrades implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission(HalaraRPG.permissionPrefix + "trades")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to configure trades.");
            return false;
        }

        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /trades <reload|spawn>");
            return false;
        }

        String operation = args[0].toLowerCase().strip();
        switch (operation) {
            case "reload", "rel":
                sender.sendMessage(ChatColor.YELLOW + "All trades reloaded from config file(s).");
                ConfigTrades.load();
                return true;
            case "spawn":
                Player player = CoreUtils.getPlayerFromSender(sender);
                if (player == null) return false;

                World world = player.getWorld();
                Location location = player.getLocation();

                sender.sendMessage(ChatColor.YELLOW + "Spawned a cave trader at your current position.");
                WanderingTrader wanderingTrader = world.spawn(location, WanderingTrader.class);
                ConfigTrades.giveTrades(wanderingTrader);
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "Usage: /trades <reload|spawn>");
                return false;
        }

    }

}
