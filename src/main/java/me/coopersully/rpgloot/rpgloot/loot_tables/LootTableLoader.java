package me.coopersully.rpgloot.rpgloot.loot_tables;

import com.google.gson.Gson;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LootTableLoader {

    private final JavaPlugin plugin;
    private final Gson gson = new Gson();

    public static LootTable calamity, dragonhide, greed, health, noble, spawn_eggs, stew, awakened, eternal, raider, traveler;

    public LootTableLoader(JavaPlugin plugin) {
        this.plugin = plugin;
        loadLootTables();
    }

    public void loadLootTables() {
        File lootTablesDir = new File(plugin.getDataFolder(), "loot_tables");
        if (!lootTablesDir.exists() || !lootTablesDir.isDirectory()) {
            plugin.getLogger().severe("Loot tables directory not found!");
            return;
        }

        calamity = loadLootTable(lootTablesDir, "general/calamity.json");
        dragonhide = loadLootTable(lootTablesDir, "general/dragonhide.json");
        greed = loadLootTable(lootTablesDir, "general/greed.json");
        health = loadLootTable(lootTablesDir, "general/health.json");
        noble = loadLootTable(lootTablesDir, "general/noble.json");
        spawn_eggs = loadLootTable(lootTablesDir, "general/spawn_eggs.json");
        stew = loadLootTable(lootTablesDir, "general/stew.json");

        awakened = loadLootTable(lootTablesDir, "quests/awakened.json");
        eternal = loadLootTable(lootTablesDir, "quests/eternal.json");
        raider = loadLootTable(lootTablesDir, "quests/raider.json");
        traveler = loadLootTable(lootTablesDir, "quests/traveler.json");
    }

    private @Nullable LootTable loadLootTable(File directory, String fileName) {
        File lootTableFile = new File(directory, fileName);
        if (!lootTableFile.exists()) {
            plugin.getLogger().severe("Loot table file not found: " + fileName);
            return null;
        }

        try (FileReader reader = new FileReader(lootTableFile)) {
            return gson.fromJson(reader, LootTable.class);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to load loot table: " + fileName);
            e.printStackTrace();
            return null;
        }
    }

}
