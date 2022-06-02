package me.coopersully.rpgloot.rpgloot.config;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

public class BetterMerchantRecipe extends MerchantRecipe {

    public BetterMerchantRecipe(@NotNull ItemStack buy, @NotNull ItemStack sell, int maxUses) {
        super(buy, maxUses);
        this.addIngredient(sell);
    }

    public BetterMerchantRecipe(@NotNull ItemStack buy, @NotNull ItemStack sell, @NotNull ItemStack sellSecondary, int maxUses) {
        super(buy, maxUses);
        this.addIngredient(sell);
        this.addIngredient(sellSecondary);
    }

}
