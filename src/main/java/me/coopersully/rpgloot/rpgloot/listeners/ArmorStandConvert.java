package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandConvert implements Listener {

    @EventHandler
    public void onArmorStandManipulate(@NotNull PlayerArmorStandManipulateEvent event) {
        ItemStack item = event.getPlayerItem();
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) return;
        if (!itemMeta.getPersistentDataContainer().has(TreasureItemKeys.traderHat)) return;

        ArmorStand armorStand = event.getRightClicked();
        armorStand.getWorld().createExplosion(armorStand.getLocation(), 0);
        armorStand.remove();

        WanderingTrader trader = (WanderingTrader) armorStand.getWorld().spawnEntity(armorStand.getLocation(), EntityType.WANDERING_TRADER);

        // Create a list to hold the MerchantRecipes
        List<MerchantRecipe> recipes = new ArrayList<>();

        // Define the price for each item
        ItemStack travelerPrice = new ItemStack(Material.IRON_BLOCK, 6);
        ItemStack raiderPrice = new ItemStack(Material.DIAMOND_BLOCK, 6);
        ItemStack awakenedPrice = new ItemStack(Material.NETHERITE_BLOCK, 6);

        // Raider Tier
        MerchantRecipe raiderHelmetRecipe = new MerchantRecipe(TreasureItem.RAIDER_HELMET, 1);
        raiderHelmetRecipe.addIngredient(TreasureItem.TRAVELERS_HELMET);
        raiderHelmetRecipe.addIngredient(travelerPrice);
        recipes.add(raiderHelmetRecipe);

        MerchantRecipe raiderChestplateRecipe = new MerchantRecipe(TreasureItem.RAIDER_CHESTPLATE, 1);
        raiderChestplateRecipe.addIngredient(TreasureItem.TRAVELERS_CHESTPLATE);
        raiderChestplateRecipe.addIngredient(travelerPrice);
        recipes.add(raiderChestplateRecipe);

        MerchantRecipe raiderLeggingsRecipe = new MerchantRecipe(TreasureItem.RAIDER_LEGGINGS, 1);
        raiderLeggingsRecipe.addIngredient(TreasureItem.TRAVELERS_LEGGINGS);
        raiderLeggingsRecipe.addIngredient(travelerPrice);
        recipes.add(raiderLeggingsRecipe);

        MerchantRecipe raiderBootsRecipe = new MerchantRecipe(TreasureItem.RAIDER_BOOTS, 1);
        raiderBootsRecipe.addIngredient(TreasureItem.TRAVELERS_BOOTS);
        raiderBootsRecipe.addIngredient(travelerPrice);
        recipes.add(raiderBootsRecipe);

        MerchantRecipe raiderSwordRecipe = new MerchantRecipe(TreasureItem.RAIDER_SWORD, 1);
        raiderSwordRecipe.addIngredient(TreasureItem.TRAVELERS_SWORD);
        raiderSwordRecipe.addIngredient(travelerPrice);
        recipes.add(raiderSwordRecipe);

        MerchantRecipe raiderPickaxeRecipe = new MerchantRecipe(TreasureItem.RAIDER_PICKAXE, 1);
        raiderPickaxeRecipe.addIngredient(TreasureItem.TRAVELERS_PICKAXE);
        raiderPickaxeRecipe.addIngredient(travelerPrice);
        recipes.add(raiderPickaxeRecipe);

        // Awakened Tier
        MerchantRecipe awakenedHelmetRecipe = new MerchantRecipe(TreasureItem.AWAKENED_HELMET, 1);
        awakenedHelmetRecipe.addIngredient(TreasureItem.RAIDER_HELMET);
        awakenedHelmetRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedHelmetRecipe);

        MerchantRecipe awakenedChestplateRecipe = new MerchantRecipe(TreasureItem.AWAKENED_CHESTPLATE, 1);
        awakenedChestplateRecipe.addIngredient(TreasureItem.RAIDER_CHESTPLATE);
        awakenedChestplateRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedChestplateRecipe);

        MerchantRecipe awakenedLeggingsRecipe = new MerchantRecipe(TreasureItem.AWAKENED_LEGGINGS, 1);
        awakenedLeggingsRecipe.addIngredient(TreasureItem.RAIDER_LEGGINGS);
        awakenedLeggingsRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedLeggingsRecipe);

        MerchantRecipe awakenedBootsRecipe = new MerchantRecipe(TreasureItem.AWAKENED_BOOTS, 1);
        awakenedBootsRecipe.addIngredient(TreasureItem.RAIDER_BOOTS);
        awakenedBootsRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedBootsRecipe);

        MerchantRecipe awakenedSwordRecipe = new MerchantRecipe(TreasureItem.AWAKENED_SWORD, 1);
        awakenedSwordRecipe.addIngredient(TreasureItem.RAIDER_SWORD);
        awakenedSwordRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedSwordRecipe);

        MerchantRecipe awakenedPickaxeRecipe = new MerchantRecipe(TreasureItem.AWAKENED_PICKAXE, 1);
        awakenedPickaxeRecipe.addIngredient(TreasureItem.RAIDER_PICKAXE);
        awakenedPickaxeRecipe.addIngredient(raiderPrice);
        recipes.add(awakenedPickaxeRecipe);

        // Eternal Tier
        MerchantRecipe eternalHelmetRecipe = new MerchantRecipe(TreasureItem.ETERNAL_HELMET, 1);
        eternalHelmetRecipe.addIngredient(TreasureItem.AWAKENED_HELMET);
        eternalHelmetRecipe.addIngredient(awakenedPrice);
        recipes.add(eternalHelmetRecipe);

        MerchantRecipe eternalChestplateRecipe = new MerchantRecipe(TreasureItem.ETERNAL_CHESTPLATE, 1);
        eternalChestplateRecipe.addIngredient(TreasureItem.AWAKENED_CHESTPLATE);
        eternalChestplateRecipe.addIngredient(awakenedPrice);
        recipes.add(eternalChestplateRecipe);

        MerchantRecipe eternalLeggingsRecipe = new MerchantRecipe(TreasureItem.ETERNAL_LEGGINGS, 1);
        eternalLeggingsRecipe.addIngredient(TreasureItem.AWAKENED_LEGGINGS);
        eternalLeggingsRecipe.addIngredient(awakenedPrice);
        recipes.add(eternalLeggingsRecipe);

        MerchantRecipe eternalBootsRecipe = new MerchantRecipe(TreasureItem.ETERNAL_BOOTS, 1);
        eternalBootsRecipe.addIngredient(TreasureItem.AWAKENED_BOOTS);
        eternalBootsRecipe.addIngredient(awakenedPrice);
        recipes.add(eternalBootsRecipe);

        // Set recipes to the trader
        trader.setRecipes(recipes);
    }
}
