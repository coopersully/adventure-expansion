package me.coopersully.rpgloot.rpgloot.loot_tables;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryLootGenerated implements Listener {

    @EventHandler
    public void onLootGenerate(@NotNull LootGenerateEvent event) {
        String lootTable = event.getLootTable().toString();
        List<ItemStack> loot = new ArrayList<>(event.getLoot());

        switch (lootTable) {
            case "minecraft:chests/bastion_other" -> LootRollers.rollTraveler(loot);
            case "minecraft:chests/bastion_bridge" -> LootRollers.rollTraveler(loot);
            case "minecraft:chests/bastion_hoglin_stable" -> LootRollers.rollTraveler(loot);
            case "minecraft:chests/bastion_treasure" -> LootRollers.rollTraveler(loot);
            case "minecraft:chests/pillager_outpost" -> {
                LootRollers.rollWandofIndecision(loot);
                LootRollers.rollBountyBow(loot);
            }
            case "minecraft:chests/woodland_mansion" -> LootRollers.rollWandofIndecision(loot);
            case "minecraft:chests/nether_bridge" -> LootRollers.rollCalamity(loot);
            case "minecraft:chests/simple_dungeon" -> LootRollers.rollSpawnEggs(loot);
            case "minecraft:chests/jungle_temple" -> {
                LootRollers.rollBountyBow(loot);
            }
            case "minecraft:chests/abandoned_mineshaft" -> {
                LootRollers.rollMagnets(loot);
            }

        }
        event.setLoot(loot);
    }

}
