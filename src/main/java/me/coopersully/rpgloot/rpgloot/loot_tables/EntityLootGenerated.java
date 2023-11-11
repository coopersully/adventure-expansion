package me.coopersully.rpgloot.rpgloot.loot_tables;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EntityLootGenerated implements Listener {

    @EventHandler
    public void onLootGenerate(@NotNull EntityDeathEvent event) {
        @NotNull EntityType entityType = event.getEntity().getType();
        List<ItemStack> loot = new ArrayList<>();

        switch (entityType) {
            case WANDERING_TRADER -> LootRollers.rollTraderHat(loot);
            case WITCH -> LootRollers.rollSpawnEggs(loot);
            case EVOKER -> LootRollers.rollEvokerBow(loot);
            case WARDEN -> LootRollers.rollSculkGear(loot);
            case VEX -> LootRollers.rollNightmareFuel(loot);
            case ENDER_DRAGON -> LootRollers.rollEndersMagic(loot);
        }

        // Drop items at entity's death location
        loot.forEach(item -> {
            Location location = event.getEntity().getLocation();
            event.getEntity().getWorld().dropItemNaturally(location, item);
        });
    }

}
