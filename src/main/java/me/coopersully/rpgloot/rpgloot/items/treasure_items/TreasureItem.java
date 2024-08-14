package me.coopersully.rpgloot.rpgloot.items.treasure_items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public static void setModelData(@NotNull ItemStack item, int customModelData) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
    }

    public static void setAttributeModifier(@NotNull ItemStack item, Attribute attribute, AttributeModifier modifier) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addAttributeModifier(attribute, modifier);
            item.setItemMeta(meta);
        }
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
    public static ItemStack PITY_TROPHY;
    public static ItemStack GONK;
    public static ItemStack ENDERS_WAKE;
    public static ItemStack GROVEGUARDIAN_BOOTS;
    public static ItemStack EVENTHORIZON_HARNESS;
    public static ItemStack SWIFTSTRIKE_WOODEN_SWORD;
    public static ItemStack SWIFTSTRIKE_STONE_SWORD;
    public static ItemStack SWIFTSTRIKE_GOLDEN_SWORD;
    public static ItemStack SWIFTSTRIKE_IRON_SWORD;
    public static ItemStack SWIFTSTRIKE_DIAMOND_SWORD;
    public static ItemStack SWIFTSTRIKE_NETHERITE_SWORD;
    public static ItemStack LIGHTRENDER_HELMET;
    public static ItemStack LIGHTRENDER_CHESTPLATE;
    public static ItemStack LIGHTRENDER_LEGGINGS;
    public static ItemStack LIGHTRENDER_BOOTS;
    public static ItemStack CRYRUSH_HELMET;
    public static ItemStack CRYRUSH_CHESTPLATE;
    public static ItemStack CRYRUSH_LEGGINGS;
    public static ItemStack CRYRUSH_BOOTS;
    public static ItemStack VELOCITY_HELMET;
    public static ItemStack VELOCITY_CHESTPLATE;
    public static ItemStack VELOCITY_LEGGINGS;
    public static ItemStack VELOCITY_BOOTS;
    public static ItemStack WOODEN_TOMAHAWK;
    public static ItemStack STONE_TOMAHAWK;
    public static ItemStack GOLDEN_TOMAHAWK;
    public static ItemStack IRON_TOMAHAWK;
    public static ItemStack DIAMOND_TOMAHAWK;
    public static ItemStack NETHERITE_TOMAHAWK;
    public static ItemStack INNERTUBE;
    public static ItemStack EXP_BLASTER;
    public static ItemStack FLAMETHROWER;
    public static ItemStack ADVENTURE_TIME_HAT;
    public static ItemStack ALEX_HAT;
    public static ItemStack ARCHEOLOGY_HAT_BLUE;
    public static ItemStack ARCHEOLOGY_HAT_GREEN;
    public static ItemStack ARCHEOLOGY_HAT_TAN;
    public static ItemStack ARI_HAT;
    public static ItemStack BEE_HAT;
    public static ItemStack BROWN_HAT;
    public static ItemStack CAKE_DADDY_HAT;
    public static ItemStack CAMERA_EYE_IMPLANT_HAT;
    public static ItemStack CHICKEN_CAP_HAT;
    public static ItemStack COPPER_GOLEM_MASK_HAT;
    public static ItemStack COWPOKE_HIDE_HAT;
    public static ItemStack CREEPER_HIDE_HAT;
    public static ItemStack DONKEY_HIDE_HOOD_HAT;
    public static ItemStack DRIPLEAF_WIDEBRIM_HAT;
    public static ItemStack EFE_HAT;
    public static ItemStack ENDER_DRAGON_HIDE_HAT;
    public static ItemStack ENDERMAN_HIDE_HAT;
    public static ItemStack FLIGHT_HELMET_HAT;
    public static ItemStack FLIGHT_HELMET2_HAT;
    public static ItemStack FOX_HIDE_HAT;
    public static ItemStack GEORGIA_DEVIL_HAT;
    public static ItemStack GHAST_MASK_HAT;
    public static ItemStack GLARE_HIDE_HAT;
    public static ItemStack GOAT_SKIN_HAT;
    public static ItemStack GRANDPAS_HAT;
    public static ItemStack GUARDIAN_EYE_MASK_HAT;
    public static ItemStack HAT_NAMES_HAT;
    public static ItemStack HOGLIN_HAT;
    public static ItemStack HORSE_SKULL_MASK_HAT;
    public static ItemStack IRON_GOLEM_MASK_HAT;
    public static ItemStack KIA_HAT;
    public static ItemStack KING_TRISKELE_HAT;
    public static ItemStack LEGENDARY_GOAT_HOOD_HAT;
    public static ItemStack LOG_HOOD_HAT;
    public static ItemStack MAKENA_HAT;
    public static ItemStack MINING_HELMET_HAT;
    public static ItemStack MOOBLOOM_HIDE_HAT;
    public static ItemStack MOOSHROOM_HIDE_HAT;
    public static ItemStack MOSSY_CAP_HAT;
    public static ItemStack NOOR_HAT;
    public static ItemStack OCELOT_HIDE_HOOD_HAT;
    public static ItemStack PHANTOM_HIDE_HAT;
    public static ItemStack PILLAGER_HAT;
    public static ItemStack PLAGUE_CLERIC_HAT;
    public static ItemStack PLAGUE_DOCTOR_HAT;
    public static ItemStack PLATYPUS_CAP_HAT;
    public static ItemStack POLAR_BEAR_HOOD_HAT;
    public static ItemStack PUFFERFISH_HIDE_HAT;
    public static ItemStack RAVAGER_HAT;
    public static ItemStack SALMON_SCALED_HAT;
    public static ItemStack SCULK_HELSING_HAT;
    public static ItemStack SNIFFER_HIDE_HAT;
    public static ItemStack SPECTACLES_F_HAT;
    public static ItemStack SPECTACLES_M_HAT;
    public static ItemStack STEVE_HAT;
    public static ItemStack STRAW_HAT;
    public static ItemStack SUNNY_HAT;
    public static ItemStack TUFF_GOLEM_MASK_HAT;
    public static ItemStack VAN_HELSING_HAT;
    public static ItemStack WARDEN_HIDE_HAT;
    public static ItemStack WITHER_SKULL_HAT;
    public static ItemStack ZOMBIE_HORSE_HOOD_HAT;
    public static ItemStack ZURI_HAT;


    public static List<ItemStack> TREASURE_ITEMS = new ArrayList<>();

    public static void loadTreasureItems() {

        BOUNTY_BOW = new ItemStack(Material.BOW, 1);
        addKeys(BOUNTY_BOW, TreasureItemKeys.bountyBow);
        addDescription(BOUNTY_BOW, "Unbeatable in genetic perfection.");
        addStatistics(BOUNTY_BOW, "Bow of the Bounty", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(BOUNTY_BOW, Enchantment.ARROW_INFINITE, 1, Enchantment.MENDING, 1);
        TREASURE_ITEMS.add(BOUNTY_BOW);

        WAND_OF_INDECISION = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(WAND_OF_INDECISION, 6605);
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
        setModelData(NIGHTMARE_FUEL, 6602);
        addKeys(NIGHTMARE_FUEL, TreasureItemKeys.nightmareFuel);
        addDescription(NIGHTMARE_FUEL, "It whispers and glows with rage.");
        addStatistics(NIGHTMARE_FUEL, "Nightmare Fuel", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        TREASURE_ITEMS.add(NIGHTMARE_FUEL);

        MINERS_HAT = new ItemStack(Material.IRON_HELMET, 1);
        addKeys(MINERS_HAT, TreasureItemKeys.hat, TreasureItemKeys.minersHat);
        addDescription(MINERS_HAT, "Your guide into the depths.");
        addStatistics(MINERS_HAT, "Miner's Hard hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(MINERS_HAT, "PASSIVE", "Illuminates nearby area");
        TREASURE_ITEMS.add(MINERS_HAT);

        ITEM_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ITEM_MAGNET, 6603);
        addKeys(ITEM_MAGNET, TreasureItemKeys.itemMagnet);
        addDescription(ITEM_MAGNET, "Material goods attract the soul.");
        addStatistics(ITEM_MAGNET, "Item Magnet Mk.I", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(ITEM_MAGNET, "PASSIVE", "Magnetizes nearby items");
        TREASURE_ITEMS.add(ITEM_MAGNET);

        EXP_MAGNET = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(EXP_MAGNET, 6603);
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
        addKeys(TRADER_HAT, TreasureItemKeys.hat, TreasureItemKeys.traderHat);
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
        addDescription(BEE_EGG, "A tiny buzz heralds the keeper of LIVING's essence.");
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
        LeatherArmorMeta DRAGONHIDE_HELMET_META = (LeatherArmorMeta) DRAGONHIDE_HELMET.getItemMeta();
        DRAGONHIDE_HELMET_META.setColor(Color.fromRGB(0, 0, 0));
        DRAGONHIDE_HELMET.setItemMeta(DRAGONHIDE_HELMET_META);
        addKeys(DRAGONHIDE_HELMET, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideHelmet);
        addDescription(DRAGONHIDE_HELMET, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_HELMET, "Dragonhide Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(DRAGONHIDE_HELMET, "PASSIVE", "Full immunity to explosion damage.");
        TREASURE_ITEMS.add(DRAGONHIDE_HELMET);

        DRAGONHIDE_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta DRAGONHIDE_CHESTPLATE_META = (LeatherArmorMeta) DRAGONHIDE_CHESTPLATE.getItemMeta();
        DRAGONHIDE_CHESTPLATE_META.setColor(Color.fromRGB(0, 0, 0));
        DRAGONHIDE_CHESTPLATE.setItemMeta(DRAGONHIDE_CHESTPLATE_META);
        addKeys(DRAGONHIDE_CHESTPLATE, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideChestplate);
        addDescription(DRAGONHIDE_CHESTPLATE, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_CHESTPLATE, "Dragonhide Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(DRAGONHIDE_CHESTPLATE, "PASSIVE", "Full immunity to explosion damage.");
        TREASURE_ITEMS.add(DRAGONHIDE_CHESTPLATE);

        DRAGONHIDE_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta DRAGONHIDE_LEGGINGS_META = (LeatherArmorMeta) DRAGONHIDE_LEGGINGS.getItemMeta();
        DRAGONHIDE_LEGGINGS_META.setColor(Color.fromRGB(0, 0, 0));
        DRAGONHIDE_LEGGINGS.setItemMeta(DRAGONHIDE_LEGGINGS_META);
        addKeys(DRAGONHIDE_LEGGINGS, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideLeggings);
        addDescription(DRAGONHIDE_LEGGINGS, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_LEGGINGS, "Dragonhide Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(DRAGONHIDE_LEGGINGS, "PASSIVE", "Full immunity to explosion damage.");
        TREASURE_ITEMS.add(DRAGONHIDE_LEGGINGS);

        DRAGONHIDE_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta DRAGONHIDE_BOOTS_META = (LeatherArmorMeta) DRAGONHIDE_BOOTS.getItemMeta();
        DRAGONHIDE_BOOTS_META.setColor(Color.fromRGB(0, 0, 0));
        DRAGONHIDE_BOOTS.setItemMeta(DRAGONHIDE_BOOTS_META);
        addKeys(DRAGONHIDE_BOOTS, TreasureItemKeys.dragonhide, TreasureItemKeys.dragonhideBoots);
        addDescription(DRAGONHIDE_BOOTS, "Formed from the skin of a beast.");
        addStatistics(DRAGONHIDE_BOOTS, "Dragonhide Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addAbilities(DRAGONHIDE_BOOTS, "PASSIVE", "Full immunity to explosion damage.");
        TREASURE_ITEMS.add(DRAGONHIDE_BOOTS);

        PITY_TROPHY = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PITY_TROPHY, 6604);
        addDescription(PITY_TROPHY, "Gee, thanks?");
        addStatistics(PITY_TROPHY, "Pity Trophy", TreasureItemSource.LIVING, TreasureItemRarity.DARK);
        addEnchantments(PITY_TROPHY, Enchantment.KNOCKBACK, 10);
        addKeys(PITY_TROPHY, TreasureItemKeys.pityTrophy);
        TREASURE_ITEMS.add(PITY_TROPHY);

        ENDERS_WAKE = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ENDERS_WAKE, 6606);
        addDescription(ENDERS_WAKE, "It hums with the spirit of the void.");
        addStatistics(ENDERS_WAKE, "Ender's Wake", TreasureItemSource.MAGIC, TreasureItemRarity.MYTHICAL);
        addAbilities(ENDERS_WAKE, "USE ITEM", "Throw a teleportation pearl");
        addKeys(ENDERS_WAKE, TreasureItemKeys.endersWake);
        TREASURE_ITEMS.add(ENDERS_WAKE);

        GONK = new ItemStack(Material.IRON_AXE, 1);
        setModelData(GONK, 6601);
        addDescription(GONK, "The earth cowers as its commander approaches.");
        addStatistics(GONK, "Gonk the B.F.H.", TreasureItemSource.LIVING, TreasureItemRarity.MYTHICAL);
        setAttributeModifier(GONK, Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "gonk_attack_speed", -2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        setAttributeModifier(GONK, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "gonk_movement_speed", -0.025, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        addEnchantments(GONK, Enchantment.DAMAGE_ALL, 40);
        addKeys(GONK, TreasureItemKeys.gonk);
        TREASURE_ITEMS.add(GONK);

        GROVEGUARDIAN_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta GROVEGUARDIAN_BOOTS_META = (LeatherArmorMeta) GROVEGUARDIAN_BOOTS.getItemMeta();
        GROVEGUARDIAN_BOOTS_META.setColor(Color.fromRGB(36, 135, 33));
        GROVEGUARDIAN_BOOTS.setItemMeta(GROVEGUARDIAN_BOOTS_META);
        addKeys(GROVEGUARDIAN_BOOTS, TreasureItemKeys.groveGuardianBoots);
        addDescription(GROVEGUARDIAN_BOOTS, "Shoes veiled in the canopy's embrace.");
        addStatistics(GROVEGUARDIAN_BOOTS, "Grove Guardian Boots", TreasureItemSource.MAGIC, TreasureItemRarity.UNCOMMON);
        addAbilities(GROVEGUARDIAN_BOOTS, "SPRINT", "Additional speed in forest biomes");
        TREASURE_ITEMS.add(GROVEGUARDIAN_BOOTS);

        EVENTHORIZON_HARNESS = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        addKeys(EVENTHORIZON_HARNESS, TreasureItemKeys.eventHorizonHarness);
        addDescription(EVENTHORIZON_HARNESS, "Collapse the void, explode into the firmament");
        addStatistics(EVENTHORIZON_HARNESS, "Event Horizon Harness", TreasureItemSource.UNKNOWN, TreasureItemRarity.MYTHICAL);
        addAbilities(EVENTHORIZON_HARNESS, "PASSIVE", "Falling into the void launches the player into the sky");
        TREASURE_ITEMS.add(EVENTHORIZON_HARNESS);

        SWIFTSTRIKE_WOODEN_SWORD = new ItemStack(Material.WOODEN_SWORD, 1);
        addKeys(SWIFTSTRIKE_WOODEN_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeWoodenSword);
        addDescription(SWIFTSTRIKE_WOODEN_SWORD, "Beneath the howl, a swift silence.");
        addStatistics(SWIFTSTRIKE_WOODEN_SWORD, "Swiftstrike Wooden Sword", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        addAbilities(SWIFTSTRIKE_WOODEN_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_WOODEN_SWORD);

        SWIFTSTRIKE_STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
        addKeys(SWIFTSTRIKE_STONE_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeStoneSword);
        addDescription(SWIFTSTRIKE_STONE_SWORD, "Behind the whisper, a fleeting shadow.");
        addStatistics(SWIFTSTRIKE_STONE_SWORD, "Swiftstrike Stone Sword", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        addAbilities(SWIFTSTRIKE_STONE_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_STONE_SWORD);

        SWIFTSTRIKE_IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        addKeys(SWIFTSTRIKE_IRON_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeIronSword);
        addDescription(SWIFTSTRIKE_IRON_SWORD, "Violence only requires but a leap.");
        addStatistics(SWIFTSTRIKE_IRON_SWORD, "Swiftstrike Iron Sword", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        addAbilities(SWIFTSTRIKE_IRON_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_IRON_SWORD);

        SWIFTSTRIKE_GOLDEN_SWORD = new ItemStack(Material.GOLDEN_SWORD, 1);
        addKeys(SWIFTSTRIKE_GOLDEN_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeGoldenSword);
        addDescription(SWIFTSTRIKE_GOLDEN_SWORD, "Through the gleam, a fleeting rush.");
        addStatistics(SWIFTSTRIKE_GOLDEN_SWORD, "Swiftstrike Golden Sword", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        addAbilities(SWIFTSTRIKE_GOLDEN_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_GOLDEN_SWORD);

        SWIFTSTRIKE_DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
        addKeys(SWIFTSTRIKE_DIAMOND_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeDiamondSword);
        addDescription(SWIFTSTRIKE_DIAMOND_SWORD, "Time stirs at the edge of thought.");
        addStatistics(SWIFTSTRIKE_DIAMOND_SWORD, "Swiftstrike Diamond Sword", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(SWIFTSTRIKE_DIAMOND_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_DIAMOND_SWORD);

        SWIFTSTRIKE_NETHERITE_SWORD = new ItemStack(Material.NETHERITE_SWORD, 1);
        addKeys(SWIFTSTRIKE_NETHERITE_SWORD, TreasureItemKeys.swiftstrike, TreasureItemKeys.swiftstrikeNetheriteSword);
        addDescription(SWIFTSTRIKE_NETHERITE_SWORD, "Light is torn by a silent charge.");
        addStatistics(SWIFTSTRIKE_NETHERITE_SWORD, "Swiftstrike Netherite Sword", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(SWIFTSTRIKE_NETHERITE_SWORD, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(SWIFTSTRIKE_NETHERITE_SWORD);

        LIGHTRENDER_HELMET = new ItemStack(Material.GOLDEN_HELMET, 1);
        addKeys(LIGHTRENDER_HELMET, TreasureItemKeys.lightrender, TreasureItemKeys.lightrenderHelmet);
        addDescription(LIGHTRENDER_HELMET, "A speed that distorts the serene.");
        addStatistics(LIGHTRENDER_HELMET, "Lightrender Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        addEnchantments(LIGHTRENDER_HELMET, Enchantment.DURABILITY, 1);
        setAttributeModifier(LIGHTRENDER_HELMET, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "lightrender_helmet_movement_speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        TREASURE_ITEMS.add(LIGHTRENDER_HELMET);

        LIGHTRENDER_CHESTPLATE = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
        addKeys(LIGHTRENDER_CHESTPLATE, TreasureItemKeys.lightrender, TreasureItemKeys.lightrenderChestplate);
        addDescription(LIGHTRENDER_CHESTPLATE, "A speed that distorts the serene.");
        addStatistics(LIGHTRENDER_CHESTPLATE, "Lightrender Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        addEnchantments(LIGHTRENDER_CHESTPLATE, Enchantment.DURABILITY, 1);
        setAttributeModifier(LIGHTRENDER_CHESTPLATE, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "lightrender_chestplate_movement_speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        TREASURE_ITEMS.add(LIGHTRENDER_CHESTPLATE);

        LIGHTRENDER_LEGGINGS = new ItemStack(Material.GOLDEN_LEGGINGS, 1);
        addKeys(LIGHTRENDER_LEGGINGS, TreasureItemKeys.lightrender, TreasureItemKeys.lightrenderLeggings);
        addDescription(LIGHTRENDER_LEGGINGS, "A speed that distorts the serene.");
        addStatistics(LIGHTRENDER_LEGGINGS, "Lightrender Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        addEnchantments(LIGHTRENDER_LEGGINGS, Enchantment.DURABILITY, 1);
        setAttributeModifier(LIGHTRENDER_LEGGINGS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "lightrender_leggings_movement_speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        TREASURE_ITEMS.add(LIGHTRENDER_LEGGINGS);

        LIGHTRENDER_BOOTS = new ItemStack(Material.GOLDEN_BOOTS, 1);
        addKeys(LIGHTRENDER_BOOTS, TreasureItemKeys.lightrender, TreasureItemKeys.lightrenderBoots);
        addDescription(LIGHTRENDER_BOOTS, "A speed that distorts the serene.");
        addStatistics(LIGHTRENDER_BOOTS, "Lightrender Boots", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        addEnchantments(LIGHTRENDER_BOOTS, Enchantment.DURABILITY, 1);
        setAttributeModifier(LIGHTRENDER_BOOTS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "lightrender_boots_movement_speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        TREASURE_ITEMS.add(LIGHTRENDER_BOOTS);

        CRYRUSH_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        addKeys(CRYRUSH_HELMET, TreasureItemKeys.cryrush, TreasureItemKeys.cryrushHelmet);
        addDescription(CRYRUSH_HELMET, "Speed that shatters tranquility.");
        addStatistics(CRYRUSH_HELMET, "Cryrush Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(CRYRUSH_HELMET, Enchantment.DURABILITY, 6);
        setAttributeModifier(CRYRUSH_HELMET, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "cryrush_helmet_movement_speed", 0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        TREASURE_ITEMS.add(CRYRUSH_HELMET);

        CRYRUSH_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        addKeys(CRYRUSH_CHESTPLATE, TreasureItemKeys.cryrush, TreasureItemKeys.cryrushChestplate);
        addDescription(CRYRUSH_CHESTPLATE, "Speed that shatters tranquility.");
        addStatistics(CRYRUSH_CHESTPLATE, "Cryrush Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(CRYRUSH_CHESTPLATE, Enchantment.DURABILITY, 6);
        setAttributeModifier(CRYRUSH_CHESTPLATE, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "cryrush_chestplate_movement_speed", 0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        TREASURE_ITEMS.add(CRYRUSH_CHESTPLATE);

        CRYRUSH_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
        addKeys(CRYRUSH_LEGGINGS, TreasureItemKeys.cryrush, TreasureItemKeys.cryrushLeggings);
        addDescription(CRYRUSH_LEGGINGS, "Speed that shatters tranquility.");
        addStatistics(CRYRUSH_LEGGINGS, "Cryrush Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(CRYRUSH_LEGGINGS, Enchantment.DURABILITY, 6);
        setAttributeModifier(CRYRUSH_LEGGINGS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "cryrush_leggings_movement_speed", 0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        TREASURE_ITEMS.add(CRYRUSH_LEGGINGS);

        CRYRUSH_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        addKeys(CRYRUSH_BOOTS, TreasureItemKeys.cryrush, TreasureItemKeys.cryrushBoots);
        addDescription(CRYRUSH_BOOTS, "Speed that shatters tranquility.");
        addStatistics(CRYRUSH_BOOTS, "Cryrush Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(CRYRUSH_BOOTS, Enchantment.DURABILITY, 6);
        setAttributeModifier(CRYRUSH_BOOTS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "cryrush_boots_movement_speed", 0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        TREASURE_ITEMS.add(CRYRUSH_BOOTS);

        VELOCITY_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
        addKeys(VELOCITY_HELMET, TreasureItemKeys.velocity, TreasureItemKeys.velocityHelmet);
        addDescription(VELOCITY_HELMET, "A flash of brilliance as haste is chained to the soul.");
        addStatistics(VELOCITY_HELMET, "Velocity Helmet", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(VELOCITY_HELMET, Enchantment.BINDING_CURSE, 1, Enchantment.VANISHING_CURSE, 1, Enchantment.MENDING, 1);
        setAttributeModifier(VELOCITY_HELMET, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "velocity_helmet_movement_speed", 0.06, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        TREASURE_ITEMS.add(VELOCITY_HELMET);

        VELOCITY_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        addKeys(VELOCITY_CHESTPLATE, TreasureItemKeys.velocity, TreasureItemKeys.velocityChestplate);
        addDescription(VELOCITY_CHESTPLATE, "A flash of brilliance as haste is chained to the soul.");
        addStatistics(VELOCITY_CHESTPLATE, "Velocity Chestplate", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(VELOCITY_CHESTPLATE, Enchantment.BINDING_CURSE, 1, Enchantment.VANISHING_CURSE, 1, Enchantment.MENDING, 1);
        setAttributeModifier(VELOCITY_CHESTPLATE, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "velocity_chestplate_movement_speed", 0.06, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        TREASURE_ITEMS.add(VELOCITY_CHESTPLATE);

        VELOCITY_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        addKeys(VELOCITY_LEGGINGS, TreasureItemKeys.velocity, TreasureItemKeys.velocityLeggings);
        addDescription(VELOCITY_LEGGINGS, "A flash of brilliance as haste is chained to the soul.");
        addStatistics(VELOCITY_LEGGINGS, "Velocity Leggings", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(VELOCITY_LEGGINGS, Enchantment.BINDING_CURSE, 1, Enchantment.VANISHING_CURSE, 1, Enchantment.MENDING, 1);
        setAttributeModifier(VELOCITY_LEGGINGS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "velocity_leggings_movement_speed", 0.06, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        TREASURE_ITEMS.add(VELOCITY_LEGGINGS);

        VELOCITY_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
        addKeys(VELOCITY_BOOTS, TreasureItemKeys.velocity, TreasureItemKeys.velocityBoots);
        addDescription(VELOCITY_BOOTS, "A flash of brilliance as haste is chained to the soul.");
        addStatistics(VELOCITY_BOOTS, "Velocity Boots", TreasureItemSource.MAGIC, TreasureItemRarity.EPIC);
        addEnchantments(VELOCITY_BOOTS, Enchantment.BINDING_CURSE, 1, Enchantment.VANISHING_CURSE, 1, Enchantment.MENDING, 1);
        setAttributeModifier(VELOCITY_BOOTS, Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "velocity_boots_movement_speed", 0.06, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        TREASURE_ITEMS.add(VELOCITY_BOOTS);

        WOODEN_TOMAHAWK = new ItemStack(Material.WOODEN_AXE, 1);
        addKeys(WOODEN_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.woodenTomahawk);
        addDescription(WOODEN_TOMAHAWK, "A shadow in the forest, silent as the falling leaf.");
        addStatistics(WOODEN_TOMAHAWK, "Wooden Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        addAbilities(WOODEN_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(WOODEN_TOMAHAWK);

        STONE_TOMAHAWK = new ItemStack(Material.STONE_AXE, 1);
        addKeys(STONE_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.stoneTomahawk);
        addDescription(STONE_TOMAHAWK, "Echoes of ancient battles linger in its swing.");
        addStatistics(STONE_TOMAHAWK, "Stone Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        addAbilities(STONE_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(STONE_TOMAHAWK);

        IRON_TOMAHAWK = new ItemStack(Material.IRON_AXE, 1);
        addKeys(IRON_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.ironTomahawk);
        addDescription(IRON_TOMAHAWK, "Every arc carries the whisper of old trees.");
        addStatistics(IRON_TOMAHAWK, "Iron Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        addAbilities(IRON_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(IRON_TOMAHAWK);

        GOLDEN_TOMAHAWK = new ItemStack(Material.GOLDEN_AXE, 1);
        addKeys(GOLDEN_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.goldenTomahawk);
        addDescription(GOLDEN_TOMAHAWK, "Cuts deeper than the cold, cleaves faster than the wind.");
        addStatistics(GOLDEN_TOMAHAWK, "Golden Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        addAbilities(GOLDEN_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(GOLDEN_TOMAHAWK);

        DIAMOND_TOMAHAWK = new ItemStack(Material.DIAMOND_AXE, 1);
        addKeys(DIAMOND_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.diamondTomahawk);
        addDescription(DIAMOND_TOMAHAWK, "Its edge, like the horizon, divides the defeated from the enduring.");
        addStatistics(DIAMOND_TOMAHAWK, "Diamond Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(DIAMOND_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(DIAMOND_TOMAHAWK);

        NETHERITE_TOMAHAWK = new ItemStack(Material.NETHERITE_AXE, 1);
        addKeys(NETHERITE_TOMAHAWK, TreasureItemKeys.tomahawk, TreasureItemKeys.netheriteTomahawk);
        addDescription(NETHERITE_TOMAHAWK, "In its heft, the weight of legends; in its strike, the breath of gods.");
        addStatistics(NETHERITE_TOMAHAWK, "Netherite Tomahawk", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        addAbilities(NETHERITE_TOMAHAWK, "USE ITEM", "Dash forward");
        TREASURE_ITEMS.add(NETHERITE_TOMAHAWK);

        INNERTUBE = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(INNERTUBE, 6607);
        addKeys(INNERTUBE, TreasureItemKeys.innertube);
        addDescription(INNERTUBE, "Slippery and wet.");
        addStatistics(INNERTUBE, "Innertube", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(INNERTUBE);

        EXP_BLASTER = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(EXP_BLASTER, 6608);
        addKeys(EXP_BLASTER, TreasureItemKeys.expBlaster);
        addDescription(EXP_BLASTER, "Bleep, blorb, zoop.");
        addStatistics(EXP_BLASTER, "EXP Blaster", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        addAbilities(EXP_BLASTER, "USE ITEM", "Shoot experience. Requires experience as ammunition");
        TREASURE_ITEMS.add(EXP_BLASTER);

        FLAMETHROWER = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(FLAMETHROWER, 6609);
        addKeys(FLAMETHROWER, TreasureItemKeys.flamethrower);
        addDescription(FLAMETHROWER, "Bring the nether to you.");
        addStatistics(FLAMETHROWER, "Flamethrower", TreasureItemSource.UNKNOWN, TreasureItemRarity.EPIC);
        addAbilities(FLAMETHROWER, "USE ITEM", "Shoot flames. Requires Blaze Powder as ammunition");
        TREASURE_ITEMS.add(FLAMETHROWER);

        ADVENTURE_TIME_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ADVENTURE_TIME_HAT, 6611);
        addKeys(ADVENTURE_TIME_HAT, TreasureItemKeys.hat, TreasureItemKeys.adventureTimeHat);
        addDescription(ADVENTURE_TIME_HAT, "Time for adventure!");
        addStatistics(ADVENTURE_TIME_HAT, "Adventure Time Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(ADVENTURE_TIME_HAT);

        ALEX_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ALEX_HAT, 6612);
        addKeys(ALEX_HAT, TreasureItemKeys.hat, TreasureItemKeys.alexHat);
        addDescription(ALEX_HAT, "A familiar headpiece.");
        addStatistics(ALEX_HAT, "Alex Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(ALEX_HAT);

        ARCHEOLOGY_HAT_BLUE = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ARCHEOLOGY_HAT_BLUE, 6613);
        addKeys(ARCHEOLOGY_HAT_BLUE, TreasureItemKeys.archeologyHatBlue);
        addDescription(ARCHEOLOGY_HAT_BLUE, "Discover ancient secrets.");
        addStatistics(ARCHEOLOGY_HAT_BLUE, "Archeology Hat Blue", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(ARCHEOLOGY_HAT_BLUE);

        ARCHEOLOGY_HAT_GREEN = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ARCHEOLOGY_HAT_GREEN, 6614);
        addKeys(ARCHEOLOGY_HAT_GREEN, TreasureItemKeys.archeologyHatGreen);
        addDescription(ARCHEOLOGY_HAT_GREEN, "Uncover lost worlds.");
        addStatistics(ARCHEOLOGY_HAT_GREEN, "Archeology Hat Green", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(ARCHEOLOGY_HAT_GREEN);

        ARCHEOLOGY_HAT_TAN = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ARCHEOLOGY_HAT_TAN, 6615);
        addKeys(ARCHEOLOGY_HAT_TAN, TreasureItemKeys.archeologyHatTan);
        addDescription(ARCHEOLOGY_HAT_TAN, "Exploring forgotten tales.");
        addStatistics(ARCHEOLOGY_HAT_TAN, "Archeology Hat Tan", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(ARCHEOLOGY_HAT_TAN);

        ARI_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ARI_HAT, 6616);
        addKeys(ARI_HAT, TreasureItemKeys.hat, TreasureItemKeys.ariHat);
        addDescription(ARI_HAT, "Mystery and elegance.");
        addStatistics(ARI_HAT, "Ari Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(ARI_HAT);

        BEE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(BEE_HAT, 6617);
        addKeys(BEE_HAT, TreasureItemKeys.hat, TreasureItemKeys.beeHat);
        addDescription(BEE_HAT, "Buzzing with life.");
        addStatistics(BEE_HAT, "Bee Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(BEE_HAT);

        BROWN_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(BROWN_HAT, 6618);
        addKeys(BROWN_HAT, TreasureItemKeys.hat, TreasureItemKeys.brownHat);
        addDescription(BROWN_HAT, "Simple yet stylish.");
        addStatistics(BROWN_HAT, "Brown Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(BROWN_HAT);

        CAKE_DADDY_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(CAKE_DADDY_HAT, 6619);
        addKeys(CAKE_DADDY_HAT, TreasureItemKeys.hat, TreasureItemKeys.cakeDaddyHat);
        addDescription(CAKE_DADDY_HAT, "Sweet and whimsical.");
        addStatistics(CAKE_DADDY_HAT, "Cake Daddy Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(CAKE_DADDY_HAT);

        CAMERA_EYE_IMPLANT_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(CAMERA_EYE_IMPLANT_HAT, 6620);
        addKeys(CAMERA_EYE_IMPLANT_HAT, TreasureItemKeys.hat, TreasureItemKeys.cameraEyeImplantHat);
        addDescription(CAMERA_EYE_IMPLANT_HAT, "Seeing beyond sight.");
        addStatistics(CAMERA_EYE_IMPLANT_HAT, "Camera Eye Implant Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(CAMERA_EYE_IMPLANT_HAT);

        CHICKEN_CAP_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(CHICKEN_CAP_HAT, 6621);
        addKeys(CHICKEN_CAP_HAT, TreasureItemKeys.hat, TreasureItemKeys.chickenCapHat);
        addDescription(CHICKEN_CAP_HAT, "Cluck, cluck, cluck.");
        addStatistics(CHICKEN_CAP_HAT, "Chicken Cap Hat", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(CHICKEN_CAP_HAT);

        COPPER_GOLEM_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(COPPER_GOLEM_MASK_HAT, 6622);
        addKeys(COPPER_GOLEM_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.copperGolemMaskHat);
        addDescription(COPPER_GOLEM_MASK_HAT, "Rustic charm.");
        addStatistics(COPPER_GOLEM_MASK_HAT, "Copper Golem Mask Hat", TreasureItemSource.MAGIC, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(COPPER_GOLEM_MASK_HAT);

        COWPOKE_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(COWPOKE_HIDE_HAT, 6623);
        addKeys(COWPOKE_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.cowpokeHideHat);
        addDescription(COWPOKE_HIDE_HAT, "Howdy, partner.");
        addStatistics(COWPOKE_HIDE_HAT, "Cowpoke Hide Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(COWPOKE_HIDE_HAT);

        CREEPER_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(CREEPER_HIDE_HAT, 6624);
        addKeys(CREEPER_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.creeperHideHat);
        addDescription(CREEPER_HIDE_HAT, "Sssssss.");
        addStatistics(CREEPER_HIDE_HAT, "Creeper Hide Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(CREEPER_HIDE_HAT);

        DONKEY_HIDE_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(DONKEY_HIDE_HOOD_HAT, 6625);
        addKeys(DONKEY_HIDE_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.donkeyHideHoodHat);
        addDescription(DONKEY_HIDE_HOOD_HAT, "Eee-aww.");
        addStatistics(DONKEY_HIDE_HOOD_HAT, "Donkey Hide Hood Hat", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(DONKEY_HIDE_HOOD_HAT);

        DRIPLEAF_WIDEBRIM_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(DRIPLEAF_WIDEBRIM_HAT, 6626);
        addKeys(DRIPLEAF_WIDEBRIM_HAT, TreasureItemKeys.hat, TreasureItemKeys.dripleafWidebrimHat);
        addDescription(DRIPLEAF_WIDEBRIM_HAT, "Leafy and green.");
        addStatistics(DRIPLEAF_WIDEBRIM_HAT, "Dripleaf Widebrim Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(DRIPLEAF_WIDEBRIM_HAT);

        EFE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(EFE_HAT, 6627);
        addKeys(EFE_HAT, TreasureItemKeys.hat, TreasureItemKeys.efeHat);
        addDescription(EFE_HAT, "Mystic energies.");
        addStatistics(EFE_HAT, "Efe Hat", TreasureItemSource.MAGIC, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(EFE_HAT);

        ENDER_DRAGON_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ENDER_DRAGON_HIDE_HAT, 6628);
        addKeys(ENDER_DRAGON_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.enderDragonHideHat);
        addDescription(ENDER_DRAGON_HIDE_HAT, "Draconic power.");
        addStatistics(ENDER_DRAGON_HIDE_HAT, "Ender Dragon Hide Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(ENDER_DRAGON_HIDE_HAT);

        ENDERMAN_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ENDERMAN_HIDE_HAT, 6629);
        addKeys(ENDERMAN_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.endermanHideHat);
        addDescription(ENDERMAN_HIDE_HAT, "Teleports away.");
        addStatistics(ENDERMAN_HIDE_HAT, "Enderman Hide Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(ENDERMAN_HIDE_HAT);

        FLIGHT_HELMET_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(FLIGHT_HELMET_HAT, 6630);
        addKeys(FLIGHT_HELMET_HAT, TreasureItemKeys.hat, TreasureItemKeys.flightHelmetHat);
        addDescription(FLIGHT_HELMET_HAT, "Ready for takeoff.");
        addStatistics(FLIGHT_HELMET_HAT, "Flight Helmet Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(FLIGHT_HELMET_HAT);

        FLIGHT_HELMET2_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(FLIGHT_HELMET2_HAT, 6631);
        addKeys(FLIGHT_HELMET2_HAT, TreasureItemKeys.hat, TreasureItemKeys.flightHelmet2Hat);
        addDescription(FLIGHT_HELMET2_HAT, "High-flying gear.");
        addStatistics(FLIGHT_HELMET2_HAT, "Flight Helmet 2 Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(FLIGHT_HELMET2_HAT);

        FOX_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(FOX_HIDE_HAT, 6632);
        addKeys(FOX_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.foxHideHat);
        addDescription(FOX_HIDE_HAT, "Cunning and sly.");
        addStatistics(FOX_HIDE_HAT, "Fox Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(FOX_HIDE_HAT);

        GEORGIA_DEVIL_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GEORGIA_DEVIL_HAT, 6633);
        addKeys(GEORGIA_DEVIL_HAT, TreasureItemKeys.hat, TreasureItemKeys.georgiaDevilHat);
        addDescription(GEORGIA_DEVIL_HAT, "Infernal mischief.");
        addStatistics(GEORGIA_DEVIL_HAT, "Georgia Devil Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(GEORGIA_DEVIL_HAT);

        GHAST_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GHAST_MASK_HAT, 6634);
        addKeys(GHAST_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.ghastMaskHat);
        addDescription(GHAST_MASK_HAT, "Haunted visage.");
        addStatistics(GHAST_MASK_HAT, "Ghast Mask Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(GHAST_MASK_HAT);

        GLARE_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GLARE_HIDE_HAT, 6635);
        addKeys(GLARE_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.glareHideHat);
        addDescription(GLARE_HIDE_HAT, "Glaring gaze.");
        addStatistics(GLARE_HIDE_HAT, "Glare Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(GLARE_HIDE_HAT);

        GOAT_SKIN_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GOAT_SKIN_HAT, 6636);
        addKeys(GOAT_SKIN_HAT, TreasureItemKeys.hat, TreasureItemKeys.goatSkinHat);
        addDescription(GOAT_SKIN_HAT, "Mountain climber.");
        addStatistics(GOAT_SKIN_HAT, "Goat Skin Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(GOAT_SKIN_HAT);

        GRANDPAS_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GRANDPAS_HAT, 6637);
        addKeys(GRANDPAS_HAT, TreasureItemKeys.hat, TreasureItemKeys.grandpasHat);
        addDescription(GRANDPAS_HAT, "Timeless and wise.");
        addStatistics(GRANDPAS_HAT, "Grandpas Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(GRANDPAS_HAT);

        GUARDIAN_EYE_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(GUARDIAN_EYE_MASK_HAT, 6638);
        addKeys(GUARDIAN_EYE_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.guardianEyeMaskHat);
        addDescription(GUARDIAN_EYE_MASK_HAT, "Watchful protector.");
        addStatistics(GUARDIAN_EYE_MASK_HAT, "Guardian Eye Mask Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(GUARDIAN_EYE_MASK_HAT);

        HAT_NAMES_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(HAT_NAMES_HAT, 6639);
        addKeys(HAT_NAMES_HAT, TreasureItemKeys.hat, TreasureItemKeys.hatNamesHat);
        addDescription(HAT_NAMES_HAT, "A hat by any name.");
        addStatistics(HAT_NAMES_HAT, "Hat Names Hat", TreasureItemSource.LIVING, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(HAT_NAMES_HAT);

        HOGLIN_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(HOGLIN_HAT, 6640);
        addKeys(HOGLIN_HAT, TreasureItemKeys.hat, TreasureItemKeys.hoglinHat);
        addDescription(HOGLIN_HAT, "Wild and fierce.");
        addStatistics(HOGLIN_HAT, "Hoglin Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(HOGLIN_HAT);

        HORSE_SKULL_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(HORSE_SKULL_MASK_HAT, 6641);
        addKeys(HORSE_SKULL_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.horseSkullMaskHat);
        addDescription(HORSE_SKULL_MASK_HAT, "Haunting remains.");
        addStatistics(HORSE_SKULL_MASK_HAT, "Horse Skull Mask Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(HORSE_SKULL_MASK_HAT);

        IRON_GOLEM_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(IRON_GOLEM_MASK_HAT, 6642);
        addKeys(IRON_GOLEM_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.ironGolemMaskHat);
        addDescription(IRON_GOLEM_MASK_HAT, "Iron guardian.");
        addStatistics(IRON_GOLEM_MASK_HAT, "Iron Golem Mask Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(IRON_GOLEM_MASK_HAT);

        KIA_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(KIA_HAT, 6643);
        addKeys(KIA_HAT, TreasureItemKeys.hat, TreasureItemKeys.kiaHat);
        addDescription(KIA_HAT, "Unique design.");
        addStatistics(KIA_HAT, "Kia Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(KIA_HAT);

        KING_TRISKELE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(KING_TRISKELE_HAT, 6644);
        addKeys(KING_TRISKELE_HAT, TreasureItemKeys.hat, TreasureItemKeys.kingTriskeleHat);
        addDescription(KING_TRISKELE_HAT, "Royalty and power.");
        addStatistics(KING_TRISKELE_HAT, "King Triskele Hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(KING_TRISKELE_HAT);

        LEGENDARY_GOAT_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(LEGENDARY_GOAT_HOOD_HAT, 6645);
        addKeys(LEGENDARY_GOAT_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.legendaryGoatHoodHat);
        addDescription(LEGENDARY_GOAT_HOOD_HAT, "Legendary resilience.");
        addStatistics(LEGENDARY_GOAT_HOOD_HAT, "Legendary Goat Hood Hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(LEGENDARY_GOAT_HOOD_HAT);

        LOG_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(LOG_HOOD_HAT, 6646);
        addKeys(LOG_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.logHoodHat);
        addDescription(LOG_HOOD_HAT, "Woodland disguise.");
        addStatistics(LOG_HOOD_HAT, "Log Hood Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(LOG_HOOD_HAT);

        MAKENA_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(MAKENA_HAT, 6647);
        addKeys(MAKENA_HAT, TreasureItemKeys.hat, TreasureItemKeys.makenaHat);
        addDescription(MAKENA_HAT, "Mystical vibes.");
        addStatistics(MAKENA_HAT, "Makena Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(MAKENA_HAT);

        MINING_HELMET_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(MINING_HELMET_HAT, 6648);
        addKeys(MINING_HELMET_HAT, TreasureItemKeys.hat, TreasureItemKeys.miningHelmetHat);
        addDescription(MINING_HELMET_HAT, "Ready to mine.");
        addStatistics(MINING_HELMET_HAT, "Mining Helmet Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(MINING_HELMET_HAT);

        MOOBLOOM_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(MOOBLOOM_HIDE_HAT, 6649);
        addKeys(MOOBLOOM_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.moobloomHideHat);
        addDescription(MOOBLOOM_HIDE_HAT, "Floral moo.");
        addStatistics(MOOBLOOM_HIDE_HAT, "Moobloom Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(MOOBLOOM_HIDE_HAT);

        MOOSHROOM_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(MOOSHROOM_HIDE_HAT, 6650);
        addKeys(MOOSHROOM_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.mooshroomHideHat);
        addDescription(MOOSHROOM_HIDE_HAT, "Fungal growth.");
        addStatistics(MOOSHROOM_HIDE_HAT, "Mooshroom Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(MOOSHROOM_HIDE_HAT);

        MOSSY_CAP_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(MOSSY_CAP_HAT, 6651);
        addKeys(MOSSY_CAP_HAT, TreasureItemKeys.hat, TreasureItemKeys.mossyCapHat);
        addDescription(MOSSY_CAP_HAT, "Moss-covered.");
        addStatistics(MOSSY_CAP_HAT, "Mossy Cap Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(MOSSY_CAP_HAT);

        NOOR_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(NOOR_HAT, 6652);
        addKeys(NOOR_HAT, TreasureItemKeys.hat, TreasureItemKeys.noorHat);
        addDescription(NOOR_HAT, "Bright and hopeful.");
        addStatistics(NOOR_HAT, "Noor Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(NOOR_HAT);

        OCELOT_HIDE_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(OCELOT_HIDE_HOOD_HAT, 6653);
        addKeys(OCELOT_HIDE_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.ocelotHideHoodHat);
        addDescription(OCELOT_HIDE_HOOD_HAT, "Stealthy and quick.");
        addStatistics(OCELOT_HIDE_HOOD_HAT, "Ocelot Hide Hood Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(OCELOT_HIDE_HOOD_HAT);

        PHANTOM_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PHANTOM_HIDE_HAT, 6654);
        addKeys(PHANTOM_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.phantomHideHat);
        addDescription(PHANTOM_HIDE_HAT, "Ghostly presence.");
        addStatistics(PHANTOM_HIDE_HAT, "Phantom Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(PHANTOM_HIDE_HAT);

        PILLAGER_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PILLAGER_HAT, 6655);
        addKeys(PILLAGER_HAT, TreasureItemKeys.hat, TreasureItemKeys.pillagerHat);
        addDescription(PILLAGER_HAT, "Menacing looks.");
        addStatistics(PILLAGER_HAT, "Pillager Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(PILLAGER_HAT);

        PLAGUE_CLERIC_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PLAGUE_CLERIC_HAT, 6656);
        addKeys(PLAGUE_CLERIC_HAT, TreasureItemKeys.hat, TreasureItemKeys.plagueClericHat);
        addDescription(PLAGUE_CLERIC_HAT, "Healer in dark times.");
        addStatistics(PLAGUE_CLERIC_HAT, "Plague Cleric Hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(PLAGUE_CLERIC_HAT);

        PLAGUE_DOCTOR_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PLAGUE_DOCTOR_HAT, 6657);
        addKeys(PLAGUE_DOCTOR_HAT, TreasureItemKeys.hat, TreasureItemKeys.plagueDoctorHat);
        addDescription(PLAGUE_DOCTOR_HAT, "Mysterious healer.");
        addStatistics(PLAGUE_DOCTOR_HAT, "Plague Doctor Hat", TreasureItemSource.LIVING, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(PLAGUE_DOCTOR_HAT);

        PLATYPUS_CAP_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PLATYPUS_CAP_HAT, 6658);
        addKeys(PLATYPUS_CAP_HAT, TreasureItemKeys.hat, TreasureItemKeys.platypusCapHat);
        addDescription(PLATYPUS_CAP_HAT, "Odd yet charming.");
        addStatistics(PLATYPUS_CAP_HAT, "Platypus Cap Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(PLATYPUS_CAP_HAT);

        POLAR_BEAR_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(POLAR_BEAR_HOOD_HAT, 6659);
        addKeys(POLAR_BEAR_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.polarBearHoodHat);
        addDescription(POLAR_BEAR_HOOD_HAT, "Arctic might.");
        addStatistics(POLAR_BEAR_HOOD_HAT, "Polar Bear Hood Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(POLAR_BEAR_HOOD_HAT);

        PUFFERFISH_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(PUFFERFISH_HIDE_HAT, 6660);
        addKeys(PUFFERFISH_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.pufferfishHideHat);
        addDescription(PUFFERFISH_HIDE_HAT, "Puffed up.");
        addStatistics(PUFFERFISH_HIDE_HAT, "Pufferfish Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(PUFFERFISH_HIDE_HAT);

        RAVAGER_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(RAVAGER_HAT, 6661);
        addKeys(RAVAGER_HAT, TreasureItemKeys.hat, TreasureItemKeys.ravagerHat);
        addDescription(RAVAGER_HAT, "Beastly appearance.");
        addStatistics(RAVAGER_HAT, "Ravager Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(RAVAGER_HAT);

        SALMON_SCALED_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SALMON_SCALED_HAT, 6662);
        addKeys(SALMON_SCALED_HAT, TreasureItemKeys.hat, TreasureItemKeys.salmonScaledHat);
        addDescription(SALMON_SCALED_HAT, "Fishy style.");
        addStatistics(SALMON_SCALED_HAT, "Salmon Scaled Hat", TreasureItemSource.LIVING, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(SALMON_SCALED_HAT);

        SCULK_HELSING_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SCULK_HELSING_HAT, 6663);
        addKeys(SCULK_HELSING_HAT, TreasureItemKeys.hat, TreasureItemKeys.sculkHelsingHat);
        addDescription(SCULK_HELSING_HAT, "Dark and eerie.");
        addStatistics(SCULK_HELSING_HAT, "Sculk Helsing Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(SCULK_HELSING_HAT);

        SNIFFER_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SNIFFER_HIDE_HAT, 6664);
        addKeys(SNIFFER_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.snifferHideHat);
        addDescription(SNIFFER_HIDE_HAT, "Keen senses.");
        addStatistics(SNIFFER_HIDE_HAT, "Sniffer Hide Hat", TreasureItemSource.LIVING, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(SNIFFER_HIDE_HAT);

        SPECTACLES_F_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SPECTACLES_F_HAT, 6665);
        addKeys(SPECTACLES_F_HAT, TreasureItemKeys.hat, TreasureItemKeys.spectaclesFHat);
        addDescription(SPECTACLES_F_HAT, "Feminine flair.");
        addStatistics(SPECTACLES_F_HAT, "Spectacles F Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(SPECTACLES_F_HAT);

        SPECTACLES_M_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SPECTACLES_M_HAT, 6666);
        addKeys(SPECTACLES_M_HAT, TreasureItemKeys.hat, TreasureItemKeys.spectaclesMHat);
        addDescription(SPECTACLES_M_HAT, "Masculine touch.");
        addStatistics(SPECTACLES_M_HAT, "Spectacles M Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(SPECTACLES_M_HAT);

        STEVE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(STEVE_HAT, 6667);
        addKeys(STEVE_HAT, TreasureItemKeys.hat, TreasureItemKeys.steveHat);
        addDescription(STEVE_HAT, "Classic look.");
        addStatistics(STEVE_HAT, "Steve Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.COMMON);
        TREASURE_ITEMS.add(STEVE_HAT);

        STRAW_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(STRAW_HAT, 6668);
        addKeys(STRAW_HAT, TreasureItemKeys.hat, TreasureItemKeys.strawHat);
        addDescription(STRAW_HAT, "Farmhand style.");
        addStatistics(STRAW_HAT, "Straw Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.UNCOMMON);
        TREASURE_ITEMS.add(STRAW_HAT);

        SUNNY_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(SUNNY_HAT, 6669);
        addKeys(SUNNY_HAT, TreasureItemKeys.hat, TreasureItemKeys.sunnyHat);
        addDescription(SUNNY_HAT, "Bright and cheerful.");
        addStatistics(SUNNY_HAT, "Sunny Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(SUNNY_HAT);

        TUFF_GOLEM_MASK_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(TUFF_GOLEM_MASK_HAT, 6670);
        addKeys(TUFF_GOLEM_MASK_HAT, TreasureItemKeys.hat, TreasureItemKeys.tuffGolemMaskHat);
        addDescription(TUFF_GOLEM_MASK_HAT, "Stone-faced.");
        addStatistics(TUFF_GOLEM_MASK_HAT, "Tuff Golem Mask Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(TUFF_GOLEM_MASK_HAT);

        VAN_HELSING_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(VAN_HELSING_HAT, 6671);
        addKeys(VAN_HELSING_HAT, TreasureItemKeys.hat, TreasureItemKeys.vanHelsingHat);
        addDescription(VAN_HELSING_HAT, "Hunter's attire.");
        addStatistics(VAN_HELSING_HAT, "Van Helsing Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(VAN_HELSING_HAT);

        WARDEN_HIDE_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(WARDEN_HIDE_HAT, 6672);
        addKeys(WARDEN_HIDE_HAT, TreasureItemKeys.hat, TreasureItemKeys.wardenHideHat);
        addDescription(WARDEN_HIDE_HAT, "Silent guardian.");
        addStatistics(WARDEN_HIDE_HAT, "Warden Hide Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(WARDEN_HIDE_HAT);

        WITHER_SKULL_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(WITHER_SKULL_HAT, 6673);
        addKeys(WITHER_SKULL_HAT, TreasureItemKeys.hat, TreasureItemKeys.witherSkullHat);
        addDescription(WITHER_SKULL_HAT, "Withering aura.");
        addStatistics(WITHER_SKULL_HAT, "Wither Skull Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.EPIC);
        TREASURE_ITEMS.add(WITHER_SKULL_HAT);

        ZOMBIE_HORSE_HOOD_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ZOMBIE_HORSE_HOOD_HAT, 6674);
        addKeys(ZOMBIE_HORSE_HOOD_HAT, TreasureItemKeys.hat, TreasureItemKeys.zombieHorseHoodHat);
        addDescription(ZOMBIE_HORSE_HOOD_HAT, "Undead steed.");
            addStatistics(ZOMBIE_HORSE_HOOD_HAT, "Zombie Horse Hood Hat", TreasureItemSource.UNDEAD, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(ZOMBIE_HORSE_HOOD_HAT);

        ZURI_HAT = new ItemStack(Material.REPEATING_COMMAND_BLOCK, 1);
        setModelData(ZURI_HAT, 6675);
        addKeys(ZURI_HAT, TreasureItemKeys.hat, TreasureItemKeys.zuriHat);
        addDescription(ZURI_HAT, "Mysterious aura.");
        addStatistics(ZURI_HAT, "Zuri Hat", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TREASURE_ITEMS.add(ZURI_HAT);
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
