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

public class TreasureItem {

    public static void addKeys(@NotNull ItemStack itemStack, NamespacedKey @NotNull ... namespacedKeys) {
        itemStack.getItemMeta().getPersistentDataContainer().set(TreasureItemKeys.treasureItem, PersistentDataType.BOOLEAN, true);
        for (NamespacedKey namespacedKey : namespacedKeys) {
            itemStack.getItemMeta().getPersistentDataContainer().set(namespacedKey, PersistentDataType.BOOLEAN, true);
        }
    }

    public static void addDescription(@NotNull ItemStack itemStack, String description) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();

            Component descriptionComponent = Component.text(description)
                    .color(TextColor.color(196, 164, 132))
                    .decorate(TextDecoration.ITALIC);
            lore.add(descriptionComponent);
            lore.add(Component.newline());

            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
    }

    public static void addStatistics(@NotNull ItemStack itemStack, TreasureItemSource source, TreasureItemRarity rarity) {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();

            Component statisticsLine = Component.text("Statistics:", NamedTextColor.GRAY);
            Component sourceLine = Component.text(" " + source.toString() + " Source", NamedTextColor.DARK_GREEN);
            Component rarityLine = Component.text(" " + rarity.toString() + " Treasure Item", NamedTextColor.DARK_GREEN);

            lore.add(statisticsLine);
            lore.add(sourceLine);
            lore.add(rarityLine);
            lore.add(Component.newline());

            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
    }

    public static void addAbilities(ItemStack itemStack, String @NotNull ... abilities) {
        if (abilities.length % 2 != 0) {
            throw new IllegalArgumentException("Abilities must be provided in pairs: type, description");
        }

        ItemMeta meta = itemStack.getItemMeta();

        if (meta != null) {
            List<Component> lore = meta.hasLore() ? meta.lore() : new ArrayList<>();

            Component abilitiesTitle = Component.text("Abilities:", NamedTextColor.GRAY);
            lore.add(abilitiesTitle);

            for (int i = 0; i < abilities.length; i += 2) {
                String type = abilities[i];
                String description = abilities[i + 1];
                Component abilityLine = Component.text(" " + type + " - " + description, NamedTextColor.DARK_GREEN);
                lore.add(abilityLine);
            }
            lore.add(Component.newline());

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


    public static void loadTreasureItems() {

        BOUNTY_BOW = new ItemStack(Material.BOW);
        addKeys(BOUNTY_BOW, TreasureItemKeys.bountyBow);
        addDescription(BOUNTY_BOW, "Unbeatable in genetic perfection.");
        addStatistics(BOUNTY_BOW, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);

        WAND_OF_INDECISION = new ItemStack(Material.REPEATING_COMMAND_BLOCK);
        addKeys(WAND_OF_INDECISION, TreasureItemKeys.wandOfIndecision);
        addDescription(WAND_OF_INDECISION, "Rattles the minds of villagers.");
        addStatistics(WAND_OF_INDECISION, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(WAND_OF_INDECISION, "USE ITEM", "Refreshes a villager's trades");

        WITHER_BOW = new ItemStack(Material.BOW);
        addKeys(WITHER_BOW, TreasureItemKeys.witherBow);
        addDescription(WITHER_BOW, "A procreation of calamity and chaos.");
        addStatistics(WITHER_BOW, TreasureItemSource.UNKNOWN, TreasureItemRarity.MYTHICAL);
        addAbilities(WITHER_BOW, "PASSIVE", "Converts shot arrows into wither skulls");

        SCULK_BOOTS = new ItemStack(Material.LEATHER_BOOTS);
        addKeys(SCULK_BOOTS, TreasureItemKeys.sculkBoots);
        addDescription(SCULK_BOOTS, "It reaches its claws into the aether.");
        addStatistics(SCULK_BOOTS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(SCULK_BOOTS, "SNEAK + JUMP", "Reveal nearby entities");

        NIGHTMARE_FUEL = new ItemStack(Material.REPEATING_COMMAND_BLOCK);
        addKeys(NIGHTMARE_FUEL, TreasureItemKeys.nightmareFuel);
        addDescription(NIGHTMARE_FUEL, "It whispers and glows with rage.");
        addStatistics(NIGHTMARE_FUEL, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);

        MINERS_HAT = new ItemStack(Material.IRON_HELMET);
        addKeys(MINERS_HAT, TreasureItemKeys.minersHat);
        addDescription(MINERS_HAT, "Your guide into the depths.");
        addStatistics(MINERS_HAT, TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(MINERS_HAT, "PASSIVE", "Illuminates nearby area");

        ITEM_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK);
        addKeys(ITEM_MAGNET, TreasureItemKeys.itemMagnet);
        addDescription(ITEM_MAGNET, "Material goods attract the soul.");
        addStatistics(ITEM_MAGNET, TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(ITEM_MAGNET, "PASSIVE", "Magnetizes nearby items");

        EXP_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK);
        addKeys(EXP_MAGNET, TreasureItemKeys.expMagnet);
        addDescription(EXP_MAGNET, "Ooh, shiny!");
        addStatistics(EXP_MAGNET, TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(EXP_MAGNET, "PASSIVE", "Magnetizes nearby experience orbs");

        EVOKER_BOW = new ItemStack(Material.BOW);
        addKeys(EVOKER_BOW, TreasureItemKeys.evokerBow);
        addDescription(EVOKER_BOW, "A man's jaws are his deadliest weapon.");
        addStatistics(EVOKER_BOW, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addAbilities(EVOKER_BOW, "PASSIVE", "Converts shot arrows into Evocation Fangs",
                "SUCCESSFUL HIT", "Immobilizes victim for 1s");

        TRADER_HAT = new ItemStack(Material.PLAYER_HEAD);
        LeatherArmorMeta TRADER_HAT_META = (LeatherArmorMeta) TRADER_HAT.getItemMeta();
        TRADER_HAT_META.setColor(Color.fromRGB(3, 78, 252));
        TRADER_HAT.setItemMeta(TRADER_HAT_META);
        addKeys(TRADER_HAT, TreasureItemKeys.traderHat);
        addDescription(TRADER_HAT, "It rumbles with memories of an exiled salesman.");
        addStatistics(TRADER_HAT, TreasureItemSource.MAGIC, TreasureItemRarity.RARE);

        TRAVELERS_HELMET = new ItemStack(Material.IRON_HELMET);
        addKeys(TRAVELERS_HELMET, TreasureItemKeys.traveler, TreasureItemKeys.travelerHelmet);
        addDescription(TRAVELERS_HELMET, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_HELMET, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_HELMET, Enchantment.DURABILITY, 4);

        TRAVELERS_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE);
        addKeys(TRAVELERS_CHESTPLATE, TreasureItemKeys.traveler, TreasureItemKeys.travelerChestplate);
        addDescription(TRAVELERS_CHESTPLATE, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_CHESTPLATE, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_CHESTPLATE, Enchantment.DURABILITY, 4);

        TRAVELERS_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS);
        addKeys(TRAVELERS_LEGGINGS, TreasureItemKeys.traveler, TreasureItemKeys.travelerLeggings);
        addDescription(TRAVELERS_LEGGINGS, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_LEGGINGS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_LEGGINGS, Enchantment.DURABILITY, 4);

        TRAVELERS_BOOTS = new ItemStack(Material.IRON_BOOTS);
        addKeys(TRAVELERS_BOOTS, TreasureItemKeys.traveler, TreasureItemKeys.travelerBoots);
        addDescription(TRAVELERS_BOOTS, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_BOOTS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_BOOTS, Enchantment.DURABILITY, 4);

        TRAVELERS_SWORD = new ItemStack(Material.IRON_SWORD);
        addKeys(TRAVELERS_SWORD, TreasureItemKeys.traveler, TreasureItemKeys.travelerSword);
        addDescription(TRAVELERS_SWORD, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_SWORD, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_SWORD, Enchantment.DURABILITY, 4);

        TRAVELERS_PICKAXE = new ItemStack(Material.IRON_PICKAXE);
        addKeys(TRAVELERS_PICKAXE, TreasureItemKeys.traveler, TreasureItemKeys.travelerPickaxe);
        addDescription(TRAVELERS_PICKAXE, "A potential beyond compare lies within.");
        addStatistics(TRAVELERS_PICKAXE, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(TRAVELERS_PICKAXE, Enchantment.DURABILITY, 4);

        RAIDER_HELMET = new ItemStack(Material.IRON_HELMET);
        addKeys(RAIDER_HELMET, TreasureItemKeys.raider, TreasureItemKeys.raiderHelmet);
        addDescription(RAIDER_HELMET, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_HELMET, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        RAIDER_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE);
        addKeys(RAIDER_CHESTPLATE, TreasureItemKeys.raider, TreasureItemKeys.raiderChestplate);
        addDescription(RAIDER_CHESTPLATE, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_CHESTPLATE, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        RAIDER_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS);
        addKeys(RAIDER_LEGGINGS, TreasureItemKeys.raider, TreasureItemKeys.raiderLeggings);
        addDescription(RAIDER_LEGGINGS, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_LEGGINGS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        RAIDER_BOOTS = new ItemStack(Material.IRON_BOOTS);
        addKeys(RAIDER_BOOTS, TreasureItemKeys.raider, TreasureItemKeys.raiderBoots);
        addDescription(RAIDER_BOOTS, "It consumes the rage of the wearer.");
        addStatistics(RAIDER_BOOTS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        RAIDER_SWORD = new ItemStack(Material.IRON_SWORD);
        addKeys(RAIDER_SWORD, TreasureItemKeys.raider, TreasureItemKeys.raiderSword);
        addDescription(RAIDER_SWORD, "It consumes the rage of the wielder.");
        addStatistics(RAIDER_SWORD, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_SWORD, Enchantment.DURABILITY, 4, Enchantment.DAMAGE_ALL, 5, Enchantment.FIRE_ASPECT, 2, Enchantment.LOOT_BONUS_MOBS, 3, Enchantment.SWEEPING_EDGE, 3, Enchantment.KNOCKBACK, 2);

        RAIDER_PICKAXE = new ItemStack(Material.IRON_PICKAXE);
        addKeys(RAIDER_PICKAXE, TreasureItemKeys.raider, TreasureItemKeys.raiderPickaxe);
        addDescription(RAIDER_PICKAXE, "It consumes the rage of the wielder.");
        addStatistics(RAIDER_PICKAXE, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(RAIDER_PICKAXE, Enchantment.DURABILITY, 4, Enchantment.DIG_SPEED, 5, Enchantment.SILK_TOUCH, 1);

        AWAKENED_HELMET = new ItemStack(Material.DIAMOND_HELMET);
        addKeys(AWAKENED_HELMET, TreasureItemKeys.awakened, TreasureItemKeys.awakenedHelmet);
        addDescription(AWAKENED_HELMET, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_HELMET, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.WATER_WORKER, 1, Enchantment.OXYGEN, 3);

        AWAKENED_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
        addKeys(AWAKENED_CHESTPLATE, TreasureItemKeys.awakened, TreasureItemKeys.awakenedChestplate);
        addDescription(AWAKENED_CHESTPLATE, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_CHESTPLATE, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        AWAKENED_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
        addKeys(AWAKENED_LEGGINGS, TreasureItemKeys.awakened, TreasureItemKeys.awakenedLeggings);
        addDescription(AWAKENED_LEGGINGS, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_LEGGINGS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        AWAKENED_BOOTS = new ItemStack(Material.DIAMOND_BOOTS);
        addKeys(AWAKENED_BOOTS, TreasureItemKeys.awakened, TreasureItemKeys.awakenedBoots);
        addDescription(AWAKENED_BOOTS, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_BOOTS, TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(AWAKENED_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.PROTECTION_FALL, 4, Enchantment.DEPTH_STRIDER, 3);

        AWAKENED_SWORD = new ItemStack(Material.DIAMOND_SWORD);
        addKeys(AWAKENED_SWORD, TreasureItemKeys.awakened, TreasureItemKeys.awakenedSword);
        addDescription(AWAKENED_SWORD, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_SWORD, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(AWAKENED_SWORD, Enchantment.DURABILITY, 4, Enchantment.DAMAGE_ALL, 9, Enchantment.FIRE_ASPECT, 3, Enchantment.LOOT_BONUS_MOBS, 3, Enchantment.SWEEPING_EDGE, 5, Enchantment.KNOCKBACK, 3, Enchantment.MENDING, 1);

        AWAKENED_PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE);
        addKeys(AWAKENED_PICKAXE, TreasureItemKeys.awakened, TreasureItemKeys.awakenedPickaxe);
        addDescription(AWAKENED_PICKAXE, "Cast by darkness, poured in starlight.");
        addStatistics(AWAKENED_PICKAXE, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(AWAKENED_PICKAXE, Enchantment.DURABILITY, 4, Enchantment.DIG_SPEED, 5, Enchantment.SILK_TOUCH, 2, Enchantment.MENDING, 1);

        ETERNAL_HELMET = new ItemStack(Material.DIAMOND_HELMET);
        addKeys(ETERNAL_HELMET, TreasureItemKeys.eternal, TreasureItemKeys.eternalHelmet);
        addDescription(ETERNAL_HELMET, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_HELMET, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_HELMET, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.WATER_WORKER, 2, Enchantment.OXYGEN, 3, Enchantment.MENDING, 1);  // Unbreaking IV, Protection VIII, Aqua Affinity II, Respiration III, Mending I

        ETERNAL_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
        addKeys(ETERNAL_CHESTPLATE, TreasureItemKeys.eternal, TreasureItemKeys.eternalChestplate);
        addDescription(ETERNAL_CHESTPLATE, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_CHESTPLATE, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_CHESTPLATE, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.MENDING, 1);  // Unbreaking IV, Protection VIII, Mending I

        ETERNAL_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
        addKeys(ETERNAL_LEGGINGS, TreasureItemKeys.eternal, TreasureItemKeys.eternalLeggings);
        addDescription(ETERNAL_LEGGINGS, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_LEGGINGS, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_LEGGINGS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.MENDING, 1);  // Unbreaking IV, Protection VIII, Mending I

        ETERNAL_BOOTS = new ItemStack(Material.DIAMOND_BOOTS);
        addKeys(ETERNAL_BOOTS, TreasureItemKeys.eternal, TreasureItemKeys.eternalBoots);
        addDescription(ETERNAL_BOOTS, "Flectere si nequeo superos, Acheronta movebo.");
        addStatistics(ETERNAL_BOOTS, TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addEnchantments(ETERNAL_BOOTS, Enchantment.DURABILITY, 4, Enchantment.PROTECTION_ENVIRONMENTAL, 8, Enchantment.PROTECTION_FALL, 4, Enchantment.DEPTH_STRIDER, 3, Enchantment.SOUL_SPEED, 3, Enchantment.MENDING, 1);  // Unbreaking IV, Protection VIII, Feather Falling IV, Depth Strider III, Soul Speed III, Mending I
    }

}
