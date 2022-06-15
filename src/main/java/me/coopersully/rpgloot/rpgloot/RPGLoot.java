package me.coopersully.rpgloot.rpgloot;

import me.coopersully.rpgloot.rpgloot.commands.Cleanse;
import me.coopersully.rpgloot.rpgloot.commands.Meta;
import me.coopersully.rpgloot.rpgloot.commands.Nightmare;
import me.coopersully.rpgloot.rpgloot.commands.TradesCommand;
import me.coopersully.rpgloot.rpgloot.config.Trades;
import me.coopersully.rpgloot.rpgloot.entities.CaveTraders;
import me.coopersully.rpgloot.rpgloot.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public final class RPGLoot extends JavaPlugin {

    private static RPGLoot plugin;
    public static final String permissionPrefix = "crpg.";
    private File trades;
    private FileConfiguration tradesConfig;

    public static RPGLoot getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Instance for accessing main class
        plugin = this;

        // Configuration files
        createTradesConfiguration();
        Trades.load();

        // Register all event listeners
        getServer().getPluginManager().registerEvents(new Armor(), this);
        getServer().getPluginManager().registerEvents(new PickupExp(), this);
        getServer().getPluginManager().registerEvents(new RightClick(), this);
        getServer().getPluginManager().registerEvents(new SculkAbility(), this);
        getServer().getPluginManager().registerEvents(new Magnets(), this);
        getServer().getPluginManager().registerEvents(new Bows(), this);
        getServer().getPluginManager().registerEvents(new ArrowLand(), this);
        getServer().getPluginManager().registerEvents(new MinersHat(), this);

        // Entity spawn controllers
        new CaveTraders();

        System.out.println(ChatColor.GREEN + " __        __   __   ___ .  __      __   __   __           __   __  ___ " + ChatColor.RESET);
        System.out.println(ChatColor.GREEN + "/  ` |  | |__) /__` |__  ' /__`    |__) |__) / _`    |    /  \\ /  \\  |"  + ChatColor.RESET);
        System.out.println(ChatColor.GREEN + "\\__, \\__/ |  \\ .__/ |___   .__/    |  \\ |    \\__>    |___ \\__/ \\__/  |"  + ChatColor.RESET);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {

        switch (label) {
            case "meta" -> {
                Meta.command(sender, args);
                return true;
            }
            case "cleanse" -> {
                Cleanse.command(sender);
                return true;
            }
            case "nightmare", "nightmarify" -> {
                Nightmare.command(sender, args);
                return true;
            }
            case "trades" -> {
                TradesCommand.command(sender, args);
                return true;
            }
        }
        return false;
    }

    public FileConfiguration getTradesConfig() {
        return this.tradesConfig;
    }

    private void createTradesConfiguration() {
        trades = new File(getDataFolder(), "trades/overworld.yml");
        if (!trades.exists()) {
            trades.getParentFile().mkdirs();
            saveResource("trades/overworld.yml", false);
        }

        tradesConfig = new YamlConfiguration();
        try {
            tradesConfig.load(trades);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
