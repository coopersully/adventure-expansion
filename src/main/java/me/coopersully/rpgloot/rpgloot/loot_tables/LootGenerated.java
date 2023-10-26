package me.coopersully.rpgloot.rpgloot.loot_tables;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.loot.LootContext;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LootGenerated implements Listener {

    @EventHandler
    public void onLootGenerate(@NotNull LootGenerateEvent event) {
        String lt = event.getLootTable().toString();
        @NotNull LootContext loot = event.getLootContext();
        switch (lt) {
            case "minecraft:chests/abandoned_mineshaft":
                break;
            case "minecraft:chests/bastion_bridge":
                break;
            case "minecraft:chests/bastion_hoglin_stable":
                break;
            case "minecraft:chests/bastion_other":
                break;
            case "minecraft:chests/bastion_treasure":
                break;
            case "minecraft:chests/desert_pyramid":
                break;
            case "minecraft:chests/jungle_temple":
                break;
            case "minecraft:chests/pillager_outpost":
                break;
            case "minecraft:chests/simple_dungeon":
                break;
            case "minecraft:chests/woodland_mansion":
                break;
        }
    }

    private void rollNoble(@NotNull LootContext loot) {
        LootTableLoader.noble.populateLoot(new Random(), loot);
    }

    private void rollTraveler(@NotNull LootContext loot) {
        LootTableLoader.traveler.populateLoot(new Random(), loot);
    }

}
