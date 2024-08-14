package me.coopersully.rpgloot.rpgloot.loot_tables;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InventoryLootGenerated implements Listener {

    @EventHandler
    public void onLootGenerate(@NotNull LootGenerateEvent event) {
        String lootTable = event.getLootTable().toString();
        List<ItemStack> loot = new ArrayList<>(event.getLoot());

        switch (lootTable) {
            case "minecraft:chests/bastion_other" -> {
                LootRollers.rollTraveler(loot);
                LootRollers.rollCommonSwiftstrikeSwords(loot);
                LootRollers.rollCommonTomahawks(loot);
                LootRollers.rollRareTomahawks(loot);
            }
            case "minecraft:chests/bastion_bridge" -> {
                LootRollers.rollTraveler(loot);
                LootRollers.rollRareTomahawks(loot);
            }
            case "minecraft:chests/bastion_hoglin_stable" -> {
                LootRollers.rollTraveler(loot);
                LootRollers.rollRareSwiftstrikeSwords(loot);
                LootRollers.rollRareTomahawks(loot);
            }
            case "minecraft:chests/bastion_treasure" -> {
                LootRollers.rollTraveler(loot);
                LootRollers.rollRareSwiftstrikeSwords(loot);
                LootRollers.rollCryrush(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/pillager_outpost" -> {
                LootRollers.rollWandofIndecision(loot);
                LootRollers.rollBountyBow(loot);
                LootRollers.rollLightrender(loot);
                LootRollers.rollCryrush(loot);
            }
            case "minecraft:chests/woodland_mansion" -> {
                LootRollers.rollWandofIndecision(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/nether_bridge" -> {
                LootRollers.rollCalamity(loot);
                LootRollers.rollLightrender(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/simple_dungeon" -> {
                LootRollers.rollSpawnEggs(loot);
                LootRollers.rollCommonSwiftstrikeSwords(loot);
                LootRollers.rollCommonTomahawks(loot);
                LootRollers.rollRareTomahawks(loot);
                LootRollers.rollLightrender(loot);
                LootRollers.rollCryrush(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/jungle_temple" -> {
                LootRollers.rollBountyBow(loot);
                LootRollers.rollCommonSwiftstrikeSwords(loot);
                LootRollers.rollRareSwiftstrikeSwords(loot);
                LootRollers.rollCommonTomahawks(loot);
                LootRollers.rollRareTomahawks(loot);
                LootRollers.rollLightrender(loot);
                LootRollers.rollCryrush(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/abandoned_mineshaft" -> {
                LootRollers.rollMagnets(loot);
                LootRollers.rollCommonTomahawks(loot);
                LootRollers.rollCommonSwiftstrikeSwords(loot);
                LootRollers.rollLightrender(loot);
            }
            case "minecraft:chests/end_city_treasure" -> {
                LootRollers.rollDragonhide(loot);
                LootRollers.rollGonk(loot);
                LootRollers.rollRareTomahawks(loot);
                LootRollers.rollRareSwiftstrikeSwords(loot);
                LootRollers.rollVelocity(loot);
            }
            case "minecraft:chests/shipwreck_treasure" -> {
                LootRollers.rollCommonSwiftstrikeSwords(loot);
                LootRollers.rollCommonTomahawks(loot);
                LootRollers.rollLightrender(loot);
            }
        }
        event.setLoot(loot);
    }

}
