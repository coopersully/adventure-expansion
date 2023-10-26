package me.coopersully.rpgloot.rpgloot.commands;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.jetbrains.annotations.NotNull;

import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteError;
import static me.coopersully.rpgloot.rpgloot.CoreUtils.noteSuccess;

public class CommandTrades implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission(AdventureExpansion.permissionPrefix + "trades")) {
            noteError(sender, "You don't have permission to configure trades.");
            return false;
        }

        if (args.length == 0) {
            noteError(sender, "Usage: /trades <reload|spawn>");
            return false;
        }

        String operation = args[0].toLowerCase().strip();
        switch (operation) {
            case "reload", "rel":
                noteSuccess(sender, "All trades reloaded from config file(s).");
                ConfigTrades.load();
                return true;
            case "spawn":
                Player player = CoreUtils.getPlayerFromSender(sender);
                if (player == null) return false;

                World world = player.getWorld();
                Location location = player.getLocation();

                noteSuccess(sender, "Spawned a cave trader at your current position.");
                WanderingTrader wanderingTrader = world.spawn(location, WanderingTrader.class);
                ConfigTrades.giveTrades(wanderingTrader);
                return true;
            default:
                noteError(sender, "Usage: /trades <reload|spawn>");
                return false;
        }

    }

}
