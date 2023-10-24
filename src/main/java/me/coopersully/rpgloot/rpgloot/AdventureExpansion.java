package me.coopersully.rpgloot.rpgloot;

import me.coopersully.rpgloot.rpgloot.commands.CommandCleanse;
import me.coopersully.rpgloot.rpgloot.commands.CommandMeta;
import me.coopersully.rpgloot.rpgloot.commands.CommandNightmare;
import me.coopersully.rpgloot.rpgloot.commands.CommandTrades;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import me.coopersully.rpgloot.rpgloot.entities.CaveTraders;
import me.coopersully.rpgloot.rpgloot.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class AdventureExpansion extends JavaPlugin {

    private static AdventureExpansion plugin;
    public static final String permissionPrefix = "crpg.";
    private FileConfiguration tradesConfig;

    // Configuration variables
    public static boolean debug;

    public static AdventureExpansion getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Instance for accessing main class
        plugin = this;

        // Configuration files
        createTradesConfiguration();
        ConfigTrades.load();

        saveDefaultConfig();
        reloadDefaultConfig();

        // Register all event listeners
        getServer().getPluginManager().registerEvents(new Armor(), this);
        getServer().getPluginManager().registerEvents(new PickupExp(), this);
        getServer().getPluginManager().registerEvents(new RightClick(), this);
        getServer().getPluginManager().registerEvents(new SculkAbility(), this);
        getServer().getPluginManager().registerEvents(new Magnets(), this);
        getServer().getPluginManager().registerEvents(new Bows(), this);
        getServer().getPluginManager().registerEvents(new ArrowLand(), this);
        getServer().getPluginManager().registerEvents(new MinersHat(), this);

        // Register all commands
        try {
            getCommand("cleanse").setExecutor(new CommandCleanse());
            getCommand("meta").setExecutor(new CommandMeta());
            getCommand("nightmare").setExecutor(new CommandNightmare());
            getCommand("trades").setExecutor(new CommandTrades());
        } catch (NullPointerException e) {
            System.out.println(ChatColor.RED + "Failed to register command(s); please contact the developer.");
        }

        // Entity spawn controllers
        new CaveTraders();

        // Send plugin loaded ASCII-art message
        System.out.println(ChatColor.GREEN + " __        __   __   ___ .  __      __   __   __           __   __  ___ " + ChatColor.RESET);
        System.out.println(ChatColor.GREEN + "/  ` |  | |__) /__` |__  ' /__`    |__) |__) / _`    |    /  \\ /  \\  |"  + ChatColor.RESET);
        System.out.println(ChatColor.GREEN + "\\__, \\__/ |  \\ .__/ |___   .__/    |  \\ |    \\__>    |___ \\__/ \\__/  |"  + ChatColor.RESET);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FileConfiguration getTradesConfig() {
        return this.tradesConfig;
    }

    private void createTradesConfiguration() {
        File trades = new File(getDataFolder(), "trades/overworld.yml");
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

    public static void reloadDefaultConfig() {
        getPlugin().reloadConfig();
        getPlugin().saveDefaultConfig();

        // Settings section
        debug = getPlugin().getConfig().getBoolean("debug");
    }
}
