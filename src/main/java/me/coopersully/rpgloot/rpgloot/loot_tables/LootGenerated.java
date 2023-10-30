package me.coopersully.rpgloot.rpgloot.loot_tables;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LootGenerated implements Listener {

    @EventHandler
    public void onLootGenerate(@NotNull LootGenerateEvent event) {
        String lootTable = event.getLootTable().toString();
        List<ItemStack> loot = new ArrayList<>(event.getLoot());

        switch (lootTable) {
            case "minecraft:chests/bastion_hoglin_stable" -> rollTraveler(loot);
            case "minecraft:chests/bastion_treasure" -> rollTraveler(loot);
            case "minecraft:chests/pillager_outpost" -> rollWandofIndecision(loot);
            case "minecraft:chests/woodland_mansion" -> rollWandofIndecision(loot);
            case "minecraft:chests/nether_bridge" -> rollCalamity(loot);
        }
        event.setLoot(loot);
    }

    private void rollTraveler(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(20)) return;

        Random random = new Random();
        switch (random.nextInt(6)) {
            case 0 -> loot.add(TreasureItem.TRAVELERS_HELMET);
            case 1 -> loot.add(TreasureItem.TRAVELERS_CHESTPLATE);
            case 2 -> loot.add(TreasureItem.TRAVELERS_LEGGINGS);
            case 3 -> loot.add(TreasureItem.TRAVELERS_BOOTS);
            case 4 -> loot.add(TreasureItem.TRAVELERS_SWORD);
            case 5 -> loot.add(TreasureItem.TRAVELERS_PICKAXE);
        }
    }

    private void rollWandofIndecision(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(10)) return;

        loot.add(TreasureItem.WAND_OF_INDECISION);
    }

    private void rollCalamity(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(5)) return;

        loot.add(TreasureItem.CALAMITY_SWORD);
    }

}
