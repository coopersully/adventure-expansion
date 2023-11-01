package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.AdventureExpansion;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItem;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemRarity;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemSource;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

public class SilkTouchSpawner implements Listener {

    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() != Material.SPAWNER) return;

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!itemStack.containsEnchantment(org.bukkit.enchantments.Enchantment.SILK_TOUCH)) return;

        if (!player.hasPermission(AdventureExpansion.permissionPrefix + "silktouch")) return;

        // Cancel default drops
        event.setDropItems(false);

        // Spawn spawner item
        CreatureSpawner spawner = (CreatureSpawner) block.getState();
        ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) spawnerItem.getItemMeta();
        CreatureSpawner cloneSpawner = (CreatureSpawner) meta.getBlockState();
        cloneSpawner.setSpawnedType(spawner.getSpawnedType());
        meta.setBlockState(cloneSpawner);
        spawnerItem.setItemMeta(meta);

        // Make into a treasure item
        String mob = formatMobName(spawner.getSpawnedType().toString());
        TreasureItem.addKeys(spawnerItem, TreasureItemKeys.mobSpawner);
        TreasureItem.addDescription(spawnerItem, "It rattles with the excitement of trapped souls.");
        TreasureItem.addStatistics(spawnerItem, mob + " Spawner", TreasureItemSource.UNKNOWN, TreasureItemRarity.RARE);
        TreasureItem.addAbilities(spawnerItem, "PASSIVE", "Spawns " + mob + " entities nearby.");

        block.getWorld().dropItemNaturally(block.getLocation(), spawnerItem);

        // Prevent exp drops for duplication
        event.setExpToDrop(0);
    }

    public String formatMobName(@NotNull String mob) {
        String[] words = mob.toLowerCase().split("_");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            formattedName.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return formattedName.toString().trim();
    }

}
