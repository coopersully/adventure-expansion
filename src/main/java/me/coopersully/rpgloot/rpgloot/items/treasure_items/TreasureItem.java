package me.coopersully.rpgloot.rpgloot.items.treasure_items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.coopersully.rpgloot.rpgloot.AdventureExpansion.getPlugin;

public class TreasureItem {

    public static void addKeys(@NotNull ItemStack itemStack, NamespacedKey @NotNull ... namespacedKeys) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(TreasureItemKeys.treasureItem, PersistentDataType.BOOLEAN, true);
        for (NamespacedKey namespacedKey : namespacedKeys) {
            itemMeta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.BOOLEAN, true);
        }
        itemStack.setItemMeta(itemMeta);
    }

    public static void addDescription(@NotNull ItemStack itemStack, String description) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
            assert lore != null;

            Component descriptionComponent = Component.text(description)
                    .color(TextColor.color(196, 164, 132))
                    .decorate(TextDecoration.ITALIC);
            lore.add(descriptionComponent);

            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
    }

    public static void addStatistics(@NotNull ItemStack itemStack, String name, TreasureItemSource source, @NotNull TreasureItemRarity rarity) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text(name, rarity.getColor()).decoration(TextDecoration.ITALIC, false));

        if (meta == null) return;

        List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
        assert lore != null;

        lore.add(Component.empty());
        Component statisticsLine = Component.text("Statistics:", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        Component sourceLine = Component.text(" " + source.toString() + " Source", NamedTextColor.DARK_GREEN).decoration(TextDecoration.ITALIC, false);
        Component rarityLine = Component.text(" " + rarity.toString() + " Treasure Item", NamedTextColor.DARK_GREEN).decoration(TextDecoration.ITALIC, false);

        lore.add(statisticsLine);
        lore.add(sourceLine);
        lore.add(rarityLine);

        meta.lore(lore);
        itemStack.setItemMeta(meta);
    }

    public static void addAbilities(ItemStack itemStack, String @NotNull ... abilities) {
        if (abilities.length % 2 != 0) {
            throw new IllegalArgumentException("Abilities must be provided in pairs: type, description");
        }

        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();
            assert lore != null;

            lore.add(Component.empty());
            Component abilitiesTitle = Component.text("Abilities:", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
            lore.add(abilitiesTitle);

            for (int i = 0; i < abilities.length; i += 2) {
                String type = abilities[i];
                String description = abilities[i + 1];
                Component abilityLine = Component.text(" " + type + " - " + description, NamedTextColor.DARK_GREEN).decoration(TextDecoration.ITALIC, false);
                lore.add(abilityLine);
            }

            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
    }

    public static void addEnchantments(@NotNull ItemStack item, Object @NotNull ... enchantments) {
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; i < enchantments.length; i += 2) {
            meta.addEnchant((Enchantment) enchantments[i], (Integer) enchantments[i + 1], true);
        }
        item.setItemMeta(meta);
    }

    public static ItemStack BOUNTY_BOW;
    public static ItemStack WAND_OF_INDECISION;
    public static ItemStack WITHER_BOW;
    public static ItemStack SCULK_BOOTS;
    public static ItemStack NIGHTMARE_FUEL;
    public static ItemStack MINERS_HAT;
    public static ItemStack ITEM_MAGNET;
    public static ItemStack EXP_MAGNET;
    public static ItemStack EVOKER_BOW;
    public static ItemStack TRADER_HAT;
    public static ItemStack TRAVELERS_HELMET;
    public static ItemStack TRAVELERS_CHESTPLATE;
    public static ItemStack TRAVELERS_LEGGINGS;
    public static ItemStack TRAVELERS_BOOTS;
    public static ItemStack TRAVELERS_SWORD;
    public static ItemStack TRAVELERS_PICKAXE;
    public static ItemStack RAIDER_HELMET;
    public static ItemStack RAIDER_CHESTPLATE;
    public static ItemStack RAIDER_LEGGINGS;
    public static ItemStack RAIDER_BOOTS;
    public static ItemStack RAIDER_SWORD;
    public static ItemStack RAIDER_PICKAXE;
    public static ItemStack AWAKENED_HELMET;
    public static ItemStack AWAKENED_CHESTPLATE;
    public static ItemStack AWAKENED_LEGGINGS;
    public static ItemStack AWAKENED_BOOTS;
    public static ItemStack AWAKENED_SWORD;
    public static ItemStack AWAKENED_PICKAXE;
    public static ItemStack ETERNAL_HELMET;
    public static ItemStack ETERNAL_CHESTPLATE;
    public static ItemStack ETERNAL_LEGGINGS;
    public static ItemStack ETERNAL_BOOTS;
    public static ItemStack CALAMITY_SWORD;
    public static ItemStack AXOLOTL_EGG;
    public static ItemStack BEE_EGG;
    public static ItemStack BLAZE_EGG;
    public static ItemStack CAMEL_EGG;
    public static ItemStack CAT_EGG;
    public static ItemStack CAVE_SPIDER_EGG;
    public static ItemStack CHICKEN_EGG;
    public static ItemStack COD_EGG;
    public static ItemStack COW_EGG;
    public static ItemStack CREEPER_EGG;
    public static ItemStack DOLPHIN_EGG;
    public static ItemStack DONKEY_EGG;
    public static ItemStack DROWNED_EGG;
    public static ItemStack ENDERMAN_EGG;
    public static ItemStack FOX_EGG;
    public static ItemStack FROG_EGG;
    public static ItemStack GLOW_SQUID_EGG;
    public static ItemStack GOAT_EGG;
    public static ItemStack GUARDIAN_EGG;
    public static ItemStack HOGLIN_EGG;
    public static ItemStack HORSE_EGG;
    public static ItemStack HUSK_EGG;
    public static ItemStack IRON_GOLEM_EGG;
    public static ItemStack LLAMA_EGG;
    public static ItemStack MAGMA_CUBE_EGG;
    public static ItemStack MOOSHROOM_EGG;
    public static ItemStack MULE_EGG;
    public static ItemStack PANDA_EGG;
    public static ItemStack PARROT_EGG;
    public static ItemStack PIGLIN_BRUTE_EGG;
    public static ItemStack PIGLIN_EGG;
    public static ItemStack PIG_EGG;
    public static ItemStack PILLAGER_EGG;
    public static ItemStack POLAR_BEAR_EGG;
    public static ItemStack PUFFER_FISH_EGG;
    public static ItemStack RABBIT_EGG;
    public static ItemStack SALMON_EGG;
    public static ItemStack SHEEP_EGG;
    public static ItemStack SHULKER_EGG;
    public static ItemStack SILVERFISH_EGG;
    public static ItemStack SKELETON_EGG;
    public static ItemStack SLIME_EGG;
    public static ItemStack SNIFFER_EGG;
    public static ItemStack SNOW_GOLEM_EGG;
    public static ItemStack SPIDER_EGG;
    public static ItemStack SQUID_EGG;
    public static ItemStack STRAY_EGG;
    public static ItemStack TADPOLE_EGG;
    public static ItemStack TROPICAL_FISH_EGG;
    public static ItemStack TURTLE_EGG;
    public static ItemStack VILLAGER_EGG;
    public static ItemStack WITHER_SKELETON_EGG;
    public static ItemStack WOLF_EGG;
    public static ItemStack ZOGLIN_EGG;
    public static ItemStack ZOMBIE_EGG;
    public static ItemStack ZOMBIE_VILLAGER_EGG;
    public static ItemStack DRAGONHIDE_HELMET;
    public static ItemStack DRAGONHIDE_CHESTPLATE;
    public static ItemStack DRAGONHIDE_LEGGINGS;
    public static ItemStack DRAGONHIDE_BOOTS;


    public static List<ItemStack> TREASURE_ITEMS = new ArrayList<>();

    public static void loadTreasureItems() {

        BOUNTY_BOW = new ItemStack(Material.BOW, 1);
        addKeys(BOUNTY_BOW, TreasureItemKeys.bountyBow);
        addDescription(BOUNTY_BOW, "Unbeatable in genetic perfection.");
        addStatistics(BOUNTY_BOW, "Bow of the Bounty", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(BOUNTY_BOW, Enchantment.ARROW_INFINITE, 1, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(BOUNTY_BOW);

        WAND_OF_INDECISION = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        addKeys(WAND_OF_INDECISION, TreasureItemKeys.wandOfIndecision);
        addDescription(WAND_OF_INDECISION, "Rattles the minds of villagers.");
        addStatistics(WAND_OF_INDECISION, "Wand of Indecision", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(WAND_OF_INDECISION, "USE ITEM", "Refreshes a villager's trades");
        TREASURE_ITEMS.add(WAND_OF_INDECISION);

        WITHER_BOW = new ItemStack(Material.BOW, 1);
        addKeys(WITHER_BOW, TreasureItemKeys.witherBow);
        addDescription(WITHER_BOW, "A procreation of calamity and chaos.");
        addStatistics(WITHER_BOW, "Fading Recurve", TreasureItemSource.UNKNOWN, TreasureItemRarity.MYTHICAL);
        addAbilities(WITHER_BOW, "PASSIVE", "Converts shot arrows into wither skulls");
        TREASURE_ITEMS.add(WITHER_BOW);

        SCULK_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        addKeys(SCULK_BOOTS, TreasureItemKeys.sculkBoots);
        addDescription(SCULK_BOOTS, "It reaches its claws into the aether.");
        addStatistics(SCULK_BOOTS, "Sculk Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(SCULK_BOOTS, "SNEAK + JUMP", "Reveal nearby entities");
        TREASURE_ITEMS.add(SCULK_BOOTS);

        NIGHTMARE_FUEL = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        addKeys(NIGHTMARE_FUEL, TreasureItemKeys.nightmareFuel);
        addDescription(NIGHTMARE_FUEL, "It whispers and glows with rage.");
        addStatistics(NIGHTMARE_FUEL, "Nightmare Fuel", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(NIGHTMARE_FUEL);

        MINERS_HAT = new ItemStack(Material.IRON_HELMET, 1);
        addKeys(MINERS_HAT, TreasureItemKeys.minersHat);
        addDescription(MINERS_HAT, "Your guide into the depths.");
        addStatistics(MINERS_HAT, "Miner's Hard hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(MINERS_HAT, "PASSIVE", "Illuminates nearby area");
        TREASURE_ITEMS.add(MINERS_HAT);

        ITEM_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        addKeys(ITEM_MAGNET, TreasureItemKeys.itemMagnet);
        addDescription(ITEM_MAGNET, "Material goods attract the soul.");
        addStatistics(ITEM_MAGNET, "Item Magnet Mk.I", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(ITEM_MAGNET, "PASSIVE", "Magnetizes nearby items");
        TREASURE_ITEMS.add(ITEM_MAGNET);

        EXP_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        addKeys(EXP_MAGNET, TreasureItemKeys.expMagnet);
        addDescription(EXP_MAGNET, "Ooh, shiny!");
        addStatistics(EXP_MAGNET, "Experience Magnet Mk.I", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(EXP_MAGNET, "PASSIVE", "Magnetizes nearby experience orbs");
        TREASURE_ITEMS.add(EXP_MAGNET);

        EVOKER_BOW = new ItemStack(Material.BOW, 1);
        addKeys(EVOKER_BOW, TreasureItemKeys.evokerBow);
        addDescription(EVOKER_BOW, "A man's jaws are his deadliest weapon.");
        addStatistics(EVOKER_BOW, "Croco-bow", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addAbilities(EVOKER_BOW, "PASSIVE", "Converts shot arrows into Evocation Fangs",
                "SUCCESSFUL HIT", "Immobilizes victim for 1s");
        TREASURE_ITEMS.add(EVOKER_BOW);

        TRADER_HAT = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta TRADER_HAT_META = (LeatherArmorMeta) TRADER_HAT.getItemMeta();
        TRADER_HAT_META.setColor(Color.fromRGB(3, 78, 252));
        TRADER_HAT.setItemMeta(TRADER_HAT_META);
        addKeys(TRADER_HAT, TreasureItemKeys.traderHat);
        addDescription(TRADER_HAT, "It rumbles with memories of an exiled salesman.");
        addStatistics(TRADER_HAT, "Wandering Cap", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(TRADER_HAT);

        TRAVELERS_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        addKeys(TRAVELERS_HELMET, TreasureItemKeys.traveler, TreasureItemKeys.travelerHelmet);
        addDescription(TRAVELERS_HELMET, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_HELMET, "Traveler's Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_HELMET, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_HELMET);

        TRAVELERS_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        addKeys(TRAVELERS_CHESTPLATE, TreasureItemKeys.traveler, TreasureItemKeys.travelerChestplate);
        addDescription(TRAVELERS_CHESTPLATE, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_CHESTPLATE, "Traveler's Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_CHESTPLATE, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_CHESTPLATE);

        TRAVELERS_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
        addKeys(TRAVELERS_LEGGINGS, TreasureItemKeys.traveler, TreasureItemKeys.travelerLeggings);
        addDescription(TRAVELERS_LEGGINGS, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_LEGGINGS, "Traveler's Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_LEGGINGS, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_LEGGINGS);

        TRAVELERS_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        addKeys(TRAVELERS_BOOTS, TreasureItemKeys.traveler, TreasureItemKeys.travelerBoots);
        addDescription(TRAVELERS_BOOTS, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_BOOTS, "Traveler's Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_BOOTS, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_BOOTS);

        TRAVELERS_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        addKeys(TRAVELERS_SWORD, TreasureItemKeys.traveler, TreasureItemKeys.travelerSword);
        addDescription(TRAVELERS_SWORD, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_SWORD, "Traveler's Sword", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_SWORD, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_SWORD);

        TRAVELERS_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        addKeys(TRAVELERS_PICKAXE, TreasureItemKeys.traveler, TreasureItemKeys.travelerPickaxe);
        addDescription(TRAVELERS_PICKAXE, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_PICKAXE, "Traveler's Pickaxe", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_PICKAXE, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(TRAVELERS_PICKAXE);

        RAIDER_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        addKeys(RAIDER_HELMET, TreasureItemKeys.raider, TreasureItemKeys.raiderHelmet);
        addDescription(RAIDER_HELMET, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_HELMET, "Raider Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(RAIDER_HELMET);

        RAIDER_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        addKeys(RAIDER_CHESTPLATE, TreasureItemKeys.raider, TreasureItemKeys.raiderChestplate);
        addDescription(RAIDER_CHESTPLATE, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_CHESTPLATE, "Raider Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(RAIDER_CHESTPLATE);

        RAIDER_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
        addKeys(RAIDER_LEGGINGS, TreasureItemKeys.raider, TreasureItemKeys.raiderLeggings);
        addDescription(RAIDER_LEGGINGS, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_LEGGINGS, "Raider Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(RAIDER_LEGGINGS);

        RAIDER_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        addKeys(RAIDER_BOOTS, TreasureItemKeys.raider, TreasureItemKeys.raiderBoots);
        addDescription(RAIDER_BOOTS, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_BOOTS, "Raider Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(RAIDER_BOOTS);

        RAIDER_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        addKeys(RAIDER_SWORD, TreasureItemKeys.raider, TreasureItemKeys.raiderSword);
        addDescription(RAIDER_SWORD, "It consumes the rage of the wielder.");
        addStatistics(RAIDER_SWORD, "Raider Sword", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_SWORD, Enchantment.DURABILITY, 4, Enchantment.DAMAGE_ALL, 5, Enchantment.FIRE_ASPECT, 2, Enchantment.LOOT_BONUS_MOBS, 3, Enchantment.SWEEPING_EDGE, 3, Enchantment.KNOCKBACK, 2);
        TREASURE_ITEMS.add(RAIDER_SWORD);

        RAIDER_PICKAXE = new ItemStack(Material.IRON_PICKAXE, 1);
        addKeys(RAIDER_PICKAXE, TreasureItemKeys.raider, TreasureItemKeys.raiderPickaxe);
        addDescription(RAIDER_PICKAXE, "It consumes the rage of the wielder.");
        addStatistics(RAIDER_PICKAXE, "Raider Pickaxe", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_PICKAXE, Enchantment.DURABILITY, 4, Enchantment.DIG_SPEED, 5, Enchantment.SILK_TOUCH, 1);
        TREASURE_ITEMS.add(RAIDER_PICKAXE);

        AWAKENED_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        addKeys(AWAKENED_HELMET, TreasureItemKeys.awakened, TreasureItemKeys.awakenedHelmet);
        addDescription(AWAKENED_HELMET, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_HELMET, "Awakened Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.WATER_WORKER, 1, Enchantment.OXYGEN, 3);
        TREASURE_ITEMS.add(AWAKENED_HELMET);

        AWAKENED_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        addKeys(AWAKENED_CHESTPLATE, TreasureItemKeys.awakened, TreasureItemKeys.awakenedChestplate);
        addDescription(AWAKENED_CHESTPLATE, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_CHESTPLATE, "Awakened Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(AWAKENED_CHESTPLATE);

        AWAKENED_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        addKeys(AWAKENED_LEGGINGS, TreasureItemKeys.awakened, TreasureItemKeys.awakenedLeggings);
        addDescription(AWAKENED_LEGGINGS, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_LEGGINGS, "Awakened Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        TREASURE_ITEMS.add(AWAKENED_LEGGINGS);

        AWAKENED_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        addKeys(AWAKENED_BOOTS, TreasureItemKeys.awakened, TreasureItemKeys.awakenedBoots);
        addDescription(AWAKENED_BOOTS, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_BOOTS, "Awakened Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.PROTECTION_FALL, 4, Enchantment.DEPTH_STRIDER, 3);
        TREASURE_ITEMS.add(AWAKENED_BOOTS);

        AWAKENED_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
        addKeys(AWAKENED_SWORD, TreasureItemKeys.awakened, TreasureItemKeys.awakenedSword);
        addDescription(AWAKENED_SWORD, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_SWORD, "Awakened Sword", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(AWAKENED_SWORD, Enchantment.DURABILITY, 4, Enchantment.DAMAGE_ALL, 9, Enchantment.FIRE_ASPECT, 3, Enchantment.LOOT_BONUS_MOBS, 3, Enchantment.SWEEPING_EDGE, 5, Enchantment.KNOCKBACK, 3, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(AWAKENED_SWORD);

        AWAKENED_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        addKeys(AWAKENED_PICKAXE, TreasureItemKeys.awakened, TreasureItemKeys.awakenedPickaxe);
        addDescription(AWAKENED_PICKAXE, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_PICKAXE, "Awakened Pickaxe", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(AWAKENED_PICKAXE, Enchantment.DURABILITY, 4, Enchantment.DIG_SPEED, 5, Enchantment.SILK_TOUCH, 2, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(AWAKENED_PICKAXE);

        ETERNAL_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        addKeys(ETERNAL_HELMET, TreasureItemKeys.eternal, TreasureItemKeys.eternalHelmet);
        addDescription(ETERNAL_HELMET, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_HELMET, "Eternal Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.WATER_WORKER, 2, Enchantment.OXYGEN, 3, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(ETERNAL_HELMET);

        ETERNAL_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        addKeys(ETERNAL_CHESTPLATE, TreasureItemKeys.eternal, TreasureItemKeys.eternalChestplate);
        addDescription(ETERNAL_CHESTPLATE, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_CHESTPLATE, "Eternal Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(ETERNAL_CHESTPLATE);

        ETERNAL_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        addKeys(ETERNAL_LEGGINGS, TreasureItemKeys.eternal, TreasureItemKeys.eternalLeggings);
        addDescription(ETERNAL_LEGGINGS, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_LEGGINGS, "Eternal Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(ETERNAL_LEGGINGS);

        ETERNAL_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        addKeys(ETERNAL_BOOTS, TreasureItemKeys.eternal, TreasureItemKeys.eternalBoots);
        addDescription(ETERNAL_BOOTS, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_BOOTS, "Eternal Boots", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FALL, 4, Enchantment.DEPTH_STRIDER, 3, Enchantment.SOUL_SPEED, 3, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(ETERNAL_BOOTS);

        CALAMITY_SWORD = new ItemStack(Material.GOLDEN_SWORD, 1);
        addKeys(CALAMITY_SWORD, TreasureItemKeys.calamity, TreasureItemKeys.calamitySword);
        addDescription(CALAMITY_SWORD, "It hums a lullaby of impending chaos.");
        addStatistics(CALAMITY_SWORD, "Midas' Cataclysm", TreasureItemSource.MAGIC, TreasureItemRarity.UNCOMMON);
        addAbilities(CALAMITY_SWORD, "PASSIVE", "May explode during combat");
        addEnchantments(CALAMITY_SWORD, Enchantment.KNOCKBACK, 10);
        TREASURE_ITEMS.add(CALAMITY_SWORD);

        AXOLOTL_EGG = new ItemStack(Material.AXOLOTL_SPAWN_EGG, 1);
        addDescription(AXOLOTL_EGG, "A ripple in the water, a glimpse of the unknown.");
        addStatistics(AXOLOTL_EGG, "Axolotl Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(AXOLOTL_EGG);

        BEE_EGG = new ItemStack(Material.BEE_SPAWN_EGG, 1);
        addDescription(BEE_EGG, "A tiny buzz heralds the keeper of nature's essence.");
        addStatistics(BEE_EGG, "Bee Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(BEE_EGG);

        BLAZE_EGG = new ItemStack(Material.BLAZE_SPAWN_EGG, 1);
        addDescription(BLAZE_EGG, "Within lies a spark of the inferno, waiting to be unleashed.");
        addStatistics(BLAZE_EGG, "Blaze Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(BLAZE_EGG);

        CAMEL_EGG = new ItemStack(Material.CAMEL_SPAWN_EGG, 1);
        addDescription(CAMEL_EGG, "A wanderer of the endless dunes, bearer of ancient whispers.");
        addStatistics(CAMEL_EGG, "Camel Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(CAMEL_EGG);

        CAT_EGG = new ItemStack(Material.CAT_SPAWN_EGG, 1);
        addDescription(CAT_EGG, "Silent whispers of the old world resonate within.");
        addStatistics(CAT_EGG, "Cat Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(CAT_EGG);

        CAVE_SPIDER_EGG = new ItemStack(Material.CAVE_SPIDER_SPAWN_EGG, 1);
        addDescription(CAVE_SPIDER_EGG, "A lurking menace, born from darkness's caress.");
        addStatistics(CAVE_SPIDER_EGG, "Cave Spider Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(CAVE_SPIDER_EGG);

        CHICKEN_EGG = new ItemStack(Material.CHICKEN_SPAWN_EGG, 1);
        addDescription(CHICKEN_EGG, "A humble beginning, a whispered promise of the skies.");
        addStatistics(CHICKEN_EGG, "Chicken Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(CHICKEN_EGG);

        COD_EGG = new ItemStack(Material.COD_SPAWN_EGG, 1);
        addDescription(COD_EGG, "A silver flash, a dance with the currents of the ancient seas.");
        addStatistics(COD_EGG, "Cod Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(COD_EGG);

        COW_EGG = new ItemStack(Material.COW_SPAWN_EGG, 1);
        addDescription(COW_EGG, "Bearer of the ancient earth's bounties, cloaked in humility.");
        addStatistics(COW_EGG, "Cow Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(COW_EGG);

        CREEPER_EGG = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
        addDescription(CREEPER_EGG, "A silent whisper of the world's forgotten dissonance.");
        addStatistics(CREEPER_EGG, "Creeper Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(CREEPER_EGG);

        DOLPHIN_EGG = new ItemStack(Material.DOLPHIN_SPAWN_EGG, 1);
        addDescription(DOLPHIN_EGG, "A playful spirit, bounding through the endless blue.");
        addStatistics(DOLPHIN_EGG, "Dolphin Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(DOLPHIN_EGG);

        DONKEY_EGG = new ItemStack(Material.DONKEY_SPAWN_EGG, 1);
        addDescription(DONKEY_EGG, "A steadfast companion, bearing the burdens of the world.");
        addStatistics(DONKEY_EGG, "Donkey Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(DONKEY_EGG);

        DROWNED_EGG = new ItemStack(Material.DROWNED_SPAWN_EGG, 1);
        addDescription(DROWNED_EGG, "A reminder of the abyss's cold embrace.");
        addStatistics(DROWNED_EGG, "Drowned Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(DROWNED_EGG);

        ENDERMAN_EGG = new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1);
        addDescription(ENDERMAN_EGG, "A wanderer between realms, eyes holding the void's mystery.");
        addStatistics(ENDERMAN_EGG, "Enderman Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(ENDERMAN_EGG);

        FOX_EGG = new ItemStack(Material.FOX_SPAWN_EGG, 1);
        addDescription(FOX_EGG, "A clever spirit, dancing with the whispers of the dawn.");
        addStatistics(FOX_EGG, "Fox Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(FOX_EGG);

        FROG_EGG = new ItemStack(Material.FROG_SPAWN_EGG, 1);
        addDescription(FROG_EGG, "A leap of faith, from lily to the stars.");
        addStatistics(FROG_EGG, "Frog Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(FROG_EGG);

        GLOW_SQUID_EGG = new ItemStack(Material.GLOW_SQUID_SPAWN_EGG, 1);
        addDescription(GLOW_SQUID_EGG, "In the abyss, a soft glow of hope shines.");
        addStatistics(GLOW_SQUID_EGG, "Glow Squid Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(GLOW_SQUID_EGG);

        GOAT_EGG = new ItemStack(Material.GOAT_SPAWN_EGG, 1);
        addDescription(GOAT_EGG, "A stubborn soul, scaling the heights of the world.");
        addStatistics(GOAT_EGG, "Goat Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(GOAT_EGG);

        GUARDIAN_EGG = new ItemStack(Material.GUARDIAN_SPAWN_EGG, 1);
        addDescription(GUARDIAN_EGG, "A sentinel of the depths, keeping ancient secrets.");
        addStatistics(GUARDIAN_EGG, "Guardian Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(GUARDIAN_EGG);

        HOGLIN_EGG = new ItemStack(Material.HOGLIN_SPAWN_EGG, 1);
        addDescription(HOGLIN_EGG, "A fierce heart, thundering through the crimson forest.");
        addStatistics(HOGLIN_EGG, "Hoglin Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(HOGLIN_EGG);

        HORSE_EGG = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
        addDescription(HORSE_EGG, "A heart that races with the wind, across boundless plains.");
        addStatistics(HORSE_EGG, "Horse Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(HORSE_EGG);

        HUSK_EGG = new ItemStack(Material.HUSK_SPAWN_EGG, 1);
        addDescription(HUSK_EGG, "A whisper of the desert's harsh embrace, lingering.");
        addStatistics(HUSK_EGG, "Husk Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(HUSK_EGG);

        IRON_GOLEM_EGG = new ItemStack(Material.IRON_GOLEM_SPAWN_EGG, 1);
        addDescription(IRON_GOLEM_EGG, "Forged of earth, a silent guardian in iron slumbers.");
        addStatistics(IRON_GOLEM_EGG, "Iron Golem Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(IRON_GOLEM_EGG);

        LLAMA_EGG = new ItemStack(Material.LLAMA_SPAWN_EGG, 1);
        addDescription(LLAMA_EGG, "A steady companion, traversing the trails of the world unseen.");
        addStatistics(LLAMA_EGG, "Llama Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(LLAMA_EGG);

        MOOSHROOM_EGG = new ItemStack(Material.MOOSHROOM_SPAWN_EGG, 1);
        addDescription(MOOSHROOM_EGG, "A whimsical wonder, from spores of the ancient mycelium.");
        addStatistics(MOOSHROOM_EGG, "Mooshroom Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(MOOSHROOM_EGG);

        MAGMA_CUBE_EGG = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG, 1);
        addDescription(MAGMA_CUBE_EGG, "A molten core, ever pulsing with the furnace’s wrath.");
        addStatistics(MAGMA_CUBE_EGG, "Magma Cube Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(MAGMA_CUBE_EGG);

        MULE_EGG = new ItemStack(Material.MULE_SPAWN_EGG, 1);
        addDescription(MULE_EGG, "A carrier of burdens through the sunlit and moonlit paths.");
        addStatistics(MULE_EGG, "Mule Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(MULE_EGG);

        AXOLOTL_EGG = new ItemStack(Material.AXOLOTL_SPAWN_EGG, 1);
        addDescription(AXOLOTL_EGG, "A dance of grace, beneath the water's gentle caress.");
        addStatistics(AXOLOTL_EGG, "Axolotl Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(AXOLOTL_EGG);

        PANDA_EGG = new ItemStack(Material.PANDA_SPAWN_EGG, 1);
        addDescription(PANDA_EGG, "A soul of gentle repose, amidst the bamboo whispers.");
        addStatistics(PANDA_EGG, "Panda Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PANDA_EGG);

        PARROT_EGG = new ItemStack(Material.PARROT_SPAWN_EGG, 1);
        addDescription(PARROT_EGG, "A melody of colors, in fluttering wings it sings.");
        addStatistics(PARROT_EGG, "Parrot Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PARROT_EGG);

        PIG_EGG = new ItemStack(Material.PIG_SPAWN_EGG, 1);
        addDescription(PIG_EGG, "A humble companion, in mud it finds its solace.");
        addStatistics(PIG_EGG, "Pig Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PIG_EGG);

        PIGLIN_EGG = new ItemStack(Material.PIGLIN_SPAWN_EGG, 1);
        addDescription(PIGLIN_EGG, "A keeper of gold, in nether flames it thrives.");
        addStatistics(PIGLIN_EGG, "Piglin Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PIGLIN_EGG);

        PIGLIN_BRUTE_EGG = new ItemStack(Material.PIGLIN_BRUTE_SPAWN_EGG, 1);
        addDescription(PIGLIN_BRUTE_EGG, "A brute force, guarding crimson halls with fervor.");
        addStatistics(PIGLIN_BRUTE_EGG, "Piglin Brute Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PIGLIN_BRUTE_EGG);

        PILLAGER_EGG = new ItemStack(Material.PILLAGER_SPAWN_EGG, 1);
        addDescription(PILLAGER_EGG, "A harbinger of raids, in plunder it seeks glory.");
        addStatistics(PILLAGER_EGG, "Pillager Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PILLAGER_EGG);

        POLAR_BEAR_EGG = new ItemStack(Material.POLAR_BEAR_SPAWN_EGG, 1);
        addDescription(POLAR_BEAR_EGG, "A guardian of snow, beneath starry nights it roams.");
        addStatistics(POLAR_BEAR_EGG, "Polar Bear Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(POLAR_BEAR_EGG);

        PUFFER_FISH_EGG = new ItemStack(Material.PUFFERFISH_SPAWN_EGG, 1);
        addDescription(PUFFER_FISH_EGG, "A small guise, a sudden swell of guarded fears.");
        addStatistics(PUFFER_FISH_EGG, "Puffer Fish Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(PUFFER_FISH_EGG);

        SALMON_EGG = new ItemStack(Material.SALMON_SPAWN_EGG, 1);
        addDescription(SALMON_EGG, "A fleeting dance in water's cool embrace.");
        addStatistics(SALMON_EGG, "Salmon Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SALMON_EGG);

        RABBIT_EGG = new ItemStack(Material.RABBIT_SPAWN_EGG, 1);
        addDescription(RABBIT_EGG, "A hop through time, in meadows it leaps.");
        addStatistics(RABBIT_EGG, "Rabbit Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(RABBIT_EGG);

        SHEEP_EGG = new ItemStack(Material.SHEEP_SPAWN_EGG, 1);
        addDescription(SHEEP_EGG, "A bearer of warmth, in woolly dreams it dwells.");
        addStatistics(SHEEP_EGG, "Sheep Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SHEEP_EGG);

        SHULKER_EGG = new ItemStack(Material.SHULKER_SPAWN_EGG, 1);
        addDescription(SHULKER_EGG, "A sentinel of the end, in silence it waits.");
        addStatistics(SHULKER_EGG, "Shulker Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SHULKER_EGG);

        SILVERFISH_EGG = new ItemStack(Material.SILVERFISH_SPAWN_EGG, 1);
        addDescription(SILVERFISH_EGG, "A silent creeper through stone's cold heart.");
        addStatistics(SILVERFISH_EGG, "Silverfish Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SILVERFISH_EGG);

        SKELETON_EGG = new ItemStack(Material.SKELETON_SPAWN_EGG, 1);
        addDescription(SKELETON_EGG, "An ancient whisper, bones that walk in night's chill.");
        addStatistics(SKELETON_EGG, "Skeleton Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SKELETON_EGG);

        SLIME_EGG = new ItemStack(Material.SLIME_SPAWN_EGG, 1);
        addDescription(SLIME_EGG, "A soft embrace, gelatinous hearts beat in rhythm.");
        addStatistics(SLIME_EGG, "Slime Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SLIME_EGG);

        SNIFFER_EGG = new ItemStack(Material.SNIFFER_SPAWN_EGG, 1);
        addDescription(SNIFFER_EGG, "A seeker of truths, in scents it finds stories.");
        addStatistics(SNIFFER_EGG, "Sniffer Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SNIFFER_EGG);

        SNOW_GOLEM_EGG = new ItemStack(Material.SNOW_GOLEM_SPAWN_EGG, 1);
        addDescription(SNOW_GOLEM_EGG, "A cold companion, snow-laden trails it leaves.");
        addStatistics(SNOW_GOLEM_EGG, "Snow Golem Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SNOW_GOLEM_EGG);

        SPIDER_EGG = new ItemStack(Material.SPIDER_SPAWN_EGG, 1);
        addDescription(SPIDER_EGG, "An eight-legged whisper, spinner of fate's silk.");
        addStatistics(SPIDER_EGG, "Spider Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SPIDER_EGG);

        SQUID_EGG = new ItemStack(Material.SQUID_SPAWN_EGG, 1);
        addDescription(SQUID_EGG, "A gentle drift, inked trails in liquid abyss.");
        addStatistics(SQUID_EGG, "Squid Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(SQUID_EGG);

        STRAY_EGG = new ItemStack(Material.STRAY_SPAWN_EGG, 1);
        addDescription(STRAY_EGG, "A cold wanderer, arrows laden with winter's breath.");
        addStatistics(STRAY_EGG, "Stray Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(STRAY_EGG);

        TADPOLE_EGG = new ItemStack(Material.TADPOLE_SPAWN_EGG, 1);
        addDescription(TADPOLE_EGG, "A humble beginning, ripples of destiny unfurl.");
        addStatistics(TADPOLE_EGG, "Tadpole Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(TADPOLE_EGG);

        TROPICAL_FISH_EGG = new ItemStack(Material.TROPICAL_FISH_SPAWN_EGG, 1);
        addDescription(TROPICAL_FISH_EGG, "A burst of color, secrets veiled in shimmering scales.");
        addStatistics(TROPICAL_FISH_EGG, "Tropical Fish Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(TROPICAL_FISH_EGG);

        TURTLE_EGG = new ItemStack(Material.TURTLE_SPAWN_EGG, 1);
        addDescription(TURTLE_EGG, "A slow wander, ancient wisdom in unhurried pace.");
        addStatistics(TURTLE_EGG, "Turtle Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(TURTLE_EGG);

        VILLAGER_EGG = new ItemStack(Material.VILLAGER_SPAWN_EGG, 1);
        addDescription(VILLAGER_EGG, "A humble seed, harbinger of bustling trade.");
        addStatistics(VILLAGER_EGG, "Villager Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(VILLAGER_EGG);

        WITHER_SKELETON_EGG = new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1);
        addDescription(WITHER_SKELETON_EGG, "A dark omen, bearer of withering doom.");
        addStatistics(WITHER_SKELETON_EGG, "Wither Skeleton Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(WITHER_SKELETON_EGG);

        WOLF_EGG = new ItemStack(Material.WOLF_SPAWN_EGG, 1);
        addDescription(WOLF_EGG, "A loyal companion, eyes aglow with untamed spirit.");
        addStatistics(WOLF_EGG, "Wolf Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(WOLF_EGG);

        ZOGLIN_EGG = new ItemStack(Material.ZOGLIN_SPAWN_EGG, 1);
        addDescription(ZOGLIN_EGG, "A wild heart, fury clad in bristling hide.");
        addStatistics(ZOGLIN_EGG, "Zoglin Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(ZOGLIN_EGG);

        ZOMBIE_EGG = new ItemStack(Material.ZOMBIE_SPAWN_EGG, 1);
        addDescription(ZOMBIE_EGG, "A restless spirit, bound to the realm of the living.");
        addStatistics(ZOMBIE_EGG, "Zombie Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(ZOMBIE_EGG);

        ZOMBIE_VILLAGER_EGG = new ItemStack(Material.ZOMBIE_VILLAGER_SPAWN_EGG, 1);
        addDescription(ZOMBIE_VILLAGER_EGG, "A twisted fate, a humble merchant now a ghastly fiend.");
        addStatistics(ZOMBIE_VILLAGER_EGG, "Zombie Villager Egg", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(ZOMBIE_VILLAGER_EGG);

        DRAGONHIDE_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
        addKeys(DRAGONHIDE_HELMET, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideHelmet);
        addDescription(DRAGONHIDE_HELMET, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_HELMET, "Dragonhide Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(DRAGONHIDE_HELMET, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(DRAGONHIDE_HELMET);

        DRAGONHIDE_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        addKeys(DRAGONHIDE_CHESTPLATE, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideChestplate);
        addDescription(DRAGONHIDE_CHESTPLATE, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_CHESTPLATE, "Dragonhide Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(DRAGONHIDE_CHESTPLATE, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(DRAGONHIDE_CHESTPLATE);

        DRAGONHIDE_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        addKeys(DRAGONHIDE_LEGGINGS, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideLeggings);
        addDescription(DRAGONHIDE_LEGGINGS, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_LEGGINGS, "Dragonhide Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(DRAGONHIDE_LEGGINGS, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(DRAGONHIDE_LEGGINGS);

        DRAGONHIDE_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        addKeys(DRAGONHIDE_BOOTS, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideBoots);
        addDescription(DRAGONHIDE_BOOTS, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_BOOTS, "Dragonhide Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(DRAGONHIDE_BOOTS, Enchantment.DURABILITY, 4);
        TREASURE_ITEMS.add(DRAGONHIDE_BOOTS);
    }

    public static void checkTreasureItems() {
        int countInvalid = 0;
        for (ItemStack item : TREASURE_ITEMS) {
            if (item == null) countInvalid++;
        }
        if (countInvalid > 0) {
            getPlugin().getLogger().severe(countInvalid + " Treasure Items were deemed invalid; they will not work properly. Please contact a developer.");
        }
    }

}
