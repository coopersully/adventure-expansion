package me.coopersully.rpgloot.rpgloot.loot_tables;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class LootRollers {

    public static void rollTraveler(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(2)) return;

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

    public static void rollMagnets(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(5)) return;

        Random random = new Random();
        switch (random.nextInt(2)) {
            case 0 -> loot.add(TreasureItem.ITEM_MAGNET);
            case 1 -> loot.add(TreasureItem.EXP_MAGNET);
        }
    }

    public static void rollNightmareFuel(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(0.00000000001)) return;

        loot.add(TreasureItem.NIGHTMARE_FUEL);
    }

    public static void rollTraderHat(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(20)) return;

        loot.add(TreasureItem.TRADER_HAT);
    }

    public static void rollEvokerBow(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(5)) return;

        loot.add(TreasureItem.EVOKER_BOW);
    }

    public static void rollBountyBow(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(0.1)) return;

        loot.add(TreasureItem.BOUNTY_BOW);
    }

    public static void rollWandofIndecision(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(10)) return;

        loot.add(TreasureItem.WAND_OF_INDECISION);
    }

    public static void rollCalamity(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(5)) return;

        loot.add(TreasureItem.CALAMITY_SWORD);
    }

    public static void rollSpawnEggs(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(0.01)) return;

        Random random = new Random();
        switch (random.nextInt(56)) {
            case 0 -> loot.add(TreasureItem.BEE_EGG);
            case 1 -> loot.add(TreasureItem.BLAZE_EGG);
            case 2 -> loot.add(TreasureItem.CAMEL_EGG);
            case 3 -> loot.add(TreasureItem.CAT_EGG);
            case 4 -> loot.add(TreasureItem.CAVE_SPIDER_EGG);
            case 5 -> loot.add(TreasureItem.CHICKEN_EGG);
            case 6 -> loot.add(TreasureItem.COD_EGG);
            case 7 -> loot.add(TreasureItem.COW_EGG);
            case 8 -> loot.add(TreasureItem.CREEPER_EGG);
            case 9 -> loot.add(TreasureItem.DOLPHIN_EGG);
            case 10 -> loot.add(TreasureItem.DONKEY_EGG);
            case 11 -> loot.add(TreasureItem.DROWNED_EGG);
            case 12 -> loot.add(TreasureItem.ENDERMAN_EGG);
            case 13 -> loot.add(TreasureItem.FOX_EGG);
            case 14 -> loot.add(TreasureItem.FROG_EGG);
            case 15 -> loot.add(TreasureItem.GLOW_SQUID_EGG);
            case 16 -> loot.add(TreasureItem.GOAT_EGG);
            case 17 -> loot.add(TreasureItem.GUARDIAN_EGG);
            case 18 -> loot.add(TreasureItem.HOGLIN_EGG);
            case 19 -> loot.add(TreasureItem.HORSE_EGG);
            case 20 -> loot.add(TreasureItem.HUSK_EGG);
            case 21 -> loot.add(TreasureItem.IRON_GOLEM_EGG);
            case 22 -> loot.add(TreasureItem.LLAMA_EGG);
            case 23 -> loot.add(TreasureItem.MOOSHROOM_EGG);
            case 24 -> loot.add(TreasureItem.MAGMA_CUBE_EGG);
            case 25 -> loot.add(TreasureItem.MULE_EGG);
            case 26 -> loot.add(TreasureItem.PANDA_EGG);
            case 27 -> loot.add(TreasureItem.PARROT_EGG);
            case 28 -> loot.add(TreasureItem.PIG_EGG);
            case 29 -> loot.add(TreasureItem.PIGLIN_EGG);
            case 30 -> loot.add(TreasureItem.PIGLIN_BRUTE_EGG);
            case 31 -> loot.add(TreasureItem.PILLAGER_EGG);
            case 32 -> loot.add(TreasureItem.POLAR_BEAR_EGG);
            case 33 -> loot.add(TreasureItem.PUFFER_FISH_EGG);
            case 34 -> loot.add(TreasureItem.SALMON_EGG);
            case 35 -> loot.add(TreasureItem.RABBIT_EGG);
            case 36 -> loot.add(TreasureItem.SHEEP_EGG);
            case 37 -> loot.add(TreasureItem.SHULKER_EGG);
            case 38 -> loot.add(TreasureItem.SILVERFISH_EGG);
            case 39 -> loot.add(TreasureItem.SKELETON_EGG);
            case 40 -> loot.add(TreasureItem.SLIME_EGG);
            case 41 -> loot.add(TreasureItem.SNIFFER_EGG);
            case 42 -> loot.add(TreasureItem.SNOW_GOLEM_EGG);
            case 43 -> loot.add(TreasureItem.SPIDER_EGG);
            case 44 -> loot.add(TreasureItem.SQUID_EGG);
            case 45 -> loot.add(TreasureItem.STRAY_EGG);
            case 46 -> loot.add(TreasureItem.TADPOLE_EGG);
            case 47 -> loot.add(TreasureItem.TROPICAL_FISH_EGG);
            case 48 -> loot.add(TreasureItem.TURTLE_EGG);
            case 49 -> loot.add(TreasureItem.VILLAGER_EGG);
            case 50 -> loot.add(TreasureItem.WITHER_SKELETON_EGG);
            case 51 -> loot.add(TreasureItem.WOLF_EGG);
            case 52 -> loot.add(TreasureItem.ZOGLIN_EGG);
            case 53 -> loot.add(TreasureItem.ZOMBIE_EGG);
            case 54 -> loot.add(TreasureItem.ZOMBIE_VILLAGER_EGG);
            case 55 -> loot.add(TreasureItem.AXOLOTL_EGG);
        }
    }

    public static void rollSculkGear(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(25)) return;

        loot.add(TreasureItem.SCULK_BOOTS);
    }

    public static void rollDragonhide(@NotNull List<ItemStack> loot) {
        if (!CoreUtils.rollChances(10)) return;

        Random random = new Random();
        switch (random.nextInt(5)) {
            case 0 -> loot.add(TreasureItem.DRAGONHIDE_HELMET);
            case 1 -> loot.add(TreasureItem.DRAGONHIDE_CHESTPLATE);
            case 2 -> loot.add(TreasureItem.DRAGONHIDE_LEGGINGS);
            case 3 -> loot.add(TreasureItem.DRAGONHIDE_BOOTS);
        }
    }
}
