package me.coopersully.rpgloot.rpgloot.config;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.RPGLoot;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class Trades {

    private static List<MerchantRecipe> merchantTrades = new ArrayList<>();

    public static void load() {

        var trades = RPGLoot.getPlugin().getTradesConfig().getConfigurationSection("trades");

        if (trades == null) {
            Bukkit.getLogger().log(Level.WARNING, "No trades were provided in overworld.yml for RPG Loot.");
            return;
        }

        var tradesKeys = trades.getKeys(false);
        merchantTrades.clear();
        for (var tradeKey : tradesKeys) {

            var trade = trades.getConfigurationSection(tradeKey);
            if (trade == null) {
                System.out.println("Invalid");
                continue;
            }

            var sell = trade.getConfigurationSection("sell");
            ItemStack sell_item;
            if (sell != null) {
                var sell_id = sell.get("id");
                var sell_amount = sell.getInt("amount");
                var sell_data = sell.getConfigurationSection("data");
                sell_item = getItemStack(sell_id, sell_amount, sell_data);
            } else {
                System.out.println(tradeKey + " was deemed invalid because it lacks a sell item; skipping this trade.");
                continue;
            }

            MerchantRecipe recipe = new MerchantRecipe(sell_item, 1);

            var buy01 = trade.getConfigurationSection("buy-1");
            ItemStack buy01_item;
            if (buy01 != null) {
                var buy01_id = buy01.get("id");
                var buy01_amount = buy01.getInt("amount");
                var buy01_data = buy01.getConfigurationSection("data");
                buy01_item = getItemStack(buy01_id, buy01_amount, buy01_data);
                recipe.addIngredient(buy01_item);
            } else {
                System.out.println(tradeKey + " was deemed invalid because it lacks a buy item; skipping this trade.");
                continue;
            }

            var buy02 = trade.getConfigurationSection("buy-2");
            ItemStack buy02_item;
            if (buy02 != null) {
                var buy02_id = buy02.get("id");
                var buy02_amount = buy02.getInt("amount");
                var buy02_data = buy02.getConfigurationSection("data");
                if (buy02_id != null) {
                    buy02_item = getItemStack(buy02_id, buy02_amount, buy02_data);
                    recipe.addIngredient(buy02_item);
                }
            }

            merchantTrades.add(recipe);
        }

    }

    private static @NotNull ItemStack getItemStack(Object id, int amount, ConfigurationSection data) {

        Material material = null;
        if (id instanceof String) {
            material = Material.getMaterial(id.toString());
        } else if (id instanceof List) {
            Random random = new Random();
            String mat = (String) ((List<?>) id).get(random.nextInt(((List<?>) id).size()));
            material = Material.getMaterial(mat);
        }

        if (material == null) {
            throw new RuntimeException("Could not parse \"" + id + "\" as an item from trades yaml file.");
        }

        ItemStack itemStack = new ItemStack(material, amount);
        if (data != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            var name = data.getString("name");
            if (name != null) {
                itemMeta.displayName(CoreUtils.translateXMLCodes(name));
            }

            var lore = data.getStringList("lore");
            if (!lore.isEmpty()) {
                List<Component> loreBuilder = new ArrayList<>();
                for (var line : lore) {
                    loreBuilder.add(CoreUtils.translateXMLCodes(line));
                }
                itemMeta.lore(Collections.unmodifiableList(loreBuilder));
            }

            var persistents = data.getStringList("persistents");
            if (!persistents.isEmpty()) {
                for (var line : persistents) {
                    NamespacedKey key = new NamespacedKey(RPGLoot.getPlugin(), line);
                    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "1");
                }
            }

            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public static void giveTrades(@NotNull WanderingTrader villager) {
        load();
        villager.setRecipes(merchantTrades);
    }
}
