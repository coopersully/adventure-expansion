package me.coopersully.rpgloot.rpgloot;

import me.coopersully.rpgloot.rpgloot.commands.*;
import me.coopersully.rpgloot.rpgloot.config.ConfigTrades;
import me.coopersully.rpgloot.rpgloot.entities.CaveTraders;
import me.coopersully.rpgloot.rpgloot.listeners.*;
import me.coopersully.rpgloot.rpgloot.loot_tables.LootGenerated;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem.checkTreasureItems;
import static me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem.loadTreasureItems;

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

        // Load all configuration files
        createTradesConfiguration();
        ConfigTrades.load();

        saveDefaultConfig();
        reloadDefaultConfig();

        // Load all treasure items
        loadTreasureItems();
        checkTreasureItems();

        // Register all event listeners
        getServer().getPluginManager().registerEvents(new Armor(), this);
        getServer().getPluginManager().registerEvents(new PickupExp(), this);
        getServer().getPluginManager().registerEvents(new RightClick(), this);
        getServer().getPluginManager().registerEvents(new SculkAbility(), this);
        getServer().getPluginManager().registerEvents(new Magnets(), this);
        getServer().getPluginManager().registerEvents(new Bows(), this);
        getServer().getPluginManager().registerEvents(new ArrowLand(), this);
        getServer().getPluginManager().registerEvents(new MinersHat(), this);
        getServer().getPluginManager().registerEvents(new LootGenerated(), this);
        getServer().getPluginManager().registerEvents(new AttachLeadToVillager(), this);
        getServer().getPluginManager().registerEvents(new UseWandOfIndecision(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandConvert(), this);
        getServer().getPluginManager().registerEvents(new WanderingTraderKilled(), this);

        // Register all commands
        try {
            getCommand("cleanse").setExecutor(new CommandCleanse());
            getCommand("meta").setExecutor(new CommandMeta());
            getCommand("nightmare").setExecutor(new CommandNightmare());
            getCommand("trades").setExecutor(new CommandTrades());
            getCommand("givetreasure").setExecutor(new CommandGive());
            getCommand("listtreasure").setExecutor(new CommandList());
        } catch (NullPointerException e) {
            getLogger().severe("Failed to register command(s); please contact the developer.");
        }

        // Schedule runtime tasks
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(getPlugin(), CaveTraders::randomlySpawnVillager, 1200);
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
            getPlugin().getLogger().severe("Failed to load trades config: " + tradesConfig.getName());
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
